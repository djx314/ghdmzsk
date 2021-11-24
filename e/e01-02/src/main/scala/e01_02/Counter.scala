package e01_02

trait Number[+A] {
  def execute[T <: TypeContext](contexts: Context[T, A])(s: T#Parameter, t: T#toDataType): T#Result
}

object Number {
  def empty[S]: Number[S] = {
    def n: Number[S] = NumberT(() => n)
    n
  }
}

case class NumberS[+A](tail: () => Number[A], head: A) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): T#Result = {
    val newDataCtx = context.convertS(t, tail)
    context.bindS(newDataCtx, parameter, head)
  }
}
case class NumberT[+A](tail: () => Number[A]) extends Number[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): T#Result = {
    val newDataCtx = context.convertT(t, tail)
    context.bindT(newDataCtx, parameter)
  }
}

trait Context[T <: TypeContext, -A] {
  type DataCtx
  def convertS(t: T#toDataType, current: () => Number[A]): DataCtx
  def convertT(t: T#toDataType, current: () => Number[A]): DataCtx
  def bindS(number: DataCtx, parameter: T#Parameter, head: A): T#Result
  def bindT(number: DataCtx, parameter: T#Parameter): T#Result
}

trait TypeContext {
  type toDataType
  type Parameter
  type Result
}
