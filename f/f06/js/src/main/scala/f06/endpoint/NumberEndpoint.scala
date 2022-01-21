package f06.endpoint

import sttp.tapir._

class NumberEndpoint extends NumberEndpointPre {

  import EndpointHelper._

  val deleteAllCountPlan = deleteAllCountPlanPre
  val resetAllCountPlan  = resetAllCountPlanPre
  val countCountPlan     = countCountPlanPre

}
