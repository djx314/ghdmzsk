package c02

case class Item(name: String)

trait NumberTo {
  def method1(numberBe: NumberBe): NumberBe
}
case class NumberToS(tail: () => NumberTo, head: Item) extends NumberTo {
  override def method1(numberBe: NumberBe): NumberBe = numberBe.method2(tail())
}
case object NumberToT extends NumberTo {
  override def method1(numberBe: NumberBe): NumberBe = numberBe
}

trait NumberBe {
  def method2(numberTo: NumberTo): NumberBe
}
case class NumberBeS(tail: NumberBe, head: Item) extends NumberBe {
  override def method2(numberTo: NumberTo): NumberBe = numberTo.method1(tail)
}
case object NumberBeT extends NumberBe {
  override def method2(numberTo: NumberTo): NumberBe = NumberBeT
}
