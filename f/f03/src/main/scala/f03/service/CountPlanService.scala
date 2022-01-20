package f03.service

import f03.mainapp.{db, MainApp}
import zio._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._

trait CountPlanService {
  def deleteAll(): Task[Int]
  def resetAll(): Task[Option[Int]]
  def count(): Task[Int]
}

object CountPlanService {
  type Live = Has[CountPlanService]
  def deleteAll(): RIO[Live, Int]        = ZIO.serviceWith[CountPlanService](_.deleteAll())
  def resetAll(): RIO[Live, Option[Int]] = ZIO.serviceWith[CountPlanService](_.resetAll())
  def count(): RIO[Live, Int]            = ZIO.serviceWith[CountPlanService](_.count())
  val service = for {
    model <- ZLayer.service[DataCollection] ++ ZLayer.requires[MainApp.AppEnv]
    r     <- ZLayer.succeed(new CountPlanServiceImpl(model): CountPlanService)
  } yield r
}

class CountPlanServiceImpl(env: MainApp.AppEnv with Has[DataCollection]) extends CountPlanService {
  override def deleteAll(): Task[Int] = db.run(CountPlan.delete).provide(env)
  override def resetAll(): Task[Option[Int]] = {
    val runner = for {
      col    <- ZIO.service[DataCollection]
      action <- ZIO.effectTotal(CountPlan ++= col.allCountPlan)
      result <- db.run(action.transactionally)
    } yield result
    runner.provide(env)
  }
  override def count(): Task[Int] = {
    val runner = CountPlan.map(_.id).size.result
    db.run(runner).provide(env)
  }
}
