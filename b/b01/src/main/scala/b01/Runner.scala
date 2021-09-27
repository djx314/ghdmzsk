package b01

object Runner {
  val item01 = Item1("1")
  val item02 = Item1("2")
  val item03 = Item1("3")
  val item04 = Item1("4")
  val item05 = Item2("5")
  val item06 = Item2("6")
  val item07 = Item2("7")
  val item08 = Item2("8")
  val item09 = Item2("9")
  val item10 = Item2("10")

  def count(num: Number3): Int = num match {
    case Number3S(tail, _, _) => count(tail) + 1
    case Number3T             => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04)
      lazy val number2: Number2 =
        Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(number3, item05), item06), item07), item08), item09), item10)
      lazy val number3: Number2 = Number2T(() => number2)
      val number4               = count(number1.method1(number2))
      assert(number4 == 24)
    }
    {
      val number1               = Number1S(Number1S(Number1S(Number1T, item01), item02), item02)
      lazy val number2: Number2 = Number2T(() => number2)
      assert(number1.method1(number2) == Number3T)
    }
    {
      lazy val number1: Number2 = Number2T(() => number1)
      assert(Number1T.method1(number1) == Number3T)
    }
    {
      lazy val number1: Number2 = Number2S(Number2S(number2, item05), item06)
      lazy val number2: Number2 = Number2T(() => number1)
      assert(Number1T.method1(number1) == Number3T)
    }
  }
}
