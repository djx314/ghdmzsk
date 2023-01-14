package h03.test01

trait Number extends ((() => Number) => Number)

object Number {

  def apply(num: (() => Number) => Number): Number = new Number {
    override def apply(n: () => Number): Number = num(n)
  }

  val Number1Positive: Number = Number(tail => Number(number2 => Number(number3 => number2.apply()(tail)(number3))))
  val Number1Zero: Number     = Number(tail => tail.apply())

  val Number2Positive: Number = Number(tail => Number(number1 => Number(number3 => number3.apply()(number1)(tail))))
  val Number2Zero: Number     = Number(tail => Number(number1 => Number(number3 => number1.apply()(tail)(number3))))

  val Number3Positive: Number = Number(tail => Number(number1 => Number(number2 => NumberPositive(() => tail.apply()(number1)(number2)))))
  val Number3Zero: Number     = Number(tail => Number(number1 => Number(number2 => number2.apply()(number1)(tail))))

  // val Append: Number = Number(tail => NumberPositive(tail))

  case class NumberPositive(tail: () => Number) extends Number {
    override def apply(f: () => Number): Number = Number1Zero(f)
  }

}
