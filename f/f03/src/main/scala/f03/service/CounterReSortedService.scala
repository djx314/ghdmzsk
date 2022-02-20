package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._
import zio.stream.ZStream

import scala.concurrent.ExecutionContext

trait CounterReSortedService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def sortedPlan(): CTask[Unit]
}

class CounterReSortedServiceImpl(db: SlickDB, dataCollection: DataCollection) extends CounterReSortedService {

  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  case class ConvertCountSet(set: String, firstStart: Int, secoundStart: Int, isLimited: Boolean)

  protected def genChildrenSet(countSet: CountSetRow): (ConvertCountSet, List[ConvertCountSet]) = {
    val i1 = ConvertCountSet(
      set = countSet.countSet,
      firstStart = countSet.firstStart,
      secoundStart = countSet.secondStart,
      isLimited = countSet.isLimited
    )
    val setRow = dataCollection.fromString(countSet.countSet)

    val i2Data = setRow.filterNot(_.i1 == 0)
    val i2     = (i2Data, 1, i1.secoundStart, i2Data.forall(_.result.isDefined))

    val i3Data = setRow.filterNot(_.i2 == 0)
    val i3     = (i3Data, i1.firstStart, 1, i3Data.forall(_.result.isDefined))

    val i4Data   = setRow.filterNot(s => s.i1 == 0 || s.i2 == 0)
    val i4       = (i4Data, 1, 1, i4Data.forall(_.result.isDefined))
    val notNull  = List(i2, i3, i4).map(s => s.copy(_1 = dataCollection.genString(s._1)))
    val setCol   = notNull.map((ConvertCountSet.apply _).tupled)
    val distinct = setCol.filter(_.set != i1.set).distinctBy(_.set)
    (i1, distinct)
  }

  protected def insertAction(parent: ConvertCountSet, children: List[ConvertCountSet]): CTask[Option[Int]] = {
    def findAction(set: ConvertCountSet) = ResultSetSort.filter(s =>
      s.countSet === set.set && s.firstStart === set.firstStart && s.secondStart === set.secoundStart && s.isLimited === set.isLimited
    )
    val preInsert = ResultSetSort returning ResultSetSort.map(_.id) into ((model, id) => model.copy(id = id))
    def extraParent(parentOpt: Option[ResultSetSortRow])(implicit ec: ExecutionContext): DBIO[Int] = {
      parentOpt match {
        case Some(value) => DBIO.successful(if (value.parent > 0) value.parent else value.id)
        case _ =>
          val row = ResultSetSortRow(
            id = -1,
            isLimited = parent.isLimited,
            countSet = parent.set,
            firstStart = parent.firstStart,
            secondStart = parent.secoundStart,
            parent = -1
          )
          val action = preInsert += row
          for (r <- action) yield r.id
      }
    }
    def insertList(parentId: Int)(implicit ec: ExecutionContext) = {
      val colDBIO =
        for (c <- children) yield for (childOpt <- findAction(c).result.headOption) yield childOpt match {
          case Some(value) => value.copy(parent = parentId)
          case _ =>
            ResultSetSortRow(
              id = -1,
              isLimited = c.isLimited,
              countSet = c.set,
              firstStart = c.firstStart,
              secondStart = c.secoundStart,
              parent = parentId
            )
        }

      for {
        col   <- DBIO.sequence(colDBIO)
        count <- ResultSetSort ++= col
      } yield count
    }

    def action(implicit ec: ExecutionContext) = for {
      parentOpt <- findAction(parent).result.headOption
      parentId  <- extraParent(parentOpt)
      count     <- insertList(parentId)
    } yield count

    db.run(implicit ec => action.transactionally)
  }

  override def sortedPlan(): CTask[Unit] = {
    val getIdAction                            = CountPlan.map(_.counterResultId).filter(_.isDefined).distinct.map(_.get)
    val plans                                  = ZStream.fromIterableM(db.run(getIdAction.result))
    def getSet(setId: Int): CTask[CountSetRow] = db.run(CountSet.filter(_.id === setId).result.head)
    val countSets = for {
      plan <- plans
      set  <- ZStream.fromEffect(getSet(plan))
    } yield set

    val sorted = for {
      row   <- countSets
      t     <- ZStream.fromEffect(ZIO.effect(genChildrenSet(row)))
      count <- ZStream.fromEffect(insertAction(t._1, t._2))
    } yield count: Option[Int]

    sorted.runDrain
  }

}
