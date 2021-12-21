package e01_02

object OpsConvertList {

  private class toListContext[A] extends Context[Unit, A, List[A]] {
    override def bindS(t: Unit, current: () => Number[A], head: A): List[A] = head :: current().execute(this)(())
    override def bindT(t: Unit, current: () => Number[A]): List[A]          = List.empty
  }

  def toList[A](number: Number[A]): List[A] = number.execute(new toListContext)(())
  def fromList[A](list: List[A]): Number[A] = list match {
    case head :: tail => NumberS(() => fromList(tail), head)
    case Nil          => NumberOps.empty
  }

}
