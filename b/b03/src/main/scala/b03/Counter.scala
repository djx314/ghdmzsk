package b03

case class Item1(name: String)
case class Item2(name: String)

trait Number1 {
  def method1(number2: Number2): Number3
}
case class Number1S(tail: Number1, head: Item1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(tail, head)
}
case object Number1T extends Number1 {
  override def method1(number2: Number2): Number3 = Number3T
}

trait Number2 {
  def method2(number1: Number1, item1: Item1): Number3
}
case class Number2S(tail: Number2, head: Item2) extends Number2 {
  override def method2(number1: Number1, item1: Item1): Number3 = number1.method1(tail)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method2(number1: Number1, item1: Item1): Number3 = Number3S(tail().method2(number1, item1))
}

trait Number3 {
  def length: Int
}
case class Number3S(tail: Number3) extends Number3 {
  override val length: Int = tail.length + 1
}
case object Number3T extends Number3 {
  override val length: Int = 0
}
