package d02

object Runner {

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(number1sGen(n - 1, zero), Item1(n)) else zero
  def number2sGen(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(number2sGen(n - 1, zero)) else zero
  def number3Gen(n: Int): Number3                    = if (n > 0) Number3S(number3Gen(n - 1)) else Number3T

  def number4Length(number4: Number4): Int = number4 match {
    case Number4S(tail) => number4Length(tail) + 1
    case Number4T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1                                = number3Gen(22)
      lazy val number2s: Number1                 = number1sGen(7, number2t)
      lazy val number2t: Number1                 = Number1T(() => number2s)
      def numberCount(number3: Number2): Number4 = number2s.method1(number3, number1)
      val value1                                 = 22d / 7
      var count                                  = 0
      for (i <- 0 to 5000) {
        val value2                 = (i * value1).toInt
        lazy val number3s: Number2 = number2sGen(i, number3t)
        lazy val number3t: Number2 = Number2T(() => number3s)
        val number4                = number4Length(numberCount(number3t))
        assert(value2 == number4)
        count += 1
      }
      println(s"匹配了 $count 个结果")
    }
  }

}
