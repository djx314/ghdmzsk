package a04

object Runner {

  lazy val number1: Number1 = Number1S(
    () => Number1S(() => Number1S(() => Number1S(() => Number1U, () => number1Zero), () => number1Zero), () => number1Zero),
    () => number1Zero
  )
  lazy val number1Zero: Number1 = Number1T(() => number1)

}
