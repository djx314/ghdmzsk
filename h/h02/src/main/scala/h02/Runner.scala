package h02

object Runner {

  def count(number: () => Number): Int = {
    val opt =
      try Option(number())
      catch {
        case _: StackOverflowError =>
          Option.empty
      }
    opt match {
      case Some(Number.NumberPositive(tail)) => count(tail) + 1
      case _                                 => 0
    }
  }

  lazy val number1v: Number = Number.V(() => number1v)
  lazy val number1t: Number = Number.T(() => number1t)
  lazy val number1u: Number = Number.U(() => number1u)

  def number1sGen(n: Int, zero: => Number): Number = if (n > 0) Number.S(() => number1sGen(n - 1, zero)) else zero
  def number1vGen(n: Int, zero: => Number): Number = if (n > 0) Number.V(() => number1vGen(n - 1, zero)) else zero
  def number1uGen(n: Int, zero: => Number): Number = if (n > 0) Number.U(() => number1uGen(n - 1, zero)) else zero

  def main(arr: Array[String]): Unit = {
    locally {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1sGen(i1, number1v)
        locally {
          val number2 = number1sGen(i2, number1v)
          def number3 = number1.apply(() => number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
        locally {
          val number2 = number1sGen(i2, number1t)
          def number3 = number1.apply(() => number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
        locally {
          val number2 = number1uGen(i2, number1t)
          def number3 = number1.apply(() => number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
        locally {
          val number2 = number1uGen(i2, number1v)
          def number3 = number1.apply(() => number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }

        val number4 = number1uGen(i1, number1v)
        locally {
          val number5 = number1sGen(i2, number1v)
          def number6 = number4.apply(() => number5)
          val count1  = count(() => number6)
          assert(count1 == i1 + i2)
        }
        locally {
          val number5 = number1uGen(i2, number1v)
          def number6 = number4.apply(() => number5)
          val count1  = count(() => number6)
          assert(count1 == i1 + i2)
        }
      }
    }
    locally {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = number1vGen(i1, number1t)
        val number2 = number1vGen(i2, number1u)
        def number3 = number1.apply(() => number2)
        val count1  = count(() => number3)
        val count2  = if (i1 >= i2) i1 - i2 else 0
        assert(count1 == count2)
      }
    }
    locally {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1                      = number1vGen(i1, number1t)
        lazy val number2Positive: Number = number1sGen(i2, number2Zero)
        lazy val number2Zero: Number     = Number.V(() => number2Positive)
        locally {
          def number3 = number1.apply(() => number2Positive)
          val count1  = count(() => number3)
          assert(count1 == i1 * i2)
        }
        locally {
          def number3 = number2Zero.apply(() => number1)
          val count1  = count(() => number3)
          assert(count1 == i1 * i2)
        }
      }
    }
    locally {
      for {
        i1 <- 0 to 5
        i2 <- 1 to 20
        i3 <- 0 to i2 - 1
      } {
        val number1                      = number1vGen(i1 * i2 + i3, number1t)
        lazy val number2Positive: Number = number1vGen(i2, number2Zero)
        lazy val number2Zero: Number     = Number.S(() => number2Positive)
        locally {
          def number3 = number1.apply(() => number2Zero)
          val count1  = count(() => number3)
          val count2  = if (i3 == 0) i1 else i1 + 1
          assert(count1 == count2)
        }
        locally {
          def number3 = number2Positive.apply(() => number1)
          val count1  = count(() => number3)
          assert(count1 == i1)
        }
      }
    }
  }

}
