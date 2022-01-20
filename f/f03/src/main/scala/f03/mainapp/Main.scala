package f03.mainapp

import org.http4s._
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.duration._

import zio._
import zio.interop.catz._

object Main extends App {
  type AppEnv    = MainApp.AppEnv
  type ZIOEnv[T] = MainApp.ZIOEnv[T]

  def builder(httpApp: HttpApp[ZIOEnv]) = BlazeServerBuilder[ZIOEnv]
    .bindHttp(8080, "localhost")
    .withHttpApp(httpApp)
    .withIdleTimeout(10.minutes)
    .withResponseHeaderTimeout(10.minutes)

  override def run(args: List[String]): URIO[ZEnv, zio.ExitCode] =
    builder(MainApp.appRoutes.routes.orNotFound).resource.use(_ => ZIO.never).provideCustomLayer(MainApp.appEnv).exitCode

}
