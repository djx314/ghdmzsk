package f03.fusion

import f03.endpoint.NumberEndpoint
import f03.views.HelperView
import sttp.tapir.ztapir._
import zio._

class NumberFusion {

  val aa11 = NumberEndpoint.pageHelper.zServerLogic(_ => ZIO.succeed(HelperView.view))
  val aabb = NumberEndpoint.index.zServerLogic(_ => ZIO.succeed("bb"))

  val routes         = List(aabb.widen[ZEnv])
  val lowLevelRoutes = List(aa11.widen[ZEnv])
  val docs           = List(NumberEndpoint.pageHelper, NumberEndpoint.index)

}
