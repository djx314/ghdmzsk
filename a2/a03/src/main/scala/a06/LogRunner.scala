package a06

object LogRunner {

  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  def number1(zero: Number4): Number1 = Number1S(() => Number1S(() => Number1V(() => zero)))

  def number2(zero: Number4): Number1 = {
    lazy val num1: Number1     = number1(num1Zero)
    lazy val num1Zero: Number4 = Number4S(() => num2)

    lazy val num2: Number1     = number1(num2Zero)
    lazy val num2Zero: Number4 = Number4S(() => num3)

    lazy val num3: Number1 = number1(zero)

    num1
  }

  def number3(zero: Number4): Number1 = {
    lazy val num1: Number1     = number2(num1Zero)
    lazy val num1Zero: Number4 = Number4S(() => num2)

    lazy val num2: Number1     = number2(num2Zero)
    lazy val num2Zero: Number4 = Number4S(() => num3)

    lazy val num3: Number1 = number2(zero)

    num1
  }

  def number4(zero: Number4): Number1 = {
    lazy val num1: Number1     = number3(num1Zero)
    lazy val num1Zero: Number4 = Number4S(() => num2)

    lazy val num2: Number1     = number3(num2Zero)
    lazy val num2Zero: Number4 = Number4S(() => num3)

    lazy val num3: Number1 = number3(zero)

    num1
  }

  def number5(zero: Number4): Number1 = {
    lazy val num1: Number1     = number4(num1Zero)
    lazy val num1Zero: Number4 = Number4S(() => num2)

    lazy val num2: Number1     = number4(num2Zero)
    lazy val num2Zero: Number4 = Number4S(() => num3)

    lazy val num3: Number1 = number4(zero)

    num1
  }

  def number6(zero: Number4): Number1 = {
    lazy val num1: Number1     = number5(num1Zero)
    lazy val num1Zero: Number4 = Number4S(() => num2)

    lazy val num2: Number1     = number5(num2Zero)
    lazy val num2Zero: Number4 = Number4S(() => num3)

    lazy val num3: Number1 = number5(zero)

    num1
  }

  def number7(zero: Number4): Number1 = {
    lazy val num1: Number1     = number6(num1Zero)
    lazy val num1Zero: Number4 = Number4S(() => num2)

    lazy val num2: Number1     = number6(num2Zero)
    lazy val num2Zero: Number4 = Number4S(() => num3)

    lazy val num3: Number1 = number6(zero)

    num1
  }

  lazy val n1     = number1(n1Zero)
  lazy val n1Zero = Number4T(() => n2)
  lazy val n2     = number2(n2Zero)
  lazy val n2Zero = Number4T(() => n3)
  lazy val n3     = number3(n3Zero)
  lazy val n3Zero = Number4T(() => n4)
  lazy val n4     = number4(n4Zero)
  lazy val n4Zero = Number4T(() => n5)
  lazy val n5     = number5(n5Zero)
  lazy val n5Zero = Number4T(() => n6)
  lazy val n6     = number6(n6Zero)
  lazy val n6Zero = Number4T(() => n7)
  lazy val n7     = number7(n7Zero)
  lazy val n7Zero = Number4T(() => null)

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(arr: Array[String]): Unit = for (i2 <- 0 to 2000) yield {
    val number2 = number2Gen(i2)
    val number3 = number2.method2(n1)

    val count1  = count(number3)
    val result1 = log(3, i2)

    println(3, i2, count1, result1)
    assert(count1 == result1)
  }

}
