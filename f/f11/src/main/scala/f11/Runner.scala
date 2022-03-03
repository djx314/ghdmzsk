package f11

object Runner {

  def number1Gen(step: Int)(n: Int, k: Int, m: Int, zero: => Number1): Number1 =
    if (n > 0)
      Number1S(k, m, (kNext, mNext) => number1Gen(step)(n - 1, kNext + 1, if (mNext > 0) mNext - 1 else (kNext + 1) * step - 1, zero))
    else zero

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0
  def pow(i: Int, n: Int): Int = if (n > 0) pow(i, n - 1) * i else 1

  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 2 to 10
      i2 <- 1 to 500
    } {
      val number1 = number1Gen(i1 - 1)(i2, 0, i1 - 1, Number1U)
      println(i1, i2, number1.length.length, log(i1, i2))
      assert(log(i1, i2) == number1.length.length)
    }
  }

}
