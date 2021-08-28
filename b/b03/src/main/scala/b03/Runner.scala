package b03

object Runner {
  def number1gen(n: Int): Number1 = n match {
    case i if i > 0 => Number1S(number1gen(i - 1), Item1(s"$i"))
    case 0          => Number1T
  }

  def number2gen(n: Int): Number2 = {
    def number2s(f: Int, zero: => Number2): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1, zero), Item2(s"$i"))
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

  def main(args: Array[String]): Unit = {
    {
      val number1 = number1gen(200)
      val number2 = number2gen(1)
      val number3 = number3gen(200)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(200)
      val number2 = number2gen(2)
      val number3 = number3gen(100)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(56)
      val number2 = number2gen(7)
      val number3 = number3gen(8)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(56)
      val number2 = number2gen(8)
      val number3 = number3gen(7)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(82)
      val number2 = number2gen(9)
      val number3 = number3gen(10)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(2)
      val number2 = number2gen(5)
      val number3 = number3gen(1)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(12)
      val number2 = number2gen(5)
      val number3 = number3gen(3)
      assert(number1.method1(number2) == number3)
    }
  }
}
