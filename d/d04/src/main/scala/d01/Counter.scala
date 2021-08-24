package d01

trait Number1[+T] {
  def length: Int
}
case class Number1S[+T](tail: Number1[T], head: T) extends Number1[T] {
  override def length: Int      = tail.length + 1
  override def toString: String = s"($head, $tail)"
}
case object Number1T extends Number1[Nothing] {
  override def length: Int      = 0
  override def toString: String = "Zero"
}

trait Number2[A, B] {
  def execute[T <: TypeContext](contexts: Context[T, A, B])(s: T#Parameter, t: T#toDataType): Number1[T#Result]
}
case class Number2S[A, B](tail: () => Number2[A, B], head: A) extends Number2[A, B] {
  override def execute[T <: TypeContext](context: Context[T, A, B])(parameter: T#Parameter, t: T#toDataType): Number1[T#Result] = {
    val newDataCtx = context.convert(t, tail())
    context.bindS(newDataCtx, parameter, head)
  }
}
case class Number2T[A, B](tail: () => Number2[A, B], head: B) extends Number2[A, B] {
  override def execute[T <: TypeContext](context: Context[T, A, B])(parameter: T#Parameter, t: T#toDataType): Number1[T#Result] = {
    val newDataCtx = context.convert(t, tail())
    context.bindT(newDataCtx, parameter, head)
  }
}

trait Context[T <: TypeContext, A, B] {
  def convert(t: T#toDataType, current: Number2[A, B]): T#DataCtx
  def bindS(number: T#DataCtx, parameter: T#Parameter, head: A): Number1[T#Result]
  def bindT(number: T#DataCtx, parameter: T#Parameter, head: B): Number1[T#Result]
}

trait TypeContext {
  type DataCtx
  type toDataType
  type Parameter
  type Result
}
