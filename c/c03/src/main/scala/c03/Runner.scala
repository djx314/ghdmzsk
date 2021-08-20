package c03

object Runner {
  val item01 = Item("01")
  val item02 = Item("02")
  val item03 = Item("03")
  val item04 = Item("04")
  val item05 = Item("05")
  val item06 = Item("06")
  val item07 = Item("07")
  val item08 = Item("08")
  val item09 = Item("09")
  val item10 = Item("10")
  val item11 = Item("11")
  val item12 = Item("12")
  val item13 = Item("13")
  val item14 = Item("14")

  lazy val number2Zero: Number2 = Number2T(() => number2Zero)
  lazy val number3Zero: Number3 = Number3T(() => number3Zero)
  lazy val number4Zero: Number4 = Number4T(() => number4Zero)
  lazy val number5Zero: Number5 = Number5T(() => number5Zero)

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number2S(() => Number2S(() => number2Zero, item01), item02)
      val number2 =
        Number3S(() => Number3S(() => Number3S(() => Number3S(() => Number3S(() => number3Zero, item03), item04), item05), item06), item07)
      val number3 =
        Number4S(() => Number4S(() => Number4S(() => Number4S(() => Number4S(() => number4Zero, item08), item09), item10), item11), item12)
      val number4 = Number5S(() => Number5S(() => number5Zero, item13), item14)
      val number5 =
        Number1S(
          Number1S(
            Number1S(
              Number1S(
                Number1S(
                  Number1S(
                    Number1S(
                      Number1S(
                        Number1S(
                          Number1S(Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04), item05),
                          item06
                        ),
                        item07
                      ),
                      item08
                    ),
                    item09
                  ),
                  item10
                ),
                item11
              ),
              item12
            ),
            item13
          ),
          item14
        )
      assert(number1.receive0(number2, number3, number4) == number5)
    }

  }
}
