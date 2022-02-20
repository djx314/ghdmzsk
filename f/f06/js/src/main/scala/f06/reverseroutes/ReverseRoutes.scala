package f06.reverseroutes

import f06.endpoint.{CounterEndpoint, NumberEndpoint}

class ReverseRoutes {

  import ReverseRoutesUtils._

  def numberEndpoint: NumberEndpoint   = new NumberEndpoint
  def counterEndpoint: CounterEndpoint = new CounterEndpoint

  def deleteAllCountPlan               = requestPlan(numberEndpoint.deleteAllCountPlan)
  def resetAllCountPlan                = requestPlan(numberEndpoint.resetAllCountPlan)
  def countAllCountPlan                = requestPlan(numberEndpoint.countCountPlan)
  def counterExecutionPlan(count: Int) = requestPlan(counterEndpoint.counterExecutionPlan)(count)
  def reSortCount                      = requestPlan(numberEndpoint.reSortCount)
  def codegen                          = requestPlan(counterEndpoint.codegen)
  def insertAllCountPlan               = requestPlan(numberEndpoint.insertAllCountPlan)

}
