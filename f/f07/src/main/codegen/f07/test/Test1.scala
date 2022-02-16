package f07.test

import f07._

import java.io.{File, PrintWriter}
import java.nio.file.Paths
object Test1 {
  def main(arr: Array[String]): Unit = {

    val printWriter = new PrintWriter(Paths.get(".","f","f07","src","main","codegen","f07","data.txt").toFile)

    var bb = 0
    def getBB:Int = {
      bb +=1
      bb
    }

    /*printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1v)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1V(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Number1V(() => number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Number1V(() => inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      val number2 = Fusion.number1dGen(i2, Fusion.number1v)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }*/
    /*printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1t)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1v)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
      printWriter.println(i1,i2,r1)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1v)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1v)
      lazy val number2: Number1 = Number1V(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1v)
      val number2 = Fusion.number1zGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1v)
      val number2 = Fusion.number1dGen(i2, Fusion.number1v)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Number1U(() => Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }*/
    /*printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Number1V(() => Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Fusion.number1sGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Fusion.number1tGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Fusion.number1uGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Fusion.number1vGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Fusion.number1yGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Fusion.number1zGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }*/
    /*printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1y)
      val number2: Number1 = Fusion.number1dGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1c)
      val number2: Number1 = Fusion.number1uGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1c)
      val number2: Number1 = Fusion.number1yGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }*/
    /*printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1c)
      val number2: Number1 = Fusion.number1zGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1c)
      val number2: Number1 = Fusion.number1zGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1c)
      val number2: Number1 = Fusion.number1dGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }*/
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1uGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1dGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      printWriter.println(i1,i2,r1)
      assert(true == r2)
    }
    printWriter.println(s"====$getBB====")
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      printWriter.println(i1,i2,r1)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
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
      val number1 = Fusion.number1uGen(i1, Fusion.number1d)
      val number2 = Fusion.number1zGen(i2, Fusion.number1d)
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
      val number1 = Fusion.number1vGen(i1, Fusion.number1u)
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      val number1 = Fusion.number1vGen(i1, Fusion.number1u)
      val number2 = Fusion.number1zGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1c)
      val number2: Number1 = Fusion.number1yGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1vGen(i1, Fusion.number1c)
      val number2: Number1 = Fusion.number1zGen(i2, Number1W)
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
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      val number1 = Fusion.number1vGen(i1, Fusion.number1d)
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
      val number1 = Fusion.number1yGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1sGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1yGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1tGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1yGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1uGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1yGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1yGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1yGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1zGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1yGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1dGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1yGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1dGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1zGen(i1, Fusion.number1u)
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      val number1 = Fusion.number1zGen(i1, Fusion.number1u)
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
      val number1 = Fusion.number1zGen(i1, Fusion.number1u)
      val number2 = Fusion.number1zGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1cGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1yGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1cGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1zGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1cGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1zGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1cGen(i1, Fusion.number1u)
      val number2: Number1 = Fusion.number1dGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1cGen(i1, Fusion.number1v)
      val number2: Number1 = Fusion.number1zGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1cGen(i1, Fusion.number1v)
      val number2: Number1 = Fusion.number1zGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1cGen(i1, Fusion.number1v)
      val number2: Number1 = Fusion.number1dGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1dGen(i1, Fusion.number1u)
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      val number1 = Fusion.number1dGen(i1, Fusion.number1u)
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
      val number1 = Fusion.number1dGen(i1, Fusion.number1u)
      val number2 = Fusion.number1zGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1 = Fusion.number1dGen(i1, Fusion.number1v)
      val number2 = Fusion.number1zGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1sGen(i1, inner_number1)
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
      lazy val inner_number1: Number1 = Fusion.number1vGen(i1, number1)
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
      lazy val inner_number1: Number1 = Fusion.number1yGen(i1, number1)
      val number2: Number1 = Fusion.number1uGen(i2, Number1B)
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
      lazy val inner_number1: Number1 = Fusion.number1yGen(i1, number1)
      val number2: Number1 = Fusion.number1vGen(i2, Number1B)
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
      lazy val number2: Number1 = Number1V(() => inner_number2)
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
      val number2 = Fusion.number1dGen(i2, Fusion.number1v)
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
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
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
      lazy val inner_number1: Number1 = Fusion.number1cGen(i1, number1)
      val number2: Number1 = Fusion.number1uGen(i2, Number1W)
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
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      lazy val number2: Number1 = Number1U(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1yGen(i2, number2)
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
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
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
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1yGen(i1, number1)
      val number2: Number1 = Fusion.number1vGen(i2, Number1B)
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
      lazy val number2: Number1 = Number1V(() => inner_number2)
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
      val number2 = Fusion.number1vGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1zGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1vGen(i2, inner_number2)
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
      lazy val inner_number1: Number1 = Fusion.number1cGen(i1, number1)
      val number2: Number1 = Fusion.number1uGen(i2, Number1X)
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
      lazy val number2: Number1 = Number1U(() => inner_number2)
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
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Number1V(() => inner_number2)
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
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      val number2 = Fusion.number1uGen(i2, Fusion.number1t)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1zGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 1 to 20
      i2 <- 1 to 20
    } yield {
      lazy val number1: Number1 = Fusion.number1vGen(i1, inner_number1)
      lazy val inner_number1: Number1 = Fusion.number1dGen(i1, number1)
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
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      lazy val number2: Number1 = Number1T(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2 = Fusion.number1uGen(i2, Fusion.number1c)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2 = Fusion.number1yGen(i2, Fusion.number1c)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2 = Fusion.number1yGen(i2, Fusion.number1d)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2 = Fusion.number1zGen(i2, Fusion.number1c)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2 = Fusion.number1cGen(i2, Fusion.number1y)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2 = Fusion.number1dGen(i2, Fusion.number1y)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 1 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      lazy val number2: Number1 = Fusion.number1uGen(i2, inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Number1U(() => Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Number1V(() => Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1sGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1tGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1uGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1vGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1yGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1yGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1zGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1cGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1cGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1cGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1dGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1W)
      val number2: Number1 = Fusion.number1dGen(i2, Number1A)
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
      lazy val number2: Number1 = Number1S(() => inner_number2)
      lazy val inner_number2: Number1 = Fusion.number1cGen(i2, number2)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1X)
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1c)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1d)
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
      val number2 = Fusion.number1zGen(i2, Fusion.number1c)
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
      val number2 = Fusion.number1cGen(i2, Fusion.number1v)
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
      val number2 = Fusion.number1cGen(i2, Fusion.number1d)
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
      val number2 = Fusion.number1dGen(i2, Fusion.number1c)
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
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1uGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1yGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1yGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1zGen(i2, Number1A)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1cGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1cGen(i2, Number1X)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1cGen(i2, Number1B)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1dGen(i2, Number1W)
      def count = number1.method1(number2)
      def r1 = Counter.count(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1A)
      val number2: Number1 = Fusion.number1dGen(i2, Number1A)
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
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      val number2 = Fusion.number1uGen(i2, Fusion.number1y)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1s)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1uGen(i1, Number1B)
      val number2 = Fusion.number1yGen(i2, Fusion.number1u)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1v)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1d)
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
      val number2 = Fusion.number1cGen(i2, Fusion.number1y)
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
      val number2 = Fusion.number1dGen(i2, Fusion.number1y)
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
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1W)
      val number2: Number1 = Fusion.number1vGen(i2, Number1A)
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
      val number1: Number1 = Fusion.number1vGen(i1, Number1A)
      val number2: Number1 = Fusion.number1sGen(i2, Number1W)
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
      val number2: Number1 = Fusion.number1uGen(i2, Number1W)
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
      val number2: Number1 = Fusion.number1vGen(i2, Number1W)
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
      val number2: Number1 = Fusion.number1yGen(i2, Number1X)
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
      val number2: Number1 = Fusion.number1yGen(i2, Number1A)
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
      val number2: Number1 = Fusion.number1yGen(i2, Number1B)
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
      val number2: Number1 = Fusion.number1zGen(i2, Number1W)
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
      val number2: Number1 = Fusion.number1zGen(i2, Number1A)
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
      val number2: Number1 = Fusion.number1cGen(i2, Number1W)
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
      val number2: Number1 = Fusion.number1cGen(i2, Number1X)
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
      val number2: Number1 = Fusion.number1dGen(i2, Number1W)
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
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      lazy val number2: Number1 = Number1T(() => inner_number2)
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
      val number2 = Fusion.number1sGen(i2, Fusion.number1y)
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
      val number2 = Fusion.number1uGen(i2, Fusion.number1y)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1s)
      def count = number1.method1(number2)
      def r1 = Counter.countOpt(() => count)
      val r2 = true
      assert(true == r2)
    }
    for {
      i1 <- 0 to 20
      i2 <- 0 to 20
    } yield {
      val number1: Number1 = Fusion.number1vGen(i1, Number1B)
      val number2 = Fusion.number1yGen(i2, Fusion.number1u)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1v)
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
      val number2 = Fusion.number1yGen(i2, Fusion.number1c)
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
      val number2 = Fusion.number1zGen(i2, Fusion.number1y)
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
      val number2 = Fusion.number1zGen(i2, Fusion.number1c)
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
      val number2 = Fusion.number1cGen(i2, Fusion.number1y)
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
      val number2 = Fusion.number1dGen(i2, Fusion.number1y)
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
      lazy val number2: Number1 = Fusion.number1sGen(i2, inner_number2)
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

    printWriter.close()
  }
}
