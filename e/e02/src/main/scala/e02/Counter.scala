package e02

import e01._

class TypeContext1[S] extends TypeContext {
  override type toDataType = Number[Unit]
  override type Parameter  = Unit
  override type Result     = S
}
class Context1[S] extends Context[TypeContext1[S], S] {
  override def bindS(t: Number[Unit], current: Number[S], parameter: Unit, head: S): Collect[S] = t.execute(new Context2[S])(head, current)
  override def bindT(t: Number[Unit], current: Number[S], parameter: Unit): Collect[S]          = t.execute(new Context3[S])((), current)
}

class TypeContext2[S] extends TypeContext {
  override type toDataType = Number[S]
  override type Parameter  = S
  override type Result     = S
}
class Context2[S] extends Context[TypeContext2[S], Unit] {
  override def bindS(t: Number[S], current: Number[Unit], parameter: S, head: Unit): Collect[S] = t.execute(new Context1[S])((), current)
  override def bindT(t: Number[S], current: Number[Unit], parameter: S): Collect[S] =
    CollectS(t.execute(new Context1[S])((), current), parameter)
}

class TypeContext3[S] extends TypeContext {
  override type toDataType = Number[S]
  override type Parameter  = Unit
  override type Result     = S
}
class Context3[S] extends Context[TypeContext3[S], Unit] {
  override def bindS(t: Number[S], current: Number[Unit], parameter: Unit, head: Unit): Collect[S] = t.execute(new Context1[S])((), current)
  override def bindT(t: Number[S], current: Number[Unit], parameter: Unit): Collect[S]             = CollectT()
}
