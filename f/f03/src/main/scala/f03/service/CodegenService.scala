package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._
import zio.stream.ZStream

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.concurrent.ExecutionContext

trait CodegenService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def codegen(): CTask[Unit]
}

class CodegenServiceImpl(db: SlickDB, dataCollection: DataCollection) extends CodegenService {

  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  override def codegen(): CTask[Unit] = {
    val getIdAction = for {
      plan <- CountPlan
      set  <- CountSet if plan.counterResultId === set.id
    } yield (plan, set)
    val planAndSet = db.run(getIdAction.result)

    def printlnSet(set: Seq[CountSetRow]): CTask[Unit] = {
      val str   = set.map(s => s"CountSet(firstStart = ${s.firstStart}, secondStart = ${s.secondStart}, set = \"${s.countSet}\")")
      val path  = Paths.get("..", "f07", "src", "main", "codegen", "f07")
      val path1 = path.resolve("CountSets.scala")
      for {
        _         <- blocking.effectBlocking(Files.createDirectories(path))
        printlner <- ZIO.effect(ZManaged.fromAutoCloseable(ZIO.effect(new PrintWriter(path1.toFile))))
        s         <- printlner.use(p => blocking.effectBlocking(str.foreach(p.println)))
      } yield s
    }

    for {
      row <- planAndSet
      (plans, sets) = row.unzip
    } yield printlnSet(sets)
  }

}
