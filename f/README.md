```scala
trait Number1 {
  def method1(number1: Number1): Number2
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = Number2S(() => tail().method1(number1))
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = tail().method1(number1)
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = Number2S(() => number1.method1(tail()))
}
case class Number1V(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = number1.method1(tail())
}

sealed trait Number2
case class Number2S(tail: () => Number2) extends Number2

object Fusion {

  lazy val number1s: Number1 = Number1S(() => number1s)
  lazy val number1t: Number1 = Number1T(() => number1t)
  lazy val number1u: Number1 = Number1U(() => number1u)
  lazy val number1v: Number1 = Number1V(() => number1v)

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1sGen(n - 1, zero)) else zero
  def number1tGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => number1tGen(n - 1, zero)) else zero
  def number1uGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1U(() => number1uGen(n - 1, zero)) else zero
  def number1vGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1V(() => number1vGen(n - 1, zero)) else zero

}
def number1gen(n: Int): Number1                           = Fusion.number1tGen(n, Fusion.number1t)
def number2PositiveGen(n: Int, zero: => Number1): Number1 = Fusion.number1sGen(n, zero)
for {
    i1 <- 0 to 20
    i2 <- 0 to 20
  } {
    val number1                       = number1gen(i1)
    lazy val number2Positive: Number1 = number2PositiveGen(i2, number2Zero)
    lazy val number2Zero: Number1     = { Number1S(() => number2Positive) }
    {
      def counter1 = number1.method1(number2Positive)
      val result1  = true
      val result2  = true
      if (result1 != result2) {
        throw new Exception(
          s"Number Count Error with Number1T_Number1T_Number1S_Top.Number1SExe.list_round(i1 = $i1, i2Positive = $i2)"
        )
      }
    }
}
```

### number1t_top/Number1T_Number1T_Number1S_Top.scala
#### tt1-ss2P
