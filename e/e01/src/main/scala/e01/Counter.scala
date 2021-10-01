package e01

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
  type DataCtx
  def convert(t: T#toDataType, current: Number[A]): DataCtx
  def bindS(number: DataCtx, parameter: T#Parameter, head: A): Collect[T#Result]
  def bindT(number: DataCtx, parameter: T#Parameter): Collect[T#Result]
}

trait TypeContext {
  type toDataType
  type Parameter
  type Result
}
