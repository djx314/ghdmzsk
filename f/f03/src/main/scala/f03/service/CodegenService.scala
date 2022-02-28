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
    val planAndSet  = db.stream(getIdAction.result, bufferNext = false)
    val planAndSet1 = db.run(getIdAction.map(_._2).distinctOn(_.id).result)

    def printlnSet(set: Seq[CountSetRow]): CTask[Unit] = {
      val str1 = set.map(s =>
        s"val countSet${s.id}: CountSet = CountSet(index = ${s.id}, firstStart = ${s.firstStart}, secondStart = ${s.secondStart}, set = \"${s.countSet}\")"
      )
      val sumStr  = set.grouped(20).map(t => s"List(${t.map(r => s"b.countSet${r.id}").mkString(",")})").mkString(" ::: ")
      val strPre1 = s"  val sum: List[CountSet] = $sumStr"
      val str2 = "package f07" :: "trait CountSetsImpl {" :: str1
        .to(List)
        .map(s => "  " + s)
        .appendedAll(List("}", "", "object CountSetsImpl {", "object b extends CountSetsImpl", strPre1, "}"))
      val path  = Paths.get("..", "f07", "src", "main", "codegen", "f07")
      val path1 = path.resolve("CountSetsImpl.scala")
      for {
        _         <- blocking.effectBlocking(Files.createDirectories(path))
        printlner <- ZIO.effect(ZManaged.fromAutoCloseable(ZIO.effect(new PrintWriter(path1.toFile))))
        s         <- printlner.use(p => blocking.effectBlocking(str2.foreach(p.println)))
      } yield s
    }

    def printlnPlan(plans: Seq[CountPlanRow], index: Long): CTask[Unit] = {
      val str1 = plans.map(p =>
        s"val plan${p.id} = CountPlan(index = ${p.id}, firstOuterName = \"${p.firstOuterName}\", firstOuterType = \"${p.firstOuterType}\", firstInnerName = \"${p.firstInnerName}\", firstInnerType = \"${p.firstInnerType}\", firstStart = ${p.firstStart}, secondOuterName = \"${p.secondOuterName}\", secondOuterType = \"${p.secondOuterType}\", secondInnerName = \"${p.secondInnerName}\", secondInnerType = \"${p.secondInnerType}\", secondStart = ${p.secondStart}, set = CountSetsImpl.b.countSet${p.counterResultId
          .getOrElse("未有值")})"
      )
      val str2 = s"  val sum: List[CountPlan] = List(${plans.map(s => s"plan${s.id}").mkString(",")})"
      val str3 = "package f07.codegen.impl" :: "import f07._" :: s"object CountPlans$index {" :: str1
        .to(List)
        .map(s => "  " + s)
        .appendedAll(List(str2, "}"))
      val path  = Paths.get("..", "f07", "src", "main", "codegen", "f07", "codegen", "impl")
      val path1 = path.resolve(s"CountPlans$index.scala")
      for {
        _         <- blocking.effectBlocking(Files.createDirectories(path))
        printlner <- ZIO.effect(ZManaged.fromAutoCloseable(ZIO.effect(new PrintWriter(path1.toFile))))
        s         <- printlner.use(p => blocking.effectBlocking(str3.foreach(p.println)))
      } yield s
    }

    def printlnPlan1(indexSeq: List[Long]): CTask[Unit] = {
      val path   = Paths.get("..", "f07", "src", "main", "codegen", "f07")
      val str1   = "package f07"
      val str1_1 = "import f07.codegen.impl._"
      val str2   = s"object CountPlans {"
      val fileSeq = for ((seq, index) <- indexSeq.grouped(50).zipWithIndex) yield {
        val path1 = path.resolve("codegen").resolve("impl").resolve(s"CountPlanSums$index.scala")
        val str3 =
          s"  val sum: List[CountPlan] = ${seq.grouped(20).grouped(20).map(s => s.map(t => t.map(u => s"CountPlans$u.sum").mkString("(", ":::", ")")).mkString("(", " ::: ", ")")).mkString(" ::: ")}"
        (List(str1, str1_1, s"object CountPlanSums$index {", str3, "}"), path1)
      }
      val strList1 =
        s"  val sum: List[CountPlan] = ${indexSeq.grouped(50).zipWithIndex.map(_._2).grouped(20).map(t => t.map(s => s"CountPlanSums$s.sum").mkString("(", " ::: ", ")")).mkString(" ::: ")}"

      val str4  = List(str1, str1_1, str2, strList1, "}")
      val path1 = path.resolve("CountPlans.scala")
      val seq   = ((str4, path1)) +: fileSeq.to(List)
      val action = seq.map { case (lines, path2) =>
        for {
          _         <- blocking.effectBlocking(Files.createDirectories(path))
          printlner <- ZIO.effect(ZManaged.fromAutoCloseable(ZIO.effect(new PrintWriter(path2.toFile))))
          s         <- printlner.use(p => blocking.effectBlocking(lines.foreach(p.println)))
        } yield s
      }
      ZIO.collectAllPar(action).unit
    }

    val exec1 = for {
      countSetRow <- planAndSet1
      l           <- ZIO.effect(countSetRow.to(List))
      t           <- printlnSet(l)
    } yield t

    val exec2 = planAndSet.map(_._1).grouped(100).zipWithIndex.mapM(s => printlnPlan(s._1, s._2)).runDrain
    val exec3 = for {
      m <- planAndSet.grouped(100).zipWithIndex.map(_._2).runCollect.map(_.to(List))
      t <- printlnPlan1(m)
    } yield t

    for {
      _ <- exec1
      _ <- exec2
      t <- exec3
    } yield t

    /*for {
      row <- planAndSet
      (plans, sets) = row.unzip
      s <- printlnSet(sets.distinctBy(_.countSet))
      groupedPlans = plans.grouped(100).to(List)
      zipCol       = groupedPlans.zipWithIndex
      actions      = zipCol.map(s => printlnPlan(s._1, s._2))
      r <- ZIO.collectAllPar(actions.to(List))
      t <- printlnPlan1(zipCol.map(_._2).to(List))
    } yield t*/
  }

}
