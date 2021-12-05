package i01

object Runner {

  def count1(number: Number1): Int = number match {
    case Number1S(tail, head, _) => count1(tail) + count2(head) + 1
    case Number1T                => 0
  }

  def count2(number: Number2): Int = number match {
    case Number2S(tail, head) => count2(tail) + count1(head) - 1
    case Number2T             => 0
  }

  def randomDeep()   = math.abs(util.Random.nextInt() % 10)
  def randomLength() = math.abs(util.Random.nextInt() % 5)

  def genNumber1(deep: Int): Number1 = {
    if (deep > randomDeep())
      Number1T
    else {
      def genList1(length: Int): Number1 = if (length > 0) Number1S(genList1(length - 1), genNumber2(deep + 1), data) else Number1T
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

  val data = Item(2)

  def main(arr: Array[String]): Unit = {
    val number1 = Number1S(
      Number1S(Number1S(Number1S(Number1S(Number1T, Number2T, data), Number2T, data), Number2T, data), Number2T, data),
      Number2S(
        Number2S(Number2S(Number2T, Number1S(Number1T, Number2S(Number2T, Number1S(Number1T, Number2T, data)), data)), Number1T),
        Number1T
      ),
      data
    )
    val number2 = Number2S(Number2T, Number1S(Number1T, Number2S(Number2S(Number2S(Number2T, Number1T), Number1T), Number1T), data))
    val number3 = number1.method1(number2)
    val number4 = number2.method2(number1, data)

    val input1  = count1(number1)
    val input2  = count2(number2)
    val result1 = count2(number3)
    val result2 = count1(number4)

    println(input1)  // 3
    println(input2)  // -3
    println(result1) // 0
    println(result2) // -2
    assert(input1 + input2 == result1)

    for {
      i <- 1 to 15
      _ <- 1 to 3
    } {
      val num1 = genNumber1(-i)
      val num2 = genNumber2(-i - 1)
      val num3 = num1.method1(num2)

      val n1 = count1(num1)
      val n2 = count2(num2)
      val n3 = count2(num3)

      assert(n1 + n2 == n3)
      println(s"$n1 + $n2 = $n3 ${n3 == n1 + n2}")

// 11 + 5 = 16 true
// 5 + 0 = 5 true
// 15 + 1 = 16 true
// 5 + 0 = 5 true
// -7 + 23 = 16 true
// -13 + 0 = -13 true
// -10 + -4 = -14 true
// -11 + 26 = 15 true
// -1 + -1 = -2 true
// 0 + 23 = 23 true
// -25 + -7 = -32 true
// -10 + 0 = -10 true
// 1 + -47 = -46 true
// -48 + 14 = -34 true
// 31 + 62 = 93 true
// -70 + 0 = -70 true
// 120 + -101 = 19 true
// -9 + -2 = -11 true
// 34 + -173 = -139 true
// -1 + 80 = 79 true
// 26 + -13 = 13 true
// 108 + 28 = 136 true
// -211 + -35 = -246 true
// 21 + -5 = 16 true
// 92 + -61 = 31 true
// 17 + -364 = -347 true
// 0 + -130 = -130 true
// 318 + 377 = 695 true
// 0 + 0 = 0 true
// -47 + 406 = 359 true
// 131 + -61 = 70 true
// -51 + -339 = -390 true
// 145 + 0 = 145 true
// -76 + 405 = 329 true
// 0 + 0 = 0 true
// 686 + 75 = 761 true
// -254 + 0 = -254 true
// 0 + -73 = -73 true
// 369 + -92 = 277 true
// 494 + -182 = 312 true
// 580 + 918 = 1498 true
// -610 + -1 = -611 true
// -342 + 5 = -337 true
// 0 + 0 = 0 true
// 1 + 0 = 1 true
    }
  }

}
