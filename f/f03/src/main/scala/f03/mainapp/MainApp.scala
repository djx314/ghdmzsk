package f03.mainapp

import cats.implicits._
import com.softwaremill.macwire._
import distage._
import f03.fusion.NumberFusion
import f03.reverseroutes.ReverseRoutes
import f03.service.DataCollection
import f03.views.{CountPlanReview, HelperView, IndexView, JsDependencies}
import org.http4s._
import org.http4s.server.staticcontent._
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.swagger.SwaggerUI
import zio._
import zio.interop.catz._

object MainApp {

  type ZIOEnv[T]    = RIO[AppEnv, T]
  type CustomAppEnv = Has[SlickDB] with logging.Logging
  type AppEnv       = ZEnv with CustomAppEnv
  lazy val appEnv = appResource.sqliteSlickLayer ++ appResource.loggingEnv

  lazy val appRoutes                        = wire[AppRoutes]
  private lazy val appConfig                = wire[AppConfig]
  private lazy val numberFusion             = wire[NumberFusion]
  private lazy val indexView                = wire[IndexView]
  private lazy val jsDependencies           = wire[JsDependencies]
  private lazy val appResource: AppResource = wire[AppResourceImpl]
  private lazy val helperView               = wire[HelperView]
  private lazy val reverseRoutes            = wire[ReverseRoutes]
  private lazy val dataCollection           = wire[DataCollection]
  private lazy val countPlanReview          = wire[CountPlanReview]

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
  type AppEnv    = MainApp.AppEnv
  type ZIOEnv[T] = MainApp.ZIOEnv[T]

  private val lowLevelRoutes = ZHttp4sServerInterpreter[AppEnv]().from(numberFusion.lowLevelRoutes).toRoutes
  private val httpRoutes     = ZHttp4sServerInterpreter[AppEnv]().from(numberFusion.routes).toRoutes
  private val fileRoutes     = webjarServiceBuilder[ZIOEnv].toRoutes

  private val docsAsYaml: String = OpenAPIDocsInterpreter().toOpenAPI(numberFusion.docs, "Number App", "1.0").toYaml
  private val swaggerUIRoute     = ZHttp4sServerInterpreter[AppEnv]().from(SwaggerUI[ZIOEnv](docsAsYaml)).toRoutes

  val routes: HttpRoutes[ZIOEnv] = httpRoutes <+> fileRoutes <+> swaggerUIRoute <+> lowLevelRoutes
}
