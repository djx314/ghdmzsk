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
      case Some(Number1X)       => 0
      case Some(Number1W)       => 0
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
