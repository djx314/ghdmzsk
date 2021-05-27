package b02

case class Item(name: String)

trait Number1 {
  def method1(number2: Number2): Number3
}
case class Number1S(tail: Number1, head: Item) extends Number1 {
  def method1(number2: Number2): Number3 = Number3S(tail.method1(number2), head)
}
case class Number1T(tail: () => Number1) extends Number1 {
  def method1(number2: Number2): Number3 = number2.method2(tail())
}

trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(tail: Number2) extends Number2 {
  def method2(number1: Number1): Number3 = number1.method1(tail)
}
case object Number2T extends Number2 {
  def method2(number1: Number1): Number3 = Number3T
}

trait Number3 {
  def length: Int
}
case class Number3S(tail: Number3, head1: Item) extends Number3 {
  override val length: Int = tail.length + 1
}
case object Number3T extends Number3 {
  override val length: Int = 0
}
