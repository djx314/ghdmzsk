package b05

object Runner {
  def number1gen(n: Int): Number1 = n match {
    case i if i > 0 => Number1S(number1gen(i - 1), Item1(s"Item${i}"))
    case 0          => Number1T
  }

  def number2gen(n: Int): Number2 = {
    def number2s(f: Int): Number2 = f match {
      case i if i > 0 => Number2S(number2s(i - 1), Item2(s"Item${i}"))
      case 0          => Number2T(() => number2Tail)
    }
    lazy val number2Tail: Number2 = number2s(n)
    number2Tail
  }

  def number3gen(n: Int): Number3 = {
    def number3s(f: Int): Number3 = f match {
      case i if i > 0 => Number3S(number3s(i - 1), Item3(s"Item${i}"))
      case 0          => Number3T(() => number3Tail)
    }
    lazy val number3Tail: Number3 = number3s(n)
    def fetchT(number3: Number3): Number3 = number3 match {
      case Number3S(t, _) => fetchT(t)
      case s: Number3T    => s
    }
    fetchT(number3Tail)
  }

  def number4gen(n: Int): Number4 = n match {
    case i if i > 0 => Number4S(number4gen(i - 1))
    case 0          => Number4T
  }

  val number1 = number1gen(28)
  val number2 = number2gen(200)
  val number3 = number3gen(200)
  val number4 = number4gen(28)

  val number5 = number1gen(28)
  val number6 = number2gen(72)
  val number7 = number3gen(83)
  val number8 = number4gen(25)

  val number9  = number1gen(268)
  val number10 = number2gen(35)
  val number11 = number3gen(63)
  val number12 = number4gen(149)

  val number13 = number1gen(268)
  val number14 = number2gen(0)
  val number15 = number3gen(63)
  val number16 = number4gen(0)

  val number17 = number1gen(0)
  val number18 = number2gen(2131)
  val number19 = number3gen(234)
  val number20 = number4gen(0)

  val number21 = number1gen(200)
  val number22 = number2gen(28)
  val number23 = number3gen(200)
  val number24 = number4gen(28)

  val number25 = number1gen(200)
  val number26 = number2gen(28)
  val number27 = number3gen(140)
  val number28 = number4gen(40)

  def main(args: Array[String]): Unit = {
    assert(number1.method1(number2, number3) == number4)
    assert(number5.method1(number6, number7) == number8)
    assert(number9.method1(number10, number11) == number12)
    assert(number13.method1(number14, number15) == number16)
    assert(number17.method1(number18, number19) == number20)
    assert(number21.method1(number22, number23) == number24)
    assert(number25.method1(number26, number27) == number28)
  }
}
