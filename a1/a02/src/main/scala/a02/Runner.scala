package a02

import a01._

object Runner {

  lazy val number1ZeroPre: Number1 = Number1R(() => number1ZeroPre)
  lazy val number1Zero: Number1    = Number1T(number1ZeroPre)

  def genNumber1(n: Int): Number1 = if (n > 0) Number1S(genNumber1(n - 1)) else number1Zero
  def genNumber2(n: Int): Number2 = if (n > 0) Number2S(genNumber2(n - 1)) else Number2T
  def countNumber1(number1: Number1): Int = number1 match {
    case Number1S(tail) => countNumber1(tail) + 1
    case Number1T(_)    => 0
  }
  def countNumber2(number2: Number2): Int = number2 match {
    case Number2S(tail) => countNumber2(tail) + 1
    case Number2T       => 0
  }
  def countNumber3(number3: Number3): Int = number3 match {
    case Number3S(tail) => countNumber3(tail) + 1
    case Number3T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number1S(Number1S(number1Zero))
      val number2 = Number2S(Number2S(Number2S(Number2S(Number2S(Number2T)))))
      val number3 = Number3S(Number3S(Number3S(Number3T)))
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(number1Zero))))
      val number2 = Number2S(Number2T)
      val number3 = Number3T
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = Number1S(Number1S(Number1S(number1Zero)))
      val number2 = Number2S(Number2S(Number2S(Number2T)))
      val number3 = Number3T
      assert(number1.method1(number2) == number3)
    }
    {
      assert(Number2T.method2(number1Zero) == Number3T)
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = genNumber1(i1)
        val number2 = genNumber2(i2)
        val number3 = number1.method1(number2)

        val count1 = countNumber1(number1)
        val count2 = countNumber2(number2)
        val count3 = countNumber3(number3)

        val result1 = if (count2 - count1 >= 0) count2 - count1 else 0

        assert(count1 == i1)
        assert(count2 == i2)
        assert(count3 == result1)
      }
    }
  }
}
