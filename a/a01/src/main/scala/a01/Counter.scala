package a01

trait Number1 {
  def method1(number2: Number2): Number3
}

trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2(number1: Number1): Number3 = number1.method1(tail)
}
case object Number2T extends Number2 {
  override def method2(number1: Number1): Number3 = Number3T
}

trait Number3
case class Number3S(tail: Number3) extends Number3
case object Number3T               extends Number3
