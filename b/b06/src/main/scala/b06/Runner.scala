package b06

object Runner {
  def number1gen(n: Int): Number1 = {
    def number1s(f: Int, zero: => Number1): Number1 = f match {
      case i if i > 0 => Number1S(number1s(i - 1, zero), Item1(s"Item${i}"))
      case 0          => zero
    }
    lazy val number1Tail: Number1 = number1s(n, number1Zero)
    lazy val number1Zero: Number1 = Number1T(() => number1Tail)
    number1Tail
  }

  def number2gen(n: Int): Number2 = {
    def number2s(f: Int, zero: => Number2): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1, zero))
      case 0          => zero
    }
    lazy val number2Tail: Number2 = number2s(n, number2Zero)
    lazy val number2Zero: Number2 = Number2T(() => number2Tail)
    number2Zero
  }

  def number3gen(n: Int): Number3 = n match {
    case i if i > 0 => Number3S(number3gen(i - 1))
    case 0          => Number3T
  }

  def number4gen(n: Int): Number4 = n match {
    case i if i > 0 => Number4S(number4gen(i - 1))
    case 0          => Number4T
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = number2gen(28)
      val number2 = number3gen(200)
      val number3 = number1gen(28)
      val number4 = number4gen(200)
      assert(Counter.count(number1, number2, number3) == number4)
    }
    {
      val number1 = number2gen(200)
      val number2 = number3gen(28)
      val number3 = number1gen(28)
      val number4 = number4gen(200)
      assert(Counter.count(number1, number2, number3) == number4)
    }
    {
      val number1 = number2gen(83)
      val number2 = number3gen(28)
      val number3 = number1gen(72)
      val number4 = number4gen(32)
      assert(Counter.count(number1, number2, number3) == number4)
    }
    {
      val number1 = number2gen(268)
      val number2 = number3gen(35)
      val number3 = number1gen(63)
      val number4 = number4gen(148)
      assert(Counter.count(number1, number2, number3) == number4)
    }
    {
      val number1 = number2gen(268)
      val number2 = number3gen(0)
      val number3 = number1gen(63)
      val number4 = number4gen(0)
      assert(Counter.count(number1, number2, number3) == number4)
    }
    {
      val number1 = number2gen(0)
      val number2 = number3gen(2131)
      val number3 = number1gen(234)
      val number4 = number4gen(0)
      assert(Counter.count(number1, number2, number3) == number4)
    }
    {
      val number1 = number2gen(200)
      val number2 = number3gen(28)
      val number3 = number1gen(140)
      val number4 = number4gen(40)
      assert(Counter.count(number1, number2, number3) == number4)
    }
  }
}
