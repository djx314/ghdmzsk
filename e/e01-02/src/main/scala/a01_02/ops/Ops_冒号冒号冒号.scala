package e01_02

object Ops_冒号冒号冒号 {

  class Ops_:::[A] extends TypeContext {
    override type toDataType = Number[A]
    override type Parameter  = Unit
    override type Result     = Number[A]
  }

  def ops_:::[A]: Context[Ops_:::[A], A] = new Context[Ops_:::[A], A] {
    override type DataCtx = (Number[A], () => Number[A])
    override def convertS(t: Number[A], current: () => Number[A]): (Number[A], () => Number[A]) = (t, current)
    override def convertT(t: Number[A], current: () => Number[A]): (Number[A], () => Number[A]) = (t, current)
    override def bindS(number: (Number[A], () => Number[A]), parameter: Unit, head: A): Number[A] =
      NumberS(() => number._2().execute(this)((), number._1), head)
    override def bindT(number: (Number[A], () => Number[A]), parameter: Unit): Number[A] = Number.empty
  }

}
