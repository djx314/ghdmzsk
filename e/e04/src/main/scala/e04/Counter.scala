package e04

import e01._

class Item1
class Item2
class Item3
class Item4

trait HList
case class HPositive[H, T <: HList](head: H, tail: T) extends HList
case class HNil()                                     extends HList

class HListAllTypeContext extends TypeContext {
  override type DataCtx = HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]]
  override type Result  = Item4
}

class HList1TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]
  override type Parameter  = Unit
}
class HList1Context extends Context[HList1TypeContext, Item1] {
  override def convert(
    t: HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]],
    current: Number[Item1]
  ): HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]] = HPositive(current, t)
  override def bindS(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit,
    head: Item1
  ): Collect[Item4] = number.tail.head.execute(new HList2Context)(parameter, HPositive(number.head, number.tail.tail))
  override def bindT(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit
  ): Collect[Item4] = CollectT()
}

class HList2TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item1], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]
  override type Parameter  = Unit
}
class HList2Context extends Context[HList2TypeContext, Item2] {
  override def convert(
    t: HPositive[Number[Item1], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]],
    current: Number[Item2]
  ): HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]] =
    HPositive(t.head, HPositive(current, t.tail))
  override def bindS(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit,
    head: Item2
  ): Collect[Item4] =
    number.tail.tail.head.execute(new HList3Context)(parameter, HPositive(number.head, HPositive(number.tail.head, number.tail.tail.tail)))
  override def bindT(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit
  ): Collect[Item4] = number.head.execute(new HList1Context)(
    parameter,
    HPositive(number.tail.head, HPositive(number.tail.tail.head, HPositive(number.tail.tail.tail.head, number.tail.tail.tail.tail)))
  )
}

class HList3TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item4], HNil]]]
  override type Parameter  = Unit
}
class HList3Context extends Context[HList3TypeContext, Item3] {
  override def convert(
    t: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item4], HNil]]],
    current: Number[Item3]
  ): HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]] =
    HPositive(t.head, HPositive(t.tail.head, HPositive(current, t.tail.tail)))
  override def bindS(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit,
    head: Item3
  ): Collect[Item4] = number.tail.tail.tail.head.execute(new HList4Context)(
    parameter,
    HPositive(number.head, HPositive(number.tail.head, HPositive(number.tail.tail.head, number.tail.tail.tail.tail)))
  )
  override def bindT(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit
  ): Collect[Item4] = number.tail.head.execute(new HList2Context)(
    parameter,
    HPositive(number.head, HPositive(number.tail.tail.head, HPositive(number.tail.tail.tail.head, number.tail.tail.tail.tail)))
  )
}

class HList4TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HNil]]]
  override type Parameter  = Unit
}
class HList4Context extends Context[HList4TypeContext, Item4] {
  override def convert(
    t: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HNil]]],
    current: Number[Item4]
  ): HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]] =
    HPositive(t.head, HPositive(t.tail.head, HPositive(t.tail.tail.head, HPositive(current, t.tail.tail.tail))))
  override def bindS(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit,
    head: Item4
  ): Collect[Item4] = CollectS(
    number.tail.tail.tail.head.execute(new HList4Context)(
      parameter,
      HPositive(number.head, HPositive(number.tail.head, HPositive(number.tail.tail.head, number.tail.tail.tail.tail)))
    ),
    head
  )
  override def bindT(
    number: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]],
    parameter: Unit
  ): Collect[Item4] = number.tail.tail.head.execute(new HList3Context)(
    parameter,
    HPositive(number.head, HPositive(number.tail.head, HPositive(number.tail.tail.tail.head, number.tail.tail.tail.tail)))
  )
}
