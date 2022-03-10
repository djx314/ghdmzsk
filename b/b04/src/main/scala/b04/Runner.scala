package b04

import scala.util.{Failure, Success}

object Runner {

  def number1Gen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(number1Gen(n - 1, zero)) else zero
  def number2Gen(n: Int): Number2                   = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T
  def number3Gen(n: Int): Number3                   = if (n > 0) Number3S(number3Gen(n - 1)) else Number3T

  def countNumber1(number1: Number1): Int = number1 match {
    case Number1S(tail) => countNumber1(tail) + 1
    case Number1T(_)    => 0
  }
  def countNumber2(number2: Number2): Int = number2 match {
    case Number2S(tail) => countNumber2(tail) + 1
    case Number2T       => 0
  }
  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      lazy val number1s: Number1 = number1Gen(0, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(25)

      val number3 =
        try Success(number1s.method1(number2))
        catch { case e: StackOverflowError => Failure(e) }

      val exception = number3.failed.get

      assert(exception.isInstanceOf[StackOverflowError])
    }
    {
      lazy val number1s: Number1 = number1Gen(0, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(0)

      val number3 =
        try Success(number1s.method1(number2))
        catch { case e: StackOverflowError => Failure(e) }

      val exception = number3.failed.get

      assert(exception.isInstanceOf[StackOverflowError])
    }
    {
      lazy val number1s: Number1 = number1Gen(1, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(200)
      val number3                = number3Gen(200)
      assert(number1s.method1(number2) == number3)
    }
    {
      lazy val number1s: Number1 = number1Gen(2, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(200)
      val number3                = number3Gen(100)
      assert(number1s.method1(number2) == number3)
    }
    {
      lazy val number1s: Number1 = number1Gen(7, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(56)
      val number3                = number3Gen(8)
      assert(number1s.method1(number2) == number3)
    }
    {
      lazy val number1s: Number1 = number1Gen(8, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(56)
      val number3                = number3Gen(7)
      assert(number1s.method1(number2) == number3)
    }
    {
      lazy val number1s: Number1 = number1Gen(9, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(82)
      val number3                = number3Gen(9)
      assert(number1s.method1(number2) == number3)
    }
    {
      lazy val number1s: Number1 = number1Gen(5, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(2)
      val number3                = number3Gen(0)
      assert(number1s.method1(number2) == number3)
    }
    {
      lazy val number1s: Number1 = number1Gen(5, number1t)
      lazy val number1t: Number1 = Number1T(() => number1s)
      val number2                = number2Gen(12)
      val number3                = number3Gen(2)
      assert(number1s.method1(number2) == number3)
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        lazy val number1s: Number1 = number1Gen(i1, number1t)
        lazy val number1t: Number1 = Number1T(() => number1s)
        val number2                = number2Gen(i2)
        val number3 =
          try Option(number1s.method1(number2))
          catch {
            case _: StackOverflowError => Option.empty
          }

        val count1 = countNumber1(number1s)
        val count2 = countNumber2(number2)
        val count3 = number3.map(count)

        val result1 = if (count1 == 0) Option.empty else Option(count2 / count1)

        assert(count1 == i1)
        assert(count2 == i2)
        assert(count3 == result1)
      }
    }
  }
}
