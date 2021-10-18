package ab01

object Runner {
  def number1gen(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(number1gen(n1 - 1), Item1(n1))
    case 0            => Number1T
  }
  def number2gen(n: Int): Number2 = n match {
    case n1 if n1 > 0 => Number2S(number2gen(n1 - 1), Item2(n1))
    case 0            => Number2T
  }
  def count(number3: Number3): Int = number3 match {
    case Number3S(tail, _, _) => count(tail) + 1
    case Number3T             => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1 = number1gen(25)
      val number2 = number2gen(36)
      val number3 = number2.method2(number1)
      assert(count(number3) == 25 * 36)
    }
    {
      val number1 = number1gen(2)
      val number2 = number2gen(1)
      val number3 = number2.method2(number1)
      assert(count(number3) == 2)
    }
    {
      val number1 = number1gen(0)
      val number2 = number2gen(5)
      val number3 = number2.method2(number1)
      assert(count(number3) == 0)
    }
    {
      val number1 = number1gen(5)
      val number2 = number2gen(0)
      val number3 = number2.method2(number1)
      assert(count(number3) == 0)
    }
    {
      val number1 = number1gen(0)
      val number2 = number2gen(0)
      val number3 = number2.method2(number1)
      assert(count(number3) == 0)
    }
  }
}
