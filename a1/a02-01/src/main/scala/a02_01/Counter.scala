package a02_01

trait Number1[+T] {
  def method1[R >: T](number2: Number2[R]): Number1[R]
}
case class Number1S[+T](tail: Number1[T], head: T) extends Number1[T] {
  override def method1[R >: T](number2: Number2[R]): Number1[R] = number2.method2(tail, head)
}
case object Number1T extends Number1[Nothing] {
  override def method1[R >: Nothing](number2: Number2[R]): Number1[R] = Number1T
}

trait Number2[+T] {
  def method2[R >: T](number1: Number1[R], item: R): Number1[R]
}
case class Number2S[+T](continueTail: () => Number2[T], stopTail: Number2[T], head: T) extends Number2[T] {
  override def method2[R >: T](number1: Number1[R], item: R): Number1[R] =
    if (head == item) number1.method1(stopTail) else Number1S(number1.method1(continueTail()), item)
}
case object Number2T extends Number2[Nothing] {
  override def method2[R >: Nothing](number1: Number1[R], item: R): Number1[R] = Number1S(number1, item)
}
