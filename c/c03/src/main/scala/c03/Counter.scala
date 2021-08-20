package c03

case class Item(name: String)

trait Number1
case class Number1S(tail: Number1, head: Item) extends Number1
case object Number1T                           extends Number1

trait Number2 {
  def receive0(number3: Number3, number4: Number4, number5: Number5): Number1
}
case class Number2S(tail: () => Number2, head: Item) extends Number2 {
  override def receive0(number3: Number3, number4: Number4, number5: Number5): Number1 = number3.receive1(tail(), number4, number5, head)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def receive0(number3: Number3, number4: Number4, number5: Number5): Number1 = number3.receive0(tail(), number4, number5)
}

trait Number3 {
  def receive0(number2: Number2, number4: Number4, number5: Number5): Number1
  def receive1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1
}
case class Number3S(tail: () => Number3, head: Item) extends Number3 {
  override def receive0(number2: Number2, number4: Number4, number5: Number5): Number1 = number4.receive1(number2, tail(), number5, head)
  override def receive1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1 =
    number4.receive2(number2, tail(), number5, item1, head)
}
case class Number3T(tail: () => Number3) extends Number3 {
  override def receive0(number2: Number2, number4: Number4, number5: Number5): Number1 = number4.receive0(number2, tail(), number5)
  override def receive1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1 =
    Number1S(number2.receive0(tail(), number4, number5), item1)
}

trait Number4 {
  def receive0(number2: Number2, number3: Number3, number5: Number5): Number1
  def receive1(number2: Number2, number3: Number3, number5: Number5, item2: Item): Number1
  def receive2(number2: Number2, number3: Number3, number5: Number5, item1: Item, item2: Item): Number1
}
case class Number4S(tail: () => Number4, head: Item) extends Number4 {
  override def receive0(number2: Number2, number3: Number3, number5: Number5): Number1 =
    number5.receive1(number2, number3, tail(), head)
  override def receive1(number2: Number2, number3: Number3, number5: Number5, item1: Item): Number1 =
    number5.receive2(number2, number3, tail(), item1, head)
  override def receive2(number2: Number2, number3: Number3, number5: Number5, item1: Item, item2: Item): Number1 =
    number5.receive3(number2, number3, tail(), item1, item2, head)
}
case class Number4T(tail: () => Number4) extends Number4 {
  override def receive0(number2: Number2, number3: Number3, number5: Number5): Number1 = number5.receive0(number2, number3, tail())
  override def receive1(number2: Number2, number3: Number3, number5: Number5, item1: Item): Number1 =
    Number1S(number3.receive0(number2, tail(), number5), item1)
  override def receive2(number2: Number2, number3: Number3, number5: Number5, item1: Item, item2: Item): Number1 =
    Number1S(number3.receive1(number2, tail(), number5, item1), item2)
}

trait Number5 {
  def receive0(number2: Number2, number3: Number3, number4: Number4): Number1
  def receive1(number2: Number2, number3: Number3, number4: Number4, item2: Item): Number1
  def receive2(number2: Number2, number3: Number3, number4: Number4, item1: Item, item2: Item): Number1
  def receive3(number2: Number2, number3: Number3, number4: Number4, item1: Item, item2: Item, item3: Item): Number1
}
case class Number5S(tail: () => Number5, head: Item) extends Number5 {
  override def receive0(number2: Number2, number3: Number3, number4: Number4): Number1 =
    Number1S(tail().receive0(number2, number3, number4), head)
  override def receive1(number2: Number2, number3: Number3, number4: Number4, item1: Item): Number1 =
    Number1S(tail().receive1(number2, number3, number4, item1), head)
  override def receive2(number2: Number2, number3: Number3, number4: Number4, item1: Item, item2: Item): Number1 =
    Number1S(tail().receive2(number2, number3, number4, item1, item2), head)
  override def receive3(number2: Number2, number3: Number3, number4: Number4, item1: Item, item2: Item, item3: Item): Number1 =
    Number1S(tail().receive3(number2, number3, number4, item1, item2, item3), head)
}
case class Number5T(tail: () => Number5) extends Number5 {
  override def receive0(number2: Number2, number3: Number3, number4: Number4): Number1 = Number1T
  override def receive1(number2: Number2, number3: Number3, number4: Number4, item1: Item): Number1 =
    Number1S(number4.receive0(number2, number3, tail()), item1)
  override def receive2(number2: Number2, number3: Number3, number4: Number4, item1: Item, item2: Item): Number1 =
    Number1S(number4.receive1(number2, number3, tail(), item1), item2)
  override def receive3(number2: Number2, number3: Number3, number4: Number4, item1: Item, item2: Item, item3: Item): Number1 =
    Number1S(number4.receive2(number2, number3, tail(), item1, item2), item3)
}
