package d03

object Runner {
  def number1gen(n: Int): Number1 = n match {
    case i if i > 0 => Number1S(number1gen(i - 1))
    case 0          => Number1T
  }

  def number2gen(n: Int): Number2 = {
    def number2s(f: Int, zero: => Number2): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1, zero))
      case 0          => zero
    }
    lazy val number2Tail: Number2 = number2s(n, number2Zero)
    lazy val number2Zero: Number2 = Number2T(() => number2Tail)
    number2Zero
  }

  def number3gen(n: Int): Number3 = {
    def number3s(f: Int, zero: => Number3): Number3 = f match {
      case i if i > 0 => Number3S(number3s(i - 1, zero))
      case 0          => zero
    }
    lazy val number2Tail: Number3 = number3s(n, number2Zero)
    lazy val number2Zero: Number3 = Number3T(() => number2Tail)
    number2Tail
  }

  def number4gen(n: Int): Number4 = n match {
    case i if i > 0 => Number4S(number4gen(i - 1))
    case 0          => Number4T
  }

  def number1Length(number1: Number1): Int = number1 match {
    case Number1S(tail) => 1 + number1Length(tail)
    case Number1T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number2                                                  = number2gen(7)
      val number3                                                  = number3gen(22)
      def numberCount(number1: Number1, number4: Number4): Number1 = Counter.count(number1, number4, number3, number2)
      val value1                                                   = -22d / 7d
      var count                                                    = 0
      for {
        i1 <- 1 to 500
        i2 <- 1 to 500
      } {
        val value2  = (i1 + i2 * value1).toInt
        val value3  = if (value2 < 0) 0 else value2
        val number1 = number1gen(i1)
        val number4 = number4gen(i2)
        val number3 = number1Length(numberCount(number1, number4))
        assert(value3 == number3)
        count += 1
      }
      println(s"匹配了 $count 个结果")
    }
  }
}
