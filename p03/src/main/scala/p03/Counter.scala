package p03

case class Item(name: String)

trait Number1 {
  def method1(number2: Number2, number3: Number3): Any
}
case class Number1S(tail: Number1, head: Item) extends Number1 {
  def method1(number2: Number2, number3: Number3): Any = number2.method2(this)
}
case object Number1T extends Number1 {
  def method1(number2: Number2, number3: Number3): Any = number2
}

trait Number2 {
  def method2(number1: Number1S, number3: Number3S): Any
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  def method2(number1: Number1S, number3: Number3S): Any = number1.tail.method1(tail)
}
case object Number2T extends Number2 {
  def method2(number1: Number1S, number3: Number3S): Any = number1
}

trait Number3 {
  def method3(number1: Number1S, number2: Number2S): Any
}
case class Number3S(tail: Number3, head: Item) extends Number3 {
  def method3(number1: Number1S, number2: Number2S): Any = number1.tail.method1(tail)
}
case object Number3T extends Number3 {
  def method3(number1: Number1S, number2: Number2S): Any = number1
}
