package f03.service

import f03.mainapp.{db, MainApp}
import zio._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._

trait CountPlanService {
  def deleteAll(): Task[Int]
  def resetAll(): Task[Int]
}

object CountPlanService {
  type Live = Has[CountPlanService]
  def deleteAll(): RIO[Live, Int]            = ZIO.serviceWith[CountPlanService](_.deleteAll())
  val service: URLayer[MainApp.AppEnv, Live] = ZLayer.fromFunction(new CountPlanServiceImpl(_))
}

class CountPlanServiceImpl(env: MainApp.AppEnv) extends CountPlanService {
  override def deleteAll(): Task[Int] = db.run(CountPlan.delete).provide(env)
  override def resetAll(): Task[Int]  = db.run(CountPlan.delete).provide(env)
}
