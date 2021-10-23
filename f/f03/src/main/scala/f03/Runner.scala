package f03

import scala.collection.mutable
import scala.util.Random

object Runner {

  def main(arr: Array[String]): Unit = {
    val hashMap: mutable.HashMap[Set[String], Int] = mutable.HashMap.empty

    for (_ <- 1 to 10000) {
      var random1 = math.abs(Random.nextInt()) % 2000
      if (random1 == 0) random1 += 1
      val result  = Counter.count(random1, random1)
      val result1 = result.groupBy(_._2).map(s => s._2.map(_._1).to(Set))
      for (each <- result1) {
        hashMap.get(each) match {
          case Some(s) => hashMap.put(each, s + 1)
          case None    => hashMap.put(each, 1)
        }
      }
    }

    println(hashMap.mkString("\n"))
  }

}
