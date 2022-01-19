package f03.reverseroutes

import f03.endpoint.NumberEndpoint
import f06.models.ReverseUrl
import sttp.model.Uri
import sttp.tapir._
import sttp.tapir.client.sttp.SttpClientInterpreter

object ReverseRoutes {

  private class FillParameter[I, Poly](val value: I)

  private object FillParameterPoly1 {
    implicit def fill: FillParameter[Unit, FillParameterPoly1.type] = new FillParameter(())
  }

  private def toUri[I, E, O, R](endpoint: PublicEndpoint[I, E, O, R])(implicit fill: FillParameter[I, FillParameterPoly1.type]): Uri = {
    val request = SttpClientInterpreter().toRequest(endpoint, Option.empty)
    request(fill.value).uri
  }

  object Number {
    val index              = toUri(NumberEndpoint.index).toString
    val deleteAllCountPlan = toUri(NumberEndpoint.deleteAllCountPlan).toString
  }

  val reverseUrl = ReverseUrl(deleteAllCountPlan = Number.deleteAllCountPlan)

}
