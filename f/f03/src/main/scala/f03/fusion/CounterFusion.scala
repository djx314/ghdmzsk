package f03.fusion

import f03.service.{CountPlanService, CounterExecutionService}
import f03.views.CounterRunnerExecutionView
import f06.endpoint.CounterEndpoint
import sttp.model.StatusCode
import sttp.tapir.ztapir._
import zio._
import zio.logging._

class CounterFusion(
  counterView: CounterRunnerExecutionView,
  counterEndpoint: CounterEndpoint,
  counterExecutionService: CounterExecutionService
) {

  type AppEnv = f03.mainapp.MainApp.AppEnv

  val counterPage = counterEndpoint.counterPage.zServerLogic(_ => ZIO.succeed(counterView.view))

  val counterExecutionPlan = counterEndpoint.counterExecutionPlan.zServerLogic { count =>
    val action = for (i <- counterExecutionService.executePlan(count)) yield (i, "计算完毕")

    def errorHandle(e: Throwable) =
      for (_ <- Logging.throwable("执行计算任务发生异常", e))
        yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

    action.flatMapError(errorHandle)
  }

  val routes = List(counterPage.widen[AppEnv], counterExecutionPlan.widen[AppEnv])
  val docs   = List(counterEndpoint.counterPage, counterEndpoint.counterExecutionPlan)

}
