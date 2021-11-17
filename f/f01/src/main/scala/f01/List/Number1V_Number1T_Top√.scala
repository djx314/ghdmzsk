package f01.List
import f01._
object Number1V_Number1T_Top {
  object Number1SExe {
    def Number1S_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result17(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result37(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result20(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result37(i1, i2)
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
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result17(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result9(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result9(i1, i2)
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
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result26(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result38(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result1(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result38(i1, i2)
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
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.countOpt(() => counter1)
        val result2  = Result.result39(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result9(i1, i2)
        assert(result1 == result2)
      }
    }

    /* 减法 */
    /* 夹娃娃的时候，投一个硬币夹一次，等待娃娃回应你的期待，通常都是空欢喜。 */
    /* 如果一直达到你的耐心上限，都没法夹到娃娃，你就会感到失落，并且离开。 */
    /* 如果在耐心极限到达前，夹到娃娃，则会体会到你和娃娃共同带来的情绪波动，波动的量视乎剩余的耐心。 */
    /* 所以会发现，如果夹了很久很久才夹到一个，那就只是安慰奖，如果很快就夹到就是运气好，开心得要命。 */
    /* 另外决定你投了多少个硬币的，是你进行了多少次夹娃娃活动，也就是你的耐心极限， */
    /* 换句话说，并不是因为你没有硬币所以你不夹娃娃了，而是你没有耐心，所以不再去买硬币了。 */
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result7(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result9(i1, i2)
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
