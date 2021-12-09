package a02_02

object Base {

  def number1FromList[T](list: List[T]): Number1[T] = list match {
    case head :: tail => Number1S(number1FromList(tail), head)
    case Nil          => Number1T
  }

  def number1ToList[T](number1: Number1[T]): List[T] = number1 match {
    case Number1S(tail, head) => head :: number1ToList(tail)
    case Number1T             => Nil
  }

  lazy val number2: Number2 = Number2S(() => number2)

  def dropRight[T](number1: Number1[T]): Number1[T] = number1 match {
    case Number1S(tail, head) => tail.method1(number2, head)
    case Number1T             => Number1T
  }

}

object Compare {

  def dropRightImpl[T](l: List[T], item: T): List[T] = l match {
    case head :: tail => item :: dropRightImpl(tail, head)
    case Nil          => Nil
  }

  def dropRight[T](l: List[T]): List[T] = l match {
    case head :: tail => dropRightImpl(tail, head)
    case Nil          => Nil
  }

}
