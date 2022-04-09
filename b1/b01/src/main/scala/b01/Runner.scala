package b01

object Runner {
  val item01 = Item1(1)
  val item02 = Item1(2)
  val item03 = Item1(3)
  val item04 = Item1(4)
  val item05 = Item2(5)
  val item06 = Item2(6)
  val item07 = Item2(7)
  val item08 = Item2(8)
  val item09 = Item2(9)
  val item10 = Item2(10)

  def genNumber1(n: Int): Number1                   = if (n > 0) Number1S(genNumber1(n - 1), Item1(n)) else Number1T
  def genNumber2(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(genNumber2(n - 1, zero), Item2(n)) else zero
  def countNumber1(number1: Number1): Int = number1 match {
    case Number1S(tail, head) => countNumber1(tail) + 1
    case Number1T             => 0
  }
  def countNumber2(number2: Number2): Int = number2 match {
    case Number2S(tail, head) => countNumber2(tail) + 1
    case Number2T(tail)       => 0
  }

  def count(num: Number3): Int = num match {
    case Number3S(tail, _, _) => count(tail) + 1
    case Number3T             => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
      lazy val number2s: Number2 =
        Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(number2t, item05), item06), item07), item08), item09), item10)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number4                = count(number1.method1(number2s))
      assert(number4 == 24)
    }
    {
      val number1               = Number1S(Number1S(Number1S(Number1T, item01), item02), item02)
      lazy val number2: Number2 = Number2T(() => number2)
      assert(number1.method1(number2) == Number3T)
    }
    {
      lazy val number1: Number2 = Number2T(() => number1)
      assert(Number1T.method1(number1) == Number3T)
    }
    {
      lazy val number1: Number2 = Number2S(Number2S(number2, item05), item06)
      lazy val number2: Number2 = Number2T(() => number1)
      assert(Number1T.method1(number1) == Number3T)
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1                = genNumber1(i1)
        lazy val number2s: Number2 = genNumber2(i2, number2t)
        lazy val number2t: Number2 = Number2T(() => number2s)
        val number3                = number1.method1(number2s)
        val number4                = number1.method1(number2t)

        val count1 = countNumber1(number1)
        val count2 = countNumber2(number2s)
        val count3 = count(number3)
        val count4 = count(number4)

        val result1 = count1 * count2
        val result2 = if (count1 == 0) 0 else (count1 - 1) * count2

        assert(count1 == i1)
        assert(count2 == i2)
        assert(count3 == result1)
        assert(count4 == result2)
      }
    }
  }
}
