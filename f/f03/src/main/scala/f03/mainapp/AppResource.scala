package f03.mainapp

import zio._
import zio.logging._
import f03.slick.model.Tables.profile.api._
import zio.stream._
import zio.interop.reactivestreams._

import scala.concurrent.{ExecutionContext, Future}

trait SlickDB {
  def run[T](dbioWith: ExecutionContext => DBIO[T]): Task[T]
  def run[T](dbio: DBIO[T]): Task[T] = run(_ => dbio)
  def stream[T](dbioWith: ExecutionContext => StreamingDBIO[_, T], bufferNext: Boolean): ZStream[Any, Throwable, T]
  def stream[T](dbio: StreamingDBIO[_, T], bufferNext: Boolean): ZStream[Any, Throwable, T] = stream(_ => dbio, bufferNext)
}

trait AppResource {
  def sqliteSlickManaged: RManaged[Logging, SlickDB]
}

class AppResourceImpl extends AppResource {

  override val sqliteSlickManaged: RManaged[Logging, SlickDB] = {
    val db1 = for {
      _  <- log.info("开始加载 mysql 数据库")
      db <- ZIO.succeed(Database.forConfig("mysqlNumberDB"))
    } yield db
    val managed = ZManaged.fromAutoCloseable(db1)
    for (db <- managed) yield new SlickDB {
      override def run[T](dbioWith: ExecutionContext => DBIO[T]): Task[T] = ZIO.fromFuture(dbioWith.andThen(db.run))
      override def stream[T](dbioWith: ExecutionContext => StreamingDBIO[_, T], bufferNext: Boolean): ZStream[Any, Throwable, T] = {
        def toStream(dbio: StreamingDBIO[_, T]) = db.stream(dbio, bufferNext).toStream()
        val actionFunc                          = { implicit ec: ExecutionContext => Future.successful(toStream(dbioWith(implicitly))) }
        ZStream.fromEffect(ZIO.fromFuture(actionFunc)).flatten
      }
    }
  }

}
