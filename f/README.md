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

object Counter {
  def count(number2: () => Number2): Int = {
    val value =
      try Option(number2())
      catch {
        case _: StackOverflowError => Option.empty
      }
    value match {
      case Some(Number2S(tail)) => count(tail) + 1
      case None                 => 0
    }
  }

  def countOpt(number2: () => Number2): Option[Int] = {
    try Option(count(number2)).filter(_ < 500)
    catch {
      case _: StackOverflowError => Option.empty
    }
  }
}

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
    lazy val number2Zero: Number1     = { Number1T(() => number2Positive) }
    {
      def counter1 = number2Zero.method1(number1)
      val result1  = Counter.countOpt(() => counter1)
      val result2  = if (i2 == 0) Option(0) else Option.empty
      if (result1 != result2) {
        throw new Exception()
      }
    }
}
println("success")
```

## number1t_top/Number1T_Number1T_Number1S_Top.scala
### tt1-ss2
#### tt1-ss2P
Counter.count = 0
#### tt1-ss2Z
Counter.count = 0
#### ss2P-tt1
Counter.countOpt = Option.empty
#### ss2Z-tt1
Counter.countOpt = Option.empty

### tt1-st2
#### tt1-st2P
Counter.count = 0
#### tt1-st2Z
Counter.count = 0
#### st2P-tt1
Counter.countOpt = if (i2 == 0) Option(0) else Option.empty
#### st2Z-tt1
Counter.countOpt = if (i2 == 0) Option(0) else Option.empty

### tt1-su2
#### tt1-su2P
Counter.count = 0
#### tt1-su2Z
Counter.count = 0
#### tt1-su2P
Counter.count = i2 + 1
#### tt1-su2Z
Counter.count = 1

### tt1-sv2
#### tt1-sv2P
Counter.count = 0
#### tt1-sv2Z
Counter.count = 0
#### sv2P-tt1
Counter.count = i2
#### sv2Z-tt1
Counter.count = 0

## number1t_top/Number1T_Number1T_Number1T_Top.scala
## tt1-ts2
### tt1-ts2P
Counter.count = 0
### tt1-ts2Z
Counter.count = 0
### ts2P-tt1
Counter.countOpt = Option.empty
### ts2Z-tt1
Counter.countOpt = Option.empty

## tt1-tt2
### tt1-tt2P
Counter.count = 0
### tt1-tt2Z
Counter.count = 0
### tt2P-tt1
Counter.count = 0
### tt2Z-tt1
Counter.count = 0

## tt1-tu2
### tt1-tu2P
Counter.count = 0
### tt1-tu2Z
Counter.count = 0
### tu2P-tt1
Counter.count = 1
### tu2Z-tt1
Counter.count = 1