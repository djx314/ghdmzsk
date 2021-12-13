package e01

trait Number[A] {
  def execute[ToDataType, Result](contexts: Context[ToDataType, A, Result])(t: ToDataType): Result
}
case class NumberS[A](tail: () => Number[A], head: A) extends Number[A] {
  override def execute[ToDataType, Result](context: Context[ToDataType, A, Result])(t: ToDataType): Result = context.bindS(t, tail(), head)
}
case class NumberT[A](tail: () => Number[A]) extends Number[A] {
  override def execute[ToDataType, Result](context: Context[ToDataType, A, Result])(t: ToDataType): Result = context.bindT(t, tail())
}

trait Context[ToDataType, A, Result] {
  def bindS(t: ToDataType, current: Number[A], head: A): Result
  def bindT(t: ToDataType, current: Number[A]): Result
}
