package d01

object Runner {
  val item01 = Item("01")
  val item02 = Item("02")
  val item03 = Item("03")
  val item04 = Item("04")
  val item05 = Item("05")
  val item06 = Item("06")
  val item07 = Item("07")
  val item08 = Item("08")
  val item09 = Item("09")
  val item10 = Item("10")
  val item11 = Item("11")

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
      for (i <- 0 to 10000) {
        val beMinus = beMinusFromInt(i)
        assert(beMinus.send0(minus).length == (if (i - 20 >= 0) i - 20 else 0))
      }
    }
  }
}
