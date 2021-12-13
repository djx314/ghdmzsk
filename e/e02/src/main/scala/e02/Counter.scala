package e02

import e01._

trait Collect[T]
case class CollectS[T](tail: Collect[T], head: T) extends Collect[T]
case class CollectT[T]()                          extends Collect[T]

class Context1[S] extends Context[Number[Unit], Collect[S], S] {
  override def bindS(t: Number[Unit], current: Number[S], head: S): Collect[S] = t.execute(new Context2[S])((current, head))
  override def bindT(t: Number[Unit], current: Number[S]): Collect[S]          = t.execute(new Context3[S])(current)
}

class Context2[S] extends Context[(Number[S], S), Collect[S], Unit] {
  override def bindS(t: (Number[S], S), current: Number[Unit], head: Unit): Collect[S] = t._1.execute(new Context1[S])(current)
  override def bindT(t: (Number[S], S), current: Number[Unit]): Collect[S] =
    CollectS(t._1.execute(new Context1[S])(current), t._2)
}

class Context3[S] extends Context[Number[S], Collect[S], Unit] {
  override def bindS(t: Number[S], current: Number[Unit], head: Unit): Collect[S] = t.execute(new Context1[S])(current)
  override def bindT(t: Number[S], current: Number[Unit]): Collect[S]             = CollectT()
}
