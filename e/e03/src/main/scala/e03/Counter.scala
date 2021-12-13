package e03

import e01._

trait Collect[T]
case class CollectS[T](tail: Collect[T], head: T) extends Collect[T]
case class CollectT[T]()                          extends Collect[T]

class DropContext[S, R] extends Context[(Number[Unit], Number[S => Boolean], Number[S => R]), Collect[R], S] {
  override def bindS(
    t: (Number[Unit], Number[S => Boolean], Number[S => R]),
    current: Number[S],
    head: S
  ): Collect[R] = t._1.execute(new ReverseDropContext[S, R])((current, t._2, t._3, head))
  override def bindT(t: (Number[Unit], Number[S => Boolean], Number[S => R]), current: Number[S]): Collect[R] = CollectT()
}

class ReverseDropContext[S, R] extends Context[(Number[S], Number[S => Boolean], Number[S => R], S), Collect[R], Unit] {
  override def bindS(
    t: (Number[S], Number[S => Boolean], Number[S => R], S),
    current: Number[Unit],
    head: Unit
  ): Collect[R] = t._1.execute(new DropContext[S, R])((current, t._2, t._3))
  override def bindT(
    t: (Number[S], Number[S => Boolean], Number[S => R], S),
    current: Number[Unit]
  ): Collect[R] = t._2.execute(new FilterContext[S, R])((t._1, current, t._3, t._4))
}

class FilterContext[S, R] extends Context[(Number[S], Number[Unit], Number[S => R], S), Collect[R], S => Boolean] {
  override def bindS(
    t: (Number[S], Number[Unit], Number[S => R], S),
    current: Number[S => Boolean],
    head: S => Boolean
  ): Collect[R] = if (head(t._4)) t._3.execute(new MapContext[S, R])((t._1, t._2, current, t._4))
  else t._1.execute(new DropContext[S, R])((t._2, current, t._3))
  override def bindT(
    t: (Number[S], Number[Unit], Number[S => R], S),
    current: Number[S => Boolean]
  ): Collect[R] = current.execute(this)((t._1, t._2, t._3, t._4))
}

class MapContext[S, R] extends Context[(Number[S], Number[Unit], Number[S => Boolean], S), Collect[R], S => R] {
  override def bindS(
    t: (Number[S], Number[Unit], Number[S => Boolean], S),
    current: Number[S => R],
    head: S => R
  ): Collect[R] = CollectS(t._1.execute(new DropContext[S, R])((t._2, t._3, current)), head(t._4))
  override def bindT(
    t: (Number[S], Number[Unit], Number[S => Boolean], S),
    current: Number[S => R]
  ): Collect[R] = current.execute(this)((t._1, t._2, t._3, t._4))
}
