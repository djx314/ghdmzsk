package f06.reverseroutes

import f06.endpoint.NumberEndpoint

trait ReverseRoutesPre {

  import ReverseRoutesUtils._

  def numberEndpoint: NumberEndpoint

  def deleteAllCountPlan = requestPlan(numberEndpoint.deleteAllCountPlan)
  def resetAllCountPlan  = requestPlan(numberEndpoint.resetAllCountPlan)
  def countAllCountPlan  = requestPlan(numberEndpoint.countCountPlan)

}
