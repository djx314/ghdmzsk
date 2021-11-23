package f02

trait Number1 {
  def method1(number1: Number1): Number2
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = Number2S(() => tail().method1(number1))
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = tail().method1(number1)
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = Number2S(() => number1.method1(tail()))
}
case class Number1V(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = number1.method1(tail())
}

sealed trait Number2
case class Number2S(tail: () => Number2) extends Number2
