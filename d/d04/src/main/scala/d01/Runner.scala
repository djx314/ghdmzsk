package d01

import scala.util.Random

object Runner {

  class DropTypeContext[S, R] extends TypeContext {
    override type fromDataCtx = (Number2[Unit, Unit], Number2[S => Boolean, Unit], Number2[S => R, Unit])
    override type toDataCtx   = (Number2[S => Boolean, Unit], Number2[S => R, Unit], Number2[S, Unit])
    override type toDataType  = Number2[Unit, Unit]
    override type Parameter   = Unit
    override type Result      = R
  }
  class DropContext[S, R] extends Context[S, Unit] {
    /*override def convert(
      t: (Number2[Unit, Unit], Number2[S => Boolean, Unit], Number2[S => R, Unit]),
      current: Number2[S, Unit]
    ): ((Number2[S => Boolean, Unit], Number2[S => R, Unit], Number2[S, Unit]), Number2[Unit, Unit]) = ((t._2, t._3, current), t._1)
    override def bindS(
      number: (Number2[S => Boolean, Unit], Number2[S => R, Unit], Number2[S, Unit]),
      current: Number2[Unit, Unit],
      parameter: Unit,
      head: S
    ): Number1[R] = Number1T
    override def bindT(
      number: (Number2[S => Boolean, Unit], Number2[S => R, Unit], Number2[S, Unit]),
      current: Number2[Unit, Unit],
      parameter: Unit,
      head: Unit
    ): Number1[R] = Number1T*/
    override def bindS[T <: TypeContext](number: T#toDataCtx, current: T#toDataType, parameter: T#Parameter, head: S): Number1[T#Result] =
      Number1T
    override def bindT[T <: TypeContext](
      number: T#toDataCtx,
      current: T#toDataType,
      parameter: T#Parameter,
      head: Unit
    ): Number1[T#Result] = Number1T
  }

  class ReverseDropContext[S, R] extends TypeContext {
    override type fromDataCtx = (Number2[S => Boolean, Unit], Number2[S => R, Unit], Number2[S, Unit])
    override type toDataCtx   = (Number2[Unit, Unit], Number2[S => Boolean, Unit], Number2[S => R, Unit])
    override type toDataType  = Number2[S, Unit]
    override type Parameter   = S
    override type Result      = S
  }
  class ReverseDropContext[S, R] extends Context[ReverseDropContext[S, R], Unit, Unit] {
    override def convert(
      t: (Number2[S => Boolean, Unit], Number2[S => R, Unit], Number2[S, Unit]),
      current: Number2[Unit, Unit]
    ): ((Number2[Unit, Unit], Number2[S => Boolean, Unit], Number2[S => R, Unit]), Number2[S, Unit]) = ((current, t._1, t._2), t._3)
    override def bindS(
      number: (Number2[Unit, Unit], Number2[S => Boolean, Unit], Number2[S => R, Unit]),
      current: Number2[S, Unit],
      parameter: S,
      head: Unit
    ): Number1[S] = Number1T
    override def bindT(
      number: (Number2[Unit, Unit], Number2[S => Boolean, Unit], Number2[S => R, Unit]),
      current: Number2[S, Unit],
      parameter: S,
      head: Unit
    ): Number1[S] = Number1T
  }

  class TypeContext3[S] extends TypeContext {
    override type fromDataCtx = Number2[S, Unit]
    override type toDataCtx   = Number2[Unit, Unit]
    override type toDataType  = Number2[S, Unit]
    override type Parameter   = Unit
    override type Result      = S
  }
  class Context3[S] extends Context[TypeContext3[S], Unit, Unit] {
    override def convert(t: Number2[S, Unit], current: Number2[Unit, Unit]): (Number2[Unit, Unit], Number2[S, Unit])    = (current, t)
    override def bindS(number: Number2[Unit, Unit], current: Number2[S, Unit], parameter: Unit, head: Unit): Number1[S] = Number1T
    override def bindT(number: Number2[Unit, Unit], current: Number2[S, Unit], parameter: Unit, head: Unit): Number1[S] = Number1T
  }

  lazy val number2Zero: Number2[Any, Unit] = Number2T(() => number2Zero, ())
  def zero[T]: Number2[T, Unit]            = number2Zero.asInstanceOf[Number2[T, Unit]]

  def dropFromInt(n: Int): Number2[Unit, Unit] = n match {
    case n1 if n1 > 0 => Number2S(() => dropFromInt(n1 - 1), ())
    case 0            => zero[Unit]
  }

  def numberFromCollection[A](n: IterableOnce[A]): Number2[A, Unit] = {
    val iterator = n.iterator
    if (iterator.hasNext) {
      val next = iterator.next()
      Number2S(() => numberFromCollection(iterator), next)
    } else zero[A]
  }

  def number1ToList[T](number1: Number1[T]): List[T] = number1 match {
    case Number1S(tail, head) => head :: number1ToList(tail)
    case Number1T             => List.empty
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
