package f01

import scala.collection.mutable
import scala.util.Random

object Runner {

  def main(arr: Array[String]): Unit = {
    val hashMap: mutable.HashMap[List[String], Int] = mutable.HashMap.empty

    for (_ <- 1 to 10000) {
      var random1 = math.abs(Random.nextInt()) % 200
      var random2 = math.abs(Random.nextInt()) % 80
      if (random1 == 0) random1 += 1
      if (random2 == 0) random2 += 1
      val result  = Counter.count(random1, random2)
      val convert = Counter.convert(result)
      for (each <- convert) {
        def keys    = each.map(_._1)
        val values  = each.map(_._2)
        val confirm = values.tail.foldLeft((values.head, true))((i1, i2) => if (i1._2) (i2, i2 >= i1._1) else (i2, false))
        if (confirm._2) {
          hashMap.get(keys) match {
            case Some(s) => hashMap.put(keys, s + 1)
            case None    => hashMap.put(keys, 1)
          }
        }
      }
    }

    println(hashMap.mkString("\n"))
  }

}
