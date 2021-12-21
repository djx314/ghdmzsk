package e03

import e01._

object Runner {
  private def zeroNumberImpl[T]: Number[T] = NumberT(() => zeroNumberImpl)

  def zeroNumber[T]: Number[T] = {
    val zeroNumber: Number[T] = zeroNumberImpl
    zeroNumber
  }

  def numberFromCollection[A](n: IterableOnce[A]): Number[A] = {
    val iterator            = n.iterator
    def toNumber: Number[A] = if (iterator.hasNext) NumberS(() => toNumber, iterator.next()) else zeroNumber
    toNumber
  }

  def numberFromFun[S](filter: S, zero: => Number[S]): Number[S] = NumberS(() => zero, filter)

  def dropFromInt(n: Int): Number[Unit] = if (n > 0) NumberS(() => dropFromInt(n - 1), ()) else zeroNumber

  def number1ToList[T](number1: Collect[T]): List[T] = number1 match {
    case CollectS(tail, head) => head :: number1ToList(tail)
    case CollectT()           => List.empty
  }

  def printlnNumber1[T](number1: Collect[T]): String = number1 match {
    case CollectS(tail, head) => s"($head, ${printlnNumber1(tail)})"
    case CollectT()           => "zero"
  }

  def main(args: Array[String]): Unit = {

    lazy val numberFilterA_1: Number[Int => Boolean] = numberFromFun((i: Int) => i < 70 || i % 5 == 0, numberFilterA_2)
    lazy val numberFilterA_2: Number[Int => Boolean] = NumberT(() => numberFilterA_1)
    lazy val numberFilterB_1: Number[Int => String]  = numberFromFun((i: Int) => s"$i--$i", numberFilterB_2)
    lazy val numberFilterB_2: Number[Int => String]  = NumberT(() => numberFilterB_1)

    val num1 = numberFromCollection(1 to 500).execute(new DropContext[Int, String])((dropFromInt(60), numberFilterA_1, numberFilterB_1))
    val col1 = (1 to 500).drop(60).filter(i => i < 70 || i % 5 == 0).map(i => s"$i--$i").to(List)
    val col2 = number1ToList(num1)
    assert(col1 == col2)
  }
}
