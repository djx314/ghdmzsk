package h01

trait Number2 extends ((() => Number2) => Number2)

object Number2 {

  var 当前状态: Int = null.asInstanceOf[Int]
  def 更新状态: Unit = this.synchronized {
    当前状态 = 当前状态 + 1
  }
  def 重置状态: Unit = this.synchronized {
    当前状态 = 0
  }

  val S: Number2 = (tail: () => Number2) =>
    (number: () => Number2) => {
      def tail1   = tail()
      def number1 = number()
      Append(() => tail1(() => number1))
    }

  val T: Number2 = (tail: () => Number2) =>
    (number: () => Number2) => {
      def tail1   = tail()
      def number1 = number()
      tail1(() => number1)
    }

  val U: Number2 = (tail: () => Number2) =>
    (number: () => Number2) => {
      def tail1   = tail()
      def number1 = number()
      Append(() => number1(() => tail1))
    }

  val V: Number2 = (tail: () => Number2) =>
    (number: () => Number2) => {
      def tail1   = tail()
      def number1 = number()
      number1(() => tail1)
    }

  val Append: Number2 = (v: () => Number2) => {
    更新状态
    Append(() => v())
  }

}
