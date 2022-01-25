package f03.mainapp

import f03.fusion.{CounterFusion, NumberFusion}
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import sttp.tapir.swagger.SwaggerUI
import sttp.tapir._
import zhttp.http.{Http, Request, Response}
import zio._

trait AppRoutes[R] {
  def app: Http[R, Throwable, Request, Response[R, Throwable]]
}

class AppRoutesImpl(numberFusion: NumberFusion, counterFusion: CounterFusion) extends AppRoutes[MainApp.AppEnv] {
  type AppEnv = MainApp.AppEnv

  private def interpreter = ZioHttpInterpreter[AppEnv]()

  private val lowLevelRoutes = interpreter.toHttp(numberFusion.lowLevelRoutes)
  private val httpRoutes     = interpreter.toHttp(numberFusion.routes ::: counterFusion.routes)

  private val webjarRoutes =
    resourcesGetServerEndpoint[RIO[AppEnv, *]]("resources")(classOf[AppRoutesImpl].getClassLoader, "META-INF/resources/webjars")
  private val webjarHttp = interpreter.toHttp(webjarRoutes)

  private val docsAsYaml: String = OpenAPIDocsInterpreter().toOpenAPI(numberFusion.docs ::: counterFusion.docs, "Number App", "1.0").toYaml
  private val swaggerUIRoute     = interpreter.toHttp(SwaggerUI[RIO[AppEnv, *]](docsAsYaml))

  override val app = httpRoutes ++ swaggerUIRoute ++ lowLevelRoutes ++ webjarHttp

}
