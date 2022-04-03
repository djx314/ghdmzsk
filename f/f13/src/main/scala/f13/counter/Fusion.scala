package f13.counter

object Fusion {

  lazy val number1s: Number1 = Number1S(() => number1s)
  lazy val number1t: Number1 = Number1T(() => number1t)
  lazy val number1u: Number1 = Number1U(() => number1u)
  lazy val number1v: Number1 = Number1V(() => number1v)
  lazy val number1w: Number1 = Number1W(() => number1w)
  lazy val number1x: Number1 = Number1X
  lazy val number1y: Number1 = Number1Y(() => number1y)
  lazy val number1z: Number1 = Number1Z

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1sGen(n - 1, zero)) else zero
  def number1tGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => number1tGen(n - 1, zero)) else zero
  def number1uGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1U(() => number1uGen(n - 1, zero)) else zero
  def number1vGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1V(() => number1vGen(n - 1, zero)) else zero
  def number1wGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1W(() => number1wGen(n - 1, zero)) else zero
  def number1yGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1Y(() => number1yGen(n - 1, zero)) else zero

}
