package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._

trait CountPlanService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def deleteAll(): CTask[Int]
  def resetAll(): CTask[Option[Int]]
  def count(): CTask[Int]
}

class CountPlanServiceImpl(db: SlickDB, dCol: DataCollection) extends CountPlanService {
  override def deleteAll(): CTask[Int] = for {
    result <- db.run(CountPlan.delete)
    _      <- log.info(s"清空了${result}条数据")
  } yield result

  override def resetAll(): CTask[Option[Int]] = for {
    action <- ZIO.effectTotal(CountPlan ++= dCol.allCountPlan)
    result <- db.run(action.transactionally)
    _      <- log.info(result.map(s => s"重置了${s}条数据").getOrElse("没有重置数据"))
  } yield result

  override def count(): CTask[Int] = {
    val runner = CountPlan.map(_.id).size.result
    db.run(runner)
  }
}
