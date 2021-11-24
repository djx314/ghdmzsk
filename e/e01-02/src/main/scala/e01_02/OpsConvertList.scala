package e01_02

object OpsConvertList {

  private class ToListContext[A] extends TypeContext {
    override type toDataType = Unit
    override type Parameter  = Unit
    override type Result     = List[A]
  }

  private def toListContext[A]: Context[ToListContext[A], A] = new Context[ToListContext[A], A] {
    override type DataCtx = () => Number[A]
    override def convertS(t: Unit, current: () => Number[A]): () => Number[A]      = current
    override def convertT(t: Unit, current: () => Number[A]): () => Number[A]      = current
    override def bindS(number: () => Number[A], parameter: Unit, head: A): List[A] = head :: number().execute(this)((), ())
    override def bindT(number: () => Number[A], parameter: Unit): List[A]          = List.empty
  }

  def toList[A](number: Number[A]): List[A] = number.execute(toListContext)((), ())
  def fromList[A](list: List[A]): Number[A] = list match {
    case head :: tail => NumberS(() => fromList(tail), head)
    case Nil          => Number.empty
  }

}
