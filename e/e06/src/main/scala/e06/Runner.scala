package e06

import e01._

object Runner {
  def genNumberS(i1: Int, zero: => Number[Item1]): Number[Item1] = if (i1 > 0) NumberS(() => genNumberS(i1 - 1, zero), new Item1) else zero
  def genNumberT(i1: Int, zero: => Number[Item1]): Number[Item1] = if (i1 > 0) NumberT(() => genNumberT(i1 - 1, zero)) else zero
  lazy val numbersZero: Number[Item1]                            = NumberS(() => numbersZero, new Item1)
  def count(collect: Collect): Int = collect match {
    case CollectS(tail) => count(tail) + 1
    case CollectT       => 0
  }

  def main(args: Array[String]): Unit = {
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
      i3 <- 0 to 20
    } {
      val list                        = genNumberS(i1, genNumberT(i2, numbersZero))
      lazy val numbers: Number[Item1] = genNumberS(i3, numbert)
      lazy val numbert: Number[Item1] = NumberT(() => numbers)
      val result1 =
        try {
          val temp1 = list.execute(new ListMutiplyContext)(numbers)
          Option(count(temp1))
        } catch {
          case _: StackOverflowError => Option.empty
        }
      val temp2   = i1 * i3
      val temp3   = if (i3 == 0) Option.empty else if (i2 % i3 == 0) Option(i2 / i3 - 1) else Option(i2 / i3)
      val result2 = for (t3 <- temp3) yield temp2 + t3
      assert(result1 == result2)
    }
  }
}
