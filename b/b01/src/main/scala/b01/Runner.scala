package b01

object Runner {
  val item01 = Item1("Item01")
  val item02 = Item1("Item02")
  val item03 = Item1("Item03")
  val item04 = Item1("Item04")
  val item05 = Item2("Item05")
  val item06 = Item2("Item06")
  val item07 = Item2("Item07")
  val item08 = Item2("Item08")
  val item09 = Item2("Item09")
  val item10 = Item2("Item10")

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
      lazy val number2: Number2 =
        Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(number3, item05), item06), item07), item08), item09), item10)
      lazy val number3: Number2 = Number2T(() => number2)
      assert(number1.method1(number2).length == 24)
    }
    {
      val number1               = Number1S(Number1S(Number1S(Number1T, item01), item02), item02)
      lazy val number2: Number2 = Number2T(() => number2)
      assert(number1.method1(number2).length == 0)
    }
    {
      lazy val number1: Number2 = Number2T(() => number1)
      assert(Number1T.method1(number1).length == 0)
    }
    {
      lazy val number1: Number2 = Number2S(Number2S(number2, item05), item06)
      lazy val number2: Number2 = Number2T(() => number1)
      assert(Number1T.method1(number1).length == 0)
    }
  }
}
