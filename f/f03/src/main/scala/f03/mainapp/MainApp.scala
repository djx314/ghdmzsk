package f03.mainapp

import com.softwaremill.macwire._
import distage._
import f03.fusion.NumberFusion
import f03.reverseroutes.ReverseRoutes
import f03.service.DataCollection
import f03.views.{CountPlanReview, HelperView, IndexView, JsDependencies}
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import sttp.tapir.swagger.SwaggerUI
import sttp.tapir._
import zio._

object MainApp {

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
  type AppEnv = MainApp.AppEnv

  private val interpreter = ZioHttpInterpreter[AppEnv]()

  private val lowLevelRoutes = interpreter.toHttp(numberFusion.lowLevelRoutes)
  private val httpRoutes     = interpreter.toHttp(numberFusion.routes)

  private val webjarRoutes =
    resourcesGetServerEndpoint[RIO[AppEnv, *]]("resources")(classOf[AppRoutes].getClassLoader, "META-INF/resources/webjars")
  private val webjarHttp = interpreter.toHttp(webjarRoutes)

  private val docsAsYaml: String = OpenAPIDocsInterpreter().toOpenAPI(numberFusion.docs, "Number App", "1.0").toYaml
  private val swaggerUIRoute     = interpreter.toHttp(SwaggerUI[RIO[AppEnv, *]](docsAsYaml))

  val app = httpRoutes ++ swaggerUIRoute ++ lowLevelRoutes ++ webjarHttp

}
