package b01

case class Item(name: String)

trait Number1

trait Number2 extends Number1 {
  def method1(Number4: Number4, item3: Item): Number1
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  def method1(Number4: Number4, item3: Item): Number1 = Number4.method5(tail)
}
case object Number2T extends Number2 {
  def method1(Number4: Number4, item3: Item): Number1 = Number4S(Number4, item3)
}

trait Number3 {
  def method2(Number2: Number2, Number4: Number4): Number1
  def method3(Number2: Number2): Number2
}
case class Number3S(tail: Number3, head: Item) extends Number3 {
  def method2(Number2: Number2, Number4: Number4): Number1 = Number4.method4(Number2, tail, head)
  def method3(Number2: Number2): Number2                   = Number2S(tail.method3(Number2), head)
}
case object Number3T extends Number3 {
  def method2(Number2: Number2, Number4: Number4): Number1 = Number4.method5(Number2)
  def method3(Number2: Number2): Number2                   = Number2
}

trait Number4 extends Number1 {
  def method4(Number2: Number2, Number3: Number3, item2: Item): Number1
  def method5(Number2: Number2): Number1
}
case class Number4S(tail: Number4, head: Item) extends Number4 {
  def method4(Number2: Number2, Number3: Number3, item2: Item): Number1 = Number3.method2(Number2, tail)
  def method5(Number2: Number2): Number1                                = Number2.method1(tail, head)
}
case object Number4T extends Number4 {
  def method4(Number2: Number2, Number3: Number3, item2: Item): Number1 = Number2S(Number3.method3(Number2), item2)
  def method5(Number2: Number2): Number1                                = Number2
}

object Counter {
  def count(Number2: Number2, Number3: Number3, Number4: Number4): Number1 = Number3.method2(Number2, Number4)
}
