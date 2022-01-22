package f06.models

import io.circe.generic.JsonCodec

@JsonCodec
case class PlanCountReview(countPlanAllCount: Int, finishedCountCount: Int, waitForCountCount: Int, countSetCount: Int)
