package f07


import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.concurrent.duration.Duration
import scala.concurrent.{blocking, Await, ExecutionContext, Future}
import scala.util.{Try, Using}
import ExecutionContext.Implicits.global

object Runner {

  val path = Paths.get(".", "f", "f07", "src", "main", "codegen", "f08")

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

  def printlnSingleResult(): Future[List[(String, Int, Int, (Int, Int) => Option[Int])]] = {
    val sets                                                           = col.map(s => (s._1, s._2.mkString("|")))
    val leftSets: List[CountSet]                                       = CountSets.sum.filter(s => sets.forall(t => t._2 != s.set))
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
      ((i1, i2) => (i2, i1), "(i1: Int, i2: Int) => (i2, i1)", "i1 = i2, i2 = i1"),
      ((i1, i2) => (i1, i2 + 1), "(i1: Int, i2: Int) => (i1, i2 + 1)", "i1 = i1, i2 = i2 + 1"),
      ((i1, i2) => (i1 + 1, i2), "(i1: Int, i2: Int) => (i1 + 1, i2)", "i1 = i1 + 1, i2 = i2"),
      ((i1, i2) => (i2, i1 + 1), "(i1: Int, i2: Int) => (i2, i1 + 1)", "i1 = i2, i2 = i1 + 1"),
      ((i1, i2) => (i2 + 1, i1), "(i1: Int, i2: Int) => (i2 + 1, i1)", "i1 = i2 + 1, i2 = i1"),
      ((i1, i2) => (i1 + 1, i2 + 1), "(i1: Int, i2: Int) => (i1 + 1, i2 + 1)", "i1 = i1 + 1, i2 = i2 + 1"),
      ((i1, i2) => (i2 + 1, i1 + 1), "(i1: Int, i2: Int) => (i2 + 1, i1 + 1)", "i1 = i2 + 1, i2 = i1 + 1"),
      ((i1, i2) => (i1 + i2, i2), "(i1: Int, i2: Int) => (i1 + i2, i2)", "i1 = i1 + i2, i2 = i2"),
      ((i1, i2) => (i1, i1 + i2), "(i1: Int, i2: Int) => (i1, i1 + i2)", "i1 = i1, i2 = i1 + i2"),
      ((i1, i2) => (i1 + i2, i1 + i2), "(i1: Int, i2: Int) => (i1 + i2, i1 + i2)", "i1 = i1 + i2, i2 = i1 + i2"),
      ((i1, i2) => (i1 * 2, i2), "(i1: Int, i2: Int) => (i1 * 2, i2)", "i1 = i1 * 2, i2"),
      ((i1, i2) => (i1, i2 * 2), "(i1: Int, i2: Int) => (i1, i2 * 2)", "i1 = i1, i2 = i2 * 2"),
      ((i1, i2) => (i1 * 2, i2 * 2), "(i1: Int, i2: Int) => (i1 * 2, i2 * 2)", "i1 = i1 * 2, i2 = i2 * 2"),
      ((i1, i2) => (i2 * 2, i1), "(i1: Int, i2: Int) => (i2 * 2, i1)", "i1 = i2 * 2, i1"),
      ((i1, i2) => (i2, i1 * 2), "(i1: Int, i2: Int) => (i2, i1 * 2)", "i1 = i2, i2 = i1 * 2"),
      ((i1, i2) => (i2 * 2, i1 * 2), "(i1: Int, i2: Int) => (i2 * 2, i1 * 2)", "i1 = i2 * 2, i2 = i1 * 2")
    )

    /*val futureSeq = for (eachSet1 <- leftSets.grouped(leftSets.size / 20)) yield Future {
      blocking {
        for {
          eachSet     <- eachSet1
          setsCount   <- SetsCol.setsCol
          eachMapping <- mapping
        } yield {
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
            /*countSets = countSets.appended(
            s"Tags.${setsCount.key}, ${eachMapping._2}",
            eachSet.firstStart,
            eachSet.secondStart,
            { (i1: Int, i2: Int) =>
              val (n1, n2) = eachMapping._1(i1, i2)
              setsCount.count(n1, n2)
            }
          )*/
            Option(
              (
                s"Tags.${setsCount.key}, ${eachMapping._2}",
                eachSet.firstStart,
                eachSet.secondStart,
                { (i1: Int, i2: Int) =>
                  val (n1, n2) = eachMapping._1(i1, i2)
                  setsCount.count(n1, n2)
                }
              )
            )
          } else Option.empty
        }
      }
    }

    val exec = for (i <- Future.sequence(futureSeq)) yield i.to(List).flatten.collect { case Some(s) => s }

    for (countS <- exec) yield {
      countS
        .map(s => (s, Try(for (i1 <- s._2 to 20; i2 <- s._3 to 20) yield (i1, i2, s._4(i1, i2))).toOption))
        .collect { case (a, Some(b)) => (a, b) }
        .groupBy(s => s._2.to(List))
        .map(_._2.head._1)
        .to(List)
    }
    /*countSets
      .map(s => (s, Try(for (i1 <- s._2 to 20; i2 <- s._3 to 20) yield (i1, i2, s._4(i1, i2))).toOption))
      .collect { case (a, Some(b)) => (a, b) }
      .groupBy(s => s._2.to(List))
      .map(_._2.head._1)
      .to(List)*/
  }*/

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

  var count = SetsCol.setsCol.size + 1
  def getCount: Int = {
    this.synchronized {
      val c = count
      count += 1
      c
    }
  }

  def main(arr: Array[String]): Unit = {
    println(s"重复的映射：${distinctRunner().sortBy(_(1)).mkString("\n")}")
    println(s"结果集总数：${CountSets.sum.size}")
    println(s"映射结果总数：${SetsCol.setsCol.size}")
    println(s"未映射结果集数量：${setsLeftover().size}")
    println(s"无效的映射 key：${colLeftover()}")
    println(s"重复的映射 key：${SetsCol.setsCol.map(_.key).groupBy(identity).filter(_._2.size > 1).map(_._1)}")

    // Gen1.genSetsRunner()

    // Gen3.genRunner()

    /*def e = Future { blocking { Gen6.printlnSingleResult() } }
    def d = Future { blocking { Gen5.printlnSingleResult() } }

    def a = Future {
      blocking {
        Gen4.printlnSingleResult()
      }
    }.flatten

    // 可立刻替换的映射
    def b = Future {
      blocking {
        for (each1 <- printlnSingleResult()) yield {
          for (each <- each1) {
            println(s"Tags.Tag${Runner.getCount}.firstart(${each._2}).secondStart(${each._3}).value(${each._1})")
          }
        }
      }
    }.flatten*/

    def c = Future {
      blocking {
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
        println("互为逆运算的法：")
        println(setColToCount.map(s => (s._1.key, s._2.key)).mkString("\n"))
      }
    }

    def action2 = {
      val a1 = a.map(_ => println("任务 a 完成"))
      val b1 = b.map(_ => println("任务 b 完成"))
      val c1 = c.map(_ => println("任务 c 完成"))
      // val d1 = d.map(_ => println("任务 d 完成"))
      for {
        _ <- a1
        _ <- b1
        _ <- c1
        // _ <- d1
      } yield 1
    }

    // Await.result(action2, Duration.Inf)

    // Await.result(d.map(_ => println("任务 d 完成")), Duration.Inf)

    // Await.result(a.map(_ => println("任务 a 完成")), Duration.Inf)

    // Await.result(e.map(_ => println("任务 e 完成")), Duration.Inf)

    /*println(
      s"出现次数：加减法：(007, 030, 119) - (002, 226) == (${countTag(Tags.Tag007)}, ${countTag(Tags.Tag030)}, ${countTag(
        Tags.Tag119
      )}) - (${countTag(Tags.Tag002)}, ${countTag(Tags.Tag226)})"
    )
    println(s"出现次数：乘除法：084 - (045, 046) == ${countTag(Tags.Tag084)} - (${countTag(Tags.Tag045)}, ${countTag(Tags.Tag046)})")
    println(s"出现次数：第三法：067 - 040 == ${countTag(Tags.Tag067)} - ${countTag(Tags.Tag040)}")*/
  }

}
