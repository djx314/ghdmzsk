package f04

import scala.collection.mutable
import scala.util.Random

object Runner {

  // 一个结果集，他们之间两两的差异，无论何时，都存在为 0 和为 1 的可能性，但是不存在其他可能性，则这些结果集为候选结果集。
  /*def mergePlus1(l: List[(String, Int)]): List[(String, Int)] = {
    11
  }*/

  def main(arr: Array[String]): Unit = {
    val hashMap: mutable.HashMap[Set[String], Int] = mutable.HashMap.empty

    for (_ <- 1 to 10000) {
      var random1 = math.abs(Random.nextInt()) % 200
      if (random1 == 0) random1 += 1
      val result  = Counter.count(random1, random1, random1)
      val result1 = result.groupBy(_._2).map(s => s._2.map(_._1).to(Set))
      for (each <- result1) {
        hashMap.get(each) match {
          case Some(s) => hashMap.put(each, s + 1)
          case None    => hashMap.put(each, 1)
        }
      }
    }

    println(hashMap.to(List).sortWith((s, t) => s._2 > t._2).mkString("\n"))
  }

}
