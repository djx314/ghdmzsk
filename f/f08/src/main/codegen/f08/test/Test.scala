package f08.test

import f07._
object Test {
  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Number1D(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Number1Z(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Number1Z(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1fGen(i1, number1)
      val number2: Number1 = Fusion.number1fGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1fGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1F(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2: Number1 = Fusion.number1fGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1F(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Number1F(() => number1)
      val number2: Number1 = Fusion.number1fGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Number1F(() => number1)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1fGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1E(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1fGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1F(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1F(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      val number2 = Number1F(() => Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
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
      val number2 = Number1U(() => Fusion.number1f)
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
      val number2 = Number1F(() => Fusion.number1u)
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
      val number2 = Number1F(() => Fusion.number1v)
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
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Number1F(() => number2)
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
      lazy val inner_number2: Number1 = Number1U(() => number2)
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
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
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
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
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
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1F(() => number2)
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
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
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
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1S(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1U(() => number2)
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
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
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
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
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
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1f)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1u)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1vGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1vGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1vGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1fGen(i1, Fusion.number1u)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1fGen(i1, Fusion.number1u)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1fGen(i1, Fusion.number1d)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1fGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1fGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1fGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1 = Fusion.number1fGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1fGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1V(() => number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1V(() => number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1F(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1F(() => number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1E(() => number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1E(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1E(() => number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1E(() => number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1E(() => number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1fGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1fGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1fGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1E(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1fGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1E(() => number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      val number2 = Number1F(() => Fusion.number1d)
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
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Number1F(() => inner_number2)
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
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
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
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
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
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
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
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Number1F(() => number2)
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
      lazy val inner_number2: Number1 = Number1U(() => number2)
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
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1F(() => number2)
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
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1U(() => number2)
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
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1E(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1fGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1E(() => inner_number2)
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
      val number1: Number1 = Fusion.number1uGen(i1, Number1X)
      val number2 = Number1F(() => Fusion.number1c)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1X)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1X)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1X)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1X)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1X)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1B)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1B)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1B)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1B)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1B)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1X)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1X)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1A)
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
      val number1: Number1 = Fusion.number1vGen(i1, Number1A)
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
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      val number2 = Number1U(() => Fusion.number1y)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      lazy val number2: Number1 = Number1F(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      lazy val number2: Number1 = Fusion.number1fGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
  }
}
