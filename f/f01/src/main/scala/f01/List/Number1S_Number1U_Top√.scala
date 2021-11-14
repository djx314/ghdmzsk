package f01.List
import f01._
object Number1S_Number1U_Top {
  object Number1SExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result3(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1TExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result4(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1UExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }

    /* 一个小朋友在自娱自乐，忽而叫来了另外一个小朋友，两个人玩得很开心。 */
    /* 叫来的小朋友一般专注度不如原来的小朋友，不久后就走了，原来的小朋友也停止了玩耍。 */

    /* 一个人在自娱自乐，打单机游戏，喝啤酒吃花生看足球，忽而叫来了另外一个朋友，两人一起联机，啤酒吃花生讨论比赛。 */
    /* 请过来的人一般都会先提出自己要走了，原来把别人请来的人一般不会先把别人赶走。 */
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result5(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1VExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result3(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1Gen(i1).method1(number2Gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result2(i1, i2)
        assert(result1 == result2)
      }
    }
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  def exe = {
    Number1SExe.exe
    Number1TExe.exe
    Number1UExe.exe
    Number1VExe.exe
  }
}
