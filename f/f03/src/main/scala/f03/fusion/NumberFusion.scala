package f03.fusion

import f03.endpoint.NumberEndpoint
import f03.views.{HelperView, IndexView}
import sttp.tapir.ztapir._
import zio._

class NumberFusion(indexView: IndexView) {

  val pageHelper = NumberEndpoint.pageHelper.zServerLogic(_ => ZIO.succeed(HelperView.view))
  val index      = NumberEndpoint.index.zServerLogic(_ => ZIO.succeed(indexView.view))

  val routes         = List(index.widen[ZEnv])
  val lowLevelRoutes = List(pageHelper.widen[ZEnv])
  val docs           = List(NumberEndpoint.pageHelper, NumberEndpoint.index)

}
