package a02

object Runner {
  val item01 = Item(1)
  val item02 = Item(2)
  val item03 = Item(3)
  val item04 = Item(4)
  val item05 = Item(5)
  val item06 = Item(6)
  val item07 = Item(7)

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
  }
}
