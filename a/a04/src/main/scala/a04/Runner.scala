package a04

import a01._

object Runner {

  def genNumber1(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(genNumber1(n - 1, zero)) else zero
  def genNumber2(n: Int): Number2                   = if (n > 0) Number2S(genNumber2(n - 1)) else Number2T
  def countNumber1(number1: Number1): Int = number1 match {
    case Number1S(tail) => countNumber1(tail) + 1
    case Number1T(_)    => 0
  }
  def countNumber2(number2: Number2): Int = number2 match {
    case Number2S(tail) => countNumber2(tail) + 1
    case Number2T       => 0
  }

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      lazy val number1s: Number1 = Number1S(Number1S(Number1S(Number1S(number1t))))
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2: Number2       = Number2S(Number2S(Number2S(Number2T)))
      val number4                = count(number1t.method1(number2))
      assert(number4 == 12)
    }
    {
      lazy val number1: Number1 = Number1T(() => number1)
      val number2: Number2 = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))))))
      assert(number1.method1(number2) == Number3T)
    }
    {
      lazy val number1: Number1 = Number1T(() => number1)
      assert(number1.method1(Number2T) == Number3T)
    }
    {
      lazy val number1s: Number1 = Number1S(Number1S(Number1S(number1t)))
      lazy val number1t: Number1 = Number1T(() => number1s)
      assert(number1t.method1(Number2T) == Number3T)
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        lazy val number1s: Number1 = genNumber1(i1, number1t)
        lazy val number1t: Number1 = Number1T(() => number1s)
        val number2                = genNumber2(i2)
        val number3                = number1t.method1(number2)

        val count1 = countNumber1(number1s)
        val count2 = countNumber2(number2)
        val count3 = count(number3)

        val result2 = count1 * count2

        assert(count1 == i1)
        assert(count2 == i2)
        assert(count3 == result2)
      }
    }
  }
}
