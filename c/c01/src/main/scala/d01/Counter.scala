package d01

case class Item(name: String)

trait Number1
case class Number1S(tail: Number1, head: Item) extends Number1
case object Number1T                           extends Number1

trait Number2 {
  def method1(number3: Number3): Number1
}
case class Number2S(tail: () => Number2, head: Item) extends Number2 {
  def method1(number3: Number3): Number1 = number3.receive1(tail(), head)
}
case class Number2T(tail: () => Number2) extends Number2 {
  def method1(number3: Number3): Number1 = number3.receive0(tail())
}

trait Number3 {
  def receive0(number2: Number2): Number1
  def receive1(number2: Number2, item: Item): Number1
}

case class Number4(tail: () => Number3) extends Number3 {
  override def receive0(number2: Number2): Number1             = Number1T // 结束
  override def receive1(number2: Number2, item: Item): Number1 = Number1S(number2.method1(tail()), item) // 上一个，后继
}

case class Number5(tail: () => Number3, head: Item) extends Number3 {
  override def receive0(number2: Number2): Number1             = Number1T // 结束
  override def receive1(number2: Number2, item: Item): Number1 = Number1S(tail().receive1(number2, item), head) // 自己，后继
}

case class Number6(tail: () => Number3) extends Number3 {
  override def receive0(number2: Number2): Number1             = Number1T // 结束
  override def receive1(number2: Number2, item: Item): Number1 = number2.method1(tail()) // 上一个
}

case class Number7(tail: () => Number3, head: Item) extends Number3 {
  override def receive0(number2: Number2): Number1             = Number1S(tail().receive0(number2), head) // 自己，后继
  override def receive1(number2: Number2, item: Item): Number1 = Number1S(tail().receive1(number2, item), head) // 自己，后继
}
