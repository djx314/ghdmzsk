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

  val number1 = Number2S(Number2S(Number2T, item01), item02)
  val number2 = Number3S(Number3S(Number3S(Number3S(Number3S(Number3T.zero, item03), item04), item05), item06), item07)
  val number3 = Number4S(Number4S(Number4S(Number4S(Number4S(Number4T.zero, item08), item09), item10), item11), item12)
  val number4 = Number1S(Number1S(Number1T, item01), item02)

  val number5 = Number2S(Number2S(Number2T, item01), item02)
  val number6 = Number3S(Number3S(Number3S(Number3S(Number3S(Number3T.zero, item03), item04), item05), item06), item07)
  val number7 = Number4S(Number4S(Number4S(Number4T.zero, item10), item11), item12)
  val number8 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)

  /** val number8 = Number2S(Number2S(Number2S(Number2S(Number2T, item01), item02), item03), item04) val number9 =
    * Number3S(Number3S(Number3S(Number3T, item05), item06), item07) val number10 =
    * Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4T, item08), item09), item10), item11), item12), item13) val number11 =
    * Number2S(Number2T, item01)
    *
    * val number12 = Number2S(Number2S(Number2S(Number2S(Number2T, item01), item02), item03), item04) val number13 =
    * Number3S(Number3S(Number3T, item05), item06) val number14 = Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4T, item07),
    * item08), item09), item10), item11), item12) val number15 = Number2T
    *
    * val number16 = Number2S(Number2S(Number2T, item01), item02) val number17 = Number3S(Number3S(Number3T, item03), item04) val number18 =
    * Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4T, item05), item06), item07), item08), item09), item10), item11)
    * val number19 = Number2T
    *
    * val number20 = Number2S(Number2S(Number2T, item01), item02) val number21 = Number3S(Number3S(Number3T, item03), item04) val number22 =
    * Number4S(Number4S(Number4S(Number4S(Number4T, item05), item06), item07), item08)
    */

  def main(args: Array[String]): Unit = {
    assert(Counter.count(number1, number2, number3) == number4)
    println(Counter.count(number5, number6, number7))
    /*assert(Counter.count(number4, number5, number6) == number7)
    assert(Counter.count(number8, number9, number10) == number11)
    assert(Counter.count(number12, number13, number14) == number15)
    assert(Counter.count(number16, number17, number18) == number19)
    assert(Counter.count(number20, number21, number22) == Number2T)*/
  }
}
