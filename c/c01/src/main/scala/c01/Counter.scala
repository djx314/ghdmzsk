package c01

case class Item1(name: String)
case class Item2(name: String)

trait Number1 {
  def method1(number2: Number2): Number3
}
case class Number1S(tail: Number1, head: Item1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(tail, head)
}
case object Number1T extends Number1 {
  override def method1(number2: Number2): Number3 = Number3T
}

trait Number2 {
  def method2(number1: Number1, item1: Item1): Number3
}
case class Number2S(tail: Number2, head: Item2) extends Number2 {
  override def method2(number1: Number1, item1: Item1): Number3 = Number3S(tail.method2(number1, item1), item1, head)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method2(number1: Number1, item1: Item1): Number3 = number1.method1(tail())
}

trait Number3
case class N3Top_1(tail: Number3)
case class N3Top_2(tail: Number3)
case class N3Middle_1(tail: Number3) {
  def method3(top: Number3): Number4 = top.method3(N3Middle_2(tail))
}
case class N3Middle_2(tail: Number3) {
  def method3(top: Number3): Number4 = top.method3(N3Middle_3(tail))
}
case class N3Middle_3(tail: Number3) {
  def method3(top: Number3): Number4 = top.method3(N3Middle_1(tail))
}
case object N3Bottom_1 {
  def method3(top: Number3): Number4 = Number4S(N3Bottom_2.method3(top))
}
case object N3Bottom_2 {
  def method3(top: Number3): Number4 = Number4S(N3Bottom_3.method3(top))
}
case object N3Bottom_3 {
  def method3(top: Number3): Number4 = Number4S(top.method3(N3Middle_1(N3Bottom_1)))
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
