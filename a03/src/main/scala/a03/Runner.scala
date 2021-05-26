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

  val number1 = Number1S(Number1S(Number1T, item01), item02)
  val number2 = Number2S(Number2S(Number2S(Number2S(Number2S(Number2T, item03), item04), item05), item06), item07)
  val number3 = Number3S(Number3S(Number3S(Number3S(Number3S(Number3T, item08), item09), item10), item11), item12)

  val number4 = Number1S(Number1S(Number1T, item01), item02)
  val number5 = Number2S(Number2S(Number2S(Number2S(Number2S(Number2T, item03), item04), item05), item06), item07)
  val number6 = Number3S(Number3S(Number3S(Number3T, item10), item11), item12)
  val number7 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)

  val number8  = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
  val number9  = Number2S(Number2S(Number2S(Number2T, item05), item06), item07)
  val number10 = Number3S(Number3S(Number3S(Number3S(Number3S(Number3S(Number3T, item08), item09), item10), item11), item12), item13)
  val number11 = Number1S(Number1T, item01)

  val number12 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
  val number13 = Number2S(Number2S(Number2T, item05), item06)
  val number14 = Number3S(Number3S(Number3S(Number3S(Number3S(Number3S(Number3T, item07), item08), item09), item10), item11), item12)
  val number15 = Number1T

  val number16 = Number1S(Number1S(Number1T, item01), item02)
  val number17 = Number2S(Number2S(Number2T, item03), item04)
  val number18 =
    Number3S(Number3S(Number3S(Number3S(Number3S(Number3S(Number3S(Number3T, item05), item06), item07), item08), item09), item10), item11)
  val number19 = Number3S(Number3S(Number3S(Number3T, item05), item06), item07)

  def main(args: Array[String]): Unit = {
    assert(number2.method2(number1, number3) == number1)
    assert(number5.method2(number4, number6) == number7)
    assert(number9.method2(number8, number10) == number11)
    assert(number13.method2(number12, number14) == number15)
    assert(number17.method2(number16, number18) == number19)
  }
}
