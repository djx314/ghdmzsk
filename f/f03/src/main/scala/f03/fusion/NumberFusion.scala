package f03.fusion

import f03.endpoint.NumberEndpoint
import f03.mainapp.AppResource
import f03.reverseroutes.ReverseRoutes
import f03.service.CountPlanService
import f03.views.{HelperView, IndexView}
import sttp.model.StatusCode
import sttp.tapir.ztapir._
import zio._
import zio.logging._

class NumberFusion(indexView: IndexView, countPlanService: CountPlanService, appResource: AppResource) {

  val layer = ZLayer.succeedMany(Has(countPlanService)) ++ appResource.loggingEnv

  val pageHelper = NumberEndpoint.pageHelper.zServerLogic(_ => ZIO.succeed(HelperView.view))
  val index      = NumberEndpoint.index.zServerLogic(_ => ZIO.succeed(indexView.view))
  val deleteAllCountPlan =
    NumberEndpoint.deleteAllCountPlan.zServerLogic { _ =>
      val action = CountPlanService.deleteAll()
      def errorHandle(e: Throwable) =
        for (_ <- Logging.throwable("删除所有 CountPlan 发生异常", e))
          yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

      action.flatMapError(errorHandle).provideCustomLayer(layer)
    }

  val reverseUrl = NumberEndpoint.reverseUrl.zServerLogic(_ => ZIO.succeed(ReverseRoutes.reverseUrl))

  val routes         = List(index.widen[ZEnv], deleteAllCountPlan.widen[ZEnv], reverseUrl.widen[ZEnv])
  val lowLevelRoutes = List(pageHelper.widen[ZEnv])
  val docs           = List(NumberEndpoint.pageHelper, NumberEndpoint.index, NumberEndpoint.deleteAllCountPlan, NumberEndpoint.reverseUrl)

}
