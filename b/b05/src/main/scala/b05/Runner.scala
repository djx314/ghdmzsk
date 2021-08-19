package b05

object Runner {
  def number1gen(n: Int): Number1 = n match {
    case i if i > 0 => Number1S(number1gen(i - 1), Item1(s"Item${i}"))
    case 0          => Number1T
  }

  def number2gen(n: Int): Number2 = {
    def number2s(f: Int, zero: => Number2): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1, zero), Item2(s"Item${i}"))
      case 0          => zero
    }
    lazy val number2Tail: Number2 = number2s(n, number2Zero)
    lazy val number2Zero: Number2 = Number2T(() => number2Tail)
    number2Tail
  }

  def number3gen(n: Int): Number3 = {
    def number3s(f: Int, zero: => Number3): Number3 = f match {
      case i if i > 0 => Number3S(number3s(i - 1, zero), Item3(s"Item${i}"))
      case 0          => zero
    }
    lazy val number3Tail: Number3 = number3s(n, number3Zero)
    lazy val number3Zero: Number3 = Number3T(() => number3Tail)
    number3Zero
  }

  def number4gen(n: Int): Number4 = n match {
    case i if i > 0 => Number4S(number4gen(i - 1))
    case 0          => Number4T
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = number1gen(28)
      val number2 = number2gen(200)
      val number3 = number3gen(200)
      val number4 = number4gen(28)
      assert(number1.method1(number2, number3) == number4)
    }
    {
      val number1 = number1gen(28)
      val number2 = number2gen(72)
      val number3 = number3gen(83)
      val number4 = number4gen(25)
      assert(number1.method1(number2, number3) == number4)
    }
    {
      val number1 = number1gen(268)
      val number2 = number2gen(35)
      val number3 = number3gen(63)
      val number4 = number4gen(149)
      assert(number1.method1(number2, number3) == number4)
    }
    {
      val number1 = number1gen(268)
      val number2 = number2gen(0)
      val number3 = number3gen(63)
      val number4 = number4gen(0)
      assert(number1.method1(number2, number3) == number4)
    }
    {
      val number1 = number1gen(0)
      val number2 = number2gen(2131)
      val number3 = number3gen(234)
      val number4 = number4gen(0)
      assert(number1.method1(number2, number3) == number4)
    }
    {
      val number1 = number1gen(200)
      val number2 = number2gen(28)
      val number3 = number3gen(200)
      val number4 = number4gen(28)
      assert(number1.method1(number2, number3) == number4)
    }
    {
      val number1 = number1gen(200)
      val number2 = number2gen(28)
      val number3 = number3gen(140)
      val number4 = number4gen(40)
      assert(number1.method1(number2, number3) == number4)
    }
  }
}
