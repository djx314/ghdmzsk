package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.util.{Try, Using}

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
      val list = for {
        i1 <- each.firstStart to 20
        i2 <- each.secondStart to 20
      } yield each.count(i1, i2)
      (each.key, (each.firstStart, each.secondStart, list.to(List)))
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

  def printlnSingleResult(): List[(String, Int, Int, (Int, Int) => Option[Int])] = {
    val sets                                                           = col.map(s => (s._1, s._2.mkString("|")))
    val leftSets                                                       = CountSets.sum.filter(s => sets.forall(t => t._2 != s.set))
    var countSets: List[(String, Int, Int, (Int, Int) => Option[Int])] = List.empty

    val mapping: List[((Int, Int) => (Int, Int), String, String)] = List(
      ((i1, i2) => (1, i2), "(i1: Int, i2: Int) => (1, i2)", "i1 = 1, i2 = i2"),
      ((i1, i2) => (i2, 1), "(i1: Int, i2: Int) => (i2, 1)", "i1 = i2, i2 = 1"),
      ((i1, i2) => (1, i1), "(i1: Int, i2: Int) => (1, i1)", "i1 = 1, i2 = i1"),
      ((i1, i2) => (i1, 1), "(i1: Int, i2: Int) => (i1, 1)", "i1 = i1, i2 = 1"),
      ((i1, i2) => (1, 1), "(i1: Int, i2: Int) => (1, 1)", "i1 = 1, i2 = 1"),
      ((i1, i2) => (0, i2), "(i1: Int, i2: Int) => (0, i2)", "i1 = 0, i2 = i2"),
      ((i1, i2) => (i2, 0), "(i1: Int, i2: Int) => (i2, 0)", "i1 = i2, i2 = 0"),
      ((i1, i2) => (0, i1), "(i1: Int, i2: Int) => (0, i1)", "i1 = 0, i2 = i1"),
      ((i1, i2) => (i1, 0), "(i1: Int, i2: Int) => (i1, 0)", "i1 = i1, i2 = 0"),
      ((i1, i2) => (0, 0), "(i1: Int, i2: Int) => (0, 0)", "i1 = 0, i2 = 0"),
      ((i1, i2) => (i1, i2), "(i1: Int, i2: Int) => (i1, i2)", "i1 = i1, i2 = i2"),
      ((i1, i2) => (i2, i1), "(i1: Int, i2: Int) => (i2, i1)", "i1 = i2, i2 = i1")
    )

    for {
      eachSet   <- leftSets
      setsCount <- SetsCol.setsCol
    } {
      for (eachMapping <- mapping) yield {
        val list = for {
          i1 <- eachSet.firstStart to 20
          i2 <- eachSet.secondStart to 20
        } yield {
          val (v1, v2) = eachMapping._1(i1, i2)
          val t1 =
            try setsCount.count(v1, v2)
            catch {
              case e: Throwable => Option.empty
            }
          s"$i1,$i2,${t1.getOrElse("unlimited")}"
        }
        if (eachSet.set == list.mkString("|")) {
          println(
            s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, ${eachMapping._3}, mappingKey: ${setsCount.key}"
          )
          countSets = countSets.appended(
            s"Tags.${setsCount.key}, ${eachMapping._2}",
            eachSet.firstStart,
            eachSet.secondStart,
            { (i1: Int, i2: Int) =>
              val (n1, n2) = eachMapping._1(i1, i2)
              setsCount.count(n1, n2)
            }
          )
        }
      }
    }

    countSets
      .map(s => (s, Try(for (i1 <- s._2 to 20; i2 <- s._3 to 20) yield (i1, i2, s._4(i1, i2))).toOption))
      .collect { case (a, Some(b)) => (a, b) }
      .groupBy(s => s._2.to(List))
      .map(_._2.head._1)
      .to(List)
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

    // Gen3.genRunner()

    // 可立刻替换的映射
    var count = 692
    for (each <- printlnSingleResult()) {
      println(s"Tags.Tag$count.firstart(${each._2}).secondStart(${each._3}).value(${each._1})")
      count += 1
    }

    /*println("互为逆运算的法：")
    val cols = SetsCol.setsCol
      .map(s => (s, for (i1 <- 1 to 20; i2 <- 1 to 20) yield s.count(i1, i2)))
      .groupBy(_._2.to(List))
      .map(_._2.head._1)
      .to(Vector)
    val setColToCount = Confirm(cols, SetsCol.setsCol).confirm
      .map(st => (for (i1 <- (1 to 20).to(List); i2 <- (1 to 20).to(List)) yield (st._1.count(i1, i2), st._2.count(i1, i2)), st))
      .groupBy(_._1)
      .to(Vector)
      .map(_._2.head._2)
    println(setColToCount.map(s => (s._1.key, s._2.key)).mkString("\n"))*/

    /*println(
      s"出现次数：加减法：(007, 030, 119) - (002, 226) == (${countTag(Tags.Tag007)}, ${countTag(Tags.Tag030)}, ${countTag(
        Tags.Tag119
      )}) - (${countTag(Tags.Tag002)}, ${countTag(Tags.Tag226)})"
    )
    println(s"出现次数：乘除法：084 - (045, 046) == ${countTag(Tags.Tag084)} - (${countTag(Tags.Tag045)}, ${countTag(Tags.Tag046)})")
    println(s"出现次数：第三法：067 - 040 == ${countTag(Tags.Tag067)} - ${countTag(Tags.Tag040)}")*/
  }

}
