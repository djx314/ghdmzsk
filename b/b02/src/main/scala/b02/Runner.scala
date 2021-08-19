package b02

object Runner {
  val item01 = Item("Item01")
  val item02 = Item("Item02")
  val item03 = Item("Item03")
  val item04 = Item("Item04")

  def main(args: Array[String]): Unit = {
    {
      lazy val number1: Number1 = Number1S(Number1S(Number1S(Number1S(number2, item01), item02), item03), item04)
      lazy val number2: Number1 = Number1T(() => number1)
      val number3: Number2      = Number2S(Number2S(Number2S(Number2T)))
      assert(number2.method1(number3).length == 12)
    }
    {
      lazy val number1: Number1 = Number1T(() => number1)
      val number2: Number2 = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))))))
      assert(number1.method1(number2).length == 0)
    }
    {
      lazy val number1: Number1 = Number1T(() => number1)
      assert(number1.method1(Number2T).length == 0)
    }
    {
      lazy val number1: Number1 = Number1S(Number1S(Number1S(number2, item01), item02), item03)
      lazy val number2: Number1 = Number1T(() => number1)
      assert(number2.method1(Number2T).length == 0)
    }
  }
}
