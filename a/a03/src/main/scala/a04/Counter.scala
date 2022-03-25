package a04

trait Number3 {
  def method3(number2: Number2): Number5
}
case class Number3S(tail3: () => Number3, tail4: () => Number3) extends Number3 {
  override def method3(number2: Number2): Number5 = number2.method2(tail3())
}
case class Number3T(tail3: () => Number3) extends Number3 {
  override def method3(number2: Number2): Number5 = Number5S(tail3().method3(number2))
}
case class Number3U(tail3: () => Number3) extends Number3 {
  override def method3(number2: Number2): Number5 = tail3().method3(number2)
}

trait Number4 {
  def method4(number2: Number2): Number5
}
case class Number4S(tail3: () => Number3) extends Number4 {
  override def method4(number2: Number2): Number5 = tail4().method4(number2)
}
case class Number4T(tail3: () => Number3) extends Number4 {
  override def method4(number2: Number2): Number5 = tail4().method4(number2)
}

trait Number2 {
  def method2(number1: Number3): Number5
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2(number1: Number3): Number5 = number1.method3(tail)
}
case object Number2T extends Number2 {
  override def method2(number1: Number3): Number5 = Number5T
}

trait Number5
case class Number5S(tail: Number5) extends Number5
case object Number5T               extends Number5
