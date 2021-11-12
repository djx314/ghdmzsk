package f01

object Fusion {

  lazy val number1s: Number1 = Number1S(() => number1s)
  lazy val number1t: Number1 = Number1T(() => number1t)
  lazy val number1u: Number1 = Number1U(() => number1u)
  lazy val number1v: Number1 = Number1V(() => number1v)

}

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
    try Option(count(number2))
    catch {
      case _: StackOverflowError => Option.empty
    }
  }
}

object Result {

  def result1(i1: Int, i2: Int): Int         = i1
  def result2(i1: Int, i2: Int): Option[Int] = Option.empty
  def result3(i1: Int, i2: Int): Int         = i1 + i2 + 1
  def result4(i1: Int, i2: Int): Int         = i1 + 1
  def result5(i1: Int, i2: Int): Int         = i1 + i2 * 2 + 1

}
