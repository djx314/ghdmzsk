package d03

trait Number1 {
  def method1(number2: Number2, number3: Number3, number4: Number4): Number1
}
case class Number1S(tail: Number1) extends Number1 {
  override def method1(number2: Number2, number3: Number3, number4: Number4): Number1 = number2.method2(tail, number3, number4)
}
case object Number1T extends Number1 {
  override def method1(number2: Number2, number3: Number3, number4: Number4): Number1 = Number1T
}

trait Number2 {
  def method2(number1: Number1, number3: Number3, number4: Number4): Number1
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2(number1: Number1, number3: Number3, number4: Number4): Number1 = number3.method3(number1, tail, number4)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method2(number1: Number1, number3: Number3, number4: Number4): Number1 = number1.method1(tail(), number3, number4)
}

trait Number3 {
  def method3(number1: Number1, number2: Number2, number4: Number4): Number1
}
case class Number3S(tail: Number3) extends Number3 {
  override def method3(number1: Number1, number2: Number2, number4: Number4): Number1 = number2.method2(number1, tail, number4)
}
case class Number3T(tail: () => Number3) extends Number3 {
  override def method3(number1: Number1, number2: Number2, number4: Number4): Number1 = number4.method3(number1, number2, tail())
}

trait Number4 {
  def method3(number1: Number1, number2: Number2, number3: Number3): Number1
}
case class Number4S(tail: Number4) extends Number4 {
  override def method3(number1: Number1, number2: Number2, number3: Number3): Number1 = number3.method3(number1, number2, tail)
}
case object Number4T extends Number4 {
  override def method3(number1: Number1, number2: Number2, number3: Number3): Number1 = number1
}
