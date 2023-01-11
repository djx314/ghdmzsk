package h02

trait Number extends ((() => Number) => Number)

object Number {

  val S: Number = (tail: () => Number) =>
    (number: () => Number) => {
      def tail1   = tail()
      def number1 = number()
      Append(() => tail1(() => number1))
    }

  val T: Number = (tail: () => Number) =>
    (number: () => Number) => {
      def tail1   = tail()
      def number1 = number()
      tail1(() => number1)
    }

  val U: Number = (tail: () => Number) =>
    (number: () => Number) => {
      def tail1   = tail()
      def number1 = number()
      Append(() => number1(() => tail1))
    }

  val V: Number = (tail: () => Number) =>
    (number: () => Number) => {
      def tail1   = tail()
      def number1 = number()
      number1(() => tail1)
    }

  val Identity: Number = (v: () => Number) => v()

  val Append: Number = (v: () => Number) => NumberPositive(v)

  case class NumberPositive(tail: () => Number) extends Number {
    override def apply(t: () => Number): Number = Identity(t)
  }

}
