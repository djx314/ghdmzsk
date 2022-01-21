package f03.fusion

import f03.service.CountPlanService
import f03.views.{CountPlanReview, CounterRunnerExecutionView, HelperView, IndexView}
import f06.endpoint.{CounterEndpoint, NumberEndpoint}
import sttp.model.StatusCode
import sttp.tapir.ztapir._
import zio._
import zio.logging._

class CounterFusion(
  counterView: CounterRunnerExecutionView,
  counterEndpoint: CounterEndpoint
) {

  type AppEnv = f03.mainapp.MainApp.AppEnv

  val counterPage = counterEndpoint.counterPage.zServerLogic(_ => ZIO.succeed(counterView.view))

  val routes = List(counterPage.widen[AppEnv])
  val docs   = List(counterEndpoint.counterPage)

}
