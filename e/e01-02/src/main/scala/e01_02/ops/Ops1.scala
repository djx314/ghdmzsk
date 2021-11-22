package e01_02.ops

import e01_02._

object Ops1 {

  class Ops_:::[A] extends TypeContext {
    override type toDataType = Number[A]
    override type Parameter  = Unit
    override type Result     = Number[A]
  }

  def ops_:::[A]: Context[Ops_:::[A], A] = new Context[Ops_:::[A], A] {
    override type DataCtx = (Number[A], () => Number[A])
    override def convertS(t: Number[A], current: () => Number[A]): (Number[A], () => Number[A]) = (t, current)
    override def convertT(t: Number[A], current: () => Number[A]): (Number[A], () => Number[A]) = (t, current)
    override def bindS(number: (Number[A], () => Number[A]), parameter: Unit, head: A): Number[A] =
      NumberS(() => number._2().execute(this)((), number._1), head)
    override def bindT(number: (Number[A], () => Number[A]), parameter: Unit): Number[A] = number._1
  }

  class Ops_Collect[A, B] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = PartialFunction[A, B]
    override type Result     = Number[B]
  }

  def ops_collect[A, B]: Context[Ops_Collect[A, B], A] = new Context[Ops_Collect[A, B], A] {
    override type DataCtx = Number[A]
    override def convertS(t: Unit, current: () => Number[A]): Number[A] = current()
    override def convertT(t: Unit, current: () => Number[A]): Number[A] = current()
    override def bindS(number: Number[A], parameter: PartialFunction[A, B], head: A): Number[B] = {
      val nextCollect = () => number.execute(this)(parameter, ())
      parameter.andThen(b => NumberS(nextCollect, b)).applyOrElse(head, (a: A) => nextCollect())
    }
    override def bindT(number: Number[A], parameter: PartialFunction[A, B]): Number[B] = Number.empty
  }

  class Ops_Contains[A1] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = A1
    override type Result     = Boolean
  }

  def ops_contains[A, A1 >: A]: Context[Ops_Contains[A1], A] = new Context[Ops_Contains[A1], A] {
    override type DataCtx = Number[A]
    override def convertS(t: Unit, current: () => Number[A]): Number[A] = current()
    override def convertT(t: Unit, current: () => Number[A]): Number[A] = current()
    override def bindS(number: Number[A], parameter: A1, head: A): Boolean =
      if (parameter == head) true else number.execute(this)(parameter, ())
    override def bindT(number: Number[A], parameter: A1): Boolean = false
  }

}
