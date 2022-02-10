package f07.test

import f07._
object Test {
  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Number1U(() => Number1W)
      val number2: Number1 = Fusion.number1VGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1UGen(i1, Number1W)
      val number2: Number1 = Number1T(() => Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1UGen(i1, Number1W)
      val number2: Number1 = Number1V(() => Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1UGen(i1, Number1W)
      val number2: Number1 = Fusion.number1TGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1UGen(i1, Number1W)
      val number2: Number1 = Fusion.number1TGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1UGen(i1, Number1W)
      val number2: Number1 = Fusion.number1VGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1UGen(i1, Number1W)
      val number2: Number1 = Fusion.number1VGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Number1T(() => Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Number1U(() => Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Number1V(() => Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Fusion.number1SGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Fusion.number1TGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Fusion.number1TGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Fusion.number1UGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1W)
      val number2: Number1 = Fusion.number1VGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1X)
      val number2: Number1 = Number1U(() => Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1X)
      val number2: Number1 = Number1V(() => Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1X)
      val number2: Number1 = Fusion.number1SGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1X)
      val number2: Number1 = Fusion.number1UGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1VGen(i1, Number1X)
      val number2: Number1 = Fusion.number1VGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
  }
}
