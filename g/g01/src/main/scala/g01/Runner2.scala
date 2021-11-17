package g01

object Runner2 {

  def count(number1: Number1): Int = number1 match {
    case Number1S(tail) => count(tail) + 1
    case Number1T       => 0
  }

  def 被加数_被减数_被乘数_被除数(n: Int): Number1            = if (n > 0) Number1S(被加数_被减数_被乘数_被除数(n - 1)) else Number1T
  def number2s(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(() => number2s(n - 1, zero)) else zero
  def number2t(n: Int, zero: => Number2): Number2 = if (n > 0) Number2T(() => number2t(n - 1, zero)) else zero

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = 被加数_被减数_被乘数_被除数(i1)
        val number2 = number2s(i2, Number2U)
        val number3 = number2.method2(number1)
        val count1  = count(number3)
        assert(count1 == i1 + i2)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = 被加数_被减数_被乘数_被除数(i1)
        val number2 = number2t(i2, Number2U)
        val number3 = number2.method2(number1)
        val count1  = count(number3)
        val count2  = if (i1 >= i2) i1 - i2 else 0
        assert(count1 == count2)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1                       = 被加数_被减数_被乘数_被除数(i1)
        lazy val number2Positive: Number2 = number2s(i2, number2Zero)
        lazy val number2Zero: Number2     = Number2T(() => number2Positive)

        {
          val number3 = number1.method1(number2Positive)
          val count1  = count(number3)
          assert(count1 == i1 * i2)
        }
        {
          val number3 = number2Zero.method2(number1)
          val count1  = count(number3)
          assert(count1 == i1 * i2)
        }
      }
    }
    {
      for {
        i1 <- 0 to 5
        i2 <- 1 to 20
        i3 <- 0 to i2 - 1
      } {
        val number1                       = 被加数_被减数_被乘数_被除数(i1 * i2 + i3)
        lazy val number2Positive: Number2 = number2t(i2, number2Zero)
        lazy val number2Zero: Number2     = Number2S(() => number2Positive)

        {
          val number3 = number1.method1(number2Zero)
          val count1  = count(number3)
          val count2  = if (i3 == 0) i1 else i1 + 1
          assert(count1 == count2)
        }
        {
          val number3 = number2Positive.method2(number1)
          val count1  = count(number3)
          assert(count1 == i1)
        }
      }
    }
  }

}
