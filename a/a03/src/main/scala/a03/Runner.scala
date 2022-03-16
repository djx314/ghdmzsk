package a03

object Runner {

  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  lazy val number1Zero1: Number1 = Number1U(() => number1Zero1)
  lazy val number1Zero2: Number1 = Number1S(() => number1Zero1)

  def number1Gen1(n: Int): Number1                   = if (n > 0) Number1T(() => number1Gen1(n - 1)) else number1Zero2
  def number1Gen2(n: Int): Number1                   = if (n > 0) Number1S(() => number1Gen2(n - 1)) else number1Zero2
  def number1Gen3(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => number1Gen3(n - 1, zero)) else zero
  def number1Gen4(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1Gen4(n - 1, zero)) else zero
  def number1Gen5(n: Int): Number1 = {
    def repeat(n1: Int, n2: Int): Number1 = if (n2 > 0) Number1S(() => repeat(n1 + n - 1, n2 - 1)) else Number1T(() => repeat(n1, n1))
    repeat(0, n)
  }
  def number1Gen6(n: Int): Number1 = if (n > 0) Number1T(() => number1Gen6(n - 1)) else number1Zero1
  def number1Gen7(n: Int): Number1 = if (n > 0) Number1S(() => number1Gen7(n - 1)) else number1Zero1

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(args: Array[String]): Unit = {
    for {
      i1 <- 2 to 10
      i2 <- 1 to 400
    } {
      val number1 = number1Gen5(i1)
      val number2 = number2Gen(i2)
      val number3 = number1.method1(number2)

      val count1  = count(number3)
      val result1 = log(i1, i2)

      assert(count1 == result1)
    }
  }
}
