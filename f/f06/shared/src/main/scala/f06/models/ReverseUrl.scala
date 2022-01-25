package f06.models

import io.circe.generic.JsonCodec

@JsonCodec
case class RequestPlan[ErrOut, Out](url: String, method: String)
