package b02

object Runner {
  val item1 = Item("1")
  val item2 = Item("2")
  val item3 = Item("3")
  val item4 = Item("4")

  def main(args: Array[String]): Unit = {
    {
      lazy val number1: Number1 = Number1S(Number1S(Number1S(Number1S(number2, item1), item2), item3), item4)
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
      lazy val number1: Number1 = Number1S(Number1S(Number1S(number2, item1), item2), item3)
      lazy val number2: Number1 = Number1T(() => number1)
      assert(number2.method1(Number2T).length == 0)
    }
  }
}
