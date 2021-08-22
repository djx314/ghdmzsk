package d01

object Runner {
  lazy val number2Zero: Number2 = Number2T(() => number2Zero, Item("01"))

  def beMinusFromInt(n: Int): Number2 = n match {
    case n1 if n1 > 0 => Number2S(() => beMinusFromInt(n1 - 1), Item("$n"))
    case 0            => number2Zero
  }

  // Implement 1
  lazy val context1: Context[Unit, Number2] = new Context[Unit, Number2] {
    override def convert(dataCtx: Number2, current: Number2): (Number2, Number2)                 = (current, dataCtx)
    override def bindS(dataCtx: Number2, current: Number2, parameter: Unit, head: Item): Number1 = current.execute(context2, head, dataCtx)
    override def bindT(dataCtx: Number2, current: Number2, parameter: Unit, head: Item): Number1 = current.execute(context3, head, dataCtx)
  }

  lazy val context2: Context[Item, Number2] = new Context[Item, Number2] {
    override def convert(dataCtx: Number2, current: Number2): (Number2, Number2)                 = (current, dataCtx)
    override def bindS(dataCtx: Number2, current: Number2, parameter: Item, head: Item): Number1 = current.execute(context1, (), dataCtx)
    override def bindT(dataCtx: Number2, current: Number2, parameter: Item, head: Item): Number1 =
      Number1S(current.execute(context1, (), dataCtx), parameter)
  }

  lazy val context3: Context[Item, Number2] = new Context[Item, Number2] {
    override def convert(dataCtx: Number2, current: Number2): (Number2, Number2)                 = (current, dataCtx)
    override def bindS(dataCtx: Number2, current: Number2, parameter: Item, head: Item): Number1 = current.execute(context1, (), dataCtx)
    override def bindT(dataCtx: Number2, current: Number2, parameter: Item, head: Item): Number1 = Number1T
  }

  // Implement 2
  trait TextContext1[T] extends Context[T, Number2] {
    override def convert(dataCtx: Number2, current: Number2): (Number2, Number2) = (current, dataCtx)
  }
  object TextContext2 extends TextContext1[Unit] {
    override def bindS(dataCtx: Number2, current: Number2, parameter: Unit, head: Item): Number1 =
      current.execute(TextContext4, head, dataCtx)
    override def bindT(dataCtx: Number2, current: Number2, parameter: Unit, head: Item): Number1 =
      current.execute(TextContext5, head, dataCtx)
  }
  trait TextContext3 extends TextContext1[Item] {
    override def bindS(dataCtx: Number2, current: Number2, parameter: Item, head: Item): Number1 =
      current.execute(TextContext2, (), dataCtx)
  }
  object TextContext4 extends TextContext3 {
    override def bindT(dataCtx: Number2, current: Number2, parameter: Item, head: Item): Number1 =
      Number1S(current.execute(context1, (), dataCtx), parameter)
  }
  object TextContext5 extends TextContext3 {
    override def bindT(dataCtx: Number2, current: Number2, parameter: Item, head: Item): Number1 = Number1T
  }

  def main(args: Array[String]): Unit = {
    {
      val minus = beMinusFromInt(20)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        assert(beMinus.execute(context1, (), minus).length == (if (i - 20 >= 0) i - 20 else 0))
      }
    }
    {
      val minus = beMinusFromInt(32)
      for (i <- 0 to 5000) {
        val beMinus = beMinusFromInt(i)
        assert(beMinus.execute(TextContext2, (), minus).length == (if (i - 32 >= 0) i - 32 else 0))
      }
    }
  }
}
