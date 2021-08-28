package c04

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
  val item11 = Item("11")
  val item12 = Item("12")

  def main(args: Array[String]): Unit = {
    {
      lazy val number1: NumberTo = NumberToS(() => NumberToS(() => number2, item1), item2)
      lazy val number2: NumberTo = NumberToT(() => number1)
      val number3: NumberBe      = NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeT, item3), item4), item5), item6)
      val number4: Result = ResultS(
        ResultS(ResultS(ResultS(ResultS(ResultS(ResultS(ResultS(ResultT, item1), item2), item1), item2), item1), item2), item1),
        item2
      )
      assert(number2.method1(number3) == number4)
    }
    {
      lazy val number1: NumberTo = NumberToS(() => NumberToS(() => NumberToS(() => NumberToS(() => number2, item1), item2), item3), item4)
      lazy val number2: NumberTo = NumberToT(() => number1)
      val number3: NumberBe      = NumberBeS(NumberBeS(NumberBeS(NumberBeS(NumberBeT, item5), item6), item7), item8)
      val number4: Result = ResultS(
        ResultS(
          ResultS(
            ResultS(
              ResultS(
                ResultS(
                  ResultS(
                    ResultS(
                      ResultS(
                        ResultS(
                          ResultS(ResultS(ResultS(ResultS(ResultS(ResultS(ResultT, item1), item2), item3), item4), item1), item2),
                          item3
                        ),
                        item4
                      ),
                      item1
                    ),
                    item2
                  ),
                  item3
                ),
                item4
              ),
              item1
            ),
            item2
          ),
          item3
        ),
        item4
      )
      assert(number2.method1(number3) == number4)
    }
  }
}
