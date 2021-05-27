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

  val Number2 = Number2S(Number2S(Number2T, item01), item02)
  val Number3 = Number3S(Number3S(Number3S(Number3S(Number3S(Number3T, item03), item04), item05), item06), item07)
  val Number4 = Number4S(Number4S(Number4S(Number4S(Number4S(Number4T, item08), item09), item10), item11), item12)

  val number4 = Number2S(Number2S(Number2T, item01), item02)
  val number5 = Number3S(Number3S(Number3S(Number3S(Number3S(Number3T, item03), item04), item05), item06), item07)
  val number6 = Number4S(Number4S(Number4S(Number4T, item10), item11), item12)
  val number7 = Number2S(Number2S(Number2S(Number2S(Number2T, item01), item02), item03), item04)

  val number8  = Number2S(Number2S(Number2S(Number2S(Number2T, item01), item02), item03), item04)
  val number9  = Number3S(Number3S(Number3S(Number3T, item05), item06), item07)
  val Number20 = Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4T, item08), item09), item10), item11), item12), item13)
  val Number21 = Number2S(Number2T, item01)

  val Number22 = Number2S(Number2S(Number2S(Number2S(Number2T, item01), item02), item03), item04)
  val Number23 = Number3S(Number3S(Number3T, item05), item06)
  val Number24 = Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4T, item07), item08), item09), item10), item11), item12)
  val Number25 = Number2T

  val Number26 = Number2S(Number2S(Number2T, item01), item02)
  val Number27 = Number3S(Number3S(Number3T, item03), item04)
  val Number28 =
    Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4S(Number4T, item05), item06), item07), item08), item09), item10), item11)
  val Number29 = Number4S(Number4S(Number4S(Number4T, item05), item06), item07)

  val Number30 = Number2S(Number2S(Number2T, item01), item02)
  val Number31 = Number3S(Number3S(Number3T, item03), item04)
  val Number32 = Number4S(Number4S(Number4S(Number4S(Number4T, item05), item06), item07), item08)

  def main(args: Array[String]): Unit = {
    assert(Number3.method2(Number2, Number4) == Number2)
    assert(number5.method2(number4, number6) == number7)
    assert(number9.method2(number8, Number20) == Number21)
    assert(Number23.method2(Number22, Number24) == Number25)
    assert(Number27.method2(Number26, Number28) == Number29)
    assert(Number31.method2(Number30, Number32) == Number2T)
  }
}
