package f01

object Counter {
  def count(input1: Int, input2: Int): List[(String, Int)] = a02Counter.count(input1, input2)
}

object a02Counter {
  import a02._

  def number1FromInt(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1S(number1FromInt(n1 - 1), Item(n1))
    case 0            => Number1T
  }

  def number2FromInt(n: Int): Number2 = n match {
    case n1 if n1 > 0 => Number2S(number2FromInt(n1 - 1), Item(n1))
    case 0            => Number2T
  }

  def countResult(number: Number1): Int = number match {
    case Number1S(tail, _) => countResult(tail) + 1
    case Number1T          => 0
  }

  def count(input1: Int, input2: Int): List[(String, Int)] = {
    val number1 = number1FromInt(input1)
    val number2 = number2FromInt(input2)
    val result1 = number1.method1(number2, Item(0))
    val result2 = number2.method2(number1)
    List(("number1.method1(number2)", countResult(result1)), ("number2.method2(number1)", countResult(result2)))
  }

}
