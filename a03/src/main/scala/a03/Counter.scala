package a03

case class Item(name: String)

trait Number1 {
  def method1(number3: Number3, item3: Item): Any
}
case class Number1S(tail: Number1, head: Item) extends Number1 {
  def method1(number3: Number3, item3: Item): Any = number3.method5(tail)
}
case object Number1T extends Number1 {
  def method1(number3: Number3, item3: Item): Any = Number3S(number3, item3)
}

trait Number2 {
  def method2(number1: Number1, number3: Number3): Any
  def method3(number1: Number1): Number1
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  def method2(number1: Number1, number3: Number3): Any = number3.method4(number1, tail, head)
  def method3(number1: Number1): Number1               = Number1S(tail.method3(number1), head)
}
case object Number2T extends Number2 {
  def method2(number1: Number1, number3: Number3): Any = number3.method5(number1)
  def method3(number1: Number1): Number1               = number1
}

trait Number3 {
  def method4(number1: Number1, number2: Number2, item2: Item): Any
  def method5(number1: Number1): Any
}
case class Number3S(tail: Number3, head: Item) extends Number3 {
  def method4(number1: Number1, number2: Number2, item2: Item): Any = number2.method2(number1, tail)
  def method5(number1: Number1): Any                                = number1.method1(tail, head)
}
case object Number3T extends Number3 {
  def method4(number1: Number1, number2: Number2, item2: Item): Any = Number1S(number2.method3(number1), item2)
  def method5(number1: Number1): Any                                = number1
}
