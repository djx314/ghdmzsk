package d03

object Runner {

  def number1Gen(n: Int): Number1                   = if (n > 0) Number1S(number1Gen(n - 1)) else Number1T
  def number2Gen(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(number2Gen(n - 1, zero)) else zero
  def number3Gen(n: Int, zero: => Number3): Number3 = if (n > 0) Number3S(number3Gen(n - 1, zero)) else zero
  def number4Gen(n: Int): Number4                   = if (n > 0) Number4S(number4Gen(n - 1)) else Number4T

  def number1Length(number1: Number1): Int = number1 match {
    case Number1S(tail) => number1Length(tail) + 1
    case Number1T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      lazy val number2s: Number2 = number2Gen(7, number2t)
      lazy val number2t: Number2 = Number2T(() => number2s)

      lazy val number3s: Number3 = number3Gen(22, number3t)
      lazy val number3t: Number3 = Number3T(() => number3s)

      /** number1 - number4 * number3 / number2
        */
      def numberCount(number1: Number1, number4: Number4): Number1 = number4.method3(number1, number2t, number3s)

      val value1 = -22d / 7
      var count  = 0
      for {
        i1 <- 1 to 500
        i2 <- 1 to 500
      } {
        val value2  = (i1 + i2 * value1).toInt
        val value3  = if (value2 >= 0) value2 else 0
        val number1 = number1Gen(i1)
        val number4 = number4Gen(i2)
        val number3 = number1Length(numberCount(number1, number4))
        assert(value3 == number3)
        count += 1
      }
      println(s"匹配了 $count 个结果")
    }
  }

}
