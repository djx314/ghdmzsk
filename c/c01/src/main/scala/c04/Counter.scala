package c04

case class Item(name: String)

trait NumberTo {
  def method1(numberBe: NumberBe): Result
}
case class NumberToS(tail: () => NumberTo, head: Item) extends NumberTo {
  override def method1(numberBe: NumberBe): Result = ResultS(tail().method1(numberBe), head)
}
case class NumberToT(tail: () => NumberTo) extends NumberTo {
  override def method1(numberBe: NumberBe): Result = numberBe.method2(tail())
}

trait NumberBe {
  def method2(numberTo: NumberTo): Result
}
case class NumberBeS(tail: NumberBe, head: Item) extends NumberBe {
  override def method2(numberTo: NumberTo): Result = numberTo.method1(tail)
}
case object NumberBeT extends NumberBe {
  override def method2(numberTo: NumberTo): Result = ResultT
}

trait Result
case class ResultS(tail: Result, head: Item) extends Result
case object ResultT                          extends Result
