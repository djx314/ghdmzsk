package g02

object Runner2 {

  def count(number1: Number1): Int = number1 match {
    case Number1S(tail) => count(tail()) + 1
    case Number1U       => 0
  }

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1sGen(n - 1, zero)) else zero
  def number1tGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => number1tGen(n - 1, zero)) else zero

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1sGen(i1, Number1U)
        val number2 = number1tGen(i2, Number1V)
        val number3 = number2.method1(number1)
        val count1  = count(number3)
        assert(count1 == i1 + i2)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1sGen(i1, Number1U)
        val number2 = number1sGen(i2, Number1V)
        val number3 = number2.method1(number1)
        val count1  = count(number3)
        val count2  = if (i1 >= i2) i1 - i2 else 0
        assert(count1 == count2)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1                       = number1sGen(i1, Number1U)
        lazy val number2Positive: Number1 = number1tGen(i2, number2Zero)
        lazy val number2Zero: Number1     = Number1S(() => number2Positive)

        {
          val number3 = number1.method1(number2Positive)
          val count1  = count(number3)
          assert(count1 == i1 * i2)
        }
        {
          val number3 = number2Zero.method1(number1)
          val count1  = count(number3)
          assert(count1 == i1 * i2)
        }
      }
    }
    {
      for {
        i1 <- 0 to 5
        i2 <- 1 to 20
        i3 <- 0 to i2 - 1
      } {
        val number1                       = number1sGen(i1 * i2 + i3, Number1U)
        lazy val number2Positive: Number1 = number1sGen(i2, number2Zero)
        lazy val number2Zero: Number1     = Number1T(() => number2Positive)

        {
          val number3 = number1.method1(number2Zero)
          val count1  = count(number3)
          val count2  = if (i3 == 0) i1 else i1 + 1
          assert(count1 == count2)
        }
        {
          val number3 = number2Positive.method1(number1)
          val count1  = count(number3)
          assert(count1 == i1)
        }
      }
    }
  }

}
