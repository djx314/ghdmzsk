package b06

object Runner {
  def number1gen(n: Int): Number1 = {
    def number1s(f: Int): Number1 = f match {
      case i if i > 0 => Number1S(number1s(i - 1), Item1(s"Item${i}"))
      case 0          => Number1T(() => number1Tail)
    }
    def number1Tail = number1s(n)
    number1Tail
  }

  def number2gen(n: Int): Number2 = {
    def number2s(f: Int): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1))
      case 0          => Number2T(() => number2Tail)
    }
    def number2Tail: Number2 = number2s(n)
    def fetchT(n: Number2): Number2 = n match {
      case Number2S(tail) => fetchT(tail)
      case s: Number2T    => s
    }
    fetchT(number2Tail)
  }

  def number3gen(n: Int): Number3 = n match {
    case i if i > 0 => Number3S(number3gen(i - 1))
    case 0          => Number3T
  }

  def number4gen(n: Int): Number4 = n match {
    case i if i > 0 => Number4S(number4gen(i - 1))
    case 0          => Number4T
  }

  val number1 = number2gen(28)
  val number2 = number3gen(200)
  val number3 = number1gen(28)
  val number4 = number4gen(200)

  val number5 = number2gen(200)
  val number6 = number3gen(28)
  val number7 = number1gen(28)
  val number8 = number4gen(200)

  val number9  = number2gen(83)
  val number10 = number3gen(28)
  val number11 = number1gen(72)
  val number12 = number4gen(32)

  val number13 = number2gen(268)
  val number14 = number3gen(35)
  val number15 = number1gen(63)
  val number16 = number4gen(148)

  val number17 = number2gen(268)
  val number18 = number3gen(0)
  val number19 = number1gen(63)
  val number20 = number4gen(0)

  val number21 = number2gen(0)
  val number22 = number3gen(2131)
  val number23 = number1gen(234)
  val number24 = number4gen(0)

  val number25 = number2gen(200)
  val number26 = number3gen(28)
  val number27 = number1gen(140)
  val number28 = number4gen(40)

  def main(args: Array[String]): Unit = {
    assert(Counter.count(number1, number2, number3) == number4)
    assert(Counter.count(number5, number6, number7) == number8)
    assert(Counter.count(number9, number10, number11) == number12)
    assert(Counter.count(number13, number14, number15) == number16)
    assert(Counter.count(number17, number18, number19) == number20)
    assert(Counter.count(number21, number22, number23) == number24)
    assert(Counter.count(number25, number26, number27) == number28)
  }
}
