package e02

import e01._

import scala.util.Random

object Runner {
  private def zeroNumberImpl[T]: Number[T] = NumberT(() => zeroNumberImpl)

  def zero[T]: Number[T] = {
    lazy val number2Zero: Number[T] = zeroNumberImpl
    number2Zero
  }

  def dropFromInt(n: Int): Number[Unit] = if (n > 0) NumberS(() => dropFromInt(n - 1), ()) else zero

  def numberFromCollection[A](n: IterableOnce[A]): Number[A] = {
    val iterator            = n.iterator
    def toNumber: Number[A] = if (iterator.hasNext) NumberS(() => toNumber, iterator.next()) else zero
    toNumber
  }

  def number1ToList[T](number1: Collect[T]): List[T] = number1 match {
    case CollectS(tail, head) => head :: number1ToList(tail)
    case CollectT()           => List.empty
  }

  def main(args: Array[String]): Unit = {
    for {
      i1 <- 1 to 500
      i2 <- 1 to 500
    } yield {
      val i3    = Random.nextInt(300)
      val col1  = i1 to (i1 + i2)
      val right = col1.to(List).drop(i3)

      val leftNum1 = numberFromCollection(col1)
      val leftNum2 = dropFromInt(i3)
      val leftCol  = leftNum1.execute(new Context1[Int])((), leftNum2)
      val left     = number1ToList(leftCol)

      assert(left == right)

      val size = if (col1.length - i3 >= 0) col1.length - i3 else 0

      assert(left.length == size)
      assert(right.length == size)
    }
  }
}
