package b02

object Runner {
  val item1 = Item(1)
  val item2 = Item(2)
  val item3 = Item(3)
  val item4 = Item(4)

  def genNumber1(n: Int): Number1                   = if (n > 0) Number1S(genNumber1(n - 1)) else Number1T
  def genNumber2(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(genNumber2(n - 1, zero), Item(n)) else zero
  def countNumber1(number1: Number1): Int = number1 match {
    case Number1S(tail) => countNumber1(tail) + 1
    case Number1T       => 0
  }
  def countNumber2(number2: Number2): Int = number2 match {
    case Number2S(tail, head) => countNumber2(tail) + 1
    case Number2T(tail)       => 0
  }

  def count(num: Number3): Int = num match {
    case Number3S(tail, _) => count(tail) + 1
    case Number3T          => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1: Number1       = Number1S(Number1S(Number1S(Number1T)))
      lazy val number2s: Number2 = Number2S(Number2S(Number2S(Number2S(number2t, item1), item2), item3), item4)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number4                = count(number2t.method2(number1))
      assert(number4 == 12)
    }
    {
      val number1: Number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))))))
      lazy val number2: Number2 = Number2T(() => number2)
      assert(number2.method2(number1) == Number3T)
    }
    {
      lazy val number1: Number2 = Number2T(() => number1)
      assert(number1.method2(Number1T) == Number3T)
    }
    {
      lazy val number1: Number2 = Number2S(Number2S(Number2S(number2, item1), item2), item3)
      lazy val number2: Number2 = Number2T(() => number1)
      assert(number2.method2(Number1T) == Number3T)
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1                = genNumber1(i1)
        lazy val number2s: Number2 = genNumber2(i2, number2t)
        lazy val number2t: Number2 = Number2T(() => number2s)
        val number3                = number2s.method2(number1)
        val number4                = number2t.method2(number1)

        val count1 = countNumber1(number1)
        val count2 = countNumber2(number2s)
        val count3 = count(number3)
        val count4 = count(number4)

        val result1 = (count1 + 1) * count2
        val result2 = count1 * count2

        assert(count1 == i1)
        assert(count2 == i2)
        assert(count3 == result1)
        assert(count4 == result2)
      }
    }
  }
}
