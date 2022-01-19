package f03.service

import f03.mainapp.{db, AppResource}
import zio._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._

trait CountPlanService {
  def deleteAll(): Task[Int]
}

object CountPlanService {
  def deleteAll(): ZIO[Has[CountPlanService], Throwable, Int] = ZIO.serviceWith[CountPlanService](_.deleteAll())
}

class CountPlanServiceImpl(appResource: AppResource) extends CountPlanService {
  override def deleteAll(): Task[Int] = db.run(CountPlan.delete).provideLayer(appResource.sqliteSlickLayer)
}
