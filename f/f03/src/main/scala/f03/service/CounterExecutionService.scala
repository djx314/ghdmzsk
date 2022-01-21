package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._

trait CounterExecutionService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def executePlan(): CTask[Int]
}

class CounterExecutionServiceImpl(db: SlickDB) extends CounterExecutionService {
  override def executePlan(): CTask[Int] = {
    val runner = CountPlan.map(_.id).size.result
    db.run(runner)
  }
}
