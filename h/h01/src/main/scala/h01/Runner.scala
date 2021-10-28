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

  def 加数_被加数(n: Int): Number1 = {
    val tail = () => 加数_被加数(n - 1)
    if (n > 0) Number1S(tail) else Number1V(tail)
  }
  def 被减数_被乘数_被除数(n: Int): Number1 = {
    val tail = () => 被减数_被乘数_被除数(n - 1)
    if (n > 0) Number1V(tail) else Number1T(tail)
  }
  def 减数(n: Int): Number1 = {
    val tail = () => 减数(n - 1)
    if (n > 0) Number1V(tail) else Number1U(tail)
  }
  def 乘数_除数(numbers: Int, numbert: Int): (Number1, Number1) = {
    def gen1(n3: Int, zero: => Number1): Number1 = n3 match {
      case n4 if n4 > 0 => Number1S(() => gen1(n4 - 1, zero))
      case 0            => zero
    }
    def gen2(n3: Int, zero: => Number1): Number1 = n3 match {
      case n4 if n4 > 0 => Number1V(() => gen2(n4 - 1, zero))
      case 0            => zero
    }
    lazy val numberTypes: Number1 = gen1(numbers, numberTypev)
    lazy val numberTypev: Number1 = gen2(numbert, numberTypes)
    (numberTypes, numberTypev)
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
        val (number2Positive, number2Zero) = 乘数_除数(numbers = i2, numbert = 1)

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
        val (number2Zero, number2Positive) = 乘数_除数(numbers = 1, numbert = i2)

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
