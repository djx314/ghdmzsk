package h03.test02

import h03.test02.Number.NumberPositive

object Runner {

  def count(number: () => Number): Option[Int] =
    try
      number() match {
        case NumberPositive(tail) => for (nextInt <- count(tail)) yield nextInt + 1
        case _                    => Some(0)
      }
    catch {
      case _: StackOverflowError => None
    }

  def number1Gen(n: Int): Number                  = if (n > 0) Number.Number1Positive(() => number1Gen(n - 1)) else Number.Number1Zero
  def number2Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number2Positive(() => number2Gen(n - 1, zero)) else zero
  def number3Gen(n: Int, zero: => Number): Number = if (n > 0) Number.Number3Positive(() => number3Gen(n - 1, zero)) else zero

  def confirm(type1: Int, type2: Int, type3: Int, num2TakePositive: Boolean, num3TakePositive: Boolean): (Int, Int, Int) => Option[Int] = {
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
    locally { () =>
      for {
        i1 <- 0 to 50
        i2 <- 0 to 50
        i3 <- 0 to 50
      } {
        val number1: Number              = number1Gen(i1)
        lazy val number2Positive: Number = number2Gen(i2, number2Zero)
        lazy val number2Zero: Number     = Number.Number2Zero(() => number2Positive)
        lazy val number3Positive: Number = number3Gen(i3, number3Zero)
        lazy val number3Zero: Number     = Number.Number3Zero(() => number3Positive)

        def r1 = number2Positive(() => number3Positive)(() => number1)
        def r2 = number3Positive(() => number1)(() => number2Positive)
        def r3 = number2Zero(() => number1)(() => number3Positive)

        val result1: Option[Int] = count(() => r1)
        val result2: Option[Int] = count(() => r2)
        val result3: Option[Int] = count(() => r3)
        val countInt1: Option[Int] = {
          if (i1 == 0 && i2 == 0) Option(0)
          else if (i2 == 0 || i3 == 0) Option.empty
          else Option(i1 / (i2 * i3))
        }
        println(i1, i2, i3, countInt1, result1)
        assert(countInt1 == result1)

        if (i2 > 0 && i3 > 0) {
          val countIntPositive1: Option[Int] = Some(i1 / i2 / i3)
          val countIntPositive2: Option[Int] = Some(i1 / (i2 * i3))
          assert(countIntPositive1 == result1)
          assert(countIntPositive1 == result2)
          assert(countIntPositive1 == result3)
          assert(countIntPositive1 == countIntPositive2)
        }
      }
    }

    locally {
      for {
        i1 <- 0 to 50
        i2 <- 1 to 50
        i3 <- 1 to 50
      } {
        val number1: Number              = number1Gen(i1)
        lazy val number2Positive: Number = number2Gen(i2, number2Zero)
        lazy val number2Zero: Number     = Number.Number2Zero(() => number2Positive)
        lazy val number3Positive: Number = number3Gen(i3, number3Zero)
        lazy val number3Zero: Number     = Number.Number3Zero(() => number3Positive)

        def r1 = number1(() => number3Zero)(() => number2Positive)
        def r2 = number1(() => number2Zero)(() => number3Zero)
        def r3 = number2Zero(() => number3Zero)(() => number1)

        val result1: Option[Int]           = count(() => r1)
        val result2: Option[Int]           = count(() => r2)
        val result3: Option[Int]           = count(() => r3)
        val countIntPositive1: Option[Int] = Some((((i1 + i2 - 1) / i2) + i3 - 1) / i3)
        val countIntPositive2: Option[Int] = Some((i1 + i2 * i3 - 1) / (i2 * i3))
        println(i1, i2, i3, countIntPositive1, result1)
        assert(countIntPositive1 == result1)
        assert(countIntPositive1 == result2)
        assert(countIntPositive1 == result3)
        assert(countIntPositive1 == countIntPositive2)
      }
    }

    locally {
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
          i2 <- 1 to 8
          i3 <- 1 to 8
        } yield {
          val yunsuan =
            confirm(type1 = type1, type2 = type2, type3 = type3, num2TakePositive = num2TakePositive, num3TakePositive = num3TakePositive)
          yunsuan(i1, i2, i3) == Some((i1 + i2 * i3 - 1) / (i2 * i3))
        }
        val confirmYunsuan = confirmListData.forall(identity)
        if (confirmYunsuan) {
          println(s"Confirm: ($type1, $type2, $type3), num2TakePositive: $num2TakePositive, num3TakePositive: $num3TakePositive")
        }
      }
    }
  }

}
