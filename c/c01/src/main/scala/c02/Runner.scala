package c02

object Runner {
  val item1  = Item("1")
  val item2  = Item("2")
  val item3  = Item("3")
  val item4  = Item("4")
  val item5  = Item("5")
  val item6  = Item("6")
  val item7  = Item("7")
  val item8  = Item("8")
  val item9  = Item("9")
  val item10 = Item("10")

  def main(args: Array[String]): Unit = {
    {
      val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => NumberToS(() => NumberToT, item1), item2), item3), item4)
      assert(number1.method1(NumberBeT) == NumberBeT)
    }
    {
      val number1: NumberBe = NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeT, item1), item2), item3), item4)
      val number2: NumberBe = NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeT, item1), item2), item3), item4)
      assert(NumberToT.method1(number1) == number2)
    }
    {
      val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => NumberToT, item5), item6), item7)
      val number2: NumberBe = NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeT, item1), item2), item3), item4)
      val number3: NumberBe = NumberBeS(NumberBeT, item1)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => NumberToT, item6), item7), item8)
      val number2: NumberBe = NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeT, item1), item2), item3), item4), item5)
      val number3: NumberBe = NumberBeS(NumberBeS(NumberBeT, item1), item2)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => NumberToS(() => NumberToT, item1), item2), item3), item4)
      val number2: NumberBe = NumberBeS(NumberBeS(NumberBeS(NumberBeT, item5), item6), item7)
      assert(number1.method1(number2) == NumberBeT)
    }
    {
      val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => NumberToS(() => NumberToT, item1), item2), item3), item4)
      val number2: NumberBe = NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeT, item5), item6), item7), item8)
      assert(number1.method1(number2) == NumberBeT)
    }
  }
}
