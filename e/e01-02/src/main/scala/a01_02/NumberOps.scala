package e01_02

object NumberOps {

  def ops_::[A, B >: A](number: Number[A])(elem: B): Number[B]              = NumberS(() => number, head = elem)
  def ops_:::[A, B >: A](numberA: Number[A])(numberB: Number[B]): Number[B] = numberB.execute(Ops_冒号冒号冒号.ops_:::)((), numberA)

}
