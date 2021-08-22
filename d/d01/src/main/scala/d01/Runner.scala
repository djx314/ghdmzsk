package d01

object Runner {
  lazy val number2Zero: Number2 = Number2T(() => number2Zero)
  lazy val number5Zero: Number5 = Number5(() => number5Zero)

  def beMinusFromInt(n: Int): Number2 = n match {
    case n1 if n1 > 0 => Number2S(() => beMinusFromInt(n1 - 1), Item("$n"))
    case 0            => number2Zero
  }

  def minusFromInt(n: Int): Number3 = n match {
    case n1 if n1 > 0 => Number4(() => minusFromInt(n1 - 1))
    case 0            => number5Zero
  }

  def main(args: Array[String]): Unit = {
    {
      val minus = minusFromInt(20)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        assert(beMinus.send0(minus).length == (if (i - 20 >= 0) i - 20 else 0))
      }
    }
    {
      val minus = minusFromInt(0)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        assert(beMinus.send0(minus).length == i)
      }
    }
    {
      val minus = minusFromInt(5000)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        assert(beMinus.send0(minus).length == 0)
      }
    }
  }
}
