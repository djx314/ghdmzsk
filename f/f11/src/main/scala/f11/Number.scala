package f11

trait Number1 {
  def length: Number3
}
case class Number1S(current: Int, left: Int, tail: (Int, Int) => Number1) extends Number1 {
  def length: Number3 = if (left > 0) tail(current, left).length else Number3S(() => tail(current, left).length)
}
case object Number1U extends Number1 {
  def length: Number3 = Number3T
}

/*trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2(number1: Number1): Number3 = number1.method1(tail)
}
case class Number2T(tail: () => Number2) extends Number2 {
  def method2(number1: Number1): Number3 = Number3S(() => tail().method2(number1))
}*/

trait Number3 {
  def length: Int
}
case class Number3S(tail: () => Number3) extends Number3 {
  def length: Int = tail().length + 1
}
case object Number3T extends Number3 {
  def length: Int = 0
}
