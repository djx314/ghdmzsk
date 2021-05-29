package b06

case class Item1(name: String)
case class Item2(name: String)
case class Item3(name: String)

trait Number1 {
  def method1(number2: Number2, number3: Number3): Number4
}
case class Number1S(tail: Number1, head: Item1) extends Number1 {
  override def method1(number2: Number2, number3: Number3): Number4 = number2.method2(tail, number3, head)
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2, number3: Number3): Number4 = Number4S(tail().method1(number2, number3))
}

trait Number2 {
  def method2(number1: Number1, number3: Number3, item1: Item1): Number4
}
case class Number2S(tail: Number2, head: Item2) extends Number2 {
  override def method2(number1: Number1, number3: Number3, item1: Item1): Number4 = number1.method1(tail, number3)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method2(number1: Number1, number3: Number3, item1: Item1): Number4 = number3.method3(number1, tail(), item1)
}

trait Number3 {
  def method3(number1: Number1, number2: Number2, item1: Item1): Number4
}
case class Number3S(tail: Number3, head: Item3) extends Number3 {
  override def method3(number1: Number1, number2: Number2, item1: Item1): Number4 = number2.method2(number1, tail, item1)
}
case object Number3T extends Number3 {
  override def method3(number1: Number1, number2: Number2, item1: Item1): Number4 = Number4T
}

trait Number4 {
  def length: Int
}
case class Number4S(tail: Number4) extends Number4 {
  override val length: Int = tail.length + 1
}
case object Number4T extends Number4 {
  override val length: Int = 0
}

object Counter {
  def count(number2: Number2, number3: Number3, number1: Number1): Number4 = number1.method1(number2, number3)
}
