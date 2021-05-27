package a02

case class Item(name: String)

trait Number1 {
  def method1(number2: Number2): Any
}
case class Number1S(tail: Number1, head: Item) extends Number1 {
  override def method1(number2: Number2): Any = number2.method2(tail, head)
}
case object Number1T extends Number1 {
  override def method1(number2: Number2): Any = number2
}

trait Number2 {
  def method2(number1: Number1, item1: Item): Any
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  override def method2(number1: Number1, item1: Item): Any = number1.method1(tail)
}
case object Number2T extends Number2 {
  override def method2(number1: Number1, item1: Item): Any = Number1S(number1, item1)
}
