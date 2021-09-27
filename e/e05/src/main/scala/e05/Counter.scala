package e05

class Item

trait NumberRight {
  def method1(left: NumberLeft): Result
}
case class NumberRightS(tail: NumberRight, head: Item) extends NumberRight {
  override def method1(left: NumberLeft): Result = ResultP(tail.method1(left), head)
}
case class NumberRightT(tail: () => NumberRight) extends NumberRight {
  override def method1(left: NumberLeft): Result = left.method3(tail())
}
case class NumberRightU(tail: NumberRight, head: NumberMiddle) extends NumberRight {
  override def method1(left: NumberLeft): Result = head.method2(left, tail)
}

trait NumberMiddle {
  def method2(left: NumberLeft, right: NumberRight): Result
}
case class NumberMiddleS(tail: NumberMiddle) extends NumberMiddle {
  override def method2(left: NumberLeft, right: NumberRight): Result = right.method1(NumberLeftU(left, tail))
}
case class NumberMiddleT(tail: () => NumberMiddle) extends NumberMiddle {
  override def method2(left: NumberLeft, right: NumberRight): Result = left.method3(NumberRightU(right, tail()))
}

trait NumberLeft {
  def method3(right: NumberRight): Result
}
case class NumberLeftS(tail: NumberLeft) extends NumberLeft {
  override def method3(right: NumberRight): Result = right.method1(tail)
}
case object NumberLeftT extends NumberLeft {
  override def method3(right: NumberRight): Result = ResultO
}
case class NumberLeftU(tail: NumberLeft, head: NumberMiddle) extends NumberLeft {
  override def method3(right: NumberRight): Result = head.method2(tail, right)
}

trait Result
case class ResultP(tail: Result, head: Item) extends Result
case object ResultO                          extends Result
