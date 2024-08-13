package h03.test03

import h03.test03.Number.NumberPositive

object Runner {

  def count(number: () => Number): Option[Int] =
    try
      number() match {
        case NumberPositive(tail) => for (nextInt <- count(tail)) yield nextInt + 1
        case _                    => Some(0)
      }
    catch {
      case _: StackOverflowError => None
    }

  def number1Gen(n: Int): Number                  = if (n > 0) Number.Number1Positive(() => number1Gen(n - 1)) else Number.Number1Zero
  def number2Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number2Positive(() => number2Gen(n - 1, zero)) else zero
  def number3Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number3Positive(() => number3Gen(n - 1, zero)) else zero
  def number4Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number4Positive(() => number4Gen(n - 1, zero)) else zero

  def main(arr: Array[String]): Unit = {
    locally {
      for {
        i1 <- 0 to 20
      } {
        for {
          i2 <- 0 to 20
          i3 <- 0 to 20
          i4 <- 0 to 20
        } {
          val number1: Number              = number1Gen(i1)
          lazy val number2Positive: Number = number2Gen(i2, number2Zero)
          lazy val number2Zero: Number     = Number.Number2Zero(() => number2Positive)
          lazy val number3Positive: Number = number3Gen(i3, number3Zero)
          lazy val number3Zero: Number     = Number.Number3Zero(() => number3Positive)
          lazy val number4Positive: Number = number4Gen(i4, number4Zero)
          lazy val number4Zero: Number     = Number.Number4Zero(() => number4Positive)

          def r1 = number1(() => number2Positive)(() => number3Positive)(() => number4Positive)

          val result1: Option[Int] = count(() => r1)
          val compare1             = i1 * i2 * i3 * i4

          // println(i1, i2, i3, i4, result1, compare1)
          assert(result1 == Some(compare1))
        }
        println(s"$i1 successed.")
      }
    }

  }

}
