package f06.reverseroutes

import f06.endpoint.{CounterEndpoint, NumberEndpoint}

class ReverseRoutes(numberEndpoint: NumberEndpoint, counterEndpoint: CounterEndpoint) {

  import ReverseRoutesUtils._

  val index                    = requestPlan(numberEndpoint.index)
  val countPlanReviewPage      = requestPlan(numberEndpoint.countPlanReviewPage)
  val reSortCountExecutionPage = requestPlan(numberEndpoint.reSortCountExecutionPage)
  val counterPage              = requestPlan(counterEndpoint.counterPage)
  val codegenPage              = requestPlan(counterEndpoint.codegenPage)

}
