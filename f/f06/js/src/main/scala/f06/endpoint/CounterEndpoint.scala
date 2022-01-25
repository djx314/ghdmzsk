package f06.endpoint

import f06.models.ResultSet
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

class CounterEndpoint extends CounterEndpointPre {
  val counterExecutionPlan = counterExecutionPlanPre
  val codegen              = codegenPre
}
