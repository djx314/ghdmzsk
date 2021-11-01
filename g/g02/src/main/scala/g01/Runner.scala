package g01

object Runner {

  def count(number1: Number1): Int = number1 match {
    case Number1S(tail) => count(tail()) + 1
    case Number1U       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      val number1 =
        Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1U))))))) // 7
      val number2 = Number1T(() => Number1T(() => Number1T(() => Number1T(() => Number1T(() => Number1V))))) // 5
      val number3 = number2.method1(number1)
      assert(count(number3) == 7 + 5)
    }
    {
      val number1 =
        Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1U))))))) // 7
      val number2 = Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1V))))) // 5
      val number3 = number2.method1(number1)
      assert(count(number3) == 7 - 5)
    }
    {
      val number1 =
        Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1U))))))) // 7
      val number2 = Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1V)))))) // 6
      val number3 = number2.method1(number1)
      assert(count(number3) == 7 - 6)
    }
    {
      val number1 =
        Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1U))))))) // 7
      val number2 =
        Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1V))))))) // 7
      val number3 = number2.method1(number1)
      assert(count(number3) == 7 - 7)
    }
    {
      val number1 =
        Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1U))))))) // 7
      val number2 =
        Number1S(() =>
          Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1V)))))))
        ) // 8
      val number3 = number2.method1(number1)
      assert(count(number3) == 0)
    }
    {
      val number1 = Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1U)))))))
      val number2 = Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => number1)))))) // 13
      lazy val number3: Number1 =
        Number1T(() => Number1T(() => Number1T(() => Number1T(() => Number1T(() => Number1T(() => Number1T(() => number4))))))) // 7
      lazy val number4: Number1 = Number1S(() => number3)
      val number5               = number2.method1(number3)
      val number6               = number4.method1(number2)
      assert(count(number5) == 13 * 7)
      assert(count(number6) == 13 * 7)
    }
    {
      val number1 = Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1U)))))))
      val number2 = Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => number1)))))))
      val number3 =
        Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => Number1S(() => number2)))))))
      val number4               = Number1S(() => Number1S(() => number3))                 // 23
      val number5               = Number1S(() => number4)                                 // 24
      lazy val number6: Number1 = Number1S(() => Number1S(() => Number1S(() => number7))) // 4
      lazy val number7: Number1 = Number1T(() => number6)
      val number8               = number4.method1(number7)
      val number9               = number6.method1(number4)
      assert(count(number8) == 23 / 3 + 1)
      assert(count(number9) == 23 / 3)
      val number10 = number5.method1(number7)
      val number11 = number6.method1(number5)
      assert(count(number10) == 24 / 3)
      assert(count(number11) == 24 / 3)
    }
  }

}
