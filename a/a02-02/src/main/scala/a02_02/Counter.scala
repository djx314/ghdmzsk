package a02_02

import cats.effect._

trait Number1[+T] {
  def method1(number2: Number2): IO[T]
}
class Number1S[U, +T](tail: U => Number1[T], head: IO[U]) extends Number1[T] {
  override def method1(number2: Number2): IO[T] = number2.method2(head.map(h => tail(h)))
}
object Number1S {
  def apply[U, T](head: IO[U])(tail: U => Number1[T]): Number1[T] = new Number1S(tail, head)
}
case class Number1T[T](head: T) extends Number1[T] {
  override def method1(number2: Number2): IO[T] = IO(head)
}
class Number1U[U, +T](tail: U => Number1[T], head: Resource[IO, U]) extends Number1[T] {
  override def method1(number2: Number2): IO[T] = head.use(h => tail(h).method1(number2))
}
object Number1U {
  def apply[U, T](head: Resource[IO, U])(tail: U => Number1[T]): Number1[T] = new Number1U(tail, head)
}

trait Number2 {
  def method2[T](number1: IO[Number1[T]]): IO[T]
}
case class Number2S(tail: () => Number2) extends Number2 {
  override def method2[T](number1: IO[Number1[T]]): IO[T] = number1.flatMap(_.method1(tail()))
}
