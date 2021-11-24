package a01_03

trait Number1[+T] {
  def dropRight: Number1[T]
  def method1[R >: T](item: R): Number1[R]
}
case class Number1S[+T](tail: Number1[T], head: T) extends Number1[T] {
  override def dropRight: Number1[T]                = tail.method1(head)
  override def method1[R >: T](item: R): Number1[R] = Number1S(tail.method1(head), item)
}
case object Number1T extends Number1[Nothing] {
  override def dropRight: Number1[Nothing]                = Number1T
  override def method1[R >: Nothing](item: R): Number1[R] = Number1T
}
