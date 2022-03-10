package b02

import a01._

case class Number1S(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = Number3S(tail.method1(number2))
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(tail())
}
