package b04

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

  def number2gen(n: Int): Number2 = n match {
    case i if i > 0 => Number2S(number2gen(i - 1), Item2(s"Item${i}"))
    case 0          => Number2T
  }

  def number3gen(n: Int): Number3 = n match {
    case i if i > 0 => Number3S(number3gen(i - 1))
    case 0          => Number3T
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = number1gen(1)
      val number2 = number2gen(200)
      val number3 = number3gen(200)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(2)
      val number2 = number2gen(200)
      val number3 = number3gen(100)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(7)
      val number2 = number2gen(56)
      val number3 = number3gen(8)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(8)
      val number2 = number2gen(56)
      val number3 = number3gen(7)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(9)
      val number2 = number2gen(82)
      val number3 = number3gen(9)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(5)
      val number2 = number2gen(2)
      val number3 = number3gen(0)
      assert(number1.method1(number2) == number3)
    }
    {
      val number1 = number1gen(5)
      val number2 = number2gen(12)
      val number3 = number3gen(2)
      assert(number1.method1(number2) == number3)
    }
  }
}
