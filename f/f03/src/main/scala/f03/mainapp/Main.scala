package f03.mainapp

import zio._
import zhttp.http._
import zhttp.service.Server

object Main extends App {
  type AppEnv = MainApp.AppEnv

  override def run(args: List[String]): URIO[ZEnv, zio.ExitCode] =
    Server.start(8090, MainApp.appRoutes.app).provideSomeLayer(MainApp.appEnv).exitCode

}
