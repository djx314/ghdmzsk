package f02.Number1V
import f02._
object Number1V_Number1U_Number1V_Top {
        object Number1SExe {
            def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
            def number2PositiveGen(n: Int, zero: => Number1): Number1 = Fusion.number1vGen(n, zero)
            def exe = {
                for {
                    i1 <- 0 to 20
                    i2 <- 0 to 20
                } {
                    val number1 = number1gen(i1)
                    lazy val number2Positive: Number1 = number2PositiveGen(i2, number2Zero)
                    lazy val number2Zero: Number1 = Number1S(() => number2Positive)
                    {
                        def counter1 = number1.method1(number2Positive)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1SExe.list_round(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number1.method1(number2Zero)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1SExe.list_round(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Positive.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1SExe.round_list(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Zero.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1SExe.round_list(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                }
            }
        }
        object Number1TExe {
            def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
            def number2PositiveGen(n: Int, zero: => Number1): Number1 = Fusion.number1vGen(n, zero)
            def exe = {
                for {
                    i1 <- 0 to 20
                    i2 <- 0 to 20
                } {
                    val number1 = number1gen(i1)
                    lazy val number2Positive: Number1 = number2PositiveGen(i2, number2Zero)
                    lazy val number2Zero: Number1 = Number1T(() => number2Positive)
                    {
                        def counter1 = number1.method1(number2Positive)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1TExe.list_round(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number1.method1(number2Zero)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1TExe.list_round(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Positive.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1TExe.round_list(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Zero.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1TExe.round_list(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                }
            }
        }
        object Number1UExe {
            def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
            def number2PositiveGen(n: Int, zero: => Number1): Number1 = Fusion.number1vGen(n, zero)
            def exe = {
                for {
                    i1 <- 0 to 20
                    i2 <- 0 to 20
                } {
                    val number1 = number1gen(i1)
                    lazy val number2Positive: Number1 = number2PositiveGen(i2, number2Zero)
                    lazy val number2Zero: Number1 = Number1U(() => number2Positive)
                    {
                        def counter1 = number1.method1(number2Positive)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1UExe.list_round(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number1.method1(number2Zero)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1UExe.list_round(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Positive.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1UExe.round_list(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Zero.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1UExe.round_list(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                }
            }
        }
        object Number1VExe {
            def number1gen(n: Int): Number1 = Fusion.number1vGen(n, Fusion.number1u)
            def number2PositiveGen(n: Int, zero: => Number1): Number1 = Fusion.number1vGen(n, zero)
            def exe = {
                for {
                    i1 <- 0 to 20
                    i2 <- 0 to 20
                } {
                    val number1 = number1gen(i1)
                    lazy val number2Positive: Number1 = number2PositiveGen(i2, number2Zero)
                    lazy val number2Zero: Number1 = Number1V(() => number2Positive)
                    {
                        def counter1 = number1.method1(number2Positive)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1VExe.list_round(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number1.method1(number2Zero)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1VExe.list_round(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Positive.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1VExe.round_list(i1 = ${i1}, i2Positive = ${$i2})")
                        }
                    }
                    {
                        def counter1 = number2Zero.method1(number1)
                        val result1 = true
                        val result2 = true
                        if (result1 ! = result2) {
                            throw new Exception("Number Count Error with Number1V_Number1U_Number1V_Top.Number1VExe.round_list(i1 = ${i1}, i2Zero = ${$i2})")
                        }
                    }
                }
            }
        }
    def exe = {
            Number1SExe.exe
            Number1TExe.exe
            Number1UExe.exe
            Number1VExe.exe
    }
}
