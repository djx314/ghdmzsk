package f03.mainapp

import cats.implicits._
import com.softwaremill.macwire._
import distage._
import f03.fusion.NumberFusion
import f03.views.IndexView
import org.http4s._
import org.http4s.server.staticcontent._
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.swagger.SwaggerUI
import zio._
import zio.interop.catz._

object MainApp {

  type ZIOEnv[T] = RIO[ZEnv, T]

  lazy val appRoutes            = wire[AppRoutes]
  private lazy val appConfig    = wire[AppConfig]
  private lazy val numberFusion = wire[NumberFusion]
  private lazy val indexView    = wire[IndexView]

  // prepare
  private object GDModule extends ModuleDef {
    make[AppRoutes]
    make[NumberFusion]
  }

  private object GDApp {
    private val injector = Injector[RIO[ZEnv, *]]()
    private val plan = injector.plan(
      GDModule,
      Activation.empty,
      Roots(
        DIKey[AppRoutes]
      )
    )
    private val resource = injector.produce(plan)
    val routes           = resource.use(s => ZIO.effect(s.get[AppRoutes]))
  }

}

class AppRoutes(numberFusion: NumberFusion) {
  private val lowLevelRoutes = ZHttp4sServerInterpreter[ZEnv]().from(numberFusion.lowLevelRoutes).toRoutes
  private val httpRoutes     = ZHttp4sServerInterpreter[ZEnv]().from(numberFusion.routes).toRoutes
  private val fileRoutes     = webjarServiceBuilder[MainApp.ZIOEnv].toRoutes

  private val docsAsYaml: String = OpenAPIDocsInterpreter().toOpenAPI(numberFusion.docs, "Number App", "1.0").toYaml
  private val swaggerUIRoute     = ZHttp4sServerInterpreter[ZEnv]().from(SwaggerUI[MainApp.ZIOEnv](docsAsYaml)).toRoutes

  val routes: HttpRoutes[MainApp.ZIOEnv] = httpRoutes <+> fileRoutes <+> swaggerUIRoute <+> lowLevelRoutes
}
