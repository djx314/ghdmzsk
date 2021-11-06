package a01_02

import cats.effect._

trait NFlatMap[A] {
  def f[T](t: A => IO[T]): IO[T]
}

trait Number1[+T] {
  def method1(number2: Number2): IO[T]
}
case class Number1S[U, +T](tail: U => Number1[T], head: NFlatMap[U]) extends Number1[T] {
  override def method1(number2: Number2): IO[T] = head.f(h => tail(h).method1(number2))
}
object Number1S {
  def apply[U, T](head: NFlatMap[U])(tail: U => Number1[T]): Number1[T] = new Number1S(tail, head)
}
case class Number1T[+T](head: T) extends Number1[T] {
  override def method1(number2: Number2): IO[T] = IO(head)
}

trait Number2
case object Number2T extends Number2
