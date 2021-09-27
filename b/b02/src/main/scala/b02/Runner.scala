package b02

object Runner {
  val item1 = Item("1")
  val item2 = Item("2")
  val item3 = Item("3")
  val item4 = Item("4")

  def count(num: Number3): Int = num match {
    case Number3S(tail, _) => count(tail) + 1
    case Number3T          => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1: Number1      = Number1S(Number1S(Number1S(Number1T)))
      lazy val number2: Number2 = Number2S(Number2S(Number2S(Number2S(number3, item1), item2), item3), item4)
      lazy val number3: Number2 = Number2T(() => number2)
      val number4               = count(number3.method2(number1))
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
  }
}
