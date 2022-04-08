package a09

object LogRunner {

  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  case class Number1V0(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1S(Number1S(Number1S(Number1S(Number1S(tail))))))
  }
  case class Number1V1(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V0(Number1V0(Number1V0(Number1V0(Number1V0(tail))))))
  }
  case class Number1V2(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V1(Number1V1(Number1V1(Number1V1(Number1V1(tail))))))
  }
  case class Number1V3(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V2(Number1V2(Number1V2(Number1V2(Number1V2(tail))))))
  }
  case class Number1V4(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V3(Number1V3(Number1V3(Number1V3(Number1V3(tail))))))
  }
  case class Number1V5(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V4(Number1V4(Number1V4(Number1V4(Number1V4(tail))))))
  }
  case class Number1V6(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V5(Number1V5(Number1V5(Number1V5(Number1V5(tail))))))
  }
  case class Number1V7(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V6(Number1V6(Number1V6(Number1V6(Number1V6(tail))))))
  }
  case class Number1V8(tail: Number1) extends Number1 {
    override def method1(number2: Number2): Number3 = number2.method2(Number1V7(Number1V7(Number1V7(Number1V7(Number1V7(tail))))))
  }

  lazy val number1: Number1     = Number1S(Number1S(Number1S(Number1S(Number1S(number1Zero)))))
  lazy val number1Zero: Number1 = Number1T(() => number2)
  lazy val number2: Number1     = Number1V0(Number1V0(Number1V0(Number1S(Number1S(number2Zero)))))
  lazy val number2Zero: Number1 = Number1T(() => number3)
  lazy val number3: Number1     = Number1V1(Number1V1(Number1V1(Number1V0(Number1S(number3Zero)))))
  lazy val number3Zero: Number1 = Number1T(() => number4)
  lazy val number4: Number1     = Number1V2(Number1V2(Number1V2(Number1V1(Number1S(number4Zero)))))
  lazy val number4Zero: Number1 = Number1T(() => number5)
  lazy val number5: Number1     = Number1V3(Number1V3(Number1V3(Number1V2(Number1S(number5Zero)))))
  lazy val number5Zero: Number1 = Number1T(() => number6)
  lazy val number6: Number1     = Number1V4(Number1V4(Number1V4(Number1V3(Number1S(number6Zero)))))
  lazy val number6Zero: Number1 = Number1T(() => number7)
  lazy val number7: Number1     = Number1V5(Number1V5(Number1V5(Number1V4(Number1S(number7Zero)))))
  lazy val number7Zero: Number1 = Number1T(() => null)

  def count(i: Number3): Int = i match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0

  def main(arr: Array[String]): Unit = {
    for (i <- 1 to 20000) yield {
      val n = number1.method1(number2Gen(i))
      println(5, i, count(n), log(5, i))
      assert(count(n) == log(5, i))
    }
  }

}
