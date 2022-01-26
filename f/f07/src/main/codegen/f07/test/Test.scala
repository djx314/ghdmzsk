package f07.test

import f07._
object Test {
  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1V(() => number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1S(() => number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1S(() => number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1S(() => number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1T(() => number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Number1S(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1tGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1tGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1t
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Number1S(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1sGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1t
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1sGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1t
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Number1S(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1sGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1t
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1sGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1tGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1tGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1uGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1V(() => number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1T(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Number1S(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1tGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1V(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Number1U(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Number1S(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Number1V(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Number1S(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      val number2 = Number1V(() => Fusion.number1s)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      val number2 = Number1U(() => Fusion.number1s)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1t)
      val number2 = Number1S(() => Fusion.number1u)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1s)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1s)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1v)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1T(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1tGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1sGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1U(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Number1U(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Number1V(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Number1S(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1S(() => inner_number2)
      lazy val inner_number2: Number1 = Number1U(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      val number2 = Number1U(() => Fusion.number1s)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      val number2 = Number1S(() => Fusion.number1u)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1s)
      val number2 = Number1V(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1s)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1sGen(i1, Fusion.number1u)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1sGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1T(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1uGen(i1, number1)
      val number2 = Number1S(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Number1S(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Number1V(() => number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Number1V(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Number1S(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1U(() => inner_number1)
      lazy val inner_number1: Number1 = Number1S(() => number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1T(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1vGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Fusion.number1sGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1S(() => inner_number1)
      lazy val inner_number1: Number1 = Number1U(() => number1)
      val number2 = Number1U(() => Fusion.number1t)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Number1V(() => Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Number1V(() => Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Number1V(() => Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Number1U(() => Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1vGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Number1U(() => Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Number1U(() => Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1U(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Number1U(() => Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1V(() => number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Number1U(() => Fusion.number1t)
      lazy val number2: Number1 = Number1S(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      val r1 = true
      val r2 = true
      assert(r1 == r2)
    }
  }
}
