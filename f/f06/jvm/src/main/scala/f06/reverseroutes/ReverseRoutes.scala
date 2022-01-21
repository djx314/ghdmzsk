package f06.reverseroutes

import f06.endpoint.{CounterEndpoint, NumberEndpoint}

import scala.language.implicitConversions

class ReverseRoutes(override val numberEndpoint: NumberEndpoint, override val counterEndpoint: CounterEndpoint) extends ReverseRoutesPre {

  import ReverseRoutesUtils._

  val index               = requestPlan(numberEndpoint.index)
  val countPlanReviewPage = requestPlan(numberEndpoint.countPlanReviewPage)
  val counterPage         = requestPlan(counterEndpoint.counterPage)

}
