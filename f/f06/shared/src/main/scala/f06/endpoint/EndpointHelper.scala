package f06.endpoint

import f06.models.ResultSet
import sttp.model.StatusCode
import sttp.tapir._

trait EndpointHelper {

  implicit class appendOutPout[A, I, E, O, -R](endpo: Endpoint[A, I, E, ResultSet[O], R]) {
    def append: Endpoint[A, I, E, (O, StatusCode), R] =
      endpo.out(statusCode).mapOut(d => (d._1.data, d._2))(s => (ResultSet(s._1, s._2.code, Option.empty), s._2))
    def appendSuccess: Endpoint[A, I, E, O, R] =
      endpo.out(statusCode(StatusCode.Ok)).mapOut(d => d.data)(s => ResultSet(s, StatusCode.Ok.code, Option.empty))

    def appendMessage: Endpoint[A, I, E, (O, StatusCode, String), R] =
      endpo
        .out(statusCode)
        .mapOut(d => (d._1.data, d._2, d._1.message.getOrElse("")))(s => (ResultSet(s._1, s._2.code, Option(s._3)), s._2))
    def appendSuccessMessage: Endpoint[A, I, E, (O, String), R] =
      endpo
        .out(statusCode(StatusCode.Ok))
        .mapOut(d => (d.data, d.message.getOrElse("")))(s => ResultSet(s._1, StatusCode.Ok.code, Option(s._2)))
  }

  implicit class appendErrorOutPout[A, I, E, O, -R](endpo: Endpoint[A, I, ResultSet[E], O, R]) {
    def appendError: Endpoint[A, I, (E, StatusCode), O, R] =
      endpo.errorOut(statusCode).mapErrorOut(d => (d._1.data, d._2))(s => (ResultSet(s._1, s._2.code, Option.empty), s._2))

    def appendErrorMessage: Endpoint[A, I, (E, StatusCode, String), O, R] =
      endpo
        .errorOut(statusCode)
        .mapErrorOut(d => (d._1.data, d._2, d._1.message.getOrElse("")))(s => (ResultSet(s._1, s._2.code, Option(s._3)), s._2))
  }

  val htmlDocTag = "Html 展示页"
  val JsonTag    = "Json 请求"

}

object EndpointHelper extends EndpointHelper
