package f08.test

import f07._
object Test {
  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1F(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1fGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1fGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1fGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1fGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1fGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
  }
}
