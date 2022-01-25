package f03.service

import f03.mainapp.MainApp
import f03.slick.model.Tables._
import zio._
import zio.stream._

trait PlanExecute {
  type CTask[T]   = RIO[MainApp.AppEnv, T]
  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  def countNumberCollection(countPlan: CountPlanRow): CStream[CountResult]
  def countNumberToString(countPlan: CountPlanRow): CTask[CountSetRow]
}

class PlanExecuteImpl(dataCollection: DataCollection) extends PlanExecute {

  override def countNumberCollection(countPlan: CountPlanRow): CStream[CountResult] = {
    val param1 = ZStream.fromIterable(countPlan.firstStart to 20)
    val param2 = ZStream.fromIterable(countPlan.secondStart to 20)
    for {
      i1 <- param1
      i2 <- param2
      action = blocking.effectBlocking(dataCollection.countNumber(countPlan, i1, i2))
      r <- ZStream.fromEffect(action)
    } yield r
  }

  override def countNumberToString(countPlan: CountPlanRow): CTask[CountSetRow] = {
    val action  = countNumberCollection(countPlan)
    val collect = action.runCollect
    for {
      builder <- collect
      col     <- ZIO.effect(builder.to(List))
      str     <- ZIO.effect(dataCollection.genString(col))
    } yield CountSetRow(
      id = -1,
      countSet = str,
      isLimited = col.forall(_.result.isDefined),
      firstStart = countPlan.firstStart,
      secondStart = countPlan.secondStart
    )
  }

}
