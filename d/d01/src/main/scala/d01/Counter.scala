package d01

trait Number1
case class Number1S(tail: Number1) extends Number1
case object Number1T               extends Number1

trait Number2 {
  def method1(number3: Number3): Number1
}
case class Number2S(tail: Number2) extends Number2 {
  def method1(number3: Number3): Number1 = number3.method2(tail)
}
// 加法这里会出现问题，只能从 number3 加到 number2
case object Number2T extends Number2 {
  def method1(number3: Number3): Number1 = Number1T
}

trait Number3 {
  def method2(number2: Number2): Number1
}

case class Number4(tail: () => Number3) extends Number3 {
  override def method2(number2: Number2): Number1 = Number1S(number2.method1(tail()))
}
object Number4 {
  def apply(tail: => Number3): Number3 = Number4(() => tail)
  lazy val zero: Number3               = Number4(zero)
}

case class Number5(tail: () => Number3) extends Number3 {
  override def method2(number2: Number2): Number1 = Number1S(tail().method2(number2))
}
object Number5 {
  def apply(tail: => Number3): Number3 = Number5(() => tail)
}

case class Number6(tail: () => Number3) extends Number3 {
  override def method2(number2: Number2): Number1 = number2.method1(tail())
}
object Number6 {
  def apply(tail: => Number3): Number3 = Number6(() => tail)
}
