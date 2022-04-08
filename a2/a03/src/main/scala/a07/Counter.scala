package a07

trait Number1 {
  def method1(number2: Number2): Number3
}
case class Number1S(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(tail)
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = Number3S(tail().method1(number2))
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = Number3S(number2.method2(tail()))
}
case class Number1V0(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1S(Number1S(Number1S(tail))))
}
case class Number1V1(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V0(Number1V0(Number1V0(tail))))
}
case class Number1V2(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V1(Number1V1(Number1V1(tail))))
}
case class Number1V3(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V2(Number1V2(Number1V2(tail))))
}
case class Number1V4(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V3(Number1V3(Number1V3(tail))))
}
case class Number1V5(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V4(Number1V4(Number1V4(tail))))
}
case class Number1V6(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V5(Number1V5(Number1V5(tail))))
}
case class Number1V7(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V6(Number1V6(Number1V6(tail))))
}
case class Number1V8(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(Number1V7(Number1V7(Number1V7(tail))))
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
