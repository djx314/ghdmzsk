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

    println(count1(number1)) // -3
    println(count2(number2)) // 3
    println(count1(number3)) // 0
    println(count2(number4)) // 2
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

// (4) + (3) = (7, true), (5, false)
// (-7) + (2) = (-3, false), (-5, true)
// (-4) + (-24) = (-28, true), (-30, false)
// (0) + (37) = (37, true), (35, false)
// (12) + (7) = (19, true), (17, false)
// (-5) + (2) = (-1, false), (-3, true)
// (3) + (-1) = (2, true), (0, false)
// (0) + (-1) = (-1, true), (-3, false)
// (34) + (-37) = (-3, true), (-5, false)
// (0) + (12) = (12, true), (10, false)
// (41) + (-4) = (37, true), (35, false)
// (0) + (-19) = (-19, true), (-21, false)
// (-1) + (1) = (2, false), (0, true)
// (3) + (19) = (22, true), (20, false)
// (0) + (39) = (39, true), (37, false)
// (12) + (100) = (112, true), (110, false)
// (100) + (-76) = (24, true), (22, false)
// (63) + (24) = (87, true), (85, false)
// (38) + (101) = (139, true), (137, false)
// (0) + (-81) = (-81, true), (-83, false)
// (-51) + (60) = (9, true), (7, false)
// (0) + (-80) = (-80, true), (-82, false)
// (60) + (26) = (88, false), (86, true)
// (-113) + (-33) = (-146, true), (-148, false)
// (0) + (-54) = (-54, true), (-56, false)
// (0) + (0) = (0, true), (0, true)
// (-28) + (0) = (-28, true), (-28, true)
// (303) + (-353) = (-48, false), (-50, true)
// (-98) + (14) = (-84, true), (-86, false)
// (195) + (1) = (196, true), (194, false)
// (-274) + (-341) = (-613, false), (-615, true)
// (-180) + (923) = (745, false), (743, true)
// (74) + (-718) = (-642, false), (-644, true)
// (-219) + (-479) = (-696, false), (-698, true)
// (258) + (-90) = (170, false), (168, true)
// (212) + (0) = (212, true), (212, true)
// (-553) + (0) = (-551, false), (-553, true)
// (0) + (1406) = (1406, true), (1404, false)
// (452) + (290) = (742, true), (740, false)
// (761) + (663) = (1426, false), (1424, true)
// (-243) + (-1030) = (-1271, false), (-1273, true)
// (-659) + (-935) = (-1594, true), (-1596, false)
// (541) + (0) = (541, true), (541, true)
// (539) + (1065) = (1606, false), (1604, true)
// (867) + (2150) = (3019, false), (3017, true)
    }
  }

}
