package d01

import scala.util.Random

object Runner {

  class TypeContext1[S] extends TypeContext {
    override type DataCtx    = (Number2[S], Number2[Unit])
    override type toDataType = Number2[Unit]
    override type Parameter  = Unit
    override type Result     = S
  }
  class Context1[S] extends Context[TypeContext1[S], S] {
    override def convert(t: Number2[Unit], current: Number2[S]): (Number2[S], Number2[Unit]) = (current, t)
    override def bindS(number: (Number2[S], Number2[Unit]), parameter: Unit, head: S): Number1[S] =
      number._2.execute(new Context2[S])(head, number._1)
    override def bindT(number: (Number2[S], Number2[Unit]), parameter: Unit): Number1[S] = number._2.execute(new Context3[S])((), number._1)
  }

  class TypeContext2[S] extends TypeContext {
    override type DataCtx    = (Number2[S], Number2[Unit])
    override type toDataType = Number2[S]
    override type Parameter  = S
    override type Result     = S
  }
  class Context2[S] extends Context[TypeContext2[S], Unit] {
    override def convert(t: Number2[S], current: Number2[Unit]): (Number2[S], Number2[Unit]) = (t, current)
    override def bindS(number: (Number2[S], Number2[Unit]), parameter: S, head: Unit): Number1[S] =
      number._1.execute(new Context1[S])((), number._2)
    override def bindT(number: (Number2[S], Number2[Unit]), parameter: S): Number1[S] =
      Number1S(number._1.execute(new Context1[S])((), number._2), parameter)
  }

  class TypeContext3[S] extends TypeContext {
    override type DataCtx    = (Number2[S], Number2[Unit])
    override type toDataType = Number2[S]
    override type Parameter  = Unit
    override type Result     = S
  }
  class Context3[S] extends Context[TypeContext3[S], Unit] {
    override def convert(t: Number2[S], current: Number2[Unit]): (Number2[S], Number2[Unit]) = (t, current)
    override def bindS(number: (Number2[S], Number2[Unit]), parameter: Unit, head: Unit): Number1[S] =
      number._1.execute(new Context1[S])((), number._2)
    override def bindT(number: (Number2[S], Number2[Unit]), parameter: Unit): Number1[S] = Number1T()
  }

  def zero[T]: Number2[T] = {
    lazy val number2Zero: Number2[T] = Number2T(() => number2Zero)
    number2Zero
  }

  def dropFromInt(n: Int): Number2[Unit] = n match {
    case n1 if n1 > 0 => Number2S(() => dropFromInt(n1 - 1), ())
    case 0            => zero
  }

  def numberFromCollection[A](n: IterableOnce[A]): Number2[A] = {
    val iterator             = n.iterator
    def toNumber: Number2[A] = if (iterator.hasNext) Number2S(() => toNumber, iterator.next()) else zero
    toNumber
  }

  def number1ToList[T](number1: Number1[T]): List[T] = number1 match {
    case Number1S(tail, head) => head :: number1ToList(tail)
    case Number1T()           => List.empty
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
