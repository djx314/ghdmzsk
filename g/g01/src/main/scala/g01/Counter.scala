package g01

trait Number1 {
  def method1(number2: Number2): Number1
}
case class Number1S(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number1 = number2.method2(tail)
}
case object Number1T extends Number1 {
  override def method1(number2: Number2): Number1 = Number1T
}

trait Number2 {
  def method2(number1: Number1): Number1
}
case class Number2S(tail: () => Number2) extends Number2 {
  override def method2(number1: Number1): Number1 = Number1S(tail().method2(number1))
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method2(number1: Number1): Number1 = number1.method1(tail())
}
case object Number2U extends Number2 {
  override def method2(number1: Number1): Number1 = number1
}
