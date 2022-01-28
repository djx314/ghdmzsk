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

  def printlnSingleResult(): Unit = {
    try {
      val sets     = col.map(s => (s._1, s._2.mkString("|")))
      val leftSets = CountSets.sum.filter(s => sets.forall(t => t._2 != s.set))
      for {
        eachSet   <- leftSets
        setsCount <- SetsCol.setsCol
      } {
        setsCount match {
          case CommonSetsList(key, firstStart, secondStart, value) =>
            val list = for {
              i1 <- eachSet.firstStart to 20
              i2 <- eachSet.secondStart to 20
            } yield {
              val v1 =
                try Option(value(1, i2))
                catch {
                  case e: Throwable => Option.empty
                }
              val str1 = s"$i1,$i2,${v1.getOrElse("unlimited")}"

              val v2 =
                try Option(value(i1, 1))
                catch {
                  case e: Throwable => Option.empty
                }
              val str2 = s"$i1,$i2,${v2.getOrElse("unlimited")}"

              val v3 =
                try Option(value(1, 1))
                catch {
                  case e: Throwable => Option.empty
                }
              val str3 = s"$i1,$i2,${v3.getOrElse("unlimited")}"

              (str1, str2, str3)
            }

            val (listStr1, listStr2, listStr3) = list.unzip3
            if (eachSet.set == listStr1.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = 1, i2 = i2, mappingKey: $key")
              throw new Exception
            }
            if (eachSet.set == listStr2.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = i1, i2 = 1, mappingKey: $key")
              throw new Exception
            }
            if (eachSet.set == listStr3.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = 1, i2 = 1, mappingKey: $key")
              throw new Exception
            }
          case OptSetsList(key, firstStart, secondStart, value) =>
            val list = for {
              i1 <- eachSet.firstStart to 20
              i2 <- eachSet.secondStart to 20
            } yield {
              val v1 =
                try Option(value(1, i2))
                catch {
                  case e: Throwable => Option.empty
                }
              val str1 = s"$i1,$i2,${v1.flatten.getOrElse("unlimited")}"

              val v2 =
                try Option(value(i1, 1))
                catch {
                  case e: Throwable => Option.empty
                }
              val str2 = s"$i1,$i2,${v2.flatten.getOrElse("unlimited")}"

              val v3 =
                try Option(value(1, 1))
                catch {
                  case e: Throwable => Option.empty
                }
              val str3 = s"$i1,$i2,${v3.flatten.getOrElse("unlimited")}"

              (str1, str2, str3)
            }

            val (listStr1, listStr2, listStr3) = list.unzip3
            if (eachSet.set == listStr1.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = 1, i2 = i2, mappingKey: $key")
              throw new Exception
            }
            if (eachSet.set == listStr2.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = i1, i2 = 1, mappingKey: $key")
              throw new Exception
            }
            if (eachSet.set == listStr3.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = 1, i2 = 1, mappingKey: $key")
              throw new Exception
            }
        }
      }
    } catch {
      case e: Throwable =>
    }
  }

  def main(arr: Array[String]): Unit = {
    println(s"重复的映射：${distinctRunner()}")
    println(s"结果集总数：${CountSets.sum.size}")
    println(s"映射结果总数：${SetsCol.setsCol.size}")
    println(s"未映射结果集数量：${setsLeftover().size}")
    println(s"无效的映射 key：${colLeftover()}")
    println(s"重复的映射 key：${SetsCol.setsCol.map(_.key).groupBy(identity).filter(_._2.size > 1).map(_._1)}")

    printlnSingleResult()

    // println(Confirm.confirm.mkString("\n"))
    // Gen3.genRunner()
  }

}
