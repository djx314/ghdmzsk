package c02

object Runner {
  val item01 = Item("Item01")
  val item02 = Item("Item02")
  val item03 = Item("Item03")
  val item04 = Item("Item04")
  val item05 = Item("Item05")
  val item06 = Item("Item06")
  val item07 = Item("Item07")
  val item08 = Item("Item08")
  val item09 = Item("Item09")
  val item10 = Item("Item10")
  val item11 = Item("Item11")
  val item12 = Item("Item12")
  val item13 = Item("Item13")

  lazy val number2Zero: Number2 = Number2T(() => number2Zero)
  lazy val number3Zero: Number3 = Number3T(() => number3Zero)
  lazy val number4Zero: Number4 = Number4T(() => number4Zero)

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number2S(() => Number2S(() => number2Zero, item01), item02)
      val number2 =
        Number3S(() => Number3S(() => Number3S(() => Number3S(() => Number3S(() => number3Zero, item03), item04), item05), item06), item07)
      val number3 = Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => number4Zero)))))
      val number4 = Number1S(Number1S(Number1T, item01), item02)
      assert(number1.receive0(number2, number3) == number4)
    }

    {
      val number1 = Number2S(() => Number2S(() => number2Zero, item01), item02)
      val number2 =
        Number3S(() => Number3S(() => Number3S(() => Number3S(() => Number3S(() => number3Zero, item03), item04), item05), item06), item07)
      val number3 = Number4S(() => Number4S(() => Number4S(() => number4Zero)))
      val number4 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
      assert(number1.receive0(number2, number3) == number4)
    }

    {
      val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04)
      val number2 = Number3S(() => Number3S(() => Number3S(() => number3Zero, item05), item06), item07)
      val number3 = Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => number4Zero))))))
      val number4 = Number1S(Number1T, item01)
      assert(number1.receive0(number2, number3) == number4)
    }

    {
      val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04)
      val number2 = Number3S(() => Number3S(() => number3Zero, item05), item06)
      val number3 = Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => number4Zero))))))
      assert(number1.receive0(number2, number3) == Number1T)
    }

    {
      val number1 = Number2S(() => Number2S(() => number2Zero, item01), item02)
      val number2 = Number3S(() => Number3S(() => number3Zero, item03), item04)
      val number3 =
        Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => number4Zero)))))))
      assert(number1.receive0(number2, number3) == Number1T)
    }

    {
      val number1 = Number2S(() => Number2S(() => number2Zero, item01), item02)
      val number2 = Number3S(() => Number3S(() => number3Zero, item03), item04)
      val number3 = Number4S(() => Number4S(() => Number4S(() => Number4S(() => number4Zero))))
      assert(number1.receive0(number2, number3) == Number1T)
    }
  }
}
