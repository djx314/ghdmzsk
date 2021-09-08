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

// =========================================================================

trait Num1

case class NumP1(per: () => Num1, tail: () => Num1) extends Num1

case class NumT1(tail: () => Num1) extends Num1

case class NumO1(per: () => Num1) extends Num1

object Ni {

  lazy val n1: Num1 = NumT1(() => n2)
  lazy val n2: Num1 = NumP1(() => n1, () => n3)
  lazy val n3: Num1 = NumP1(() => n2, () => n4)
  lazy val n4: Num1 = NumO1(() => n3)

  // 诸位还记得大明湖畔那个总是不准的乘除吗？
  // 象棋先走和后走总是没有区别的，因为相差并不足以造成输赢差距
  // 所以乘除的那对阴阳，如果再微一点，直至导数，会如何？
  // 无穷小，可否解决现在增长速度等于自身的这个问题？
  // 因为他需要的，恰恰是导数
  // 零、微和万，紧密相连，互相独立。

}