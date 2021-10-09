package b04

import scala.util.{Failure, Success}

object Runner {
  def number1gen(n: Int): Number1 = n match {
    case i if i > 0 => Number1S(number1gen(i - 1), Item2(i))
    case 0          => Number1T
  }

  def number2gen(n: Int): (Number2, Number2) = {
    def number2s(f: Int, zero: => Number2): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1, zero), Item1(i))
      case 0          => zero
    }
    lazy val number1Tail: Number2 = number2s(n, number1Zero)
    lazy val number1Zero: Number2 = Number2T(() => number1Tail)
    (number1Tail, number1Zero)
  }

  def number3gen(n: Int): Number3 = n match {
    case i if i > 0 => Number3S(number3gen(i - 1))
    case 0          => Number3T
  }

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1              = number1gen(25)
      val (number2s, number2t) = number2gen(0)

      val number3 =
        try Success(number2s.method2(number1))
        catch { case e: StackOverflowError => Failure(e) }

      val exception = number3 match { case Failure(e) => e }

      assert(exception.isInstanceOf[StackOverflowError])
    }
    {
      val number1              = number1gen(0)
      val (number2s, number2t) = number2gen(0)

      val number3 =
        try Success(number2s.method2(number1))
        catch { case e: StackOverflowError => Failure(e) }

      val exception = number3 match { case Failure(e) => e }

      assert(exception.isInstanceOf[StackOverflowError])
    }
    {
      val number1              = number1gen(200)
      val (number2s, number2t) = number2gen(1)
      val number3              = number3gen(200)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1              = number1gen(200)
      val (number2s, number2t) = number2gen(2)
      val number3              = number3gen(100)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1              = number1gen(56)
      val (number2s, number2t) = number2gen(7)
      val number3              = number3gen(8)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1              = number1gen(56)
      val (number2s, number2t) = number2gen(8)
      val number3              = number3gen(7)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1              = number1gen(82)
      val (number2s, number2t) = number2gen(9)
      val number3              = number3gen(9)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1              = number1gen(2)
      val (number2s, number2t) = number2gen(5)
      val number3              = number3gen(0)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1              = number1gen(12)
      val (number2s, number2t) = number2gen(5)
      val number3              = number3gen(2)
      assert(number2s.method2(number1) == number3)
    }
  }
}
