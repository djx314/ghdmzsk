package f03.mainapp

import zio._
import zio.logging._
import f03.slick.model.Tables.profile.api._

import scala.concurrent.ExecutionContext

trait SlickDB {
  def run[T](dbioWith: ExecutionContext => DBIO[T]): Task[T]
  def run[T](dbio: DBIO[T]): Task[T] = run(_ => dbio)
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
    }
  }

}
