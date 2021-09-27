package d02

import d01._

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
