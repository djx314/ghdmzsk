package d02

object Runner {
  def main(args: Array[String]): Unit = {
    {
      // 12 + 12
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context5(() => context4)
      lazy val context3: Context2 = Context7(() => context1)
      lazy val context4: Context2 = new Context2 {
        override def method2(number2: Number2): PartialFunction[Number3, Number1] =
          context2.method2(number2).orElse(context3.method2(number2))
      }

      val number1               = Number2S(Number2S(Number2S(Number2S(Number2T))))
      lazy val number2          = Number3S(Number3S(Number3S(number3)))
      lazy val number3: Number3 = Number3T(() => number3)
      assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))))
    }

    {
      // 12 - 12
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context6(() => context1)
      lazy val context3: Context2 = Context7(() => context1)
      lazy val context4: Context2 = new Context2 {
        override def method2(number2: Number2): PartialFunction[Number3, Number1] =
          context2.method2(number2).orElse(context3.method2(number2))
      }

      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2T)))))
        lazy val number2          = Number3S(Number3S(Number3S(number3)))
        lazy val number3: Number3 = Number3T(() => number3)
        assert(context1.method1(number2)(number1) == Number1S(Number1S(Number1T)))
      }
      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))
        lazy val number2          = Number3S(Number3S(Number3S(Number3S(Number3S(number3)))))
        lazy val number3: Number3 = Number3T(() => number3)
        assert(context1.method1(number2)(number1) == Number1S(Number1T))
      }
      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))
        lazy val number2          = Number3S(Number3S(Number3S(Number3S(Number3S(Number3S(number3))))))
        lazy val number3: Number3 = Number3T(() => number3)
        assert(context1.method1(number2)(number1) == Number1T)
      }
      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))
        lazy val number2          = Number3S(Number3S(Number3S(Number3S(Number3S(Number3S(Number3S(number3)))))))
        lazy val number3: Number3 = Number3T(() => number3)
        assert(context1.method1(number2)(number1) == Number1T)
      }
    }

    {
      // 12 × 12
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context5(() => context4)
      lazy val context3: Context2 = Context9(() => context1)
      lazy val context4: Context2 = new Context2 {
        override def method2(number2: Number2): PartialFunction[Number3, Number1] =
          context2.method2(number2).orElse(context3.method2(number2))
      }

      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2T)))))
        lazy val number2: Number3 = Number3S(Number3S(Number3S(number3)))
        lazy val number3: Number3 = Number3T(() => number2)
        assert(
          context1.method1(number2)(number1) == Number1S(
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
        val number1               = Number2S(Number2S(Number2T))
        lazy val number2: Number3 = Number3S(Number3S(Number3S(Number3S(number3))))
        lazy val number3: Number3 = Number3T(() => number2)
        assert(
          context1.method1(number2)(number1) == Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1S(Number1T))))))))
        )
      }
    }

    {
      // 12 ÷ 12（上舍法）
      lazy val context1: Context1 = Context3(() => context4)
      lazy val context2: Context2 = Context6(() => context1)
      lazy val context3: Context2 = Context8(() => context4)
      lazy val context4: Context2 = new Context2 {
        override def method2(number2: Number2): PartialFunction[Number3, Number1] =
          context2.method2(number2).orElse(context3.method2(number2))
      }

      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T)))))))))
        lazy val number2: Number3 = Number3S(Number3S(Number3S(Number3S(number3))))
        lazy val number3: Number3 = Number3T(() => number2)
        assert(context1.method1(number3)(number1) == Number1S(Number1S(Number1S(Number1T))))
      }
      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T)))))))))
        lazy val number2: Number3 = Number3S(Number3S(Number3S(number3)))
        lazy val number3: Number3 = Number3T(() => number2)
        assert(context1.method1(number3)(number1) == Number1S(Number1S(Number1S(Number1T))))
      }
      {
        val number1               = Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2S(Number2T))))))))))
        lazy val number2: Number3 = Number3S(Number3S(Number3S(number3)))
        lazy val number3: Number3 = Number3T(() => number2)
        assert(context1.method1(number3)(number1) == Number1S(Number1S(Number1S(Number1S(Number1T)))))
      }
    }
  }
}
