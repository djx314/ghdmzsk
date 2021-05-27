package b02

object Runner {
  val item01 = Item("Item01")
  val item02 = Item("Item02")
  val item03 = Item("Item03")
  val item04 = Item("Item04")

  val number1: Number1      = Number1S(Number1S(Number1S(Number1S(number2, item01), item02), item03), item04)
  lazy val number2: Number1 = Number1T(() => number1)
  val number3: Number2      = Number2S(Number2S(Number2S(Number2T)))

  lazy val number4: Number1 = Number1T(() => number4)
  val number5: Number2      = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))))))

  val number6: Number1      = Number1S(Number1S(Number1S(number7, item01), item02), item03)
  lazy val number7: Number1 = Number1T(() => number6)

  def main(args: Array[String]): Unit = {
    assert(number2.method1(number3).length == 12)
    assert(number4.method1(number5).length == 0)
    assert(number4.method1(Number2T).length == 0)
    assert(number7.method1(Number2T).length == 0)
  }
}
