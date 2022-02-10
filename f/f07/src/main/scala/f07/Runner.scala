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

  val col1 = for {
    each <- SetsCol.setsCol
  } yield {
    val list = for {
      i1 <- each.firstStart to 20
      i2 <- each.secondStart to 20
    } yield s"$i1,$i2,${each.count(i1, i2).getOrElse("unlimited")}"
    (each.key, list.to(List))
  }
  val col = col1.to(List)

  def setsLeftover(): List[CountSet] = {
    val countSets = col.map(_._2.mkString("|"))
    CountSets.sum.filterNot(s => countSets.exists(t => t == s.set))
  }

  def colLeftover(): List[String] = {
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

              val v4 =
                try Option(value(i1, i2))
                catch {
                  case e: Throwable => Option.empty
                }
              val str4 = s"$i1,$i2,${v4.getOrElse("unlimited")}"

              val v5 =
                try Option(value(i2, i1))
                catch {
                  case e: Throwable => Option.empty
                }
              val str5 = s"$i1,$i2,${v5.getOrElse("unlimited")}"

              ((str1, str2, str3), (str4, str5))
            }

            val (ll1, ll2)                     = list.unzip
            val (listStr0, listStr4)           = ll2.unzip
            val (listStr1, listStr2, listStr3) = ll1.unzip3
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
            if (eachSet.set == listStr0.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = i1, i2 = i2, mappingKey: $key")
              throw new Exception
            }
            if (eachSet.set == listStr4.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = i2, i2 = i1, mappingKey: $key")
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

              val v4 =
                try Option(value(i1, i2))
                catch {
                  case e: Throwable => Option.empty
                }
              val str4 = s"$i1,$i2,${v4.flatten.getOrElse("unlimited")}"

              val v5 =
                try Option(value(i2, i1))
                catch {
                  case e: Throwable => Option.empty
                }
              val str5 = s"$i1,$i2,${v5.flatten.getOrElse("unlimited")}"

              ((str1, str2, str3), (str4, str5))
            }

            val (ll1, ll2)                     = list.unzip
            val (listStr0, listStr4)           = ll2.unzip
            val (listStr1, listStr2, listStr3) = ll1.unzip3
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
            if (eachSet.set == listStr0.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = i1, i2 = i2, mappingKey: $key")
              throw new Exception
            }
            if (eachSet.set == listStr4.mkString("|")) {
              println(s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, i1 = i2, i2 = i1, mappingKey: $key")
              throw new Exception
            }
        }
      }
    } catch {
      case e: Throwable =>
    }
  }

  def countTag(tag: String): Int = {
    val count = SetsCol.setsCol.filter(_.key == tag).head
    val num = for {
      i1 <- count.firstStart to 20
      i2 <- count.secondStart to 20
    } yield s"$i1,$i2,${count.count(i1, i2).getOrElse("unlimited")}"
    val str  = num.mkString("|")
    val sets = CountSets.sum.filter(_.set == str)
    val set  = sets.head
    assert(sets.size == 1)
    CountPlans.sum.filter(_.set.index == set.index).size
  }

  def main(arr: Array[String]): Unit = {
    println(s"重复的映射：${distinctRunner()}")
    println(s"结果集总数：${CountSets.sum.size}")
    println(s"映射结果总数：${SetsCol.setsCol.size}")
    println(s"未映射结果集数量：${setsLeftover().size}")
    println(s"无效的映射 key：${colLeftover()}")
    println(s"重复的映射 key：${SetsCol.setsCol.map(_.key).groupBy(identity).filter(_._2.size > 1).map(_._1)}")

    // 可立刻替换的映射
    printlnSingleResult()

    println("互为逆运算的法：")
    println(Confirm(SetsCol.setsCol).confirm.mkString("\n"))

    println(
      s"出现次数：加减法：(007, 030, 119) - (002, 226) == (${countTag(Tags.Tag007)}, ${countTag(Tags.Tag030)}, ${countTag(
        Tags.Tag119
      )}) - (${countTag(Tags.Tag002)}, ${countTag(Tags.Tag226)})"
    )
    println(s"出现次数：乘除法：084 - (045, 046) == ${countTag(Tags.Tag084)} - (${countTag(Tags.Tag045)}, ${countTag(Tags.Tag046)})")
    println(s"出现次数：第三法：067 - 040 == ${countTag(Tags.Tag067)} - ${countTag(Tags.Tag040)}")

    // Gen3.genRunner()
  }

}
