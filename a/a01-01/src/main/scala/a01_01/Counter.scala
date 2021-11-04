package a01_01

trait Number1[+T]
case class Number1S[+T](tail: Number1[T], head: T) extends Number1[T]
case object Number1T                               extends Number1[Nothing]

trait Number2[+T] {
  def method1[R >: T](number1: Number1[R]): Number1[R]
}
case class Number2S[+T](tail: Number2[T], head: T) extends Number2[T] {
  override def method1[R >: T](number1: Number1[R]): Number1[R] = tail.method1(Number1S(number1, head))
}
case object Number2T extends Number2[Nothing] {
  override def method1[R >: Nothing](number1: Number1[R]): Number1[R] = number1
}
