package d03

import d01._

class ListContext[S, R] extends TypeContext {
  override type DataCtx = (Number[S], Number[Unit], Number[S => Boolean], Number[S => R])
}

class DropTypeContext[S, R] extends ListContext[S, R] {
  override type toDataType = (Number[Unit], Number[S => Boolean], Number[S => R])
  override type Parameter  = Unit
  override type Result     = R
}
class DropContext[S, R] extends Context[DropTypeContext[S, R], S] {
  override def convert(
    t: (Number[Unit], Number[S => Boolean], Number[S => R]),
    current: Number[S]
  ): (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]) = (current, t._1, t._2, t._3)
  override def bindS(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: Unit,
    head: S
  ): Collect[R] = number._2.execute(new ReverseDropContext[S, R])(head, (number._1, number._3, number._4))
  override def bindT(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: Unit
  ): Collect[R] = CollectT()
}

class ReverseDropTypeContext[S, R] extends ListContext[S, R] {
  override type toDataType = (Number[S], Number[S => Boolean], Number[S => R])
  override type Parameter  = S
  override type Result     = R
}
class ReverseDropContext[S, R] extends Context[ReverseDropTypeContext[S, R], Unit] {
  override def convert(
    t: (Number[S], Number[S => Boolean], Number[S => R]),
    current: Number[Unit]
  ): (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]) = (t._1, current, t._2, t._3)
  override def bindS(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: S,
    head: Unit
  ): Collect[R] = number._1.execute(new DropContext[S, R])((), (number._2, number._3, number._4))
  override def bindT(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: S
  ): Collect[R] = number._3.execute(new FilterContext[S, R])(parameter, (number._1, number._2, number._4))
}

class FilterTypeContext[S, R] extends ListContext[S, R] {
  override type toDataType = (Number[S], Number[Unit], Number[S => R])
  override type Parameter  = S
  override type Result     = R
}
class FilterContext[S, R] extends Context[FilterTypeContext[S, R], S => Boolean] {
  override def convert(
    t: (Number[S], Number[Unit], Number[S => R]),
    current: Number[S => Boolean]
  ): (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]) = (t._1, t._2, current, t._3)
  override def bindS(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: S,
    head: S => Boolean
  ): Collect[R] = if (head(parameter)) number._4.execute(new MapContext[S, R])(parameter, (number._1, number._2, number._3))
  else number._1.execute(new DropContext[S, R])((), (number._2, number._3, number._4))
  override def bindT(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: S
  ): Collect[R] = number._3.execute(this)(parameter, (number._1, number._2, number._4))
}

class MapTypeContext[S, R] extends ListContext[S, R] {
  override type toDataType = (Number[S], Number[Unit], Number[S => Boolean])
  override type Parameter  = S
  override type Result     = R
}
class MapContext[S, R] extends Context[MapTypeContext[S, R], S => R] {
  override def convert(
    t: (Number[S], Number[Unit], Number[S => Boolean]),
    current: Number[S => R]
  ): (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]) = (t._1, t._2, t._3, current)
  override def bindS(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: S,
    head: S => R
  ): Collect[R] = CollectS(number._1.execute(new DropContext[S, R])((), (number._2, number._3, number._4)), head(parameter))
  override def bindT(
    number: (Number[S], Number[Unit], Number[S => Boolean], Number[S => R]),
    parameter: S
  ): Collect[R] = number._4.execute(this)(parameter, (number._1, number._2, number._3))
}
