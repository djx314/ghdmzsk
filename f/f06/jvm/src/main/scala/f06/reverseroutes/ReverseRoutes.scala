package f06.reverseroutes

import f06.endpoint.NumberEndpoint

import scala.language.implicitConversions

class ReverseRoutes(override val numberEndpoint: NumberEndpoint) extends ReverseRoutesPre {

  import ReverseRoutesUtils._

  val index               = requestPlan(numberEndpoint.index)
  val countPlanReviewPage = requestPlan(numberEndpoint.countPlanReviewPage)

}
