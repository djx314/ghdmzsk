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
  def execute[S, T](context: Context[S, T], s: S, t: T): Number1
}
case class Number2S(tail: () => Number2, head: Item) extends Number2 {
  override def execute[S, T](context: Context[S, T], parameter: S, t: T): Number1 = {
    val (newDataCtx, newNum) = context.convert(t, tail())
    context.bindS(newDataCtx, newNum, parameter, head)
  }
}
case class Number2T(tail: () => Number2, head: Item) extends Number2 {
  override def execute[S, T](context: Context[S, T], parameter: S, t: T): Number1 = {
    val (newDataCtx, newNum) = context.convert(t, tail())
    context.bindT(newDataCtx, newNum, parameter, head)
  }
}

trait Context[S, T] {
  def convert(t: T, current: Number2): (T, Number2)
  def bindS(number: T, current: Number2, parameter: S, head: Item): Number1
  def bindT(number: T, current: Number2, parameter: S, head: Item): Number1
}
