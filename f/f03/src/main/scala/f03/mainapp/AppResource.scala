package f03.mainapp

import zio._
import zio.logging._
import f03.slick.model.Tables.profile.api._

import scala.concurrent.{ExecutionContext, Future}

trait SlickDB {
  def run[T](dbioWith: ExecutionContext => DBIO[T]): ExecutionContext => Future[T]
}

object db {
  def run[T](dbio: DBIO[T]): RIO[Has[SlickDB], T] = ZIO.serviceWith[SlickDB](s => ZIO.fromFuture(s.run(_ => dbio)))
  def runWith[T](dbioWith: ExecutionContext => DBIO[T]): RIO[Has[SlickDB], T] =
    ZIO.serviceWith[SlickDB](s => ZIO.fromFuture(s.run(dbioWith)))
}

trait AppResource {
  def sqliteSlickManaged: UManaged[SlickDB]
  def sqliteSlickLayer: ULayer[Has[SlickDB]]
  def loggingEnv: URLayer[ZEnv, Logging]
}

class AppResourceImpl extends AppResource {

  override val sqliteSlickManaged: UManaged[SlickDB] = {
    val db1     = ZIO.succeed(Database.forURL("jdbc:sqlite:./db/numberdatabase.db", driver = "org.sqlite.JDBC"))
    val managed = ZManaged.fromAutoCloseable(db1)
    for (db <- managed) yield new SlickDB {
      override def run[T](dbioWith: ExecutionContext => DBIO[T]): ExecutionContext => Future[T] = dbioWith.andThen(db.run)
    }
  }

  override val sqliteSlickLayer: ULayer[Has[SlickDB]] = sqliteSlickManaged.toLayer

  val loggingEnv: URLayer[ZEnv, Logging] =
    Logging.console(logLevel = LogLevel.Info, format = LogFormat.ColoredLogFormat()) >>> Logging.withRootLoggerName("number-app")

}
