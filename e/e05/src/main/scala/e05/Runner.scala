package e05

object Runner {

  def countNumber(num: Result): Int = num match {
    case ResultP(tail, head) => countNumber(tail) + 1
    case ResultO             => 0
  }

  def numberGen1(n: Int, zero: => NumberRight): NumberRight   = if (n > 0) NumberRightS(numberGen1(n - 1, zero), new Item) else zero
  def numberGen2(n: Int, zero: => NumberMiddle): NumberMiddle = if (n > 0) NumberMiddleS(numberGen2(n - 1, zero)) else zero
  def numberGen3(n: Int): NumberLeft                          = if (n > 0) NumberLeftS(numberGen3(n - 1)) else NumberLeftT

  def countPow(n1: Int, n2: Int): Result = {
    lazy val num1: NumberRight     = numberGen1(n1, num1Impl)
    lazy val num1Impl: NumberRight = NumberRightT(() => num1)

    lazy val num2: NumberMiddle     = numberGen2(n1, num2Impl)
    lazy val num2Impl: NumberMiddle = NumberMiddleT(() => num2)

    val num3                       = numberGen3(n1)
    def num4(n3: Int): NumberRight = if (n3 > 2) NumberRightU(num4(n3 - 1), num2) else num1

    val num5 = num4(n2)
    num3.method3(num5)
  }

  def main(arr: Array[String]): Unit = {
    {
      val n1: NumberLeft = NumberLeftS(NumberLeftS(NumberLeftS(NumberLeftS(NumberLeftT))))

      lazy val n2: NumberMiddle = NumberMiddleS(NumberMiddleS(NumberMiddleS(NumberMiddleS(n3))))
      lazy val n3: NumberMiddle = NumberMiddleT(() => n2)

      lazy val n4: NumberRight = NumberRightS(NumberRightS(NumberRightS(NumberRightS(n5, new Item), new Item), new Item), new Item)
      lazy val n5: NumberRight = NumberRightT(() => n4)

      val n6 = NumberRightU(NumberRightU(NumberRightU(n4, n2), n2), n2)

      val n7 = n1.method3(n6)

      val res1 = countNumber(n7)
      val res2 = math.pow(4, 5).toInt
      assert(res1 == res2)
    }
    {
      for {
        i1 <- 1 to 5
        i2 <- 2 to 8
      } {
        val res1 = countNumber(countPow(i1, i2))
        val res2 = math.pow(i1, i2).toInt
        assert(res1 == res2)
      }
    }
  }

}
