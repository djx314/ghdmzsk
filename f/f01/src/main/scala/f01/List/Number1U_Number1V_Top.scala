package f01.List
import f01._
object Number1U_Number1V_Top {
  object Number1SExe {
    def Number1S_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1sGen(n, Fusion.number1v)
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
  object Number1TExe {
    def Number1S_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1tGen(n, Fusion.number1v)
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
  object Number1UExe {
    def Number1S_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }

    /* 加法                                                                                         */
    /* 两人产生互动，互动过程中双方都有情绪波动产生，不久后一方虽然继续与对方互动，但热情消失，另一方则仍有热情， */
    /* 不久后另一方热情也消失，但双方即使没有热情，依然保持互动，直至天荒地老。                              */
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
        val result1  = Counter.count(() => counter1)
        val result2  = Result.result6(i1, i2)
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
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1s)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1T_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1t)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1U_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
      def number2gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        def counter1 = number1gen(i1).method1(number2gen(i2))
      }
    }
    def Number1V_exe = {
      def number1gen(n: Int): Number1 = Fusion.number1uGen(n, Fusion.number1v)
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
