package a03

case class Item(name: String)

trait Number1

trait Number2 extends Number1 {
  def method1(number4: Number4, item3: Item): Number1
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  override def method1(number4: Number4, item3: Item): Number1 = number4.method5(tail)
}
case object Number2T extends Number2 {
  override def method1(number4: Number4, item3: Item): Number1 = Number4S(number4, item3)
}

trait Number3 {
  def method2(number2: Number2, number4: Number4): Number1
  def method3(number2: Number2): Number2
}
case class Number3S(tail: Number3, head: Item) extends Number3 {
  override def method2(number2: Number2, number4: Number4): Number1 = number4.method4(number2, tail, head)
  override def method3(number2: Number2): Number2                   = Number2S(tail.method3(number2), head)
}
case object Number3T extends Number3 {
  override def method2(number2: Number2, number4: Number4): Number1 = number4.method5(number2)
  override def method3(number2: Number2): Number2                   = number2
}

trait Number4 extends Number1 {
  def method4(number2: Number2, number3: Number3, item2: Item): Number1
  def method5(number2: Number2): Number1
}
case class Number4S(tail: Number4, head: Item) extends Number4 {
  override def method4(number2: Number2, number3: Number3, item2: Item): Number1 = number3.method2(number2, tail)
  override def method5(number2: Number2): Number1                                = number2.method1(tail, head)
}
case object Number4T extends Number4 {
  override def method4(number2: Number2, number3: Number3, item2: Item): Number1 = Number2S(number3.method3(number2), item2)
  override def method5(number2: Number2): Number1                                = number2
}

object Counter {
  def count(number2: Number2, number3: Number3, number4: Number4): Number1 = number3.method2(number2, number4)
}
