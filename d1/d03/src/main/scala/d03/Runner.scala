package d03

import d01._

object Runner {
  def zeroNumber[T]: Number[T] = {
    lazy val zeroNumber: Number[T] = NumberT(() => zeroNumber)
    zeroNumber
  }

  def numberFromCollection[A](n: IterableOnce[A]): Number[A] = {
    val iterator            = n.iterator
    def toNumber: Number[A] = if (iterator.hasNext) NumberS(() => toNumber, iterator.next()) else zeroNumber
    toNumber
  }

  def dropFromInt(n: Int): Number[Unit] = n match {
    case n1 if n1 > 0 => NumberS(() => dropFromInt(n1 - 1), ())
    case 0            => zeroNumber
  }

  def numberFromFun[S](filter: S): Number[S] = {
    lazy val number1: Number[S] = NumberS(() => number2, filter)
    lazy val number2: Number[S] = NumberT(() => number1)
    number1
  }

  def number1ToList[T](number1: Collect[T]): List[T] = number1 match {
    case CollectS(tail, head) => head :: number1ToList(tail)
    case CollectT()           => List.empty
  }

  def printlnNumber1[T](number1: Collect[T]): String = number1 match {
    case CollectS(tail, head) => s"($head, ${printlnNumber1(tail)})"
    case CollectT()           => "zero"
  }

  def main(args: Array[String]): Unit = {
    val num1 = numberFromCollection(1 to 500).execute(new DropContext[Int, String])(
      (),
      (dropFromInt(60), numberFromFun((i: Int) => i < 70 || i % 5 == 0), numberFromFun((i: Int) => s"$i--$i"))
    )
    val col1 = (1 to 500).drop(60).filter(i => i < 70 || i % 5 == 0).map(i => s"$i--$i").to(List)
    val col2 = number1ToList(num1)
    assert(col1 == col2)
  }
}
