package c03

case class Item(name: String)

trait Number1
case class Number1S(tail: Number1, head: Item) extends Number1
case object Number1T                           extends Number1

trait Number6
case class Number6S(var tail: Number6, head1: Item, head2: Item) extends Number6
case object Number6T                                             extends Number6

object MergeContext {
  var value: Number6    = Number6T
  var context2: Number6 = Number6T
  def append(head1: Item, head2: Item): Unit = {
    context2 match {
      case Number6T =>
        context2 = Number6S(Number6T, head1, head2)
        value = context2
      case c2 @ Number6S(_, _, _) =>
        c2.tail = Number6S(Number6T, head1, head2)
        context2 = c2.tail
    }
  }
  def reset: Unit = {
    value = Number6T
    context2 = Number6T
  }
}

trait Number2 {
  def send0(number3: Number3, number4: Number4, number5: Number5): Number1
  def reSend0(number3: Number3, number4: Number4, number5: Number5): Number1
  def reSend1(number3: Number3, number4: Number4, number5: Number5, item1: Item): Number1
  def reSend2(number3: Number3, number4: Number4, number5: Number5, item1: Item, item2: Item): Number1
  def reSend3(number3: Number3, number4: Number4, number5: Number5, item1: Item, item2: Item, item3: Item): Number1
}
case class Number2S(tail: () => Number2, head: Item) extends Number2 {
  override def send0(number3: Number3, number4: Number4, number5: Number5): Number1   = number3.send1(tail(), number4, number5, head)
  override def reSend0(number3: Number3, number4: Number4, number5: Number5): Number1 = number3.send1(tail(), number4, number5, head)
  override def reSend1(number3: Number3, number4: Number4, number5: Number5, item1: Item): Number1 = {
    MergeContext.append(item1, head)
    tail().reSend0(number3, number4, number5)
  }
  override def reSend2(number3: Number3, number4: Number4, number5: Number5, item1: Item, item2: Item): Number1 = {
    MergeContext.append(item2, head)
    tail().reSend1(number3, number4, number5, item1)
  }
  override def reSend3(number3: Number3, number4: Number4, number5: Number5, item1: Item, item2: Item, item3: Item): Number1 = {
    MergeContext.append(item3, head)
    tail().reSend2(number3, number4, number5, item1, item2)
  }
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def send0(number3: Number3, number4: Number4, number5: Number5): Number1   = number3.send0(tail(), number4, number5)
  override def reSend0(number3: Number3, number4: Number4, number5: Number5): Number1 = number3.send0(tail(), number4, number5)
  override def reSend1(number3: Number3, number4: Number4, number5: Number5, item1: Item): Number1 =
    tail().reSend0(number3, number4, number5)
  override def reSend2(number3: Number3, number4: Number4, number5: Number5, item1: Item, item2: Item): Number1 =
    tail().reSend1(number3, number4, number5, item1)
  override def reSend3(number3: Number3, number4: Number4, number5: Number5, item1: Item, item2: Item, item3: Item): Number1 =
    tail().reSend2(number3, number4, number5, item1, item2)
}

trait Number3 {
  def send0(number2: Number2, number4: Number4, number5: Number5): Number1
  def send1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1
  def reSend0(number2: Number2, number4: Number4, number5: Number5): Number1
  def reSend1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1
  def reSend2(number2: Number2, number4: Number4, number5: Number5, item1: Item, item2: Item): Number1
}
case class Number3S(tail: () => Number3, head: Item) extends Number3 {
  override def send0(number2: Number2, number4: Number4, number5: Number5): Number1 = number2.reSend1(tail(), number4, number5, head)
  override def send1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1 = {
    MergeContext.append(item1, head)
    number2.reSend0(tail(), number4, number5)
  }
  override def reSend0(number2: Number2, number4: Number4, number5: Number5): Number1 = number2.reSend1(tail(), number4, number5, head)
  override def reSend1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1 =
    number2.reSend2(tail(), number4, number5, item1, head)
  override def reSend2(number2: Number2, number4: Number4, number5: Number5, item1: Item, item2: Item): Number1 =
    number2.reSend3(tail(), number4, number5, item1, item2, head)
}
case class Number3T(tail: () => Number3) extends Number3 {
  override def send0(number2: Number2, number4: Number4, number5: Number5): Number1 = number4.send0(number2, tail(), number5)
  override def send1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1 =
    number4.send1(number2, tail(), number5, item1)
  override def reSend0(number2: Number2, number4: Number4, number5: Number5): Number1 = number2.reSend0(tail(), number4, number5)
  override def reSend1(number2: Number2, number4: Number4, number5: Number5, item1: Item): Number1 =
    number2.reSend1(tail(), number4, number5, item1)
  override def reSend2(number2: Number2, number4: Number4, number5: Number5, item1: Item, item2: Item): Number1 =
    number2.reSend2(tail(), number4, number5, item1, item2)
}

trait Number4 {
  def send0(number2: Number2, number3: Number3, number5: Number5): Number1
  def send1(number2: Number2, number3: Number3, number5: Number5, item2: Item): Number1
  def reSend0(number2: Number2, number3: Number3, number5: Number5): Number1
  def reSend1(number2: Number2, number3: Number3, number5: Number5, item1: Item): Number1
}
case class Number4S(tail: () => Number4, head: Item) extends Number4 {
  override def send0(number2: Number2, number3: Number3, number5: Number5): Number1 = number3.reSend1(number2, tail(), number5, head)
  override def send1(number2: Number2, number3: Number3, number5: Number5, item1: Item): Number1 = {
    MergeContext.append(item1, head)
    number3.reSend0(number2, tail(), number5)
  }
  override def reSend0(number2: Number2, number3: Number3, number5: Number5): Number1 =
    number3.reSend1(number2, tail(), number5, head)
  override def reSend1(number2: Number2, number3: Number3, number5: Number5, item1: Item): Number1 =
    number3.reSend2(number2, tail(), number5, item1, head)
}
case class Number4T(tail: () => Number4) extends Number4 {
  override def send0(number2: Number2, number3: Number3, number5: Number5): Number1 = number5.send0(number2, number3, tail())
  override def send1(number2: Number2, number3: Number3, number5: Number5, item1: Item): Number1 =
    number5.send1(number2, number3, tail(), item1)
  override def reSend0(number2: Number2, number3: Number3, number5: Number5): Number1 =
    number3.reSend0(number2, tail(), number5)
  override def reSend1(number2: Number2, number3: Number3, number5: Number5, item1: Item): Number1 =
    number3.reSend1(number2, tail(), number5, item1)
}

trait Number5 {
  def send0(number2: Number2, number3: Number3, number4: Number4): Number1
  def send1(number2: Number2, number3: Number3, number4: Number4, item2: Item): Number1
}
case class Number5S(tail: () => Number5, head: Item) extends Number5 {
  override def send0(number2: Number2, number3: Number3, number4: Number4): Number1 = number4.reSend1(number2, number3, tail(), head)
  override def send1(number2: Number2, number3: Number3, number4: Number4, item1: Item): Number1 = {
    MergeContext.append(item1, head)
    number4.reSend0(number2, number3, tail())
  }
}
case class Number5T(tail: () => Number5) extends Number5 {
  override def send0(number2: Number2, number3: Number3, number4: Number4): Number1 = Number1T
  override def send1(number2: Number2, number3: Number3, number4: Number4, item1: Item): Number1 =
    Number1S(number4.reSend0(number2, number3, tail()), item1)
}
