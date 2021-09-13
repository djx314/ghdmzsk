package d05

class Item

trait Number1 {
  def 右推动(left: Number1): Result                = ResultO
  def 左推动(right: Number1): Result               = ResultO
  def 推动(left: Number1, right: Number1): Result = ResultO
}
case class Number1RightS(tail: Number1, head: Item) extends Number1 {
  override def 右推动(left: Number1): Result = ResultP(tail.右推动(left), head)
}
case class Number1RightT(tail: () => Number1) extends Number1 {
  override def 右推动(left: Number1): Result = left.左推动(tail())
}
case class Number1MiddleS(tail: Number1) extends Number1 {
  override def 推动(left: Number1, right: Number1): Result = right.右推动(Number1U(left, tail))
}
case class Number1MiddleT(tail: () => Number1) extends Number1 {
  override def 推动(left: Number1, right: Number1): Result = left.左推动(Number1U(right, tail()))
}
case class Number1LeftS(tail: Number1) extends Number1 {
  override def 左推动(right: Number1): Result = right.右推动(tail)
}
case object Number1LeftT extends Number1 {
  override def 左推动(right: Number1): Result = ResultO
}
case class Number1U(tail: Number1, head: Number1) extends Number1 {
  override def 右推动(left: Number1): Result  = head.推动(left, tail)
  override def 左推动(right: Number1): Result = head.推动(tail, right)
}

trait Result
case class ResultP(tail: Result, head: Item) extends Result
case object ResultO                          extends Result
