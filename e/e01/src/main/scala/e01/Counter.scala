package e01

trait Collect[T]
case class CollectS[T](tail: Collect[T], head: T) extends Collect[T]
case class CollectT[T]()                          extends Collect[T]

trait Number[A] {
  def execute[T <: TypeContext](contexts: Context[T, A])(s: T#Parameter, t: T#toDataType): Collect[T#Result]
}
case class NumberS[A](tail: () => Number[A], head: A) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): Collect[T#Result] =
    context.bindS(t, tail(), parameter, head)
}
case class NumberT[A](tail: () => Number[A]) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): Collect[T#Result] =
    context.bindT(t, tail(), parameter)
}

trait Context[T <: TypeContext, A] {
  def bindS(t: T#toDataType, current: Number[A], parameter: T#Parameter, head: A): Collect[T#Result]
  def bindT(t: T#toDataType, current: Number[A], parameter: T#Parameter): Collect[T#Result]
}

trait TypeContext {
  type toDataType
  type Parameter
  type Result
}
