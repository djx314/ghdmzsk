package a04

object LogRunner {

  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  lazy val number1 = Number3S(() => Number3S(() => Number3T(() => number2_2), () =>  number2_1), () => number2_2)

  lazy val number2_1 = Number3S(() => Number3S(() => Number3S(() => Number3T(() => null), () => null), () => null), () => null)
  lazy val number2_2 = Number3S(() => Number3S(() => Number3S(() => Number3U(() => number2_1), () => null), () => null), () => null)

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0

  def count(num: Number5): Int = num match {
    case Number5S(tail) => count(tail) + 1
    case Number5T       => 0
  }

  def exec: Unit = for (i2 <- 0 to 2000) yield {
    val number2 = number2Gen(i2)
  }

}
