package f03.mainapp

import distage._
import f03.fusion.NumberFusion
import f03.reverseroutes.ReverseRoutes
import f03.service.{CountPlanService, CountPlanServiceImpl, DataCollection, DataCollectionImpl}
import f03.views.{CountPlanReview, HelperView, IndexView, JsDependencies}
import f06.models.ReverseUrl
import zio._

object MainApp {

  type CustomAppEnv = logging.Logging
  type AppEnv       = ZEnv with CustomAppEnv

  private val injector = Injector[RIO[AppEnv, *]]()
  private val plan     = injector.plan(NumberModule, Activation.empty, Roots(DIKey[AppRoutes[AppEnv]]))
  private val resource = injector.produce(plan)
  val routes           = resource.map(_.get[AppRoutes[AppEnv]])

  private object NumberModule extends ModuleDef {
    make[AppRoutes[AppEnv]].from[AppRoutesImpl]
    make[AppResource].from[AppResourceImpl]
    make[SlickDB].fromResource { appResources: AppResource =>
      appResources.sqliteSlickManaged
    }
    make[AppConfig]
    make[NumberFusion]
    make[IndexView]
    make[JsDependencies]
    make[HelperView]
    make[ReverseRoutes]
    make[ReverseUrl].from { reverseRoutes: ReverseRoutes =>
      reverseRoutes.reverseUrl
    }
    make[DataCollection].from[DataCollectionImpl]
    make[CountPlanReview]
    make[CountPlanService].from[CountPlanServiceImpl]
  }

}
