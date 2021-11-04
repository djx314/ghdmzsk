package a02_01

object Base {

  def number1FromList[T](l: List[T]): Number1[T] = l match {
    case head :: tail => Number1S(number1FromList(tail), head)
    case Nil          => Number1T
  }
  def number2FromItem[T](item: T): Number2[T] = {
    lazy val number2: Number2[T] = Number2S(() => number2, Number2T, item)
    number2
  }
  def listToNumber[T](number1: Number1[T]): List[T] = number1 match {
    case Number1S(tail, head) => head :: listToNumber(tail)
    case Number1T             => Nil
  }

}

object Compare {

  def dropFirstSameItem[T](l: List[T], item: T): List[T] = l match {
    case head :: tail => if (head == item) tail else head :: dropFirstSameItem(tail, item)
    case Nil          => Nil
  }

}
