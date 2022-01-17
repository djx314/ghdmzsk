package f04

import scala.collection.mutable
import scala.util.Random

object Runner {

  def main(arr: Array[String]): Unit = {
    val hashMap: mutable.HashMap[Set[String], Int] = mutable.HashMap.empty

    for (_ <- 1 to 10000) {
      var random1 = math.abs(Random.nextInt()) % 200
      if (random1 == 0) random1 += 1
      hashMap += ((Set("1213", "werwrwer"), 2))
    }

    println(hashMap.to(List).sortWith((s, t) => s._2 > t._2).mkString("\n"))
  }

}
