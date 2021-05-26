package p02

case class Item(name: String)

trait Number1 {
  def method1(number2: Number2): Any
}
case class Number1S(tail: Number1, head: Item) extends Number1 {
  def method1(number2: Number2): Any = number2.method2(this)
}
case object Number1T extends Number1 {
  def method1(number2: Number2): Any = number2
}

trait Number2 {
  def method2(number1: Number1S): Any
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  def method2(number1: Number1S): Any = number1.tail.method1(tail)
}
case object Number2T extends Number2 {
  def method2(number1: Number1S): Any = number1
}
