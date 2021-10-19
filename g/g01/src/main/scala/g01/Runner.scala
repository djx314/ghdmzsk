package g01

object Runner {

  def count(number1: Number1): Int = number1 match {
    case Number1S(tail) => count(tail) + 1
    case Number1T       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T)))))))           // 7
      val number2 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2U))))) // 5
      val number3 = number2.method2(number1)
      assert(count(number3) == 7 + 5)
    }
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T)))))))           // 7
      val number2 = Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2U))))) // 5
      val number3 = number2.method2(number1)
      assert(count(number3) == 7 - 5)
    }
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T)))))))                           // 7
      val number2 = Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2U)))))) // 6
      val number3 = number2.method2(number1)
      assert(count(number3) == 7 - 6)
    }
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))) // 7
      val number2 =
        Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2U))))))) // 7
      val number3 = number2.method2(number1)
      assert(count(number3) == 7 - 7)
    }
    {
      val number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))) // 7
      val number2 =
        Number2T(() =>
          Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2T(() => Number2U)))))))
        ) // 8
      val number3 = number2.method2(number1)
      assert(count(number3) == 0)
    }
    {
      val number1 = Number1S(
        Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))))))))
      ) // 13
      lazy val number2: Number2 =
        Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number3))))))) // 7
      lazy val number3: Number2 = Number2T(() => number2)
      val number4               = number1.method1(number2)
      val number5               = number3.method2(number1)
      assert(count(number4) == 13 * 7)
      assert(count(number5) == 13 * 7)
    }
    {
      val number1 = Number1S(
        Number1S(
          Number1S(
            Number1S(
              Number1S(
                Number1S(
                  Number1S(
                    Number1S(
                      Number1S(
                        Number1S(
                          Number1S(
                            Number1S(
                              Number1S(
                                Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))))))
                              )
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
      ) // 23
      val number2               = Number1S(number1)                                       // 24
      lazy val number3: Number2 = Number2T(() => Number2T(() => Number2T(() => number4))) // 4
      lazy val number4: Number2 = Number2S(() => number3)
      val number5               = number1.method1(number4)
      val number6               = number3.method2(number1)
      assert(count(number5) == 23 / 3 + 1)
      assert(count(number6) == 23 / 3)
      val number7 = number2.method1(number4)
      val number8 = number3.method2(number2)
      assert(count(number7) == 24 / 3)
      assert(count(number8) == 24 / 3)
    }
  }

}
