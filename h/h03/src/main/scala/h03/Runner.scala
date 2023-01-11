package h03

import h03.Number.NumberPositive

object Runner {

  def count(number: () => Number): Int = number() match {
    case NumberPositive(tail) => count(tail) + 1
    case _                    => 0
  }

  def number1Gen(n: Int): Number                  = if (n > 0) Number.Number1Positive(() => number1Gen(n - 1)) else Number.Number1Zero
  def number2Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number2Positive(() => number2Gen(n - 1, zero)) else zero
  def number3Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number3Positive(() => number3Gen(n - 1, zero)) else zero

  def main(arr: Array[String]): Unit = {
    locally {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
        i3 <- 0 to 20
      } {
        val number1: Number              = number1Gen(i1)
        lazy val number2Positive: Number = number2Gen(i2, number2Zero)
        lazy val number2Zero: Number     = Number.Number2Zero(() => number2Positive)
        lazy val number3Positive: Number = number3Gen(i3, number3Zero)
        lazy val number3Zero: Number     = Number.Number3Zero(() => number3Positive)

        def r           = number1(() => number2Positive)(() => number3Positive)
        val result: Int = count(() => r)
        println(i1, i2, i3, i1 * i2 * i3, result)
        assert(i1 * i2 * i3 == result)
      }
    }
  }

}
