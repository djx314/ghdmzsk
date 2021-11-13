package f01.List
import f01._

/* ss1 作为左侧数的运算都有一个共同特征，单方面的慈爱、憎恨、付出，以为到了一段时间之后就会终止，*/
/* 但真正等到那个时间来临之后却发现，这些守护、怨念、付出，根本不会有停止的念头，               */
/* 只会单方面地，完全不顾及对方地，永无停止地产生这种念头                                   */

object Number1S_Number1S_Top {
  object Number1SExe {
    def Number1S_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      def number2Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
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
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      def number2Gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1t)
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
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
    def Number1T_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      def number2Gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
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
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      def number2Gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
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
    def Number1U_exe = {
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
      def number1Gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
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
