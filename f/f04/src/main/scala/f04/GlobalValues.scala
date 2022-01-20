package f04

import f04.utils.RequestUtils
import f06.models.{ResultSet, ReverseUrl}

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

object GlobalValues {

  val reverseUrl: Future[ReverseUrl] = RequestUtils.getJson[ResultSet[ReverseUrl]]("reverseUrl", null).map(_.data)

}
