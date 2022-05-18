package f06.reverseroutes

import f06.models.RequestPlan

import scala.language.implicitConversions
import sttp.model.{Method, Uri}
import sttp.tapir._
import sttp.tapir.client.sttp.SttpClientInterpreter

trait ReverseRoutesUtils {
  class FillParameter[I, Poly](val value: I)
  object FillParameter {
    implicit def fillLiteral[T, U](t: T): FillParameter[T, U] = new FillParameter(t)
  }

  object FillParameterPoly1 {
    implicit def fill: FillParameter[Unit, FillParameterPoly1.type] = new FillParameter(())
  }

  def toUri[I, E, O, R](
    endpoint: PublicEndpoint[I, E, O, R]
  )(implicit fill: FillParameter[I, FillParameterPoly1.type]): (Uri, Method) = {
    val request = SttpClientInterpreter().toRequest(endpoint, Option.empty)
    val r       = request(fill.value)
    (r.uri, r.method)
  }

  def requestPlan[I, E, O, R](
    endpoint: PublicEndpoint[I, E, O, R]
  )(implicit fill: FillParameter[I, FillParameterPoly1.type]): RequestPlan[E, O] = {
    val (uri, method) = toUri(endpoint)
    RequestPlan(uri.toString(), method.method)
  }
}

object ReverseRoutesUtils extends ReverseRoutesUtils
