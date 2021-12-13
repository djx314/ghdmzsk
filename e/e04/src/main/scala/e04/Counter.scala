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
  override type Result = Item4
}

class HList1TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]
}
class HList1Context extends Context[HList1TypeContext, Item1] {
  override def bindS(
    t: HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]],
    current: Number[Item1],
    head: Item1
  ): Collect[Item4] = t.head.execute(new HList2Context)(HPositive(current, t.tail))
  override def bindT(
    t: HPositive[Number[Item2], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]],
    current: Number[Item1]
  ): Collect[Item4] = CollectT()
}

class HList2TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item1], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]]
}
class HList2Context extends Context[HList2TypeContext, Item2] {
  override def bindS(
    t: HPositive[Number[Item1], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]],
    current: Number[Item2],
    head: Item2
  ): Collect[Item4] = t.tail.head.execute(new HList3Context)(HPositive(t.head, HPositive(current, t.tail.tail)))
  override def bindT(
    t: HPositive[Number[Item1], HPositive[Number[Item3], HPositive[Number[Item4], HNil]]],
    current: Number[Item2]
  ): Collect[Item4] =
    t.head.execute(new HList1Context)(HPositive(current, HPositive(t.tail.head, HPositive(t.tail.tail.head, t.tail.tail.tail))))
}

class HList3TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item4], HNil]]]
}
class HList3Context extends Context[HList3TypeContext, Item3] {
  override def bindS(
    t: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item4], HNil]]],
    current: Number[Item3],
    head: Item3
  ): Collect[Item4] =
    t.tail.tail.head.execute(new HList4Context)(HPositive(t.head, HPositive(t.tail.head, HPositive(current, t.tail.tail.tail))))
  override def bindT(
    t: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item4], HNil]]],
    current: Number[Item3]
  ): Collect[Item4] =
    t.tail.head.execute(new HList2Context)(HPositive(t.head, HPositive(current, HPositive(t.tail.tail.head, t.tail.tail.tail))))
}

class HList4TypeContext extends HListAllTypeContext {
  override type toDataType = HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HNil]]]
}
class HList4Context extends Context[HList4TypeContext, Item4] {
  override def bindS(
    t: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HNil]]],
    current: Number[Item4],
    head: Item4
  ): Collect[Item4] = CollectS(
    current.execute(new HList4Context)(HPositive(t.head, HPositive(t.tail.head, HPositive(t.tail.tail.head, t.tail.tail.tail)))),
    head
  )
  override def bindT(
    t: HPositive[Number[Item1], HPositive[Number[Item2], HPositive[Number[Item3], HNil]]],
    current: Number[Item4]
  ): Collect[Item4] =
    t.tail.tail.head.execute(new HList3Context)(HPositive(t.head, HPositive(t.tail.head, HPositive(current, t.tail.tail.tail))))
}
