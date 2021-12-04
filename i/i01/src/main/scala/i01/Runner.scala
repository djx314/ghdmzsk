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
    val number3 = number1.method1(number2)
    val number4 = number2.method2(number1)

    val input1  = count1(number1)
    val input2  = count2(number2)
    val result1 = count2(number3)
    val result2 = count1(number4)

    println(count1(number1)) // -3
    println(count2(number2)) // 3
    println(count2(number3)) // 0
    println(count1(number4)) // -2
    assert(input1 + input2 == result1)

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

      assert(n1 + n2 == n3)
      println(s"$n1 + $n2 = $n3 ${n3 == n1 + n2}")

// 0 + 17 = 17 true
// 1 + 2 = 3 true
// -1 + 18 = 17 true
// 0 + 10 = 10 true
// -5 + 33 = 28 true
// 0 + -28 = -28 true
// 0 + -5 = -5 true
// 0 + 1 = 1 true
// 7 + 20 = 27 true
// 24 + 73 = 97 true
// -8 + 25 = 17 true
// 20 + 0 = 20 true
// 0 + 14 = 14 true
// 0 + -34 = -34 true
// 62 + 109 = 171 true
// 0 + 0 = 0 true
// 19 + 24 = 43 true
// 92 + 41 = 133 true
// -9 + 0 = -9 true
// 64 + 1 = 65 true
// 17 + 104 = 121 true
// -50 + -174 = -224 true
// -160 + 86 = -74 true
// -110 + -96 = -206 true
// -58 + 51 = -7 true
// -9 + -149 = -158 true
// 38 + 6 = 44 true
// -102 + 0 = -102 true
// -224 + -159 = -383 true
// 50 + -315 = -265 true
// 0 + 254 = 254 true
// 0 + -667 = -667 true
// 0 + 0 = 0 true
// 0 + 0 = 0 true
// 969 + 0 = 969 true
// 265 + -838 = -573 true
// 1734 + 1037 = 2771 true
// -506 + 820 = 314 true
// -218 + 700 = 482 true
// 405 + 0 = 405 true
// -1055 + -822 = -1877 true
// 12 + 16 = 28 true
// 1157 + 354 = 1511 true
// -277 + -1791 = -2068 true
// 1048 + -319 = 729 true
    }
  }

}
