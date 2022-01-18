package f03.slick

import io.circe._
import io.circe.generic._

object CirceHelper {

  import model.Tables._

  implicit val countPlanRowCodec: Codec[CountPlanRow] = semiauto.deriveCodec

}
