package a01

object Runner {
  val item01 = Item("Item01")
  val item02 = Item("Item02")
  val item03 = Item("Item03")
  val item04 = Item("Item04")
  val item05 = Item("Item05")
  val item06 = Item("Item06")
  val item07 = Item("Item07")

  val number1 = Number1S(Number1S(Number1S(Number1T, item01), item02), item03)
  val number2 = Number2S(Number2S(Number2S(Number2S(Number2T, item04), item05), item06), item07)
  val number3 =
    Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04), item05), item06), item07)

  def main(args: Array[String]): Unit = {
    assert(number2.method1(number1) == number3)
  }
}
