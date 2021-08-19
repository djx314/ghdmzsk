package a03

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

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number2S(() => Number2S(() => Number2.zero, item01), item02)
      val number2 = Number3S(
        () => Number3S(() => Number3S(() => Number3S(() => Number3S(() => Number3T.zero, item03), item04), item05), item06),
        item07
      )
      val number3 = Number4S(
        () => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4T.zero, item08), item09), item10), item11),
        item12
      )
      val number4 = Number1S(Number1S(Number1T, item01), item02)
      assert(Counter.count(number1, number2, number3) == number4)
    }

    {
      val number1 = Number2S(() => Number2S(() => Number2.zero, item01), item02)
      val number2 = Number3S(
        () => Number3S(() => Number3S(() => Number3S(() => Number3S(() => Number3T.zero, item03), item04), item05), item06),
        item07
      )
      val number3 = Number4S(() => Number4S(() => Number4S(() => Number4T.zero, item10), item11), item12)
      val number4 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
      assert(Counter.count(number1, number2, number3) == number4)
    }

    {
      val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2.zero, item01), item02), item03), item04)
      val number2 = Number3S(() => Number3S(() => Number3S(() => Number3T.zero, item05), item06), item07)
      val number3 =
        Number4S(
          () =>
            Number4S(
              () => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4T.zero, item08), item09), item10), item11),
              item12
            ),
          item13
        )
      val number4 = Number1S(Number1T, item01)
      assert(Counter.count(number1, number2, number3) == number4)
    }

    {
      val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2.zero, item01), item02), item03), item04)
      val number2 =
        Number3S(() => Number3S(() => Number3T.zero, item05), item06)
      val number3 = Number4S(
        () =>
          Number4S(
            () => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4T.zero, item07), item08), item09), item10),
            item11
          ),
        item12
      )
      assert(Counter.count(number1, number2, number3) == Number1T)
    }

    {
      val number1 = Number2S(() => Number2S(() => Number2.zero, item01), item02)
      val number2 = Number3S(() => Number3S(() => Number3T.zero, item03), item04)
      val number3 =
        Number4S(
          () =>
            Number4S(
              () =>
                Number4S(
                  () => Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4T.zero, item05), item06), item07), item08),
                  item09
                ),
              item10
            ),
          item11
        )
      assert(Counter.count(number1, number2, number3) == Number1T)
    }

    {
      val number1 = Number2S(() => Number2S(() => Number2.zero, item01), item02)
      val number2 = Number3S(() => Number3S(() => Number3T.zero, item03), item04)
      val number3 = Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4T.zero, item05), item06), item07), item08)
      assert(Counter.count(number1, number2, number3) == Number1T)
    }
  }
}
