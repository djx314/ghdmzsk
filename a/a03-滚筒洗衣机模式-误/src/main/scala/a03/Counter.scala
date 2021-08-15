package a03

case class Item(name: String)

trait Number1
case class Number1S(tail: Number1, head: Item) extends Number1
case object Number1T                           extends Number1

trait Number2 {
  def method1(number3: Number3, number4: Number4): Number1
}
case class Number2S(tail: Number2, head: Item) extends Number2 {
  override def method1(number3: Number3, number4: Number4): Number1 = number3.method2(tail, number4, head)
}
case object Number2T extends Number2 {
  override def method1(number3: Number3, number4: Number4): Number1 = Number1T
}

trait Number3 {
  def method2(number2: Number2, number4: Number4, item2: Item): Number1
}
case class Number3S(tail: Number3, head: Item) extends Number3 {
  override def method2(number2: Number2, number4: Number4, item2: Item): Number1 = number4.method4(number2, tail, item2, head)
}
case class Number3T(tail: () => Number3) extends Number3 {
  override def method2(number2: Number2, number4: Number4, item2: Item): Number1 = number4.method5(number2, tail(), item2)
}
object Number3T {
  lazy val zero: Number3 = Number3T(() => zero)
}

trait Number4 {
  def method4(number2: Number2, number3: Number3, item2: Item, item3: Item): Number1
  def method5(number2: Number2, number3: Number3, item2: Item): Number1
}
case class Number4S(tail: Number4, head: Item) extends Number4 {
  override def method4(number2: Number2, number3: Number3, item2: Item, item3: Item): Number1 =
    Number1S(number2.method1(number3, tail), item2)
  override def method5(number2: Number2, number3: Number3, item2: Item): Number1 = number2.method1(number3, tail)
}
case class Number4T(tail: () => Number4) extends Number4 {
  override def method4(number2: Number2, number3: Number3, item2: Item, item3: Item): Number1 =
    Number1S(Number1S(number2.method1(number3, tail()), item2), item3)
  override def method5(number2: Number2, number3: Number3, item2: Item): Number1 = Number1S(number2.method1(number3, tail()), item2)
}
object Number4T {
  lazy val zero: Number4 = Number4T(() => zero)
}

object Counter {
  def count(number2: Number2, number3: Number3, number4: Number4): Number1 = number2.method1(number3, number4)
}
