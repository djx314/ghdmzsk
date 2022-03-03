package f11.step2

trait Number1 {
  def method1(number2: Number2): Number3
}
case class Number1S(tail: Number1) extends Number1 {
  def method1(number2: Number2): Number3 = number2.method2(tail)
}
case object Number1T extends Number1 {
  def method1(number2: Number2): Number3 = Number3T
}

trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(current: Int, round: Int, step: Int, tail: Int => Number2) extends Number2 {
  def method2(number1: Number1): Number3 = if (current > 0)
    number1.method1(Number2S(current - 1, round, step, r => tail(r + step)))
  else number1.method1(tail(round + step))
}
case class Number2T(round: Int, tail: Int => Number2) extends Number2 {
  def method2(number1: Number1): Number3 = Number3S(() => tail(round).method2(number1))
}

trait Number3 {
  def length: Int
}
case class Number3S(tail: () => Number3) extends Number3 {
  def length: Int = tail().length + 1
}
case object Number3T extends Number3 {
  def length: Int = 0
}
