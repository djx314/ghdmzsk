package f03.service

import f03.mainapp.{db, AppResource, MainApp}
import zio._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._

trait CountPlanService {
  def deleteAll(): RIO[MainApp.AppEnv, Int]
}

object CountPlanService {
  def deleteAll(): RIO[Has[CountPlanService] with MainApp.AppEnv, Int] = for {
    c     <- ZIO.service[CountPlanService]
    count <- c.deleteAll()
  } yield count
}

class CountPlanServiceImpl(appResource: AppResource) extends CountPlanService {
  override def deleteAll(): RIO[MainApp.AppEnv, Int] = db.run(CountPlan.delete) // .provideLayer(appResource.sqliteSlickLayer)
}
