package f03

object Counter {
  def count(input1: Int, input2: Int): List[(String, Int)] = b03Counter.count(input1, input2) ::: b04Counter.count(input1, input2)

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

  def tranList(list: List[(String, Int)]): List[List[(String, Int)]] = {
    val l1                               = list.sortBy(_._2)
    var total: List[List[(String, Int)]] = List(List.empty)
    var current: List[(String, Int)]     = List(l1.head)
    var currentValue: Int                = l1.head._2

    def flush = total = for {
      c <- convert(current)
      t <- total
    } yield t ::: c

    for (each <- l1.tail) {
      if (each._2 == currentValue) {
        current = current.appended(each)
      } else {
        flush
        current = List(each)
        currentValue = each._2
      }
    }

    flush

    total
  }
}

object b03Counter {
  import b03._

  def number1FromInt(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(number1FromInt(n1 - 1), Item1(n1))
    case 0            => Number1T
  }

  def number2FromInt(n: Int): (Number2, Number2) = {
    def gen(n1: Int, zero: => Number2): Number2 = n1 match {
      case n2 if n2 > 0 => Number2S(gen(n2 - 1, zero), Item2(n2))
      case 0            => zero
    }
    lazy val number2Positive: Number2 = gen(n, number2Zero)
    lazy val number2Zero: Number2     = Number2T(() => number2Positive)
    (number2Positive, number2Zero)
  }

  def countResult(number: Number3): Int = number match {
    case Number3S(tail) => countResult(tail) + 1
    case Number3T       => 0
  }

  def count(input1: Int, input2: Int): List[(String, Int)] = {
    val number1                        = number1FromInt(input1)
    val (number2Positive, number2Zero) = number2FromInt(input2)
    val result1                        = number1.method1(number2Positive)
    val result2                        = number1.method1(number2Zero)
    List(("number1.method1(number2Positive)", countResult(result1)), ("number1.method1(number2Zero)", countResult(result2)))
  }

}

object b04Counter {
  import b04._

  def number1FromInt(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(number1FromInt(n1 - 1), Item1(n1))
    case 0            => Number1T
  }

  def number2FromInt(n: Int): (Number2, Number2) = {
    def gen(n1: Int, zero: => Number2): Number2 = n1 match {
      case n2 if n2 > 0 => Number2S(gen(n2 - 1, zero), Item2(n2))
      case 0            => zero
    }
    lazy val number2Positive: Number2 = gen(n, number2Zero)
    lazy val number2Zero: Number2     = Number2T(() => number2Positive)
    (number2Positive, number2Zero)
  }

  def countResult(number: Number3): Int = number match {
    case Number3S(tail) => countResult(tail) + 1
    case Number3T       => 0
  }

  def count(input1: Int, input2: Int): List[(String, Int)] = {
    val number1                        = number1FromInt(input1)
    val (number2Positive, number2Zero) = number2FromInt(input2)
    val result1                        = number2Positive.method2(number1)
    val result2                        = number2Zero.method2(number1)
    List(("number2Positive.method2(number1)", countResult(result1)), ("number2Zero.method2(number1)", countResult(result2)))
  }

}
