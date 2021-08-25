package d04

object Runner {

  class ListContext[S, R] extends TypeContext {
    override type DataCtx = (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R])
  }

  class DropTypeContext[S, R] extends ListContext[S, R] {
    override type toDataType = (Number2[Unit], Number2[S => Boolean], Number2[S => R])
    override type Parameter  = Unit
    override type Result     = R
  }
  class DropContext[S, R] extends Context[DropTypeContext[S, R], S] {
    override def convert(
      t: (Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      current: Number2[S]
    ): (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]) = (current, t._1, t._2, t._3)
    override def bindS(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: Unit,
      head: S
    ): Number1[R] = number._2.execute(new ReverseDropContext[S, R])(head, (number._1, number._3, number._4))
    override def bindT(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: Unit
    ): Number1[R] = Number1T()
  }

  class ReverseDropTypeContext[S, R] extends ListContext[S, R] {
    override type toDataType = (Number2[S], Number2[S => Boolean], Number2[S => R])
    override type Parameter  = S
    override type Result     = R
  }
  class ReverseDropContext[S, R] extends Context[ReverseDropTypeContext[S, R], Unit] {
    override def convert(
      t: (Number2[S], Number2[S => Boolean], Number2[S => R]),
      current: Number2[Unit]
    ): (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]) = (t._1, current, t._2, t._3)
    override def bindS(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: S,
      head: Unit
    ): Number1[R] = number._1.execute(new DropContext[S, R])((), (number._2, number._3, number._4))
    override def bindT(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: S
    ): Number1[R] = number._3.execute(new FilterContext[S, R])(parameter, (number._1, number._2, number._4))
  }

  class FilterTypeContext[S, R] extends ListContext[S, R] {
    override type toDataType = (Number2[S], Number2[Unit], Number2[S => R])
    override type Parameter  = S
    override type Result     = R
  }
  class FilterContext[S, R] extends Context[FilterTypeContext[S, R], S => Boolean] {
    override def convert(
      t: (Number2[S], Number2[Unit], Number2[S => R]),
      current: Number2[S => Boolean]
    ): (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]) = (t._1, t._2, current, t._3)
    override def bindS(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: S,
      head: S => Boolean
    ): Number1[R] = if (head(parameter)) number._4.execute(new MapContext[S, R])(parameter, (number._1, number._2, number._3))
    else number._1.execute(new DropContext[S, R])((), (number._2, number._3, number._4))
    override def bindT(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: S
    ): Number1[R] = number._3.execute(this)(parameter, (number._1, number._2, number._4))
  }

  class MapTypeContext[S, R] extends ListContext[S, R] {
    override type toDataType = (Number2[S], Number2[Unit], Number2[S => Boolean])
    override type Parameter  = S
    override type Result     = R
  }
  class MapContext[S, R] extends Context[MapTypeContext[S, R], S => R] {
    override def convert(
      t: (Number2[S], Number2[Unit], Number2[S => Boolean]),
      current: Number2[S => R]
    ): (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]) = (t._1, t._2, t._3, current)
    override def bindS(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: S,
      head: S => R
    ): Number1[R] = Number1S(number._1.execute(new DropContext[S, R])((), (number._2, number._3, number._4)), head(parameter))
    override def bindT(
      number: (Number2[S], Number2[Unit], Number2[S => Boolean], Number2[S => R]),
      parameter: S
    ): Number1[R] = number._4.execute(this)(parameter, (number._1, number._2, number._3))
  }

  def zeroNumber[T]: Number2[T] = {
    lazy val zeroNumber: Number2[T] = Number2T(() => zeroNumber)
    zeroNumber
  }

  def numberFromCollection[A](n: IterableOnce[A]): Number2[A] = {
    val iterator             = n.iterator
    def toNumber: Number2[A] = if (iterator.hasNext) Number2S(() => toNumber, iterator.next()) else zeroNumber
    toNumber
  }

  def dropFromInt(n: Int): Number2[Unit] = n match {
    case n1 if n1 > 0 => Number2S(() => dropFromInt(n1 - 1), ())
    case 0            => zeroNumber
  }

  def numberFromFun[S](filter: S): Number2[S] = {
    lazy val number1: Number2[S] = Number2S(() => number2, filter)
    lazy val number2: Number2[S] = Number2T(() => number1)
    number1
  }

  def number1ToList[T](number1: Number1[T]): List[T] = number1 match {
    case Number1S(tail, head) => head :: number1ToList(tail)
    case Number1T()           => List.empty
  }

  def printlnNumber1[T](number1: Number1[T]): String = number1 match {
    case Number1S(tail, head) => s"($head, ${printlnNumber1(tail)})"
    case Number1T()           => "zero"
  }

  def main(args: Array[String]): Unit = {
    val num1 = numberFromCollection(1 to 500).execute(new DropContext[Int, String])(
      (),
      (dropFromInt(60), numberFromFun((i: Int) => i < 70 || i % 5 == 0), numberFromFun((i: Int) => s"$i--$i"))
    )
    println(printlnNumber1(num1))
    val col1 = (1 to 500).drop(60).filter(i => i < 70 || i % 5 == 0).map(i => s"$i--$i").to(List)
    val col2 = number1ToList(num1)
    assert(col1 == col2)
  }
}
