package f06.endpoint

import f06.models.{PlanCountReview, ResultSet}
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

trait NumberEndpointPre {

  import EndpointHelper._

  protected val deleteAllCountPlanPre = root.in("countPlan").delete.out(jsonBody[ResultSet[Int]]).errorOut(jsonBody[ResultSet[Unit]])

  protected val resetAllCountPlanPre =
    root.in("resetAllCountPlan").post.out(jsonBody[ResultSet[Option[Int]]]).errorOut(jsonBody[ResultSet[Unit]])

  protected val insertAllCountPlanPre =
    root.in("insertAllCountPlan").post.out(jsonBody[ResultSet[Long]]).errorOut(jsonBody[ResultSet[Unit]])

  protected val countCountPlanPre =
    root.in("countCountPlan").post.out(jsonBody[ResultSet[PlanCountReview]]).errorOut(jsonBody[ResultSet[Unit]])

  protected val reSortCountPre = root.in("reSortCount").post.out(jsonBody[ResultSet[Unit]]).errorOut(jsonBody[ResultSet[Unit]])

}
