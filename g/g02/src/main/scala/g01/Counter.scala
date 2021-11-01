package g01

trait Number1 {
  def method1(number1: Number1): Number1
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = number1.method1(tail())
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number1 = Number1S(() => tail().method1(number1))
}
case object Number1U extends Number1 {
  override def method1(number1: Number1): Number1 = Number1U
}
case object Number1V extends Number1 {
  override def method1(number1: Number1): Number1 = number1
}
