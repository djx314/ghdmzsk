package f07

trait Number1 {
  def method1(number1: Number1): Number1
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = Number1S(() => tail().method1(number1))
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = tail().method1(number1)
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = Number1S(() => number1.method1(tail()))
}
case class Number1V(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = number1.method1(tail())
}
case object Number1W extends Number1 {
  override def method1(number1: Number1): Number1 = number1
}
case object Number1X extends Number1 {
  override def method1(number1: Number1): Number1 = Number1X
}
case class Number1Y(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = number1
}
case class Number1Z(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = Number1X
}
case object Number1A extends Number1 {
  override def method1(number1: Number1): Number1 = Number1S(() => number1)
}
case object Number1B extends Number1 {
  override def method1(number1: Number1): Number1 = Number1S(() => Number1X)
}
case class Number1C(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = Number1S(() => number1)
}
case class Number1D(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = Number1S(() => Number1X)
}
