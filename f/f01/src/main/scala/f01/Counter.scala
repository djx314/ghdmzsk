package f01

object Counter {
  def count(input1: Int, input2: Int): List[(String, Int)] = b01Counter.count(input1, input2) ::: b02Counter.count(input1, input2)

  def convert[T](l: List[T]): List[List[T]] = innerConvert(l, l.size)

  def innerConvert[T](l: List[T], count: Int): List[List[T]] = if (count > 0) {
    l match {
      case head :: Nil => List(List(head))
      case head :: tail =>
        val l1 = innerConvert(tail, tail.size).map(s => head :: s)
        val l2 = tail.appended(head)
        innerConvert(l2, count - 1) ::: l1
    }
  } else List.empty
}

object b01Counter {
  import b01._

  def number1FromInt(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(number1FromInt(n1 - 1), Item1(n1))
    case 0            => Number1T
  }

  def number2FromInt(n: Int): (Number2, Number2) = {
    def gen(n1: Int, zero: => Number2): Number2 = n1 match {
      case n2 if n2 > 0 => Number2S(gen(n2 - 1, zero), Item2(n2))
      case 0            => zero
    }
    lazy val number2Tail: Number2 = gen(n, number2Zero)
    lazy val number2Zero: Number2 = Number2T(() => number2Tail)
    (number2Tail, number2Zero)
  }

  def countResult(number: Number3): Int = number match {
    case Number3S(tail, _, _) => countResult(tail) + 1
    case Number3T             => 0
  }

  def count(input1: Int, input2: Int): List[(String, Int)] = {
    val number1                    = number1FromInt(input1)
    val (number2Tail, number2Zero) = number2FromInt(input2)
    val result1                    = number1.method1(number2Tail)
    val result2                    = number1.method1(number2Zero)
    List(("number1.method1(number2Tail)", countResult(result1)), ("number1.method1(number2Zero)", countResult(result2)))
  }

}

object b02Counter {
  import b02._

  def number1FromInt(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(number1FromInt(n1 - 1))
    case 0            => Number1T
  }

  def number2FromInt(n: Int): (Number2, Number2) = {
    def gen(n1: Int, zero: => Number2): Number2 = n1 match {
      case n2 if n2 > 0 => Number2S(gen(n2 - 1, zero), Item(n2))
      case 0            => zero
    }
    lazy val number2Tail: Number2 = gen(n, number2Zero)
    lazy val number2Zero: Number2 = Number2T(() => number2Tail)
    (number2Tail, number2Zero)
  }

  def countResult(number: Number3): Int = number match {
    case Number3S(tail, _) => countResult(tail) + 1
    case Number3T          => 0
  }

  def count(input1: Int, input2: Int): List[(String, Int)] = {
    val number1                    = number1FromInt(input1)
    val (number2Tail, number2Zero) = number2FromInt(input2)
    val result1                    = number2Tail.method2(number1)
    val result2                    = number2Zero.method2(number1)
    List(("number2Tail.method2(number1)", countResult(result1)), ("number2Zero.method2(number1)", countResult(result2)))
  }

}
