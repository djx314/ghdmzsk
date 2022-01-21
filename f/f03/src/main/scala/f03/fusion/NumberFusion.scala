package f03.fusion

import f06.endpoint.NumberEndpoint
import f03.service.CountPlanService
import f03.views.{CountPlanReview, HelperView, IndexView}
import sttp.model.StatusCode
import sttp.tapir.ztapir._
import zio._
import zio.logging._

class NumberFusion(
  indexView: IndexView,
  helperView: HelperView,
  countPlanReview: CountPlanReview,
  countPlanService: CountPlanService
) {

  type AppEnv = f03.mainapp.MainApp.AppEnv

  val pageHelper          = NumberEndpoint.pageHelper.zServerLogic(_ => ZIO.succeed(helperView.view))
  val index               = NumberEndpoint.index.zServerLogic(_ => ZIO.succeed(indexView.view))
  val countPlanReviewPage = NumberEndpoint.countPlanReviewPage.zServerLogic(_ => ZIO.succeed(countPlanReview.view))

  val deleteAllCountPlan =
    NumberEndpoint.deleteAllCountPlan.zServerLogic { _ =>
      val action = countPlanService.deleteAll()
      def errorHandle(e: Throwable) =
        for (_ <- Logging.throwable("删除所有 CountPlan 发生异常", e))
          yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

      action.flatMapError(errorHandle)
    }

  val resetAllCountPlan =
    NumberEndpoint.resetAllCountPlan.zServerLogic { _ =>
      val action = countPlanService.resetAll()
      def errorHandle(e: Throwable) =
        for (_ <- Logging.throwable("重置所有 CountPlan 发生异常", e))
          yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

      action.flatMapError(errorHandle)
    }

  val countCountPlan = NumberEndpoint.countCountPlan.zServerLogic { _ =>
    val action = countPlanService.count()
    def errorHandle(e: Throwable) =
      for (_ <- Logging.throwable("统计 CountPlan 数量发生异常", e))
        yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

    action.flatMapError(errorHandle)
  }

  val routes = List(
    index.widen[AppEnv],
    countPlanReviewPage.widen[AppEnv],
    deleteAllCountPlan.widen[AppEnv],
    resetAllCountPlan.widen[AppEnv],
    countCountPlan.widen[AppEnv]
  )
  val lowLevelRoutes = List(pageHelper.widen[AppEnv])
  val docs = List(
    NumberEndpoint.pageHelper,
    NumberEndpoint.index,
    NumberEndpoint.countPlanReviewPage,
    NumberEndpoint.deleteAllCountPlan,
    NumberEndpoint.resetAllCountPlan,
    NumberEndpoint.countCountPlan
  )

}
