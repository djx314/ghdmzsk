package h01

object Runner {

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

  lazy val number1v: Number1 = Number1V(() => number1v)
  lazy val number1t: Number1 = Number1T(() => number1t)
  lazy val number1u: Number1 = Number1U(() => number1u)

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1sGen(n - 1, zero)) else zero
  def number1vGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1V(() => number1vGen(n - 1, zero)) else zero
  def number1uGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1U(() => number1uGen(n - 1, zero)) else zero

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1sGen(i1, number1v)

        {
          val number2 = number1sGen(i2, number1v)
          def number3 = number1.method1(number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
        {
          val number2 = number1sGen(i2, number1t)
          def number3 = number1.method1(number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
        {
          val number2 = number1uGen(i2, number1t)
          def number3 = number1.method1(number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
        {
          val number2 = number1uGen(i2, number1v)
          def number3 = number1.method1(number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }

        val number4 = number1uGen(i1, number1v)

        {
          val number5 = number1sGen(i2, number1v)
          def number6 = number4.method1(number5)
          val count1  = count(() => number6)
          assert(count1 == i1 + i2)
        }
        {
          val number5 = number1uGen(i2, number1v)
          def number6 = number4.method1(number5)
          val count1  = count(() => number6)
          assert(count1 == i1 + i2)
        }
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1vGen(i1, number1t)
        val number2 = number1vGen(i2, number1u)
        def number3 = number1.method1(number2)
        val count1  = count(() => number3)
        val count2  = if (i1 >= i2) i1 - i2 else 0
        assert(count1 == count2)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1                       = number1vGen(i1, number1t)
        lazy val number2Positive: Number1 = number1sGen(i2, number2Zero)
        lazy val number2Zero: Number1     = Number1V(() => number2Positive)

        {
          def number3 = number1.method1(number2Positive)
          val count1  = count(() => number3)
          assert(count1 == i1 * i2)
        }
        {
          def number3 = number2Zero.method1(number1)
          val count1  = count(() => number3)
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
        val number1                       = number1vGen(i1 * i2 + i3, number1t)
        lazy val number2Positive: Number1 = number1vGen(i2, number2Zero)
        lazy val number2Zero: Number1     = Number1S(() => number2Positive)

        {
          def number3 = number1.method1(number2Zero)
          val count1  = count(() => number3)
          val count2  = if (i3 == 0) i1 else i1 + 1
          assert(count1 == count2)
        }
        {
          def number3 = number2Positive.method1(number1)
          val count1  = count(() => number3)
          assert(count1 == i1)
        }
      }
    }
  }

}
