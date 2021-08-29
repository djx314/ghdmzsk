package d04

trait Collect[T]
case class CollectS[T](tail: Collect[T], head: T) extends Collect[T]
case class CollectT[T]()                          extends Collect[T]

trait Number[A] {
  def execute[T <: TypeContext](contexts: Context[T, A])(s: T#Parameter, t: T#toDataType): Collect[T#Result]
}
case class NumberS[A](tail: () => Number[A], head: A) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): Collect[T#Result] = {
    val newDataCtx = context.convert(t, tail())
    context.bindS(newDataCtx, parameter, head)
  }
}
case class NumberT[A](tail: () => Number[A]) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): Collect[T#Result] = {
    val newDataCtx = context.convert(t, tail())
    context.bindT(newDataCtx, parameter)
  }
}

trait Context[T <: TypeContext, A] {
  def convert(t: T#toDataType, current: Number[A]): T#DataCtx
  def bindS(number: T#DataCtx, parameter: T#Parameter, head: A): Collect[T#Result]
  def bindT(number: T#DataCtx, parameter: T#Parameter): Collect[T#Result]
}

trait TypeContext {
  type DataCtx
  type toDataType
  type Parameter
  type Result
}
