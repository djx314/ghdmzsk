package f10

object Runner {

  def main(arr: Array[String]): Unit = {
    {
      val exec = false
      if (exec) {
        def number1Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number1Gen(n - 1, zero)).resultPre else zero
        def number2Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number2Gen(n - 1, zero)).resultPre else zero
        for {
          i1 <- 0 to 20
          i2 <- 0 to 20
        } yield {
          lazy val MNumber1Zero: MNumber = MNumber(() => MNumber1Zero).reverse
          lazy val MNumber2Zero: MNumber = MNumber(() => MNumber2Zero)
          val number1                    = number1Gen(i1, MNumber1Zero)
          val number2                    = number2Gen(i2, MNumber2Zero)
          def number3                    = number1.method(number2)
          val number4                    = i1 + i2
          assert(MNumber.count(() => number1) == i1)
          assert(MNumber.count(() => number2) == i2)
          assert(Number.count(() => number3) == number4)
        }
        println("执行了第一个 assert")
      }
    }

    {
      val exec = false
      if (exec) {
        def number1Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number1Gen(n - 1, zero)).reverse else zero
        def number2Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number2Gen(n - 1, zero)).reverse else zero
        for {
          i1 <- 0 to 20
          i2 <- 0 to 20
        } yield {
          lazy val MNumber1Zero: MNumber = MNumber(() => MNumber1Zero)
          lazy val MNumber2Zero: MNumber = MNumber(() => MNumber2Zero).reverse.resultPre
          val number1                    = number1Gen(i1, MNumber1Zero)
          val number2                    = number2Gen(i2, MNumber2Zero)
          def number3                    = number1.method(number2)
          val number4                    = if (i1 - i2 > 0) i1 - i2 else 0
          assert(MNumber.count(() => number1) == i1)
          assert(MNumber.count(() => number2) == i2)
          assert(Number.count(() => number3) == number4)
        }
        println("执行了第二个 assert")
      }
    }

    {
      val exec = true
      if (exec) {
        def number1Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number1Gen(n - 1, zero)).reverse else zero
        def number2Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number2Gen(n - 1, zero)).resultPre else zero
        for {
          i1 <- 0 to 20
          i2 <- 0 to 20
        } yield {
          lazy val MNumber1Zero: MNumber = MNumber(() => MNumber1Zero)
          val number1: MNumber           = number1Gen(i1, MNumber1Zero)
          lazy val number2: MNumber      = number2Gen(i2, number2Zero)
          lazy val number2Zero: MNumber  = MNumber(() => number2).reverse
          def number3                    = number1.method(number2)
          val number4                    = i1 * i2
          assert(MNumber.count(() => number1) == i1)
          assert(MNumber.count(() => number2) == i2)
          assert(Number.count(() => number3) == number4)
        }
        println("执行了第三个 assert")
      }
    }

    {
      val exec = true
      if (exec) {
        def number1Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number1Gen(n - 1, zero)).reverse else zero
        def number2Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number2Gen(n - 1, zero)).reverse else zero
        for {
          i1 <- 0 to 20
          i2 <- 0 to 20
        } yield {
          lazy val MNumber1Zero: MNumber = MNumber(() => MNumber1Zero)
          val number1: MNumber           = number1Gen(i1, MNumber1Zero)
          lazy val number2: MNumber      = number2Gen(i2, number2Zero)
          lazy val number2Zero: MNumber  = MNumber(() => number2).resultPre
          def number3                    = number1.method(number2Zero)
          val number4 =
            if (i1 == 0) Option(0)
            else if (i2 == 0) Option.empty
            else if (i1 % i2 == 0) Option(i1 / i2)
            else Option(i1 / i2 + 1)
          assert(MNumber.count(() => number1) == i1)
          assert(MNumber.count(() => number2) == i2)
          assert(Number.countOpt(() => number3) == number4)
        }
        println("执行了第四个 assert")
      }
    }

    {
      val exec = true
      if (exec) {
        def number1Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number1Gen(n - 1, zero)).reverse else zero
        def number2Gen(n: Int, zero: => MNumber): MNumber = if (n > 0) MNumber(() => number2Gen(n - 1, zero)).reverse else zero
        for {
          i1 <- 0 to 20
          i2 <- 0 to 20
        } yield {
          lazy val number1: MNumber      = number1Gen(i1, MNumber1Zero)
          lazy val MNumber1Zero: MNumber = MNumber(() => number1).resultPre
          lazy val number2Zero: MNumber  = MNumber(() => number2Zero)
          val number2: MNumber           = number2Gen(i2, number2Zero)
          def number3                    = number1.method(number2)
          val number4                    = if (i1 == 0) Option.empty else Option(i2 / i1)
          assert(MNumber.count(() => number1) == i1)
          assert(MNumber.count(() => number2) == i2)
          assert(Number.countOpt(() => number3) == number4)
        }
        println("执行了第五个 assert")
      }
    }
  }

}
