package f06.endpoint

import f06.models.ResultSet
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

trait NumberEndpointPre {

  private val root = endpoint

  protected val deleteAllCountPlanPre = root.in("countPlan").delete.out(jsonBody[ResultSet[Int]]).errorOut(jsonBody[ResultSet[Unit]])

  protected val resetAllCountPlanPre =
    root.in("resetAllCountPlan").post.out(jsonBody[ResultSet[Option[Int]]]).errorOut(jsonBody[ResultSet[Unit]])

  protected val countCountPlanPre = root.in("countCountPlan").post.out(jsonBody[ResultSet[Int]]).errorOut(jsonBody[ResultSet[Unit]])

}
