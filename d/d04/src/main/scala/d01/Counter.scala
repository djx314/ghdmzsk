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
  def execute[T <: TypeContext](contexts: Context[A, B], convert: Convert[T, Number2[A, B]])(
    s: T#Parameter,
    t: T#fromDataCtx
  ): Number1[T#Result]
}
case class Number2S[A, B](tail: () => Number2[A, B], head: A) extends Number2[A, B] {
  override def execute[T <: TypeContext](
    context: Context[A, B],
    convert: Convert[T, Number2[A, B]]
  )(parameter: T#Parameter, t: T#fromDataCtx): Number1[T#Result] = {
    val (newDataCtx, newNum) = convert.convert(t, tail())
    context.bindS(newDataCtx, newNum, parameter, head)
  }
}
case class Number2T[A, B](tail: () => Number2[A, B], head: B) extends Number2[A, B] {
  override def execute[T <: TypeContext](
    context: Context[A, B],
    convert: Convert[T, Number2[A, B]]
  )(parameter: T#Parameter, t: T#fromDataCtx): Number1[T#Result] = {
    val (newDataCtx, newNum) = convert.convert(t, tail())
    context.bindT(newDataCtx, newNum, parameter, head)
  }
}

trait Context[A, B] {
  def bindS[T <: TypeContext](number: T#toDataCtx, current: T#toDataType, parameter: T#Parameter, head: A): Number1[T#Result]
  def bindT[T <: TypeContext](number: T#toDataCtx, current: T#toDataType, parameter: T#Parameter, head: B): Number1[T#Result]
}

trait Convert[T <: TypeContext, fromDataType] {
  def convert(t: T#fromDataCtx, current: fromDataType): (T#toDataCtx, T#toDataType)
}

trait TypeContext {
  type fromDataCtx
  type toDataCtx
  type toDataType
  type Parameter
  type Result
}
