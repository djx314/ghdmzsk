package a02

object Runner {
  val item01 = Item(1)
  val item02 = Item(2)
  val item03 = Item(3)
  val item04 = Item(4)
  val item05 = Item(5)
  val item06 = Item(6)
  val item07 = Item(7)

  def genNumber1(n: Int): Number1 = if (n > 0) Number1S(genNumber1(n - 1), Item(n)) else Number1T
  def genNumber2(n: Int): Number2 = if (n > 0) Number2S(genNumber2(n - 1), Item(n)) else Number2T
  def countNumber1(number1: Number1): Int = number1 match {
    case Number1S(tail, head) => countNumber1(tail) + 1
    case Number1T             => 0
  }
  def countNumber2(number2: Number2): Int = number2 match {
    case Number2S(tail, head) => countNumber2(tail) + 1
    case Number2T             => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04), item05)
      val number2 = Number2S(Number2S(Number2T, item06), item07)
      val number3 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)
      assert(number2.method2(number1) == number3)
    }
    {
      val number1 = Number1S(Number1T, item01)
      val number2 = Number2S(Number2S(Number2S(Number2S(Number2T, item02), item03), item04), item05)
      val number3 = Number1T
      assert(number2.method2(number1) == number3)
    }
    {
      val number1 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)
      val number2 = Number2S(Number2S(Number2S(Number2T, item04), item05), item06)
      val number3 = Number1T
      assert(number2.method2(number1) == number3)
    }
    {
      assert(Number2T.method2(Number1T) == Number1T)
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = genNumber1(i1)
        val number2 = genNumber2(i2)
        val number3 = number2.method2(number1)
        val number4 = number1.method1(number2, Item(0))

        val count1 = countNumber1(number1)
        val count2 = countNumber2(number2)
        val count3 = countNumber1(number3)
        val count4 = countNumber1(number4)

        val result1 = if (count1 - count2 >= 0) count1 - count2 else 0
        val result2 = if (count1 - count2 - 1 >= 0) count1 - count2 - 1 else 0

        assert(count1 == i1)
        assert(count2 == i2)
        assert(count3 == result1)
        assert(count4 == result2)
      }
    }
  }
}
