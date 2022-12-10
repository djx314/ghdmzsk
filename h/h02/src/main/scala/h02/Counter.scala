package h01

trait Number extends ((() => Number) => Number)

object Number {

  var 当前状态: Int = null.asInstanceOf[Int]
  def 更新状态: Unit = this.synchronized {
    当前状态 = 当前状态 + 1
  }
  def 重置状态: Unit = this.synchronized {
    当前状态 = 0
  }

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

  val Append: Number = (v: () => Number) => {
    更新状态
    v()
  }

}
