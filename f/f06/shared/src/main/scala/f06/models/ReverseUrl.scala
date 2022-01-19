package f06.models

import io.circe.generic.JsonCodec

@JsonCodec
case class ReverseUrl(deleteAllCountPlan: String)
