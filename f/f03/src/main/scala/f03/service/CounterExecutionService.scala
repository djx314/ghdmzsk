package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._
import zio.stream.ZStream

trait CounterExecutionService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def executePlan(count: Int): CTask[List[Int]]
}

class CounterExecutionServiceImpl(db: SlickDB, planExecute: PlanExecute) extends CounterExecutionService {

  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  protected def firstCounterPlan(count: Int): CStream[CountPlanRow] = {
    val action = CountPlan.filter(_.counterResultId.isEmpty).take(count).to[List].result
    val collM  = db.run(action)
    ZStream.fromIterableM(collM)
  }

  protected def executeCountPlan(plan: CountPlanRow, countSetRow: CountSetRow): CTask[Int] = {
    val dbio =
      CountSet.filter(s => s.countSet === countSetRow.countSet && s.isLimited === countSetRow.isLimited).take(1).to[List].result.headOption
    val action = for (exists <- db.run(dbio)) yield exists match {
      case Some(s) => DBIO.successful(s)
      case _ =>
        CountSet returning CountSet.map(_.id) into ((model, id) => model.copy(id = id)) += countSetRow
    }
    def updatePlan(set: CountSetRow) = CountPlan.filter(_.id === plan.id).map(_.counterResultId).update(Option(set.id))

    for {
      a1 <- action
      a2 <- db.run(implicit ec =>
        for {
          row   <- a1
          count <- updatePlan(row)
        } yield count
      )
    } yield a2
  }

  override def executePlan(count: Int): CTask[List[Int]] = {
    val setNeedToInsert = firstCounterPlan(count).mapMPar(5)(row => for (set <- planExecute.countNumberToString(row)) yield (row, set))
    def filterInsert(list: List[(CountPlanRow, CountSetRow)]) =
      ZStream.fromIterable(list).mapMPar(5) { case (plan, set) => executeCountPlan(plan, set) }
    for {
      chunk     <- setNeedToInsert.runCollect
      inertList <- filterInsert(chunk.to(List)).runCollect
    } yield inertList.to(List)
  }
}
