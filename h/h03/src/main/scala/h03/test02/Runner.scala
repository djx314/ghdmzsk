package h03.test02

import h03.test02.Number.NumberPositive

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
        i1 <- 0 to 200
        i2 <- 1 to 8
        i3 <- 1 to 8
      } {
        val number1: Number              = number1Gen(i1)
        lazy val number2Positive: Number = number2Gen(i2, number2Zero)
        lazy val number2Zero: Number     = Number.Number2Zero(() => number2Positive)
        lazy val number3Positive: Number = number3Gen(i3, number3Zero)
        lazy val number3Zero: Number     = Number.Number3Zero(() => number3Positive)

        def r1            = number2Positive(() => number3Positive)(() => number1)
        def r2            = number3Positive(() => number1)(() => number2Positive)
        val result1: Int  = count(() => r1)
        val result2: Int  = count(() => r2)
        val countInt: Int = i1 / (i2 * i3)
        println(i1, i2, i3, countInt, result1, countInt == result1)
        assert(countInt == result1)
        assert(countInt == result2)
      }
    }
  }

}
