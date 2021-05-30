package b03

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
    def number2Fetch(number2: Number2): Number2 = number2 match {
      case Number2S(tail, _) => number2Fetch(tail)
      case s: Number2T       => s
    }
    number2Fetch(number2Tail)
  }

  def number3gen(n: Int): Number3 = n match {
    case i if i > 0 => Number3S(number3gen(i - 1))
    case 0          => Number3T
  }

  val number1 = number1gen(200)
  val number2 = number2gen(1)
  val number3 = number3gen(200)

  val number4 = number1gen(200)
  val number5 = number2gen(2)
  val number6 = number3gen(100)

  val number7 = number1gen(56)
  val number8 = number2gen(7)
  val number9 = number3gen(8)

  val number10 = number1gen(56)
  val number11 = number2gen(8)
  val number12 = number3gen(7)

  val number13 = number1gen(82)
  val number14 = number2gen(9)
  val number15 = number3gen(10)

  val number16 = number1gen(2)
  val number17 = number2gen(5)
  val number18 = number3gen(1)

  val number19 = number1gen(12)
  val number20 = number2gen(5)
  val number21 = number3gen(3)

  def main(args: Array[String]): Unit = {
    assert(number1.method1(number2) == number3)
    assert(number4.method1(number5) == number6)
    assert(number7.method1(number8) == number9)
    assert(number10.method1(number11) == number12)
    assert(number13.method1(number14) == number15)
    assert(number16.method1(number17) == number18)
    assert(number19.method1(number20) == number21)
  }
}
