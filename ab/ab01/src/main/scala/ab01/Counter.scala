package ab01

case class Item1(name: Int)
case class Item2(name: Int)

trait Number1 {
  def method1(head2: Item2): Number3
}
case class Number1S(tail: Number1, head: Item1) extends Number1 {
  override def method1(head2: Item2): Number3 = Number3S(tail.method1(head2), head, head2)
}
case object Number1T extends Number1 {
  override def method1(head2: Item2): Number3 = Number3T
}

trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(tail: Number2, head: Item2) extends Number2 {
  override def method2(number1: Number1): Number3 = number1.method1(head).method3(tail.method2(number1))
}
case object Number2T extends Number2 {
  override def method2(number1: Number1): Number3 = Number3T
}

trait Number3 {
  def method3(n: Number3): Number3
}
case class Number3S(tail: Number3, head1: Item1, head2: Item2) extends Number3 {
  override def method3(n: Number3): Number3 = Number3S(tail.method3(n), head1, head2)
}
case object Number3T extends Number3 {
  override def method3(n: Number3): Number3 = n
}
