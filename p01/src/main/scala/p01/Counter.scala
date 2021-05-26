package p01

case class Item(name: String)

trait Number1
case class Number1S(tail: Number1, head: Item) extends Number1
case object Number1T                           extends Number1

trait Number2 {
  def method1(number1: Number1): Number1
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  def method1(number1: Number1): Number1 = Number1S(tail.method1(number1), head)
}
case object Number2T extends Number2 {
  def method1(number1: Number1): Number1 = number1
}
