package f11.step2

object Runner {

  def number1Gen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(number1Gen(n - 1, zero)) else zero

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0
  def pow(i: Int, n: Int): Int = if (n > 0) pow(i, n - 1) * i else 1

  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 2 to 10
      i2 <- 1 to 120
    } {
      val number1                             = number1Gen(i2, Number1T)
      lazy val number2Gen: Int => Number2     = round => Number2S(round - 1, round, i1 - 1, number2ZeroGen)
      lazy val number2ZeroGen: Int => Number2 = round => Number2T(round, number2Gen)
      val number2                             = number2Gen(i1 - 1)

      println(i1, i2, number1.method1(number2).length, log(i1, i2))
      assert(log(i1, i2) == number1.method1(number2).length)
    }
  }

}
