package d01

case class Item(name: String)

trait Number1 {
  def length: Int
}
case class Number1S(tail: Number1, head: Item) extends Number1 {
  override def length: Int = tail.length + 1
}
case object Number1T extends Number1 {
  override def length: Int = 0
}

trait Number2 {
  def send0(number3: Number3): Number1
}
case class Number2S(tail: () => Number2, head: Item) extends Number2 {
  def send0(number3: Number3): Number1 = number3.send1(tail(), head)
}
case class Number2T(tail: () => Number2) extends Number2 {
  def send0(number3: Number3): Number1 = number3.send0(tail())
}

trait Number3 {
  def send0(number2: Number2): Number1
  def send1(number2: Number2, item: Item): Number1
}

case class Number4(tail: () => Number3) extends Number3 {
  override def send0(number2: Number2): Number1             = number2.send0(tail())
  override def send1(number2: Number2, item: Item): Number1 = number2.send0(tail())
}

case class Number5(tail: () => Number3) extends Number3 {
  override def send0(number2: Number2): Number1             = Number1T
  override def send1(number2: Number2, item: Item): Number1 = Number1S(number2.send0(tail()), item)
}
