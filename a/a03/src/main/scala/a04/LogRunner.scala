package a04

object LogRunner {

  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  def number1(zero: => Number1): Number1 = Number1S(() => Number1S(() => zero))
  def number2(zero: => Number1): Number1 = number1(number1(number1(zero)))
  def number3(zero: => Number1): Number1 = number2(number2(number2(zero)))
  def number4(zero: => Number1): Number1 = number3(number3(number3(zero)))
  def number5(zero: => Number1): Number1 = number4(number4(number4(zero)))
  def number6(zero: => Number1): Number1 = number5(number5(number5(zero)))
  def number7(zero: => Number1): Number1 = number6(number6(number6(zero)))

  lazy val num1 = number1(Number1T(() => num2))
  lazy val num2 = number2(Number1T(() => num3))
  lazy val num3 = number3(Number1T(() => num4))
  lazy val num4 = number4(Number1T(() => num5))
  lazy val num5 = number5(Number1T(() => num6))
  lazy val num6 = number6(Number1T(() => num7))
  lazy val num7 = number7(Number1T(() => null))

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(arr: Array[String]): Unit = for (i2 <- 0 to 2000) yield {
    val number2 = number2Gen(i2)
    val number3 = number2.method2(num1)

    val count1  = count(number3)
    val result1 = log(3, i2)

    println(3, i2, count1, result1)
    assert(count1 == result1)
  }

}
