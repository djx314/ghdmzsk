package c01

case class Item1(name: String)
case class Item2(name: String)

trait Number1 {
  def method1(number3: Number3): Number4
}
case class Number1S(tail: Number1, head: Item1) extends Number1 {
  override def method1(number3: Number3): Number4 = number3.method3(N3Top_1(number3), tail)
}
case object Number1T extends Number1 {
  override def method1(number3: Number3): Number4 = Number4T
}

trait Number3 {
  def method3(bottom: Number3, number1: Number1): Number4
}

case class N3Top_1(tail: Number3) extends Number3 {
  def method3(bottom: Number3, number1: Number1): Number4 = bottom.method3(N3Top_2(tail), number1)
}
case class N3Top_2(tail: Number3) extends Number3 {
  def method3(bottom: Number3, number1: Number1): Number4 = bottom.method3(N3Top_3(tail), number1)
}
case class N3Top_3(tail: Number3) extends Number3 {
  def method3(bottom: Number3, number1: Number1): Number4 = number1.method1(N3Middle_1(tail))
}
case class N3Middle_1(tail: Number3) extends Number3 {
  def method3(newTail: Number3, number1: Number1): Number4 = newTail.method3(N3Middle_1(tail), number1)
}
case class N3Middle_2(tail: Number3) extends Number3 {
  def method3(newTail: Number3, number1: Number1): Number4 = newTail.method3(N3Middle_3(tail), number1)
}
case class N3Middle_3(tail: Number3) extends Number3 {
  def method3(newTail: Number3, number1: Number1): Number4 = newTail.method3(N3Middle_1(tail), number1)
}
case object N3Bottom_1 extends Number3 {
  def method3(top: Number3, number1: Number1): Number4 = Number4S(N3Bottom_2.method3(top, number1))
}
case object N3Bottom_2 extends Number3 {
  def method3(top: Number3, number1: Number1): Number4 = Number4S(N3Bottom_3.method3(top, number1))
}
case object N3Bottom_3 extends Number3 {
  def method3(top: Number3, number1: Number1): Number4 = Number4S(top.method3(N3Bottom_1, number1))
}

trait Number4 {
  def length: Int
}
case class Number4S(tail: Number4) extends Number4 {
  override val length: Int = tail.length + 1
}
case object Number4T extends Number4 {
  override val length: Int = 0
}
