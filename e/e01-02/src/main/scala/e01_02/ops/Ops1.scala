package e01_02.ops

import e01_02._

object Ops1 {

  class Ops_:::[A] extends TypeContext {
    override type toDataType = Number[A]
    override type Parameter  = Unit
    override type Result     = Number[A]
  }

  def ops_:::[A]: Context[Ops_:::[A], A] = new Context[Ops_:::[A], A] {
    override def bindS(t: Number[A], current: () => Number[A], parameter: Unit, head: A): Number[A] =
      NumberS(() => current().execute(this)((), t), head)
    override def bindT(t: Number[A], current: () => Number[A], parameter: Unit): Number[A] = t
  }

  class Ops_Collect[A, B] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = PartialFunction[A, B]
    override type Result     = Number[B]
  }

  def ops_collect[A, B]: Context[Ops_Collect[A, B], A] = new Context[Ops_Collect[A, B], A] {
    override def bindS(t: Unit, current: () => Number[A], parameter: PartialFunction[A, B], head: A): Number[B] = {
      val nextCollect = () => current().execute(this)(parameter, ())
      parameter.andThen(b => NumberS(nextCollect, b)).applyOrElse(head, (a: A) => nextCollect())
    }
    override def bindT(t: Unit, current: () => Number[A], parameter: PartialFunction[A, B]): Number[B] = Number.empty
  }

  class Ops_Contains[A1] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = A1
    override type Result     = Boolean
  }

  def ops_contains[A, A1 >: A]: Context[Ops_Contains[A1], A] = new Context[Ops_Contains[A1], A] {
    override def bindS(t: Unit, current: () => Number[A], parameter: A1, head: A): Boolean =
      if (parameter == head) true else current().execute(this)(parameter, ())
    override def bindT(t: Unit, current: () => Number[A], parameter: A1): Boolean = false
  }

  class Ops_Corresponds1[A, B] extends TypeContext {
    override type toDataType = Number[B]
    override type Parameter  = (A, B) => Boolean
    override type Result     = Boolean
  }

  def ops_corresponds1[A, B]: Context[Ops_Corresponds1[A, B], A] = new Context[Ops_Corresponds1[A, B], A] {
    override def bindS(t: Number[B], current: () => Number[A], parameter: (A, B) => Boolean, head: A): Boolean =
      t.execute(ops_corresponds2[A, B])((parameter, head), current())
    override def bindT(t: Number[B], current: () => Number[A], parameter: (A, B) => Boolean): Boolean = t.execute(ops_corresponds3)((), ())
  }

  class Ops_Corresponds2[A, B] extends TypeContext {
    override type toDataType = Number[A]
    override type Parameter  = ((A, B) => Boolean, A)
    override type Result     = Boolean
  }

  def ops_corresponds2[A, B]: Context[Ops_Corresponds2[A, B], B] = new Context[Ops_Corresponds2[A, B], B] {
    override def bindS(t: Number[A], current: () => Number[B], parameter: ((A, B) => Boolean, A), head: B): Boolean =
      if (parameter._1(parameter._2, head))
        t.execute(ops_corresponds1[A, B])(parameter._1, current())
      else
        false
    override def bindT(t: Number[A], current: () => Number[B], parameter: ((A, B) => Boolean, A)): Boolean = false
  }

  class Ops_Corresponds3 extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = Boolean
  }

  def ops_corresponds3[B]: Context[Ops_Corresponds3, B] = new Context[Ops_Corresponds3, B] {
    override def bindS(t: Unit, current: () => Number[B], parameter: Unit, head: B): Boolean = false
    override def bindT(t: Unit, current: () => Number[B], parameter: Unit): Boolean          = true
  }

  class HeadOptionTypeContext[A] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = Option[A]
  }

  def ops_headOption[A]: Context[HeadOptionTypeContext[A], A] = new Context[HeadOptionTypeContext[A], A] {
    override def bindS(t: Unit, current: () => Number[A], parameter: Unit, head: A): Option[A] = Option(head)
    override def bindT(t: Unit, current: () => Number[A], parameter: Unit): Option[A]          = Option.empty
  }

  class HeadTypeContext[A] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = A
  }

  def ops_head[A]: Context[HeadTypeContext[A], A] = new Context[HeadTypeContext[A], A] {
    override def bindS(t: Unit, current: () => Number[A], parameter: Unit, head: A): A = head
    override def bindT(t: Unit, current: () => Number[A], parameter: Unit): A = throw new NoSuchElementException("head of empty list")
  }

  class EqualsContext1[B] extends TypeContext {
    override type toDataType = Number[B]
    override type Parameter  = Unit
    override type Result     = Boolean
  }

  def ops_equals1[A, B]: Context[EqualsContext1[B], A] = new Context[EqualsContext1[B], A] {
    override def bindS(t: Number[B], current: () => Number[A], parameter: Unit, head: A): Boolean =
      t.execute(ops_equals2[A, B])(head, current())
    override def bindT(t: Number[B], current: () => Number[A], parameter: Unit): Boolean = t.execute(ops_equals3[A, B])((), ())
  }

  class EqualsContext2[A, B] extends TypeContext {
    override type toDataType = Number[A]
    override type Parameter  = A
    override type Result     = Boolean
  }

  def ops_equals2[A, B]: Context[EqualsContext2[A, B], B] = new Context[EqualsContext2[A, B], B] {
    override def bindS(t: Number[A], current: () => Number[B], parameter: A, head: B): Boolean =
      if (parameter == head) t.execute(ops_equals1[A, B])((), current()) else false
    override def bindT(t: Number[A], current: () => Number[B], parameter: A): Boolean = false
  }

  class EqualsContext3 extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = Boolean
  }

  def ops_equals3[A, B]: Context[EqualsContext3, B] = new Context[EqualsContext3, B] {
    override def bindS(t: Unit, current: () => Number[B], parameter: Unit, head: B): Boolean = false
    override def bindT(t: Unit, current: () => Number[B], parameter: Unit): Boolean          = true
  }

  class ExistsContext[A] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = A => Boolean
    override type Result     = Boolean
  }

  def existsContext[A]: Context[ExistsContext[A], A] = new Context[ExistsContext[A], A] {
    override def bindS(t: Unit, current: () => Number[A], parameter: A => Boolean, head: A): Boolean =
      if (parameter(head)) true else current().execute(this)(parameter, ())
    override def bindT(t: Unit, current: () => Number[A], parameter: A => Boolean): Boolean = false
  }

}
