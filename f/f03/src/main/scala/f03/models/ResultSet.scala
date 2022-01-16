package f03.models

import io.circe.generic.JsonCodec

  @JsonCodec
  case class ResultSet[T](data: T, code: Int)



