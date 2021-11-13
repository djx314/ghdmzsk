package c02

object Runner {

  def number1Gen(n: Int): Number1                    = if (n > 0) Number1S(number1Gen(n - 1)) else Number1T
  def number2sGen(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(number2sGen(n - 1, zero)) else zero
  def number3sGen(n: Int, zero: => Number3): Number3 = if (n > 0) Number3S(number3sGen(n - 1, zero)) else zero
  def number4Gen(n: Int): Number4                    = if (n > 0) Number4S(number4Gen(n - 1)) else Number4T

  def main(args: Array[String]): Unit = {
    {
      val number1                = number1Gen(28)
      lazy val number2s: Number2 = number2sGen(200, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(200, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(28 * 200 * 200)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
    {
      val number1                = number1Gen(28)
      lazy val number2s: Number2 = number2sGen(72, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(83, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(28 * 72 * 83)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
    {
      val number1                = number1Gen(83)
      lazy val number2s: Number2 = number2sGen(28, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(72, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(83 * 28 * 72)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
    {
      val number1                = number1Gen(268)
      lazy val number2s: Number2 = number2sGen(35, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(63, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(268 * 35 * 63)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
    {
      val number1                = number1Gen(268)
      lazy val number2s: Number2 = number2sGen(0, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(63, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(0)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
    {
      val number1                = number1Gen(0)
      lazy val number2s: Number2 = number2sGen(2131, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(234, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(0)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
    {
      val number1                = number1Gen(200)
      lazy val number2s: Number2 = number2sGen(28, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(200, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(200 * 28 * 200)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
    {
      val number1                = number1Gen(200)
      lazy val number2s: Number2 = number2sGen(28, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)
      lazy val number3s: Number3 = number3sGen(140, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)
      val number4                = number4Gen(200 * 28 * 140)
      assert(number1.method1(number2s, number3s) == number4)
      assert(number2t.method2(number1, number3s) == number4)
      assert(number3t.method3(number1, number2t) == number4)
    }
  }
}
