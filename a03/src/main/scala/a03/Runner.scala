package a03

object Runner {
  val item01 = Item("Item01")
  val item02 = Item("Item02")
  val item03 = Item("Item03")
  val item04 = Item("Item04")
  val item05 = Item("Item05")
  val item06 = Item("Item06")
  val item07 = Item("Item07")

  val number1 = Number1S(Number1S(Number1T, item01), item02)
  val number2 = Number2S(Number2S(Number2S(Number2S(Number2S(Number2T, item03), item04), item05), item06), item07)
  val number3 = Number2S(Number2S(Number2S(Number2T, item03), item04), item05)

  val number4 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
  val number5 = Number2S(Number2T, item05)
  val number6 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)

  val number7 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)
  val number8 = Number2S(Number2S(Number2S(Number2T, item04), item05), item06)
  val number9 = Number2T

  val number10 = Number1T
  val number11 = Number2T
  val number12 = Number2T

  def main(args: Array[String]): Unit = {
    assert(number1.method1(number2) == number3)
    assert(number4.method1(number5) == number6)
    assert(number7.method1(number8) == number9)
    assert(number10.method1(number11) == number12)
  }
}
