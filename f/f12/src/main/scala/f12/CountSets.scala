package f12

import f07._

object CountSets {
  val sum: List[CountSet] = CountSetsImpl.sum
  val sumFuncAll          = for (t1 <- sum; t2 <- t1.toMap.to(List)) yield t2
}
