package c01

object Runner {
  def number1gen(n: Int): Number1 = n match {
    case i if i > 0 => Number1S(number1gen(i - 1))
    case 0          => Number1T
  }

  def number2gen(n: Int): (Number2, Number2) = {
    def number2s(f: Int, zero: => Number2): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1, zero))
      case 0          => zero
    }
    lazy val number2Tail: Number2 = number2s(n, number2Zero)
    lazy val number2Zero: Number2 = Number2T(() => number2Tail)
    (number2Tail, number2Zero)
  }

  def number3gen(n: Int): (Number3, Number3) = {
    def number3s(f: Int, zero: => Number3): Number3 = f match {
      case i if i > 0 => Number3S(number3s(i - 1, zero))
      case 0          => zero
    }
    lazy val number3Tail: Number3 = number3s(n, number3Zero)
    lazy val number3Zero: Number3 = Number3T(() => number3Tail)
    (number3Tail, number3Zero)
  }

  def number4gen(n: Int): Number4 = n match {
    case i if i > 0 => Number4S(number4gen(i - 1))
    case 0          => Number4T
  }

  def main(args: Array[String]): Unit = {
    {
      val number1              = number1gen(28)
      val (number2s, number2t) = number2gen(200)
      val (number3s, number3t) = number3gen(200)
      val number4              = number4gen(28 * 200 / 200)
      assert(number1.method1(number2s, number3t) == number4)
      assert(number2t.method2(number1, number3t) == number4)
      assert(number3s.method3(number1, number2t) == number4)
    }
    {
      val number1              = number1gen(28)
      val (number2s, number2t) = number2gen(72)
      val (number3s, number3t) = number3gen(83)
      val number4              = number4gen(28 * 72 / 83 + 1)
      val number5              = number4gen(28 * 72 / 83)
      assert(number1.method1(number2s, number3t) == number4) // 上舍法
      assert(number2t.method2(number1, number3t) == number4) // 上舍法
      assert(number3s.method3(number1, number2t) == number5) // 下舍法
    }
    {
      val number1              = number1gen(83)
      val (number2s, number2t) = number2gen(28)
      val (number3s, number3t) = number3gen(72)
      val number4              = number4gen(83 * 28 / 72 + 1)
      val number5              = number4gen(83 * 28 / 72)
      assert(number1.method1(number2s, number3t) == number4) // 上舍法
      assert(number2t.method2(number1, number3t) == number4) // 上舍法
      assert(number3s.method3(number1, number2t) == number5) // 下舍法
    }
    {
      val number1              = number1gen(268)
      val (number2s, number2t) = number2gen(35)
      val (number3s, number3t) = number3gen(63)
      val number4              = number4gen(268 * 35 / 63 + 1)
      val number5              = number4gen(268 * 35 / 63)
      assert(number1.method1(number2s, number3t) == number4) // 上舍法
      assert(number2t.method2(number1, number3t) == number4) // 上舍法
      assert(number3s.method3(number1, number2t) == number5) // 下舍法
    }
    {
      val number1              = number1gen(268)
      val (number2s, number2t) = number2gen(0)
      val (number3s, number3t) = number3gen(63)
      val number4              = number4gen(268 * 0 / 63)
      assert(number1.method1(number2s, number3t) == number4)
      assert(number2t.method2(number1, number3t) == number4)
      assert(number3s.method3(number1, number2t) == number4)
    }
    {
      val number1              = number1gen(0)
      val (number2s, number2t) = number2gen(2131)
      val (number3s, number3t) = number3gen(234)
      val number4              = number4gen(0 * 2131 / 234)
      assert(number1.method1(number2s, number3t) == number4)
      assert(number2t.method2(number1, number3t) == number4)
      assert(number3s.method3(number1, number2t) == number4)
    }
    {
      val number1              = number1gen(200)
      val (number2s, number2t) = number2gen(28)
      val (number3s, number3t) = number3gen(200)
      val number4              = number4gen(200 * 28 / 200)
      assert(number1.method1(number2s, number3t) == number4)
      assert(number2t.method2(number1, number3t) == number4)
      assert(number3s.method3(number1, number2t) == number4)
    }
    {
      val number1              = number1gen(200)
      val (number2s, number2t) = number2gen(28)
      val (number3s, number3t) = number3gen(140)
      val number4              = number4gen(200 * 28 / 140)
      assert(number1.method1(number2s, number3t) == number4)
      assert(number2t.method2(number1, number3t) == number4)
      assert(number3s.method3(number1, number2t) == number4)
    }
  }
}
