package f06.models

import io.circe.generic.JsonCodec

@JsonCodec
case class ReverseUrl(
  index: RequestPlan,
  countPlanReviewPage: RequestPlan,
  deleteAllCountPlan: RequestPlan,
  resetAllCountPlan: RequestPlan,
  countAllCountPlan: RequestPlan
)
@JsonCodec
case class RequestPlan(url: String, method: String)
