package g01

object Runner2 {

  def count(number1: Number1): Int = number1 match {
    case Number1S(tail) => count(tail) + 1
    case Number1T       => 0
  }

  def 被加数_被减数_被乘数_被除数(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(被加数_被减数_被乘数_被除数(n1 - 1))
    case 0            => Number1T
  }
  def 加数(n: Int): Number2 = n match {
    case n1 if n1 > 0 => Number2S(() => 加数(n1 - 1))
    case 0            => Number2U
  }
  def 减数(n: Int): Number2 = n match {
    case n1 if n1 > 0 => Number2T(() => 减数(n1 - 1))
    case 0            => Number2U
  }
  def 乘数(n: Int): (Number2, Number2) = {
    def gen(n1: Int, zero: => Number2): Number2 = n1 match {
      case n2 if n2 > 0 => Number2S(() => gen(n2 - 1, zero))
      case 0            => zero
    }
    lazy val number2s: Number2 = gen(n, number2t)
    lazy val number2t: Number2 = Number2T(() => number2s)
    (number2s, number2t)
  }
  def 除数(n: Int): (Number2, Number2) = {
    def gen(n1: Int, zero: => Number2): Number2 = n1 match {
      case n2 if n2 > 0 => Number2T(() => gen(n2 - 1, zero))
      case 0            => zero
    }
    lazy val number2t: Number2 = gen(n, number2s)
    lazy val number2s: Number2 = Number2S(() => number2t)
    (number2t, number2s)
  }

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = 被加数_被减数_被乘数_被除数(i1)
        val number2 = 加数(i2)
        val number3 = number2.method2(number1)
        val count1  = count(number3)
        assert(count1 == i1 + i2)
      }
    }
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val number1 = 被加数_被减数_被乘数_被除数(i1)
        val number2 = 减数(i2)
        val number3 = number2.method2(number1)
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
        val number1                        = 被加数_被减数_被乘数_被除数(i1)
        val (number2Positive, number2Zero) = 乘数(i2)

        {
          val number3 = number1.method1(number2Positive)
          val count1  = count(number3)
          assert(count1 == i1 * i2)
        }
        {
          val number3 = number2Zero.method2(number1)
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
        val number1                        = 被加数_被减数_被乘数_被除数(i1 * i2 + i3)
        val (number2Positive, number2Zero) = 除数(i2)

        {
          val number3 = number1.method1(number2Zero)
          val count1  = count(number3)
          val count2  = if (i3 == 0) i1 else i1 + 1
          assert(count1 == count2)
        }
        {
          val number3 = number2Positive.method2(number1)
          val count1  = count(number3)
          assert(count1 == i1)
        }
      }
    }
  }

}
