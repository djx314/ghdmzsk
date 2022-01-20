package f03.reverseroutes

import f03.endpoint.NumberEndpoint
import f06.models.{RequestPlan, ReverseUrl}

import scala.language.implicitConversions
import sttp.model.{Method, Uri}
import sttp.tapir._
import sttp.tapir.client.sttp.SttpClientInterpreter
import zio._

class ReverseRoutes {

  private class FillParameter[I, Poly](val value: I)

  private object FillParameterPoly1 {
    implicit def fill: FillParameter[Unit, FillParameterPoly1.type]              = new FillParameter(())
    implicit def fillLiteral[T](t: T): FillParameter[T, FillParameterPoly1.type] = new FillParameter(t)
  }

  private def toUri[I, E, O, R](
    endpoint: PublicEndpoint[I, E, O, R]
  )(implicit fill: FillParameter[I, FillParameterPoly1.type]): (Uri, Method) = {
    val request = SttpClientInterpreter().toRequest(endpoint, Option.empty)
    val r       = request(fill.value)
    (r.uri, r.method)
  }

  private def requestPlan[I, E, O, R](
    endpoint: PublicEndpoint[I, E, O, R]
  )(implicit fill: FillParameter[I, FillParameterPoly1.type]): RequestPlan = {
    val i = toUri(endpoint)
    RequestPlan(i._1.toString(), i._2.method)
  }

  object Number {
    val index              = requestPlan(NumberEndpoint.index)
    val deleteAllCountPlan = requestPlan(NumberEndpoint.deleteAllCountPlan)
    val resetAllCountPlan  = requestPlan(NumberEndpoint.resetAllCountPlan)
  }

  val reverseUrl = ReverseUrl(deleteAllCountPlan = Number.deleteAllCountPlan, resetAllCountPlan = Number.resetAllCountPlan)

}
