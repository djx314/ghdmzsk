package f07

object Fusion {

  lazy val number1s: Number1 = Number1S(() => number1s)
  lazy val number1t: Number1 = Number1T(() => number1t)
  lazy val number1u: Number1 = Number1U(() => number1u)
  lazy val number1v: Number1 = Number1V(() => number1v)
  lazy val number1w: Number1 = Number1W
  lazy val number1x: Number1 = Number1X
  lazy val number1y: Number1 = Number1Y(() => number1y)
  lazy val number1z: Number1 = Number1Z(() => number1z)

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1sGen(n - 1, zero)) else zero
  def number1tGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => number1tGen(n - 1, zero)) else zero
  def number1uGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1U(() => number1uGen(n - 1, zero)) else zero
  def number1vGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1V(() => number1vGen(n - 1, zero)) else zero
  def number1yGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1Y(() => number1yGen(n - 1, zero)) else zero
  def number1zGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1Z(() => number1zGen(n - 1, zero)) else zero

}

object Counter {
  def count(number2: () => Number1): Int = {
    val value =
      try Option(number2())
      catch {
        case _: StackOverflowError => Option.empty
      }
    value match {
      case Some(Number1S(tail)) => count(tail) + 1
      case Some(Number1T(tail)) => count(tail) + 1
      case Some(Number1U(tail)) => count(tail) + 1
      case Some(Number1V(tail)) => count(tail) + 1
      case Some(Number1W)       => 0
      case Some(Number1X)       => 0
      case Some(Number1Y(tail)) => count(tail) + 1
      case Some(Number1Z(tail)) => count(tail) + 1
      case None                 => 0
    }
  }

  def countOpt(number2: () => Number1): Option[Int] = {
    try Option(count(number2)).filter(_ < 500)
    catch {
      case _: StackOverflowError => Option.empty
    }
  }
}
