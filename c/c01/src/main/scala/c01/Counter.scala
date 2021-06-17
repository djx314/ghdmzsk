package c01

trait Number1 {
  def method1: Number4
}

trait Number2 {
  def method2(number4: Number3): Number4
}

trait Number3 {
  def method3(number1: Number2): Number4
  def method4(number1: Number2): Number4
}

case class TopNumber0(tail: Number2) extends Number1 {
  override def method1: Number4 = tail.method2(YBottomNumber0)
}
case class TopNumber1(tail: Number2) extends Number1 {
  override def method1: Number4 = tail.method2(YBottomNumber1)
}
case class TopNumber2(tail: Number2) extends Number1 {
  override def method1: Number4 = tail.method2(YBottomNumber2)
}

case class MiddleNumber0(tail: Number2) extends Number2 {
  override def method2(number4: Number3): Number4 = tail.method2(YMiddleNumber0(number4))
}
case class MiddleNumber1(tail: Number2) extends Number2 {
  override def method2(number4: Number3): Number4 = tail.method2(YMiddleNumber1(number4))
}
case class MiddleNumber2(tail: Number2) extends Number2 {
  override def method2(number4: Number3): Number4 = tail.method2(YMiddleNumber2(number4))
}

case object BottomNumber0 extends Number2 {
  override def method2(number4: Number3): Number4 = YTopNumber2(number4).method1
}
case object BottomNumber1 extends Number2 {
  override def method2(number4: Number3): Number4 = YTopNumber0(number4).method1
}
case object BottomNumber2 extends Number2 {
  override def method2(number4: Number3): Number4 = YTopNumber1(number4).method1
}

case class YTopNumber0(tail: Number3) extends Number1 {
  override def method1: Number4 = tail.method3(BottomNumber0)
}
case class YTopNumber1(tail: Number3) extends Number1 {
  override def method1: Number4 = tail.method3(BottomNumber1)
}
case class YTopNumber2(tail: Number3) extends Number1 {
  override def method1: Number4 = tail.method4(BottomNumber2)
}

case class YMiddleNumber0(tail: Number3) extends Number3 {
  override def method3(number2: Number2): Number4 = tail.method3(MiddleNumber0(number2))
  override def method4(number2: Number2): Number4 = tail.method4(MiddleNumber2(number2))
}
case class YMiddleNumber1(tail: Number3) extends Number3 {
  override def method3(number2: Number2): Number4 = tail.method3(MiddleNumber1(number2))
  override def method4(number2: Number2): Number4 = tail.method3(MiddleNumber0(number2))
}
case class YMiddleNumber2(tail: Number3) extends Number3 {
  override def method3(number2: Number2): Number4 = tail.method3(MiddleNumber2(number2))
  override def method4(number2: Number2): Number4 = tail.method3(MiddleNumber1(number2))
}

case object YBottomNumber0 extends Number3 {
  override def method3(number2: Number2): Number4 = Number4Positive(TopNumber0(number2).method1)
  override def method4(number2: Number2): Number4 = Number4Zero
}
case object YBottomNumber1 extends Number3 {
  override def method3(number2: Number2): Number4 = Number4Positive(TopNumber1(number2).method1)
  override def method4(number2: Number2): Number4 = Number4Positive(TopNumber0(number2).method1)
}
case object YBottomNumber2 extends Number3 {
  override def method3(number2: Number2): Number4 = Number4Positive(TopNumber2(number2).method1)
  override def method4(number2: Number2): Number4 = Number4Positive(TopNumber1(number2).method1)
}

trait Number4 {
  def length: Int
}
case class Number4Positive(tail: Number4) extends Number4 {
  override def length: Int = tail.length + 1
}
case object Number4Zero extends Number4 {
  override def length: Int = 0
}
