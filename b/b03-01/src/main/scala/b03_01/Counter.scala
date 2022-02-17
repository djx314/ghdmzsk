package b03_01

trait Number1[+T] {
  def method1(number2: Number2): Number3[T]
}
case class Number1S[+T](tail: Number1[T], head: T) extends Number1[T] {
  override def method1(number2: Number2): Number3[T] = number2.method2(tail, head)
}
case object Number1T extends Number1[Nothing] {
  override def method1(number2: Number2): Number3[Nothing] = Number3T
}

trait Number2 {
  def method2[T](number1: Number1[T], item1: T): Number3[T]
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2[T](number1: Number1[T], item1: T): Number3[T] = Number3S(number1.method1(tail), item1)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method2[T](number1: Number1[T], item1: T): Number3[T] = number1.method1(tail())
}

trait Number3[+T]
case class Number3S[+T](tail: Number3[T], item: T) extends Number3[T]
case object Number3T                               extends Number3[Nothing]
