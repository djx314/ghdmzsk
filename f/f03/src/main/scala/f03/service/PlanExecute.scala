package f03.service

import e01.NumberGen
import f03.mainapp.MainApp
import f03.slick.model.Tables._
import zio._
import zio.stream._

trait PlanExecute {
  type CTask[T]   = RIO[MainApp.AppEnv, T]
  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  def countNumberCollection(countPlan: CountPlanRow): CStream[(Int, Int, Option[Int])]
  def countNumberToString(countPlan: CountPlanRow): CTask[CountSetRow]
}

class PlanExecuteImpl(dataCollection: DataCollection) extends PlanExecute {

  override def countNumberCollection(countPlan: CountPlanRow): CStream[(Int, Int, Option[Int])] = {
    val param1 = ZStream.fromIterable(0 to 15)
    val param2 = ZStream.fromIterable(0 to 15)
    for {
      i1 <- param1
      i2 <- param2
      action = blocking.effectBlocking(
        NumberGen.executeCount(countPlan.planInfo(0), countPlan.planInfo(1), i1, countPlan.planInfo(2), countPlan.planInfo(3), i2)
      )
      r <- ZStream.fromEffect(action)
    } yield (i1, i2, r)
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
      isLimited = col.forall(_._3.isDefined)
    )
  }

}
