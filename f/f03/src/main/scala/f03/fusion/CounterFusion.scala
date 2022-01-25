package f03.fusion

import f03.service.{CodegenService, CounterExecutionService}
import f03.views.{CodegenView, CounterRunnerExecutionView}
import f06.endpoint.CounterEndpoint
import sttp.model.StatusCode
import sttp.tapir.ztapir._
import zio._
import zio.logging._

class CounterFusion(
  counterView: CounterRunnerExecutionView,
  counterEndpoint: CounterEndpoint,
  counterExecutionService: CounterExecutionService,
  codegenView: CodegenView,
  codegenService: CodegenService
) {

  type AppEnv = f03.mainapp.MainApp.AppEnv

  val counterPage = counterEndpoint.counterPage.zServerLogic(_ => ZIO.succeed(counterView.view))
  val codegenPage = counterEndpoint.codegenPage.zServerLogic(_ => ZIO.succeed(codegenView.view))

  val counterExecutionPlan = counterEndpoint.counterExecutionPlan.zServerLogic { count =>
    val action = for (i <- counterExecutionService.executePlan(count)) yield (i, s"任务成功，剩余未计算计划数量：${i} 条")

    def errorHandle(e: Throwable) =
      for (_ <- Logging.throwable("执行计算任务发生异常", e))
        yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

    action.flatMapError(errorHandle)
  }

  val codegen = counterEndpoint.codegen.zServerLogic { _ =>
    val action = for (s <- codegenService.codegen()) yield (s, "任务成功")

    def errorHandle(e: Throwable) =
      for (_ <- Logging.throwable("执行任务发生异常", e))
        yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

    action.flatMapError(errorHandle)
  }

  val routes = List(counterPage.widen[AppEnv], codegenPage.widen[AppEnv], counterExecutionPlan.widen[AppEnv], codegen.widen[AppEnv])
  val docs   = List(counterEndpoint.counterPage, counterEndpoint.codegenPage, counterEndpoint.counterExecutionPlan, counterEndpoint.codegen)

}
