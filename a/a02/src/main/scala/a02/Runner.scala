package a02

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
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1Gen6(i1)
        val number2 = number2Gen(i2)
        val number3 = number2.method2(number1)

        val count1  = count(number3)
        val result1 = if (i2 == 0) 0 else i1 + i2

        assert(count1 == result1)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1Gen7(i1)
        val number2 = number2Gen(i2)
        val number3 = number2.method2(number1)

        val count1  = count(number3)
        val result1 = if (i2 - i1 >= 0) i2 - i1 else 0

        assert(count1 == result1)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        lazy val number1s: Number1 = number1Gen3(i1, number1t)
        lazy val number1t: Number1 = Number1S(() => number1s)
        val number2                = number2Gen(i2)
        val number3                = number2.method2(number1s)

        val count1  = count(number3)
        val result1 = i1 * i2

        assert(count1 == result1)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        lazy val number1s: Number1 = number1Gen4(i1, number1t)
        lazy val number1t: Number1 = Number1T(() => number1s)
        val number2                = number2Gen(i2)
        val number3 =
          try Option(number2.method2(number1t))
          catch {
            case _: StackOverflowError => Option.empty
          }

        val count1  = number3.map(count)
        val result1 = if (i2 == 0) Option(0) else if (i1 == 0) Option.empty else if (i2 % i1 == 0) Option(i2 / i1) else Option(i2 / i1 + 1)

        assert(count1 == result1)
      }
    }
  }
}
