package b03_01

object Base {

  def number1FromList[T](l: List[T]): Number1[T] = l match {
    case head :: tail => Number1S(number1FromList(tail), head)
    case Nil          => Number1T
  }

  def number2FromInt(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(number2FromInt(n - 1, zero)) else zero

  def numberToList[T](number1: Number3[T]): List[T] = number1 match {
    case Number3S(tail, head) => head :: numberToList(tail)
    case Number3T             => Nil
  }

}

object Compare {

  def drop[T](l: List[T], split: Int): List[T] = l.zipWithIndex.filter(s => (s._2 + 1) % split > 0).map(_._1)

}
