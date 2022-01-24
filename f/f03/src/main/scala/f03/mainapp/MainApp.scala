package f03.mainapp

import distage._
import f03.fusion.{CounterFusion, NumberFusion}
import f06.reverseroutes.ReverseRoutes
import f03.service.{
  CountPlanService,
  CountPlanServiceImpl,
  CounterExecutionService,
  CounterExecutionServiceImpl,
  DataCollection,
  DataCollectionImpl,
  PlanExecute,
  PlanExecuteImpl
}
import f03.views.{CountPlanReview, CounterRunnerExecutionView, HelperView, IndexView, JsDependencies, ReSortCountExecutionPage}
import f06.endpoint.{CounterEndpoint, NumberEndpoint}
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

    include(ViewModule)
    include(FusionModule)
    include(ConfigModule)
    include(ResourceModule)
    include(ServiceModule)
    include(EndpointModule)
  }

  private object ViewModule extends ModuleDef {
    make[IndexView]
    make[JsDependencies]
    make[HelperView]
    make[CountPlanReview]
    make[CounterRunnerExecutionView]
    make[ReverseRoutes]
    make[ReSortCountExecutionPage]
  }

  private object FusionModule extends ModuleDef {
    make[NumberFusion]
    make[CounterFusion]
  }

  private object ConfigModule extends ModuleDef {
    make[AppConfig]
  }

  private object ResourceModule extends ModuleDef {
    make[AppResource].from[AppResourceImpl]
    make[SlickDB].fromResource { AppResource: AppResource =>
      AppResource.sqliteSlickManaged
    }
  }

  private object ServiceModule extends ModuleDef {
    make[DataCollection].from[DataCollectionImpl]
    make[CountPlanService].from[CountPlanServiceImpl]
    make[CounterExecutionService].from[CounterExecutionServiceImpl]
    make[PlanExecute].from[PlanExecuteImpl]
  }

  private object EndpointModule extends ModuleDef {
    make[NumberEndpoint]
    make[CounterEndpoint]
  }

}
