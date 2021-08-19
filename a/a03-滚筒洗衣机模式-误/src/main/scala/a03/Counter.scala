package a03

case class Item(name: String)

trait Number1
case class Number1S(tail: Number1, head: Item) extends Number1
case object Number1T                           extends Number1

trait Number2 {
  def method1(number3: Number3, number4: Number4): Number1
}
case class Number2S(tail: () => Number2, head: Item) extends Number2 {
  override def method1(number3: Number3, number4: Number4): Number1 = number3.receive1(tail(), number4, head)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method1(number3: Number3, number4: Number4): Number1 = number3.receive0(tail(), number4)
}
object Number2 {
  lazy val zero: Number2 = Number2T(() => zero)
}

trait Number3 {
  def receive0(number2: Number2, number4: Number4): Number1
  def receive1(number2: Number2, number4: Number4, item1: Item): Number1
}
case class Number3S(tail: () => Number3, head: Item) extends Number3 {
  override def receive0(number2: Number2, number4: Number4): Number1              = number4.receive1(number2, tail(), head)
  override def receive1(number2: Number2, number4: Number4, item1: Item): Number1 = number4.receive2(number2, tail(), item1, head)
}
case class Number3T(tail: () => Number3) extends Number3 {
  override def receive0(number2: Number2, number4: Number4): Number1              = Number1T
  override def receive1(number2: Number2, number4: Number4, item1: Item): Number1 = number4.receive1(number2, tail(), item1)
}
object Number3T {
  lazy val zero: Number3 = Number3T(() => zero)
}

trait Number4 {
  def receive0(number2: Number2, number3: Number3): Number1
  def receive1(number2: Number2, number3: Number3, item2: Item): Number1
  def receive2(number2: Number2, number3: Number3, item1: Item, item2: Item): Number1
}
case class Number4S(tail: () => Number4, head: Item) extends Number4 {
  override def receive0(number2: Number2, number3: Number3): Number1                           = Number1T
  override def receive1(number2: Number2, number3: Number3, item1: Item): Number1              = number2.method1(number3, tail())
  override def receive2(number2: Number2, number3: Number3, item1: Item, item2: Item): Number1 = number3.receive1(number2, tail(), item1)
}
case class Number4T(tail: () => Number4) extends Number4 {
  override def receive0(number2: Number2, number3: Number3): Number1              = Number1T
  override def receive1(number2: Number2, number3: Number3, item2: Item): Number1 = Number1S(number2.method1(number3, tail()), item2)
  override def receive2(number2: Number2, number3: Number3, item1: Item, item2: Item): Number1 =
    Number1S(number3.receive1(number2, tail(), item1), item2)
}
object Number4T {
  lazy val zero: Number4 = Number4T(() => zero)
}

object Counter {
  def count(number2: Number2, number3: Number3, number4: Number4): Number1 = number2.method1(number3, number4)
}
