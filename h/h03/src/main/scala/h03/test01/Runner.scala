package h03.test01

import h03.test01.Number.NumberPositive

object Runner {

  def count(number: () => Number): Int = number() match {
    case NumberPositive(tail) => count(tail) + 1
    case _                    => 0
  }

  def number1Gen(n: Int): Number                  = if (n > 0) Number.Number1Positive(() => number1Gen(n - 1)) else Number.Number1Zero
  def number2Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number2Positive(() => number2Gen(n - 1, zero)) else zero
  def number3Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number3Positive(() => number3Gen(n - 1, zero)) else zero

  def confirm(type1: Int, type2: Int, type3: Int, num2TakePositive: Boolean, num3TakePositive: Boolean): (Int, Int, Int) => Int = {
    (i1, i2, i3) =>
      val number1: Number              = number1Gen(i1)
      lazy val number2Positive: Number = number2Gen(i2, number2Zero)
      lazy val number2Zero: Number     = Number.Number2Zero(() => number2Positive)
      lazy val number3Positive: Number = number3Gen(i3, number3Zero)
      lazy val number3Zero: Number     = Number.Number3Zero(() => number3Positive)

      val num2 = if (num2TakePositive) number2Positive else number2Zero
      val num3 = if (num3TakePositive) number3Positive else number3Zero

      def takeNum(n: Int): Number = n match {
        case 1 => number1
        case 2 => num2
        case 3 => num3
      }

      val numResult = takeNum(type1)(() => takeNum(type2))(() => takeNum(type3))

      count(() => numResult)
  }

  def main(arr: Array[String]): Unit = {
    locally {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
        i3 <- 0 to 20
      } {
        val number1: Number              = number1Gen(i1)
        lazy val number2Positive: Number = number2Gen(i2, number2Zero)
        lazy val number2Zero: Number     = Number.Number2Zero(() => number2Positive)
        lazy val number3Positive: Number = number3Gen(i3, number3Zero)
        lazy val number3Zero: Number     = Number.Number3Zero(() => number3Positive)

        def r1 = number1(() => number2Positive)(() => number3Positive)
        def r2 = number1(() => number3Zero)(() => number2Positive)
        def r3 = number2Zero(() => number1)(() => number3Positive)
        def r4 = number2Zero(() => number3Zero)(() => number1)
        def r5 = number3Zero(() => number2Positive)(() => number1)
        def r6 = number3Zero(() => number1)(() => number2Zero)

        val result1: Int  = count(() => r1)
        val result2: Int  = count(() => r2)
        val result3: Int  = count(() => r3)
        val result4: Int  = count(() => r4)
        val result5: Int  = count(() => r5)
        val result6: Int  = count(() => r6)
        val countInt: Int = i1 * i2 * i3
        println(i1, i2, i3, countInt, result1, countInt == result1)
        assert(countInt == result1)
        assert(countInt == result2)
        assert(countInt == result3)
        assert(countInt == result4)
        assert(countInt == result5)
        assert(countInt == result6)
      }
    }
    locally { () =>
      val testCol = for {
        i1 <- 1 to 3
        i2 <- 1 to 3
        i3 <- 1 to 3
      } yield ((i1, i2, i3), Set(i1, i2, i3))
      val dataCol = testCol.filter(_._2.size == 3).map(_._1)

      for {
        num2TakePositive      <- List(true, false)
        num3TakePositive      <- List(true, false)
        (type1, type2, type3) <- dataCol
      } {
        val confirmListData = for {
          i1 <- 0 to 20
          i2 <- 0 to 20
          i3 <- 0 to 20
        } yield {
          val yunsuan =
            confirm(type1 = type1, type2 = type2, type3 = type3, num2TakePositive = num2TakePositive, num3TakePositive = num3TakePositive)
          yunsuan(i1, i2, i3) == i1 * i2 * i3
        }
        val confirmYunsuan = confirmListData.forall(identity)
        if (confirmYunsuan) {
          println(s"Confirm: ($type1, $type2, $type3), num2TakePositive: $num2TakePositive, num3TakePositive: $num3TakePositive")
        }
      }
    }
  }

}
