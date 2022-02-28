package f03.counter

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
      case Some(Number1A)       => 0
      case Some(Number1B)       => 0
      case Some(Number1C(tail)) => count(tail) + 1
      case Some(Number1D(tail)) => count(tail) + 1
      case Some(Number1E(tail)) => count(tail) + 1
      case Some(Number1F(tail)) => count(tail) + 1
      case Some(Number1G(tail)) => count(tail) + 1
      case Some(Number1H(tail)) => count(tail) + 1
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
