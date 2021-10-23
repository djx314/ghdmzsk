package h01

object Runner {

  def count(number2: () => Number2): Int = {
    val value =
      try {
        Option(number2())
      } catch {
        case _: StackOverflowError => Option.empty
      }
    value match {
      case Some(Number2S(tail)) => count(tail) + 1
      case None                 => 0
    }
  }

  lazy val number1vZero: Number1 = Number1V(() => number1vZero)
  lazy val number1tZero: Number1 = Number1T(() => number1tZero)
  lazy val number1uZero: Number1 = Number1U(() => number1uZero)

  def 加数_被加数(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(() => 加数_被加数(n1 - 1))
    case 0            => number1vZero
  }
  def 被减数_被乘数_被除数(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1V(() => 被减数_被乘数_被除数(n1 - 1))
    case 0            => number1tZero
  }
  def 减数(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1V(() => 减数(n1 - 1))
    case 0            => number1uZero
  }
  def 乘数(n: Int): (Number1, Number1) = {
    def gen(n1: Int, zero: => Number1): Number1 = n1 match {
      case n2 if n2 > 0 => Number1S(() => gen(n2 - 1, zero))
      case 0            => zero
    }
    lazy val number1s: Number1 = gen(n, number1v)
    lazy val number1v: Number1 = Number1V(() => number1s)
    (number1s, number1v)
  }
  def 除数(n: Int): (Number1, Number1) = {
    def gen(n1: Int, zero: => Number1): Number1 = n1 match {
      case n2 if n2 > 0 => Number1V(() => gen(n2 - 1, zero))
      case 0            => zero
    }
    lazy val number1v: Number1 = gen(n, number1s)
    lazy val number1s: Number1 = Number1S(() => number1v)
    (number1v, number1s)
  }

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = 加数_被加数(i1)
        val number2 = 加数_被加数(i2)

        {
          def number3 = number1.method1(number2)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
        {
          def number3 = number2.method1(number1)
          val count1  = count(() => number3)
          assert(count1 == i1 + i2)
        }
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = 被减数_被乘数_被除数(i1)
        val number2 = 减数(i2)
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
        val number1                        = 被减数_被乘数_被除数(i1)
        val (number2Positive, number2Zero) = 乘数(i2)

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
        val number1                        = 被减数_被乘数_被除数(i1 * i2 + i3)
        val (number2Positive, number2Zero) = 除数(i2)

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
