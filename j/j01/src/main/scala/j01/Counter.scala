package j01

trait Number[ToDataType, Result] {
  def method1(o: ToDataType): Result
}

case class NumberS[ToDataType, A, Result](head: A, context: ContextS[ToDataType, A, Result]) extends Number[ToDataType, Result] {
  override def method1(o: ToDataType): Result = context.executeMethod1(head, o)
}
trait ContextS[ToDataType, A, Result] {
  val tail: () => Number[ToDataType, Result]
  def executeMethod1(head: A, other: ToDataType): Result
}

case class NumberT[ToDataType, Result](context: ContextT[ToDataType, Result]) extends Number[ToDataType, Result] {
  override def method1(o: ToDataType): Result = context.executeMethod1(o)
}
trait ContextT[ToDataType, Result] {
  val tail: () => Number[ToDataType, Result]
  def executeMethod1(other: ToDataType): Result
}
