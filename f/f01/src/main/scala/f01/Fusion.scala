package f01

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

object Result {

  def result1(i1: Int, i2: Int): Int          = i1
  def result2(i1: Int, i2: Int): Option[Int]  = Option.empty
  def result3(i1: Int, i2: Int): Int          = i1 + i2 + 1
  def result4(i1: Int, i2: Int): Int          = i1 + 1
  def result5(i1: Int, i2: Int): Int          = i1 + i2 * 2 + 1
  def result6(i1: Int, i2: Int): Int          = i1 + i2
  def result7(i1: Int, i2: Int): Int          = if (i1 - i2 >= 0) i1 - i2 else 0
  def result8(i1: Int, i2: Int): Int          = i2
  def result9(i1: Int, i2: Int): Int          = 0
  def result10(i1: Int, i2: Int): Int         = i2 + 1
  def result11(i1: Int, i2: Int): Int         = 1
  def result12(i1: Int, i2: Int): Int         = i2 * 2 + 1
  def result13(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option.empty else Option(i2 + 1)
  def result14(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option.empty else Option(1)
  def result15(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option(i2 * 2 + 1) else Option.empty
  def result16(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option(i2 + 1) else Option.empty
  def result17(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else Option.empty
  def result18(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i2 + 1
  def result19(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i1 * 2 + i2
  def result20(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i1 + i2
  def result21(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else 1
  def result22(i1: Int, i2: Int): Int         = i1 * 2

}
