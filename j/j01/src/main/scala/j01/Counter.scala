package j01

trait Number[Other, Result] {
  def method1(o: Other): Result
}
case class NumberS[Current, Other, Result](head: Current, context: ContextS[Current, Other, Result]) extends Number[Other, Result] {
  override def method1(o: Other): Result = context.executeMethod1(head, o)
}
case class NumberT[Other, Result](context: ContextT[Other, Result]) extends Number[Other, Result] {
  override def method1(o: Other): Result = context.executeMethod1(o)
}

trait ContextS[Current, Other, Result] {
  type NextNumber
  def next: NextNumber
  def executeMethod1(current: Current, other: Other): Result
}
trait ContextT[Other, Result] {
  type NextNumber
  def next: NextNumber
  def executeMethod1(nextOther: Other): Result
}
