package f06.models

import io.circe.generic.JsonCodec

@JsonCodec
case class ReverseUrl(deleteAllCountPlan: RequestPlan, resetAllCountPlan: RequestPlan)
@JsonCodec
case class RequestPlan(url: String, method: String)
