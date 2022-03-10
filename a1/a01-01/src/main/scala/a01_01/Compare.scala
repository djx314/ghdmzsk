package a01_01

object Base {

  def numberFromList[T](l: List[T]): Number2[T] = l match {
    case head :: tail => Number2S(numberFromList(tail), head)
    case Nil          => Number2T
  }
  def listToNumber[T](number1: Number1[T]): List[T] = number1 match {
    case Number1S(tail, head) => head :: listToNumber(tail)
    case Number1T             => Nil
  }

}

object Compare {

  def reverseListImpl[T](l: List[T], base: List[T]): List[T] = l match {
    case head :: tail => reverseListImpl(tail, head :: base)
    case Nil          => base
  }
  def reverseList[T](l: List[T]): List[T] = reverseListImpl(l, List.empty)

}
