package d02

object Runner {
  lazy val number2Zero: Number2 = Number2T(() => number2Zero)

  def main(args: Array[String]): Unit = {
    {
      // 12 + 12
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context4(() => context1)
      lazy val context3: Context2 = Context5(() => context4)
      lazy val context4: Context2 = Context2.merge(context2, context3)

      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))
        lazy val number2 = Number3S(() => Number3S(() => Number3S(() => number3)))
        lazy val number3: Number3 = Number3U(() => number3)
        assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))))
      }
      {
        lazy val number1 = Number3S(() => Number3S(() => Number3S(() => number2)))
        lazy val number2: Number3 = Number3U(() => number2)
        assert(context1.method1(number1)(number2Zero) == Number1S(Number1S(Number1S(Number1T))))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))
        lazy val number2: Number3 = Number3U(() => number2)
        assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1S(Number1S(Number1T)))))
      }
    }

    {
      // 12 - 12
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context4(() => context1)
      lazy val context3: Context2 = Context6(() => context1)
      lazy val context4: Context2 = Context2.merge(context2, context3)

      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero)))))
        lazy val number2 = Number3T(() => Number3T(() => Number3T(() => number3)))
        lazy val number3: Number3 = Number3U(() => number3)
        assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1T)))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))))
        lazy val number2 = Number3T(() => Number3T(() => Number3T(() => Number3T(() => Number3T(() => number3)))))
        lazy val number3: Number3 = Number3U(() => number3)
        assert(context1.method1(number2)(number1) == Number1S(Number1T))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))))
        lazy val number2 = Number3T(() => Number3T(() => Number3T(() => Number3T(() => Number3T(() => Number3T(() => number3))))))
        lazy val number3: Number3 = Number3U(() => number3)
        assert(context1.method1(number2)(number1) == Number1T)
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))))
        lazy val number2 =
          Number3T(() => Number3T(() => Number3T(() => Number3T(() => Number3T(() => Number3T(() => Number3T(() => number3)))))))
        lazy val number3: Number3 = Number3U(() => number3)
        assert(context1.method1(number2)(number1) == Number1T)
      }
    }

    {
      // 12 × 12
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context5(() => context4)
      lazy val context3: Context2 = Context6(() => context1)
      lazy val context4: Context2 = Context2.merge(context2, context3)

      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero)))))
        lazy val number2: Number3 = Number3S(() => Number3S(() => Number3S(() => number3)))
        lazy val number3: Number3 = Number3T(() => number2)
        assert(
          context1.method1(number3)(number1) == Number1S(
            Number1S(
              Number1S(
                Number1S(
                  Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T)))))))))))
                )
              )
            )
          )
        )
      }
      {
        val number1 = Number2S(() => Number2S(() => number2Zero))
        lazy val number2: Number3 = Number3S(() => Number3S(() => Number3S(() => Number3S(() => number3))))
        lazy val number3: Number3 = Number3T(() => number2)
        assert(
          context1.method1(number3)(number1) == Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))))
        )
      }
    }

    {
      // 12 ÷ 12（上舍法）
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context5(() => context4)
      lazy val context3: Context2 = Context6(() => context1)
      lazy val context4: Context2 = Context2.merge(context2, context3)

      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero)))))))))
        lazy val number2: Number3 = Number3T(() => Number3T(() => Number3T(() => Number3T(() => number3))))
        lazy val number3: Number3 = Number3S(() => number2)
        assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1T)))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero)))))))))
        lazy val number2: Number3 = Number3T(() => Number3T(() => Number3T(() => number3)))
        lazy val number3: Number3 = Number3S(() => number2)
        assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1S(Number1T))))
      }
      {
        val number1 = Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => Number2S(() => number2Zero))))))))))
        lazy val number2: Number3 = Number3T(() => Number3T(() => Number3T(() => number3)))
        lazy val number3: Number3 = Number3S(() => number2)
        assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1S(Number1T))))
      }
    }
  }
}
