package c02

object Runner {
  def number1gen(n: Int): Number1 = {
    def number1s(f: Int, zero: => Number1): Number1 = f match {
      case i if i > 0 => Number1S(number1s(i - 1, zero), Item1(s"Item${i}"))
      case 0          => zero
    }
    lazy val number1Tail: Number1 = number1s(n, number1Zero)
    lazy val number1Zero: Number1 = Number1T(() => number1Tail)
    number1Tail
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

  def number3gen(n: Int): Number3 = n match {
    case i if i > 0 => Number3S(number3gen(i - 1))
    case 0          => Number3T
  }

  def number4gen(n: Int): Number4 = n match {
    case i if i > 0 => Number4S(number4gen(i - 1))
    case 0          => Number4T
  }

  def number4Length(number4: Number4): Int = number4 match {
    case Number4S(tail) => 1 + number4Length(tail)
    case Number4T       => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val number1                                = number3gen(22)
      val number2                                = number1gen(7)
      def numberCount(number3: Number2): Number4 = Counter.count(number3, number1, number2)
      val value1                                 = 22d / 7d
      var count                                  = 0
      for (i <- 0 to 5000) {
        val value2  = (i * value1).toInt
        val number3 = number4Length(numberCount(number2gen(i)))
        assert(value2 == number3)
        count += 1
      }
      println(s"匹配了 $count 个结果")
    }
  }
}
