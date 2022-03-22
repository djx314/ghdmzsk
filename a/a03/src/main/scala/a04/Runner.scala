package a04

object Runner {

  lazy val number1: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number1Zero, () => number_2_1), () => number_2_2), () => number_2_3)
  lazy val number1Zero: Number1 = Number1T(() => number1)
  lazy val number1Each: Number1 = Number1T(() => number1)

  lazy val number_2_1: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number_2_1_Zero, () => number_2_1_Each), () => number_2_1_Each), () => number_2_1_Each)
  lazy val number_2_1_Zero: Number1 = Number1T(() => number_2_1)
  lazy val number_2_1_Each: Number1 = Number1T(() => number_2_1)

  lazy val number_2_2: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number_2_2_Zero, () => number_2_2_Each), () => number_2_2_Each), () => number_2_2_Each)
  lazy val number_2_2_Zero: Number1 = Number1T(() => number_2_1)
  lazy val number_2_2_Each: Number1 = Number1T(() => number_2_1)

  lazy val number_2_3: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number_2_3_Zero, () => number_2_3_Each), () => number_2_3_Each), () => number_2_3_Each)
  lazy val number_2_3_Zero: Number1 = Number1T(() => number_2_3)
  lazy val number_2_3_Each: Number1 = Number1T(() => number_2_3)

}
