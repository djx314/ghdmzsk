package d05

class Item

trait NumberRight {
  def 右推动(left: NumberLeft): Result
}
case class NumberRightS(tail: NumberRight, head: Item) extends NumberRight {
  override def 右推动(left: NumberLeft): Result = ResultP(tail.右推动(left), head)
}
case class NumberRightT(tail: () => NumberRight) extends NumberRight {
  override def 右推动(left: NumberLeft): Result = left.左推动(tail())
}
case class NumberRightU(tail: NumberRight, head: NumberMiddle) extends NumberRight {
  override def 右推动(left: NumberLeft): Result = head.推动(left, tail)
}

trait NumberMiddle {
  def 推动(left: NumberLeft, right: NumberRight): Result
}
case class NumberMiddleS(tail: NumberMiddle) extends NumberMiddle {
  override def 推动(left: NumberLeft, right: NumberRight): Result = right.右推动(NumberLeftU(left, tail))
}
case class NumberMiddleT(tail: () => NumberMiddle) extends NumberMiddle {
  override def 推动(left: NumberLeft, right: NumberRight): Result = left.左推动(NumberRightU(right, tail()))
}

trait NumberLeft {
  def 左推动(right: NumberRight): Result
}
case class NumberLeftS(tail: NumberLeft) extends NumberLeft {
  override def 左推动(right: NumberRight): Result = right.右推动(tail)
}
case object NumberLeftT extends NumberLeft {
  override def 左推动(right: NumberRight): Result = ResultO
}
case class NumberLeftU(tail: NumberLeft, head: NumberMiddle) extends NumberLeft {
  override def 左推动(right: NumberRight): Result = head.推动(tail, right)
}

trait Result
case class ResultP(tail: Result, head: Item) extends Result
case object ResultO                          extends Result
