package d01

trait Number1[T] {
  def length: Int
}
case class Number1S[T](tail: Number1[T], head: T) extends Number1[T] {
  override def length: Int      = tail.length + 1
  override def toString: String = s"($head, $tail)"
}
case class Number1T[T]() extends Number1[T] {
  override def length: Int      = 0
  override def toString: String = "Zero"
}

trait Number2[A] {
  def execute[T <: TypeContext](context: Context[T, A])(s: T#Parameter, t: T#toDataType): Number1[T#Result]
}
case class Number2S[A](tail: () => Number2[A], head: A) extends Number2[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): Number1[T#Result] = {
    val newDataCtx = context.convert(t, tail())
    context.bindS(newDataCtx, parameter, head)
  }
}
case class Number2T[A](tail: () => Number2[A]) extends Number2[A] {
  override def execute[T <: TypeContext](context: Context[T, A])(parameter: T#Parameter, t: T#toDataType): Number1[T#Result] = {
    val newDataCtx = context.convert(t, tail())
    context.bindT(newDataCtx, parameter)
  }
}

trait Context[T <: TypeContext, A] {
  def convert(t: T#toDataType, current: Number2[A]): T#DataCtx
  def bindS(number: T#DataCtx, parameter: T#Parameter, head: A): Number1[T#Result]
  def bindT(number: T#DataCtx, parameter: T#Parameter): Number1[T#Result]
}

trait TypeContext {
  type DataCtx
  type toDataType
  type Parameter
  type Result
}
