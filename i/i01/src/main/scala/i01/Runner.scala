package i01

object Runner {

  def count1(number: Number1): Int = number match {
    case Number1S(tail, head) => count1(tail) + count2(head) - 1
    case Number1T             => 0
  }

  def count2(number: Number2): Int = number match {
    case Number2S(tail, head) => count2(tail) + count1(head) + 1
    case Number2T             => 0
  }

  def main(arr: Array[String]): Unit = {
    val number1 = Number1S(
      Number1S(Number1S(Number1S(Number1S(Number1T, Number2T), Number2T), Number2T), Number2T),
      Number2S(Number2S(Number2S(Number2T, Number1S(Number1T, Number2S(Number2T, Number1S(Number1T, Number2T)))), Number1T), Number1T)
    )
    val number2 = Number2S(Number2T, Number1S(Number1T, Number2S(Number2S(Number2S(Number2T, Number1T), Number1T), Number1T)))
    val number3 = number2.method2(number1)
    val number4 = number1.method1(number2)

    val input1  = count1(number1)
    val input2  = count2(number2)
    val result1 = count1(number3)

    println(count1(number1))
    println(count2(number2))
    println(count1(number3))
    println(count2(number4))
    assert(input1 + input2 == result1)
  }

}
