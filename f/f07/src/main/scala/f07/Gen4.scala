package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.{Try, Using}

object Gen4 {

  def printlnSingleResult(): Unit = {
    val sets                                                           = Runner.col.map(s => (s._1, s._2.mkString("|")))
    val leftSets: List[CountSet]                                       = CountSets.sum.filter(s => sets.forall(t => t._2 != s.set))
    var countSets: List[(String, Int, Int, (Int, Int) => Option[Int])] = List.empty

    val mapping: List[(Option[Int] => Option[Int], String)] = List(
      /*(i1 => i1.map(b => 1), "(i1: Option[Int]) => i1.map(b => 1)"),
      (i1 => i1.map(b => 0), "(i1: Option[Int]) => i1.map(b => 0)"),*/
      (i1 => i1.map(b => b + 1), "(i1: Option[Int]) => i1.map(b => b + 1)"),
      (i1 => i1.map(b => b - 1).filter(_ >= 0), "(i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0)"),
      (i1 => i1.map(b => b * 2), "(i1: Option[Int]) => i1.map(b => b * 2)"),
      (i1 => i1.map(b => b / 2), "(i1: Option[Int]) => i1.map(b => b / 2)")
    )

    for {
      eachSet     <- leftSets
      setsCount   <- SetsCol.setsCol
      eachMapping <- mapping
    } {
      val list = for {
        i1 <- eachSet.firstStart to 20
        i2 <- eachSet.secondStart to 20
      } yield {
        val t1 =
          try eachMapping._1(setsCount.count(i1, i2))
          catch {
            case e: Throwable => Option.empty
          }
        s"$i1,$i2,${t1.getOrElse("unlimited")}"
      }
      if (eachSet.set == list.mkString("|")) {
        /*println(
          s"可立刻替换的映射：firstStart:${eachSet.firstStart}, secondStart: ${eachSet.secondStart}, ${eachMapping._2}, mappingKey: ${setsCount.key}"
        )*/
        countSets = countSets.appended(
          s"Tags.${setsCount.key}, ${eachMapping._2}",
          eachSet.firstStart,
          eachSet.secondStart,
          { (i1: Int, i2: Int) =>
            eachMapping._1(setsCount.count(i1, i2))
          }
        )
      }
    }

    val output = countSets
      .map(s => (s, Try(for (i1 <- s._2 to 20; i2 <- s._3 to 20) yield (i1, i2, s._4(i1, i2))).toOption))
      .collect { case (a, Some(b)) => (a, b) }
      .groupBy(s => s._2.to(List))
      .map(_._2.head._1)
      .to(List)

    var count = SetsCol.setsCol.size + 1
    for (each <- output) {
      println(s"Tags.Tag$count.firstart(${each._2}).secondStart(${each._3}).mapResult(${each._1})")
      count += 1
    }
  }

}
