package d01

object Runner {
  val item01 = Item("01")
  val item02 = Item("02")
  val item03 = Item("03")
  val item04 = Item("04")
  val item05 = Item("05")
  val item06 = Item("06")
  val item07 = Item("07")
  val item08 = Item("08")
  val item09 = Item("09")
  val item10 = Item("10")
  val item11 = Item("11")

  lazy val number2Zero: Number2 = Number2T(() => number2Zero)

  def main(args: Array[String]): Unit = {
    /*{
      // 12 + 12
      {
        val number1               = Number2S(() => Number2S(() => number2Zero, item01), item02)
        lazy val number2: Number3 = Number5(() => Number5(() => Number5(() => number3, item03), item04), item05)
        lazy val number3: Number3 = Number4(() => number3)
        val number4               = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04), item05))
      }
      {
        lazy val number1: Number3 = Number5(() => Number5(() => Number5(() => number2, item01), item02), item03)
        lazy val number2: Number3 = Number4(() => number2)
        val number4               = number2Zero.method1(number1)
        assert(number4 == Number1S(Number1S(Number1S(Number1T, item01), item02), item03))
      }
      {
        val number1               = Number2S(() => Number2S(() => number2Zero, item01), item02)
        lazy val number3: Number3 = Number4(() => number3)
        val number4               = number1.method1(number3)
        assert(number4 == Number1S(Number1S(Number1T, item01), item02))
      }
    }

    {
      // 12 - 12
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04)
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number4(() => number3)
        val number4               = number1.method1(number2)
        assert(number4 == Number1S(Number1T, item01))
      }
      {
        val number1               = Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03)
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number4(() => number3)
        val number4               = number1.method1(number2)
        assert(number4 == Number1T)
      }
      {
        val number1               = Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03)
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => Number6(() => number3))))
        lazy val number3: Number3 = Number4(() => number3)
        val number4               = number1.method1(number2)
        assert(number4 == Number1T)
      }
    }*/

    {
      // 12 × 12
      val number1               = Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03)
      lazy val number2: Number3 = Number5(() => Number5(() => Number5(() => Number5(() => number3, item04), item05), item06), item07)
      lazy val number3: Number3 = Number6(() => number2)
      val number4               = number1.method1(number2)
      assert(
        number4 == Number1S(
          Number1S(
            Number1S(
              Number1S(
                Number1S(
                  Number1S(
                    Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T, item04), item05), item06), item07), item04), item05),
                    item06
                  ),
                  item07
                ),
                item04
              ),
              item05
            ),
            item06
          ),
          item07
        )
      )
    }

    /*{
      // 12 ÷ 12（下舍法）
      {
        val number1 = Number2S(
          () =>
            Number2S(
              () =>
                Number2S(
                  () =>
                    Number2S(
                      () =>
                        Number2S(
                          () => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04),
                          item05
                        ),
                      item06
                    ),
                  item07
                ),
              item08
            ),
          item09
        )
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => Number6(() => number3))))
        lazy val number3: Number3 = Number5(() => number2, item10)
        val number4               = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1T, item10), item10))
      }
      {
        val number1 = Number2S(
          () =>
            Number2S(
              () =>
                Number2S(
                  () =>
                    Number2S(
                      () =>
                        Number2S(
                          () => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04),
                          item05
                        ),
                      item06
                    ),
                  item07
                ),
              item08
            ),
          item09
        )
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number5(() => number2, item10)
        val number4               = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1S(Number1T, item10), item10), item10))
      }
      {
        val number1 = Number2S(
          () =>
            Number2S(
              () =>
                Number2S(
                  () =>
                    Number2S(
                      () =>
                        Number2S(
                          () =>
                            Number2S(
                              () =>
                                Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04),
                              item05
                            ),
                          item06
                        ),
                      item07
                    ),
                  item08
                ),
              item09
            ),
          item10
        )
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number5(() => number2, item11)
        val number4               = number1.method1(number2)
        assert(number4 == Number1S(Number1S(Number1S(Number1T, item11), item11), item11))
      }
    }*/

    {
      // 12 ÷ 12（下舍法）
      {
        val number1 = Number2S(
          () =>
            Number2S(
              () =>
                Number2S(
                  () =>
                    Number2S(
                      () =>
                        Number2S(
                          () => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04),
                          item05
                        ),
                      item06
                    ),
                  item07
                ),
              item08
            ),
          item09
        )
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => Number6(() => number3))))
        lazy val number3: Number3 = Number5(() => number2, item10)
        val number4               = number1.method1(number3)
        println(number4)
      }
      {
        val number1 = Number2S(
          () =>
            Number2S(
              () =>
                Number2S(
                  () =>
                    Number2S(
                      () =>
                        Number2S(
                          () => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04),
                          item05
                        ),
                      item06
                    ),
                  item07
                ),
              item08
            ),
          item09
        )
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number5(() => number2, item10)
        val number4               = number1.method1(number3)
        println(number4)
      }
      {
        val number1 = Number2S(
          () =>
            Number2S(
              () =>
                Number2S(
                  () =>
                    Number2S(
                      () =>
                        Number2S(
                          () =>
                            Number2S(
                              () =>
                                Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero, item01), item02), item03), item04),
                              item05
                            ),
                          item06
                        ),
                      item07
                    ),
                  item08
                ),
              item09
            ),
          item10
        )
        lazy val number2: Number3 = Number6(() => Number6(() => Number6(() => number3)))
        lazy val number3: Number3 = Number5(() => number2, item11)
        val number4               = number1.method1(number3)
        println(number4)
      }
    }
  }
}
