package a06

trait RunnerImplement {
  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  val num: Int
  val nextGen: Number1 => Number1
  def gen1(next: Number1 => Number1): Number1 => Number1
  def gen2(next: Number1 => Number1): Number1 => Number1  = gen1(gen1(next))
  def gen3(next: Number1 => Number1): Number1 => Number1  = gen2(gen1(next))
  def gen4(next: Number1 => Number1): Number1 => Number1  = gen3(gen1(next))
  def gen5(next: Number1 => Number1): Number1 => Number1  = gen4(gen1(next))
  def gen6(next: Number1 => Number1): Number1 => Number1  = gen5(gen1(next))
  def gen7(next: Number1 => Number1): Number1 => Number1  = gen6(gen1(next))
  def gen8(next: Number1 => Number1): Number1 => Number1  = gen7(gen1(next))
  def gen9(next: Number1 => Number1): Number1 => Number1  = gen8(gen1(next))
  def gen10(next: Number1 => Number1): Number1 => Number1 = gen9(gen1(next))

  lazy val number1: Number1     = nextGen(number1Zero)
  lazy val number1Zero: Number1 = Number1T(() => number2)

  lazy val number2: Number1     = gen1(nextGen)(number2Zero)
  lazy val number2Zero: Number1 = Number1T(() => number3)

  lazy val number3: Number1     = gen2(nextGen)(number3Zero)
  lazy val number3Zero: Number1 = Number1T(() => number4)

  lazy val number4: Number1     = gen3(nextGen)(number4Zero)
  lazy val number4Zero: Number1 = Number1T(() => number5)

  lazy val number5: Number1     = gen4(nextGen)(number5Zero)
  lazy val number5Zero: Number1 = Number1T(() => number6)

  lazy val number6: Number1     = gen5(nextGen)(number6Zero)
  lazy val number6Zero: Number1 = Number1T(() => number7)

  lazy val number7: Number1     = gen6(nextGen)(number7Zero)
  lazy val number7Zero: Number1 = Number1T(() => number8)

  lazy val number8: Number1     = gen7(nextGen)(number8Zero)
  lazy val number8Zero: Number1 = Number1T(() => number9)

  lazy val number9: Number1     = gen8(nextGen)(number9Zero)
  lazy val number9Zero: Number1 = Number1T(() => number10)

  lazy val number10: Number1     = gen9(nextGen)(number10Zero)
  lazy val number10Zero: Number1 = Number1T(() => number11)

  lazy val number11: Number1     = gen10(nextGen)(number11Zero)
  lazy val number11Zero: Number1 = Number1T(() => null)

  def log(i: Int, n: Int): Int = if (n >= i) log(i, n / i) + 1 else 0

  def count(num: Number3): Int = num match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def exec: Unit = {
    for {
      i2 <- 0 to 2000
    } yield {
      val number2 = number2Gen(i2)
      val number3 = number2.method2(number1)

      val count1  = count(number3)
      val result1 = log(num, i2)

      println(i2, count1, result1)
      assert(count1 == result1)
    }
  }

}

object Runner {

  def main(arr: Array[String]): Unit = {
    val runner1 = new RunnerImplement {
      override val num: Int                                           = 2
      override val nextGen: Number1 => Number1                        = s => Number1S(() => s)
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(s))
    }
    val runner2 = new RunnerImplement {
      override val num: Int                                           = 3
      override val nextGen: Number1 => Number1                        = s => Number1S(() => Number1S(() => s))
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(next(s)))
    }
    val runner3 = new RunnerImplement {
      override val num: Int                                           = 4
      override val nextGen: Number1 => Number1                        = s => Number1S(() => Number1S(() => Number1S(() => s)))
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(next(next(s))))
    }
    val runner4 = new RunnerImplement {
      override val num: Int                    = 5
      override val nextGen: Number1 => Number1 = s => Number1S(() => Number1S(() => Number1S(() => Number1S(() => s))))
      override def gen1(next: Number1 => Number1): Number1 => Number1 = s => next(next(next(next(next(s)))))
    }

    runner1.exec
    runner2.exec
    runner3.exec
    runner4.exec
  }

}
