package a03

trait PowRunnerImplement {
  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  val num: Int
  val nextGen: Number1 => Number1
  def gen1(next: Number1 => Number1): Number1 => Number1

  lazy val number1: Number1     = nextGen(number1Zero)
  lazy val number1Zero: Number1 = Number1S(() => number2)

  lazy val number2: Number1     = gen1(nextGen)(number2Zero)
  lazy val number2Zero: Number1 = Number1S(() => number3)

  lazy val number3: Number1     = gen1(gen1(nextGen))(number3Zero)
  lazy val number3Zero: Number1 = Number1S(() => number4)

  lazy val number4: Number1     = gen1(gen1(gen1(nextGen)))(number4Zero)
  lazy val number4Zero: Number1 = Number1S(() => number5)

  lazy val number5: Number1     = gen1(gen1(gen1(gen1(nextGen))))(number5Zero)
  lazy val number5Zero: Number1 = Number1S(() => number6)

  lazy val number6: Number1     = gen1(gen1(gen1(gen1(gen1(nextGen)))))(number6Zero)
  lazy val number6Zero: Number1 = Number1S(() => number7)

  lazy val number7: Number1     = gen1(gen1(gen1(gen1(gen1(gen1(nextGen))))))(number7Zero)
  lazy val number7Zero: Number1 = Number1S(() => number8)

  lazy val number8: Number1     = gen1(gen1(gen1(gen1(gen1(gen1(gen1(nextGen)))))))(number8Zero)
  lazy val number8Zero: Number1 = Number1S(() => number9)

  lazy val number9: Number1     = gen1(gen1(gen1(gen1(gen1(gen1(gen1(gen1(nextGen))))))))(number9Zero)
  lazy val number9Zero: Number1 = Number1S(() => number10)

  lazy val number10: Number1     = gen1(gen1(gen1(gen1(gen1(gen1(gen1(gen1(gen1(nextGen)))))))))(number10Zero)
  lazy val number10Zero: Number1 = Number1S(() => number11)

  lazy val number11: Number1     = gen1(gen1(gen1(gen1(gen1(gen1(gen1(gen1(gen1(gen1(nextGen))))))))))(number11Zero)
  lazy val number11Zero: Number1 = Number1S(() => null)

  def pow(i: Int, n: Int): Int = if (n > 0) pow(i, n - 1) * i else 1

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def exec: Unit = for (i2 <- 0 to 6) yield {
    val number2 = number2Gen(i2)
    val number3 = number2.method2(number1)

    val count1  = count(number3)
    val result1 = pow(num, i2)

    println(i2, num, count1, result1)
    assert(count1 + 1 == result1)
  }

}

object PowRunner {

  def main(arr: Array[String]): Unit = {
    val runner1 = new PowRunnerImplement {
      override val num: Int                                           = 2
      override val nextGen: Number1 => Number1                        = s => Number1T(() => s)
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(s))
    }
    val runner2 = new PowRunnerImplement {
      override val num: Int                                           = 3
      override val nextGen: Number1 => Number1                        = s => Number1T(() => Number1T(() => s))
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(next(s)))
    }
    val runner3 = new PowRunnerImplement {
      override val num: Int                                           = 4
      override val nextGen: Number1 => Number1                        = s => Number1T(() => Number1T(() => Number1T(() => s)))
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(next(next(s))))
    }
    val runner4 = new PowRunnerImplement {
      override val num: Int                    = 5
      override val nextGen: Number1 => Number1 = s => Number1T(() => Number1T(() => Number1T(() => Number1T(() => s))))
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(next(next(next(s)))))
    }

    runner1.exec
    runner2.exec
    runner3.exec
    runner4.exec
  }

}
