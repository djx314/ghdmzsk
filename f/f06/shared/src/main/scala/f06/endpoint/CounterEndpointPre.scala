package f06.endpoint

import f06.models.ResultSet
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

trait CounterEndpointPre {

  import EndpointHelper._

  protected val counterExecutionPlanPre =
    root.in("counter" / "execution" / "plan").post.in(query[Int]("count")).out(jsonBody[ResultSet[Int]]).errorOut(jsonBody[ResultSet[Unit]])

}
