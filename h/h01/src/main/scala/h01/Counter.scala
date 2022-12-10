package h01

trait Number1 {
  def method1(number1: Number1): Number3
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number3 = Number3S(() => tail().method1(number1))
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number3 = tail().method1(number1)
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number3 = Number3S(() => number1.method1(tail()))
}
case class Number1V(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number3 = number1.method1(tail())
}

sealed trait Number3
case class Number3S(tail: () => Number3) extends Number3
