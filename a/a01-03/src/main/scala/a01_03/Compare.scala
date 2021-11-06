package a01_03

object Base {

  def number1FromList[T](list: List[T]): Number1[T] = list match {
    case head :: tail => Number1S(number1FromList(tail), head)
    case Nil          => Number1T
  }

  def number1ToList[T](number1: Number1[T]): List[T] = number1 match {
    case Number1S(tail, head) => head :: number1ToList(tail)
    case Number1T             => Nil
  }

}
