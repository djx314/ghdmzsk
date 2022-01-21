package f06.reverseroutes

import f06.endpoint.NumberEndpoint
import f06.endpoint.CounterEndpoint

trait ReverseRoutesPre {

  import ReverseRoutesUtils._

  def numberEndpoint: NumberEndpoint
  def counterEndpoint: CounterEndpoint

  def deleteAllCountPlan               = requestPlan(numberEndpoint.deleteAllCountPlan)
  def resetAllCountPlan                = requestPlan(numberEndpoint.resetAllCountPlan)
  def countAllCountPlan                = requestPlan(numberEndpoint.countCountPlan)
  def counterExecutionPlan(count: Int) = requestPlan(counterEndpoint.counterExecutionPlan)(count)

}
