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

  def randomDeep()   = math.abs(util.Random.nextInt() % 10)
  def randomLength() = math.abs(util.Random.nextInt() % 5)

  def genNumber1(deep: Int): Number1 = {
    if (deep > randomDeep())
      Number1T
    else {
      def genList1(length: Int): Number1 = if (length > 0) Number1S(genList1(length - 1), genNumber2(deep + 1)) else Number1T
      genList1(randomLength())
    }
  }

  def genNumber2(deep: Int): Number2 = {
    if (deep > randomDeep())
      Number2T
    else {
      def genList2(length: Int): Number2 = if (length > 0) Number2S(genList2(length - 1), genNumber1(deep + 1)) else Number2T
      genList2(randomLength())
    }
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
    val result2 = count2(number4)

    println(count1(number1))
    println(count2(number2))
    println(count1(number3))
    println(count2(number4))
    assert(input1 + input2 == result1 || input1 + input2 == result2)

    for {
      i <- 1 to 15
      _ <- 1 to 3
    } {
      val num1 = genNumber1(-i)
      val num2 = genNumber2(-i - 1)
      val num3 = num1.method1(num2)
      val num4 = num2.method2(num1)

      val n1 = count1(num1)
      val n2 = count2(num2)
      val n3 = count2(num3)
      val n4 = count1(num4)

      assert(n1 + n2 == n3 || n1 + n2 == n4)
      println(s"($n1) + ($n2) = ($n3, ${n3 == n1 + n2}), ($n4, ${n4 == n1 + n2})")
    }
  }

}
