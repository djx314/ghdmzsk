package f03.endpoint

import f03.models.ResultSet
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

object NumberEndpoint   {

  implicit class appendOutPout[A, I, E, O, -R](endpo: Endpoint[A, I, E, ResultSet[O], R]) {
    def append: Endpoint[A, I, E, (O, StatusCode), R] =
      endpo.out(statusCode).mapOut(d => (d._1.data, d._2))(s => (ResultSet(s._1, s._2.code), s._2))
    def appendSuccess: Endpoint[A, I, E, O, R] =
      endpo.out(statusCode(StatusCode.Ok)).mapOut(d => d.data)(s => ResultSet(s, StatusCode.Ok.code))
  }

  implicit class appendErrorOutPout[A, I, E, O, -R](endpo: Endpoint[A, I, ResultSet[E], O, R]) {
    def appendError: Endpoint[A, I, (E, StatusCode), O, R] =
      endpo.errorOut(statusCode).mapErrorOut(d => (d._1.data, d._2))(s => (ResultSet(s._1, s._2.code), s._2))
  }

}