package e03

import e01._

class DropTypeContext[S, R] extends TypeContext {
  override type toDataType = (Number[Unit], Number[S => Boolean], Number[S => R])
  override type Parameter  = Unit
  override type Result     = R
}
class DropContext[S, R] extends Context[DropTypeContext[S, R], S] {
  override def bindS(
    t: (Number[Unit], Number[S => Boolean], Number[S => R]),
    current: Number[S],
    parameter: Unit,
    head: S
  ): Collect[R] = t._1.execute(new ReverseDropContext[S, R])(head, (current, t._2, t._3))
  override def bindT(
    t: (Number[Unit], Number[S => Boolean], Number[S => R]),
    current: Number[S],
    parameter: Unit
  ): Collect[R] = CollectT()
}

class ReverseDropTypeContext[S, R] extends TypeContext {
  override type toDataType = (Number[S], Number[S => Boolean], Number[S => R])
  override type Parameter  = S
  override type Result     = R
}
class ReverseDropContext[S, R] extends Context[ReverseDropTypeContext[S, R], Unit] {
  override def bindS(
    t: (Number[S], Number[S => Boolean], Number[S => R]),
    current: Number[Unit],
    parameter: S,
    head: Unit
  ): Collect[R] = t._1.execute(new DropContext[S, R])((), (current, t._2, t._3))
  override def bindT(
    t: (Number[S], Number[S => Boolean], Number[S => R]),
    current: Number[Unit],
    parameter: S
  ): Collect[R] = t._2.execute(new FilterContext[S, R])(parameter, (t._1, current, t._3))
}

class FilterTypeContext[S, R] extends TypeContext {
  override type toDataType = (Number[S], Number[Unit], Number[S => R])
  override type Parameter  = S
  override type Result     = R
}
class FilterContext[S, R] extends Context[FilterTypeContext[S, R], S => Boolean] {
  override def bindS(
    t: (Number[S], Number[Unit], Number[S => R]),
    current: Number[S => Boolean],
    parameter: S,
    head: S => Boolean
  ): Collect[R] = if (head(parameter)) t._3.execute(new MapContext[S, R])(parameter, (t._1, t._2, current))
  else t._1.execute(new DropContext[S, R])((), (t._2, current, t._3))
  override def bindT(
    t: (Number[S], Number[Unit], Number[S => R]),
    current: Number[S => Boolean],
    parameter: S
  ): Collect[R] = current.execute(this)(parameter, (t._1, t._2, t._3))
}

class MapTypeContext[S, R] extends TypeContext {
  override type toDataType = (Number[S], Number[Unit], Number[S => Boolean])
  override type Parameter  = S
  override type Result     = R
}
class MapContext[S, R] extends Context[MapTypeContext[S, R], S => R] {
  override def bindS(
    t: (Number[S], Number[Unit], Number[S => Boolean]),
    current: Number[S => R],
    parameter: S,
    head: S => R
  ): Collect[R] = CollectS(t._1.execute(new DropContext[S, R])((), (t._2, t._3, current)), head(parameter))
  override def bindT(
    t: (Number[S], Number[Unit], Number[S => Boolean]),
    current: Number[S => R],
    parameter: S
  ): Collect[R] = current.execute(this)(parameter, (t._1, t._2, t._3))
}
