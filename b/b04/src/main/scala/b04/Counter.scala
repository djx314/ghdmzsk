package b04

case class Item1(name: Int)
case class Item2(name: Int)

trait Number1 {
  def method1(number2: Number2, item1: Item1): Number3
}
case class Number1S(tail: Number1, head: Item2) extends Number1 {
  override def method1(number2: Number2, item1: Item1): Number3 = number2.method2(tail)
}
case object Number1T extends Number1 {
  override def method1(number2: Number2, item1: Item1): Number3 = Number3T
}

trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(tail: Number2, head: Item1) extends Number2 {
  override def method2(number1: Number1): Number3 = number1.method1(tail, head)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method2(number1: Number1): Number3 = Number3S(tail().method2(number1))
}

trait Number3
case class Number3S(tail: Number3) extends Number3
case object Number3T               extends Number3
