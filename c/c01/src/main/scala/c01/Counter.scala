package c01

case class Item(name: String)

trait NumberTo {
  def method1(numberBe: NumberBe): NumberBe
}
case class NumberToS(tail: () => NumberTo, head: Item) extends NumberTo {
  override def method1(numberBe: NumberBe): NumberBe = NumberBeS(tail().method1(numberBe), head)
}
case object NumberToT extends NumberTo {
  override def method1(numberBe: NumberBe): NumberBe = numberBe
}

trait NumberBe
case class NumberBeS(tail: NumberBe, head: Item) extends NumberBe
case object NumberBeT                            extends NumberBe
