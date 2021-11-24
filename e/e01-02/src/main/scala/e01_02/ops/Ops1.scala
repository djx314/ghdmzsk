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

  class Ops_Corresponds1[A, B] extends TypeContext {
    override type toDataType = Number[B]
    override type Parameter  = (A, B) => Boolean
    override type Result     = Boolean
  }

  def ops_corresponds1[A, B]: Context[Ops_Corresponds1[A, B], A] = new Context[Ops_Corresponds1[A, B], A] {
    override type DataCtx = (Number[A], Number[B])
    override def convertS(t: Number[B], current: () => Number[A]): (Number[A], Number[B]) = (current(), t)
    override def convertT(t: Number[B], current: () => Number[A]): (Number[A], Number[B]) = (current(), t)
    override def bindS(number: (Number[A], Number[B]), parameter: (A, B) => Boolean, head: A): Boolean =
      number._2.execute(ops_corresponds2[A, B])((parameter, head), number._1)
    override def bindT(number: (Number[A], Number[B]), parameter: (A, B) => Boolean): Boolean = number._2.execute(ops_corresponds3)((), ())
  }

  class Ops_Corresponds2[A, B] extends TypeContext {
    override type toDataType = Number[A]
    override type Parameter  = ((A, B) => Boolean, A)
    override type Result     = Boolean
  }

  def ops_corresponds2[A, B]: Context[Ops_Corresponds2[A, B], B] = new Context[Ops_Corresponds2[A, B], B] {
    override type DataCtx = (Number[A], Number[B])
    override def convertS(t: Number[A], current: () => Number[B]): (Number[A], Number[B]) = (t, current())
    override def convertT(t: Number[A], current: () => Number[B]): (Number[A], Number[B]) = (t, current())
    override def bindS(number: (Number[A], Number[B]), parameter: ((A, B) => Boolean, A), head: B): Boolean =
      if (parameter._1(parameter._2, head)) number._1.execute(ops_corresponds1[A, B])(parameter._1, number._2)
      else false
    override def bindT(number: (Number[A], Number[B]), parameter: ((A, B) => Boolean, A)): Boolean = false
  }

  class Ops_Corresponds3 extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = Boolean
  }

  def ops_corresponds3[B]: Context[Ops_Corresponds3, B] = new Context[Ops_Corresponds3, B] {
    override type DataCtx = Unit
    override def convertS(t: Unit, current: () => Number[B]): Unit      = ()
    override def convertT(t: Unit, current: () => Number[B]): Unit      = ()
    override def bindS(number: Unit, parameter: Unit, head: B): Boolean = false
    override def bindT(number: Unit, parameter: Unit): Boolean          = true
  }

  class HeadOptionTypeContext[A] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = Option[A]
  }

  def ops_headOption[A]: Context[HeadOptionTypeContext[A], A] = new Context[HeadOptionTypeContext[A], A] {
    override type DataCtx = () => Number[A]
    override def convertS(t: Unit, current: () => Number[A]): () => Number[A]        = current
    override def convertT(t: Unit, current: () => Number[A]): () => Number[A]        = current
    override def bindS(number: () => Number[A], parameter: Unit, head: A): Option[A] = Option(head)
    override def bindT(number: () => Number[A], parameter: Unit): Option[A]          = Option.empty
  }

  class HeadTypeContext[A] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = A
  }

  def ops_head[A]: Context[HeadTypeContext[A], A] = new Context[HeadTypeContext[A], A] {
    override type DataCtx = () => Number[A]
    override def convertS(t: Unit, current: () => Number[A]): () => Number[A] = current
    override def convertT(t: Unit, current: () => Number[A]): () => Number[A] = current
    override def bindS(number: () => Number[A], parameter: Unit, head: A): A  = head
    override def bindT(number: () => Number[A], parameter: Unit): A           = throw new NoSuchElementException("head of empty list")
  }

  class EqualsContext1[B] extends TypeContext {
    override type toDataType = Number[B]
    override type Parameter  = Unit
    override type Result     = Boolean
  }

  def ops_equals1[A, B]: Context[EqualsContext1[B], A] = new Context[EqualsContext1[B], A] {
    override type DataCtx = (Number[B], () => Number[A])
    override def convertS(t: Number[B], current: () => Number[A]): (Number[B], () => Number[A]) = (t, current)
    override def convertT(t: Number[B], current: () => Number[A]): (Number[B], () => Number[A]) = (t, current)
    override def bindS(number: (Number[B], () => Number[A]), parameter: Unit, head: A): Boolean =
      number._1.execute(ops_equals2[A, B])(head, number._2())
    override def bindT(number: (Number[B], () => Number[A]), parameter: Unit): Boolean = number._1.execute(ops_equals3[A, B])((), ())
  }

  class EqualsContext2[A, B] extends TypeContext {
    override type toDataType = Number[A]
    override type Parameter  = A
    override type Result     = Boolean
  }

  def ops_equals2[A, B]: Context[EqualsContext2[A, B], B] = new Context[EqualsContext2[A, B], B] {
    override type DataCtx = (Number[B], Number[A])
    override def convertS(t: Number[A], current: () => Number[B]): (Number[B], Number[A]) = (current(), t)
    override def convertT(t: Number[A], current: () => Number[B]): (Number[B], Number[A]) = (current(), t)
    override def bindS(number: (Number[B], Number[A]), parameter: A, head: B): Boolean =
      if (parameter == head) number._2.execute(ops_equals1[A, B])((), number._1) else false
    override def bindT(number: (Number[B], Number[A]), parameter: A): Boolean = false
  }

  class EqualsContext3 extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = Boolean
  }

  def ops_equals3[A, B]: Context[EqualsContext3, B] = new Context[EqualsContext3, B] {
    override type DataCtx = Unit
    override def convertS(t: Unit, current: () => Number[B]): Unit      = ()
    override def convertT(t: Unit, current: () => Number[B]): Unit      = ()
    override def bindS(number: Unit, parameter: Unit, head: B): Boolean = false
    override def bindT(number: Unit, parameter: Unit): Boolean          = true
  }

}
