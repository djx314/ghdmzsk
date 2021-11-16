package f01.List
import f01._
object Number1U_Number1T_Top {
  object Number1SExe {
    def Number1S_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
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
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result18(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result19(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1v)
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
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1TExe {
    def Number1S_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
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
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result21(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result22(i1, i2)
        assert(result1 == result2)
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1v)
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
    def exe = {
      Number1S_exe
      Number1T_exe
      Number1U_exe
      Number1V_exe
    }
  }
  object Number1UExe {
    def Number1S_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
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
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
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
