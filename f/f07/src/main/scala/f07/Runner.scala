package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.util.Using

object Runner {

  val path = Paths.get(".", "f", "f07", "src", "main", "codegen", "f07")

  def genTagsRunner(): Unit = {
    val lines = for (i <- 1 to 500) yield {
      val num = if (i < 10) s"00$i" else if (i < 100) s"0${i}" else i.toString
      s"  val Tag$num = \"Tag$num\""
    }
    val lineList = "package f07" :: "object Tags {" :: lines.to(List).appendedAll(List("}"))
    Files.createDirectories(path)
    val filePath = path.resolve("Tags.scala")
    Using.resource(new PrintWriter(filePath.toFile)) { writer =>
      lineList.foreach(writer.println)
    }
  }

  def distinctRunner(): List[List[String]] = {
    val cols = for (each <- SetsCol.setsCol) yield {
      each match {
        case CommonSetsList(key, firstStart, secondStart, value) =>
          val list = for {
            i1 <- 0 to 20
            i2 <- 0 to 20
          } yield Option(value(i1, i2))
          (key, list.to(List))
        case OptSetsList(key, firstStart, secondStart, value) =>
          val list = for {
            i1 <- 0 to 20
            i2 <- 0 to 20
          } yield value(i1, i2)
          (key, list.to(List))
      }
    }
    cols.groupBy(s => s._2).filter(_._2.size > 1).map(_._2.map(_._1).to(List)).to(List)
  }

  def setsLeftover(): List[CountSet] = {
    val countSets = col.map(_._2.mkString("|"))
    CountSets.sum.filterNot(s => countSets.exists(t => t == s.set))
  }
  val col = for {
    each <- SetsCol.setsCol
  } yield each match {
    case CommonSetsList(key, firstStart, secondStart, value) =>
      val list = for {
        i1 <- firstStart to 20
        i2 <- secondStart to 20
      } yield s"$i1,$i2,${value(i1, i2)}"
      (key, list)
    case OptSetsList(key, firstStart, secondStart, value) =>
      val list = for {
        i1 <- firstStart to 20
        i2 <- secondStart to 20
      } yield s"$i1,$i2,${value(i1, i2).getOrElse("unlimited")}"
      (key, list)
  }
  def colLeftover(): Vector[String] = {
    val countSets = col.map(s => (s._1, s._2.mkString("|")))
    countSets.filterNot(s => CountSets.sum.exists(t => t.set == s._2)).map(_._1)
  }

  def main(arr: Array[String]): Unit = {
    println(CountSets.sum.size)
    println(setsLeftover().size)
    println(colLeftover())

    println(
      CountPlans.sum
        .groupBy(s =>
          (
            s.firstOuterName,
            s.firstOuterType,
            s.firstInnerName,
            s.firstInnerType,
            s.secondOuterName,
            s.secondOuterType,
            s.secondInnerName,
            s.secondInnerType
          )
        )
        .size
    )
  }

}
