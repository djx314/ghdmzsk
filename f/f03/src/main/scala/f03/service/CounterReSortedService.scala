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

  def sortedPlan(): CTask[Int]
}

class CounterReSortedServiceImpl(db: SlickDB, dataCollection: DataCollection) extends CounterReSortedService {

  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  case class ConvertCountSet(set: String, firstStart: Int, secoundStart: Int)

  protected def genChildrenSet(countSet: CountSetRow): (ConvertCountSet, List[ConvertCountSet]) = {
    val i1     = ConvertCountSet(set = countSet.countSet, firstStart = countSet.firstStart, secoundStart = countSet.secondStart)
    val setRow = dataCollection.fromString(countSet.countSet)
    val i2     =  (setRow.filterNot(_.i1 == 0), 1, i1.secoundStart)
    val i3     =  (setRow.filterNot(_.i2 == 0), i1.firstStart, 1)
    val i4 = (setRow.filterNot(s => s.i1 == 0 || s.i2 == 0), 1, 1)
    val notNull = List(i2, i3, i4).map(s=>s.copy(_1 = dataCollection.genString(s._1)))
    val setCol = notNull.map((ConvertCountSet.apply _).tupled)
    val distinct = setCol.filter(_.set != i1.set).distinctBy(_.set)
    (i1, distinct)
  }

  protected def insertAction(parent: ConvertCountSet, children : List[ConvertCountSet]): CTask[Int] = {
    val findAction = ResultSetSort.filter(s => s.)
  }

  override def sortedPlan(): CTask[Int] = {
    val getIdAction                            = CountPlan.map(_.counterResultId).filter(_.isDefined).distinct.map(_.get)
    val plans                                  = ZStream.fromIterableM(db.run(getIdAction.result))
    def getSet(setId: Int): CTask[CountSetRow] = db.run(CountSet.filter(_.id === setId).result.head)
    val countSets = for {
      plan <- plans
      set  <- ZStream.fromEffect(getSet(plan))
    } yield set

  }

}
