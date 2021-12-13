package e02

import e01._

class TypeContext1[S] extends TypeContext {
  override type toDataType = Number[Unit]
  override type Result     = S
}
class Context1[S] extends Context[TypeContext1[S], S] {
  override def bindS(t: Number[Unit], current: Number[S], head: S): Collect[S] = t.execute(new Context2[S])((current, head))
  override def bindT(t: Number[Unit], current: Number[S]): Collect[S]          = t.execute(new Context3[S])(current)
}

class TypeContext2[S] extends TypeContext {
  override type toDataType = (Number[S], S)
  override type Result     = S
}
class Context2[S] extends Context[TypeContext2[S], Unit] {
  override def bindS(t: (Number[S], S), current: Number[Unit], head: Unit): Collect[S] = t._1.execute(new Context1[S])(current)
  override def bindT(t: (Number[S], S), current: Number[Unit]): Collect[S] =
    CollectS(t._1.execute(new Context1[S])(current), t._2)
}

class TypeContext3[S] extends TypeContext {
  override type toDataType = Number[S]
  override type Result     = S
}
class Context3[S] extends Context[TypeContext3[S], Unit] {
  override def bindS(t: Number[S], current: Number[Unit], head: Unit): Collect[S] = t.execute(new Context1[S])(current)
  override def bindT(t: Number[S], current: Number[Unit]): Collect[S]             = CollectT()
}
