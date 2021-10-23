package a01

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
      val number1 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)
      val number2 = Number2S(Number2S(Number2S(Number2S(Number2T, item04), item05), item06), item07)
      val number3 =
        Number1S(
          Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04), item05), item06),
          item07
        )
      assert(number2.method1(number1) == number3)
    }
    {
      val number1 = Number1T
      val number2 = Number2S(Number2S(Number2S(Number2S(Number2T, item01), item02), item03), item04)
      val number3 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
      assert(number2.method1(number1) == number3)
    }
    {
      val number1 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)
      val number2 = Number2T
      val number3 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)
      assert(number2.method1(number1) == number3)
    }
  }
}
