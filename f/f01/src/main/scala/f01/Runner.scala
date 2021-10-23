package f01

import scala.collection.mutable

object Runner {

  def main(arr: Array[String]): Unit = {
    val hashMap: mutable.HashMap[Set[String], Int] = mutable.HashMap.empty

    for (i <- 0 to 9999) {
      val result  = Counter.count(i, i) ::: Counter.count(i + 1, i)
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
