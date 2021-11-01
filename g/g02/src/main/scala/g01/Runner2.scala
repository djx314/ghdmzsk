package g01

object Runner2 {

  def count(number1: Number1): Int = number1 match {
    case Number1S(tail) => count(tail()) + 1
    case Number1U       => 0
  }

  def 被加数_被减数_被乘数_被除数(n: Int): Number1 = {
    val tail = () => 被加数_被减数_被乘数_被除数(n - 1)
    if (n > 0) Number1S(tail) else Number1U
  }
  def 加数(n: Int): Number1 = {
    val tail = () => 加数(n - 1)
    if (n > 0) Number1T(tail) else Number1V
  }
  def 减数(n: Int): Number1 = {
    val tail = () => 减数(n - 1)
    if (n > 0) Number1S(tail) else Number1V
  }
  def 乘数_除数(nt: Int, ns: Int): (Number1, Number1) = {
    def gent(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => gent(n - 1, zero)) else zero
    def gens(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => gens(n - 1, zero)) else zero
    lazy val number1t: Number1                  = gent(nt, number1s)
    lazy val number1s: Number1                  = gens(ns, number1t)
    (number1t, number1s)
  }

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = 被加数_被减数_被乘数_被除数(i1)
        val number2 = 加数(i2)
        val number3 = number2.method1(number1)
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
        val number2 = 减数(i2)
        val number3 = number2.method1(number1)
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
        val number1                        = 被加数_被减数_被乘数_被除数(i1)
        val (number2Positive, number2Zero) = 乘数_除数(i2, 1)

        {
          val number3 = number1.method1(number2Positive)
          val count1  = count(number3)
          assert(count1 == i1 * i2)
        }
        {
          val number3 = number2Zero.method1(number1)
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
        val number1                        = 被加数_被减数_被乘数_被除数(i1 * i2 + i3)
        val (number2Zero, number2Positive) = 乘数_除数(1, i2)

        {
          val number3 = number1.method1(number2Zero)
          val count1  = count(number3)
          val count2  = if (i3 == 0) i1 else i1 + 1
          assert(count1 == count2)
        }
        {
          val number3 = number2Positive.method1(number1)
          val count1  = count(number3)
          assert(count1 == i1)
        }
      }
    }
  }

}
