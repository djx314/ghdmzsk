package d05

object Runner {

  def countNumber(num: Result): Int = num match {
    case ResultP(tail, head) => countNumber(tail) + 1
    case ResultO             => 0
  }

  def numberGen1(n: Int): NumberRight = {
    def gen(n1: Int, zero: => NumberRight): NumberRight = n1 match {
      case n2 if n2 > 0 => NumberRightS(gen(n2 - 1, zero), new Item)
      case 0            => zero
    }
    lazy val num1: NumberRight = gen(n, num2)
    lazy val num2: NumberRight = NumberRightT(() => num1)
    num1
  }

  def numberGen2(n: Int): NumberMiddle = {
    def gen(n1: Int, zero: => NumberMiddle): NumberMiddle = n1 match {
      case n2 if n2 > 0 => NumberMiddleS(gen(n2 - 1, zero))
      case 0            => zero
    }
    lazy val num1: NumberMiddle = gen(n, num2)
    lazy val num2: NumberMiddle = NumberMiddleT(() => num1)
    num1
  }

  def numberGen3(n: Int): NumberLeft = n match {
    case n1 if n1 > 0 => NumberLeftS(numberGen3(n1 - 1))
    case 0            => NumberLeftT
  }

  def countPow(n1: Int, n2: Int): Result = {
    val num1 = numberGen1(n1)
    val num2 = numberGen2(n1)
    val num3 = numberGen3(n1)
    def num4(n3: Int): NumberRight = n3 match {
      case n4 if n4 > 2 => NumberRightU(num4(n4 - 1), num2)
      case 2            => num1
    }
    val num5 = num4(n2)
    num3.左推动(num5)
  }

  def main(arr: Array[String]): Unit = {
    {
      val n1: NumberLeft = NumberLeftS(NumberLeftS(NumberLeftS(NumberLeftS(NumberLeftT))))

      lazy val n2: NumberMiddle = NumberMiddleS(NumberMiddleS(NumberMiddleS(NumberMiddleS(n3))))
      lazy val n3: NumberMiddle = NumberMiddleT(() => n2)

      lazy val n4: NumberRight = NumberRightS(NumberRightS(NumberRightS(NumberRightS(n5, new Item), new Item), new Item), new Item)
      lazy val n5: NumberRight = NumberRightT(() => n4)

      val n6 = NumberRightU(NumberRightU(NumberRightU(n4, n2), n2), n2)

      val n7 = n1.左推动(n6)

      val res1 = countNumber(n7)
      val res2 = math.pow(4, 5).toInt
      assert(res1 == res2)
    }

    {
      for {
        i1 <- 1 to 6
        i2 <- 2 to 8
      } {
        val res1 = countNumber(countPow(i1, i2))
        val res2 = math.pow(i1, i2).toInt
        assert(res1 == res2)
      }
    }
  }

}
