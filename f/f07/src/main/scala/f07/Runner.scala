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
            i1 <- firstStart to 20
            i2 <- secondStart to 20
          } yield Option(value(i1, i2))
          (key, (firstStart, secondStart, list.to(List)))
        case OptSetsList(key, firstStart, secondStart, value) =>
          val list = for {
            i1 <- firstStart to 20
            i2 <- secondStart to 20
          } yield value(i1, i2)
          (key, (firstStart, secondStart, list.to(List)))
      }
    }
    cols.groupBy(s => s._2).filter(_._2.size > 1).map(_._2.map(_._1).to(List)).to(List)
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

  def setsLeftover(): List[CountSet] = {
    val countSets = col.map(_._2.mkString("|"))
    CountSets.sum.filterNot(s => countSets.exists(t => t == s.set))
  }

  def colLeftover(): Vector[String] = {
    val countSets = col.map(s => (s._1, s._2.mkString("|")))
    countSets.filterNot(s => CountSets.sum.exists(t => t.set == s._2)).map(_._1)
  }

  def main(arr: Array[String]): Unit = {
    println(s"重复的映射：${distinctRunner()}")
    println(s"结果集总数：${CountSets.sum.size}")
    println(s"映射结果总数：${SetsCol.setsCol.size}")
    println(s"未映射结果集数量：${setsLeftover().size}")
    println(s"无效的映射 key：${colLeftover()}")
    println(s"重复的映射 key：${SetsCol.setsCol.map(_.key).groupBy(identity).filter(_._2.size > 1).map(_._1)}")
    // Gen3.genRunner()
  }

}
