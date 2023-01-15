package f12
package do1

import f07._

object Runner {
  def main(arr: Array[String]): Unit = {
    var successCount: Int                          = 0
    var failedCount: List[(Int, Int, Option[Int])] = List.empty
    for {
      each <- CountSets.sumFuncAll
    } {
      if (Shi.sum.exists(t => if (t.isDefinedAt(each._1)) t(each._1._1, each._1._2) == each._2 else false))
        successCount += 1
      else
        failedCount = failedCount.appended(each._1._1, each._1._2, each._2)
    }

    println(s"successCount: $successCount, failedCount: ${failedCount.size}")
    println(failedCount.sortBy(s => (s._1, s._2)).mkString("\n"))
  }
}
