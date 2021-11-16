package b04

import scala.util.{Failure, Success}

object Runner {

  def number1Gen(n: Int): Number1                    = if (n > 0) Number1S(number1Gen(n - 1), Item1(n)) else Number1T
  def number2sGen(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(number2sGen(n - 1, zero), Item2(n)) else zero
  def number3Gen(n: Int): Number3                    = if (n > 0) Number3S(number3Gen(n - 1)) else Number3T

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1                = number1Gen(25)
      lazy val number2s: Number2 = number2sGen(0, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)

      val number3 =
        try Success(number2s.method2(number1))
        catch { case e: StackOverflowError => Failure(e) }

      val exception = number3.failed.get

      assert(exception.isInstanceOf[StackOverflowError])
    }
    {
      val number1                = number1Gen(0)
      lazy val number2s: Number2 = number2sGen(0, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)

      val number3 =
        try Success(number2s.method2(number1))
        catch { case e: StackOverflowError => Failure(e) }

      val exception = number3.failed.get

      assert(exception.isInstanceOf[StackOverflowError])
    }
    {
      val number1                = number1Gen(200)
      lazy val number2s: Number2 = number2sGen(1, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number3                = number3Gen(200)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1                = number1Gen(200)
      lazy val number2s: Number2 = number2sGen(2, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number3                = number3Gen(100)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1                = number1Gen(56)
      lazy val number2s: Number2 = number2sGen(7, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number3                = number3Gen(8)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1                = number1Gen(56)
      lazy val number2s: Number2 = number2sGen(8, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number3                = number3Gen(7)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1                = number1Gen(82)
      lazy val number2s: Number2 = number2sGen(9, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number3                = number3Gen(9)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1                = number1Gen(2)
      lazy val number2s: Number2 = number2sGen(5, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number3                = number3Gen(0)
      assert(number2s.method2(number1) == number3)
    }
    {
      val number1                = number1Gen(12)
      lazy val number2s: Number2 = number2sGen(5, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      val number3                = number3Gen(2)
      assert(number2s.method2(number1) == number3)
    }
  }
}
