package f04

object Counter {
  def count(input1: Int, input2: Int, input3: Int): List[(String, Int)] = c01Counter.count(input1, input2, input3)

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

object c01Counter {
  import c01._

  def number1FromInt(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(number1FromInt(n1 - 1))
    case 0            => Number1T
  }

  def number2FromInt(n: Int): (Number2, Number2) = {
    def gen(n1: Int, zero: => Number2): Number2 = n1 match {
      case n2 if n2 > 0 => Number2S(gen(n2 - 1, zero))
      case 0            => zero
    }
    lazy val number2Positive: Number2 = gen(n, number2Zero)
    lazy val number2Zero: Number2     = Number2T(() => number2Positive)
    (number2Positive, number2Zero)
  }

  def number3FromInt(n: Int): (Number3, Number3) = {
    def gen(n1: Int, zero: => Number3): Number3 = n1 match {
      case n2 if n2 > 0 => Number3S(gen(n2 - 1, zero))
      case 0            => zero
    }
    lazy val number3Positive: Number3 = gen(n, number3Zero)
    lazy val number3Zero: Number3     = Number3T(() => number3Positive)
    (number3Positive, number3Zero)
  }

  def countResult(number: Number4): Int = number match {
    case Number4S(tail) => countResult(tail) + 1
    case Number4T       => 0
  }

  def count(input1: Int, input2: Int, input3: Int): List[(String, Int)] = {
    val number1                        = number1FromInt(input1)
    val (number2Positive, number2Zero) = number2FromInt(input2)
    val (number3Positive, number3Zero) = number3FromInt(input3)
    val result1                        = number1.method1(number2Positive, number3Positive)
    val result2                        = number1.method1(number2Positive, number3Zero)
    val result3                        = number1.method1(number2Zero, number3Positive)
    val result4                        = number1.method1(number2Zero, number3Zero)
    val result5                        = number2Positive.method2(number1, number3Positive)
    val result6                        = number2Positive.method2(number1, number3Zero)
    val result7                        = number2Zero.method2(number1, number3Positive)
    val result8                        = number2Zero.method2(number1, number3Zero)
    val result9                        = number3Positive.method3(number1, number2Positive)
    val result10                       = number3Positive.method3(number1, number2Zero)
    val result11                       = number3Zero.method3(number1, number2Positive)
    val result12                       = number3Zero.method3(number1, number2Zero)
    List(
      ("1.method1(2p, 3p)", countResult(result1)),
      ("1.method1(2p, 3o)", countResult(result2)),
      ("1.method1(2o, 3p)", countResult(result3)),
      ("1.method1(2o, 3o)", countResult(result4)),
      ("2p.method1(1, 3p)", countResult(result5)),
      ("2p.method1(1, 3o)", countResult(result6)),
      ("2o.method1(1, 3p)", countResult(result7)),
      ("2o.method1(1, 3o)", countResult(result8)),
      ("3p.method1(1, 2p)", countResult(result9)),
      ("3p.method1(1, 2o)", countResult(result10)),
      ("3o.method1(1, 2p)", countResult(result11)),
      ("3o.method1(1, 2o)", countResult(result12))
    )
  }

}
