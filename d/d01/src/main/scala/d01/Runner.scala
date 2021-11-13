package d01

object Runner {

  def beMinusFromInt(n: Int): Number1 = if (n > 0) Number1S(beMinusFromInt(n - 1), Item(n)) else Number1T
  def minusFromInt(n: Int): Number2   = if (n > 0) Number2S(minusFromInt(n - 1), Item(n)) else Number2T
  def beMinusLength(number1: Number1): Int = number1 match {
    case Number1S(tail, _) => 1 + beMinusLength(tail)
    case Number1T          => 0
  }

  def main(args: Array[String]): Unit = {
    {
      val minus = minusFromInt(20)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        val value   = beMinusLength(beMinus.method1(minus))
        assert(value == (if (i - 20 >= 0) i - 20 else 0))
      }
    }
    {
      val minus = minusFromInt(0)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        val value   = beMinusLength(beMinus.method1(minus))
        assert(value == i)
      }
    }
    {
      val minus = minusFromInt(5000)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        val value   = beMinusLength(beMinus.method1(minus))
        assert(value == 0)
      }
    }
  }

}
