package f03.mainapp

import zio._
import zhttp.http._
import zhttp.service.Server
import zio.logging._

object Main extends App {
  type AppEnv = MainApp.AppEnv

  val loggingEnv: URLayer[ZEnv, Logging] =
    Logging.console(logLevel = LogLevel.Info, format = LogFormat.ColoredLogFormat()) >>> Logging.withRootLoggerName("number-app")

  override def run(args: List[String]): URIO[ZEnv, zio.ExitCode] =
    MainApp.routes.use(s => Server.start(8080, s.app)).provideSomeLayer(loggingEnv).exitCode

}
