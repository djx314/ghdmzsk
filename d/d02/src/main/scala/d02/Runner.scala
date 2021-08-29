package d02

import d01._

import scala.util.Random

object Runner {

  class TypeContext1[S] extends TypeContext {
    override type DataCtx    = (Number[S], Number[Unit])
    override type toDataType = Number[Unit]
    override type Parameter  = Unit
    override type Result     = S
  }
  class Context1[S] extends Context[TypeContext1[S], S] {
    override def convert(t: Number[Unit], current: Number[S]): (Number[S], Number[Unit]) = (current, t)
    override def bindS(number: (Number[S], Number[Unit]), parameter: Unit, head: S): Collect[S] =
      number._2.execute(new Context2[S])(head, number._1)
    override def bindT(number: (Number[S], Number[Unit]), parameter: Unit): Collect[S] = number._2.execute(new Context3[S])((), number._1)
  }

  class TypeContext2[S] extends TypeContext {
    override type DataCtx    = (Number[S], Number[Unit])
    override type toDataType = Number[S]
    override type Parameter  = S
    override type Result     = S
  }
  class Context2[S] extends Context[TypeContext2[S], Unit] {
    override def convert(t: Number[S], current: Number[Unit]): (Number[S], Number[Unit]) = (t, current)
    override def bindS(number: (Number[S], Number[Unit]), parameter: S, head: Unit): Collect[S] =
      number._1.execute(new Context1[S])((), number._2)
    override def bindT(number: (Number[S], Number[Unit]), parameter: S): Collect[S] =
      CollectS(number._1.execute(new Context1[S])((), number._2), parameter)
  }

  class TypeContext3[S] extends TypeContext {
    override type DataCtx    = (Number[S], Number[Unit])
    override type toDataType = Number[S]
    override type Parameter  = Unit
    override type Result     = S
  }
  class Context3[S] extends Context[TypeContext3[S], Unit] {
    override def convert(t: Number[S], current: Number[Unit]): (Number[S], Number[Unit]) = (t, current)
    override def bindS(number: (Number[S], Number[Unit]), parameter: Unit, head: Unit): Collect[S] =
      number._1.execute(new Context1[S])((), number._2)
    override def bindT(number: (Number[S], Number[Unit]), parameter: Unit): Collect[S] = CollectT()
  }

  def zero[T]: Number[T] = {
    lazy val number2Zero: Number[T] = NumberT(() => number2Zero)
    number2Zero
  }

  def dropFromInt(n: Int): Number[Unit] = n match {
    case n1 if n1 > 0 => NumberS(() => dropFromInt(n1 - 1), ())
    case 0            => zero
  }

  def numberFromCollection[A](n: IterableOnce[A]): Number[A] = {
    val iterator            = n.iterator
    def toNumber: Number[A] = if (iterator.hasNext) NumberS(() => toNumber, iterator.next()) else zero
    toNumber
  }

  def number1ToList[T](number1: Collect[T]): List[T] = number1 match {
    case CollectS(tail, head) => head :: number1ToList(tail)
    case CollectT()           => List.empty
  }

  def main(args: Array[String]): Unit = {
    for {
      i1 <- 1 to 500
      i2 <- 1 to 500
    } yield {
      val i3       = Random.nextInt(300)
      val col1     = i1 to (i1 + i2)
      val right    = col1.to(List).drop(i3)
      val leftNum1 = numberFromCollection(col1)
      val leftNum2 = dropFromInt(i3)
      val leftCol  = leftNum1.execute(new Context1[Int])((), leftNum2)
      val left     = number1ToList(leftCol)
      assert(left == right)
      val size = if (col1.length - i3 >= 0) col1.length - i3 else 0
      assert(left.length == size)
      assert(right.length == size)
    }
  }
}
