package aaaaaaaaaaaaaa

trait Number1 {
  def method1(number2: Number2): Result
}
case class Number1P(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Result = ResultO
}
case object Number1O extends Number1 {
  override def method1(number2: Number2): Result = ResultO
}

trait Number2 {
  def method2(number1: Number1): Result
}
case class Number2P(tail: Number3) extends Number2 {
  override def method2(number1: Number1): Result = ResultO
}

trait Number3
case class Number3P(tail: Number2) extends Number3

trait Result
case class ResultP(tail: Result) extends Result
case object ResultO              extends Result
