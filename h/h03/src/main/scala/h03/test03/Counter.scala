package h03.test03

trait Number extends ((() => Number) => Number)

object Number {

  def apply(num: (() => Number) => Number): Number = new Number {
    override def apply(n: () => Number): Number = num(n)
  }

  val Number1Positive: Number =
    Number(number1 => Number(number2 => Number(number3 => Number(number4 => number2()(number1)(number3)(number4)))))
  val `Number1Zero理论上`: Number =
    Number(number1 => Number(number2 => Number(number3 => Number(number4 => number1()(number2)(number3)(number4)))))
  val Number1Zero: Number = Number(tail => tail.apply())

  val Number2Positive1: Number =
    Number(number1 => Number(number2 => Number(number3 => Number(number4 => number3()(number2)(number1)(number4)))))
  val Number2Positive: Number =
    Number(number1 => Number(number2 => Number(number3 => Number(number4 => Number1Positive(number2)(number3)(number1)(number4)))))
  val Number2Zero: Number = Number1Positive

  val Number3Positive1: Number =
    Number(number1 => Number(number2 => Number(number3 => Number(number4 => number4()(number2)(number3)(number1)))))
  val Number3Positive: Number =
    Number(number1 => Number(number2 => Number(number3 => Number(number4 => Number2Positive(number3)(number2)(number4)(number1)))))
  val Number3Zero: Number = Number2Positive

  val Number4Positive: Number = Number(number1 =>
    Number(number2 => Number(number3 => Number(number4 => NumberPositive(() => `Number1Zero理论上`(number1)(number2)(number3)(number4)))))
  )
  val Number4Zero: Number = Number3Positive

  case class NumberPositive(tail: () => Number) extends Number {
    override def apply(f: () => Number): Number = throw new Exception
  }

}
