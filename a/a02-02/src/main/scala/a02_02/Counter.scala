package a02_02

trait Number1[+T] {
  def method1[R >: T](number2: Number2, item: R): Number1[R]
}
case class Number1S[+T](tail: Number1[T], head: T) extends Number1[T] {
  override def method1[R >: T](number2: Number2, item: R): Number1[R] = number2.method2(tail, head, item)
}
case object Number1T extends Number1[Nothing] {
  override def method1[R](number2: Number2, item: R): Number1[Nothing] = Number1T
}

trait Number2 {
  def method2[T](number1: Number1[T], item1: T, item2: T): Number1[T]
}
case class Number2S(tail: () => Number2) extends Number2 {
  override def method2[T](number1: Number1[T], item1: T, item2: T): Number1[T] = Number1S(number1.method1(tail(), item1), item2)
}
