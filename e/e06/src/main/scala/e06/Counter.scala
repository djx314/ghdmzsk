package e06

import e01._

class Item1

trait Collect
case class CollectS(tail: Collect) extends Collect
case object CollectT               extends Collect

class ListMutiplyContext extends Context[Number[Item1], Item1, Collect] {
  override def bindS(t: Number[Item1], current: Number[Item1], head: Item1): Collect = t.execute(new RoundMutiplyContext)(current)
  override def bindT(t: Number[Item1], current: Number[Item1]): Collect              = t.execute(new RoundDivisionContext)(current)
}

class RoundMutiplyContext extends Context[Number[Item1], Item1, Collect] {
  override def bindS(t: Number[Item1], current: Number[Item1], head: Item1): Collect = CollectS(current.execute(this)(t))
  override def bindT(t: Number[Item1], current: Number[Item1]): Collect              = t.execute(new ListMutiplyContext)(current)
}

class ListDivisionContext extends Context[Number[Item1], Item1, Collect] {
  override def bindS(t: Number[Item1], current: Number[Item1], head: Item1): Collect = CollectT
  override def bindT(t: Number[Item1], current: Number[Item1]): Collect              = t.execute(new RoundDivisionContext)(current)
}

class RoundDivisionContext extends Context[Number[Item1], Item1, Collect] {
  override def bindS(t: Number[Item1], current: Number[Item1], head: Item1): Collect = t.execute(new ListDivisionContext)(current)
  override def bindT(t: Number[Item1], current: Number[Item1]): Collect              = CollectS(current.execute(this)(t))
}
