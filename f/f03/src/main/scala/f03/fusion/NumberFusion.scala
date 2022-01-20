package f03.fusion

import f03.endpoint.NumberEndpoint
import f03.reverseroutes.ReverseRoutes
import f03.service.CountPlanService
import f03.views.{HelperView, IndexView}
import sttp.model.StatusCode
import sttp.tapir.ztapir._
import zio._
import zio.logging._

class NumberFusion(indexView: IndexView, helperView: HelperView, reverseRoutes: ReverseRoutes) {

  type AppEnv = f03.mainapp.MainApp.AppEnv

  val layer = CountPlanService.service

  val pageHelper = NumberEndpoint.pageHelper.zServerLogic(_ => ZIO.succeed(helperView.view))
  val index      = NumberEndpoint.index.zServerLogic(_ => ZIO.succeed(indexView.view))
  val deleteAllCountPlan =
    NumberEndpoint.deleteAllCountPlan.zServerLogic { _ =>
      val action = CountPlanService.deleteAll()
      def errorHandle(e: Throwable) =
        for (_ <- Logging.throwable("删除所有 CountPlan 发生异常", e))
          yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

      action.flatMapError(errorHandle).provideSomeLayer[AppEnv](layer)
    }

  val reverseUrl = NumberEndpoint.reverseUrl.zServerLogic(_ => ZIO.succeed(reverseRoutes.reverseUrl))

  val routes         = List(index.widen[AppEnv], deleteAllCountPlan.widen[AppEnv], reverseUrl.widen[AppEnv])
  val lowLevelRoutes = List(pageHelper.widen[AppEnv])
  val docs           = List(NumberEndpoint.pageHelper, NumberEndpoint.index, NumberEndpoint.deleteAllCountPlan, NumberEndpoint.reverseUrl)

}
