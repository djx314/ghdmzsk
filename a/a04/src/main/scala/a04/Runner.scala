package a04

trait Runner {

  lazy val number2: Number1 => Number2 = number1 =>
    Number2S(
      Number1S(Number2S(Number1S(Number2S(Number1S(Number2S(number1, number2, null)), number2, null)), number2, null)),
      number2,
      null
    )

}
