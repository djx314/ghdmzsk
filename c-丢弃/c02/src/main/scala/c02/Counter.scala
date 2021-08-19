package c02

trait Number1 {
  def method1(number2: Number2): Number5
}

case class Number1Positive(tail: Number1) extends Number1 {
  def method1(number2: Number2): Number5 = number2.method2(tail)
}

case object Number1Zero extends Number1 {
  def method1(number2: Number2): Number5 = Number5Zero
}

trait Number2 {
  def method2(number1: Number1): Number5
}

trait Number3 {
  def method3(number4: Number4, number1: Number1): Number5
}

trait Number4 {
  def method4(number3: Number3, number1: Number1): Number5
  def method5(number3: Number3, number1: Number1): Number5
}

case class TopNumber1(tail: Number3) extends Number2 {
  override def method2(number1: Number1): Number5 = tail.method3(YBottomNumber1, number1)
}
case class TopNumber2(tail: Number3) extends Number2 {
  override def method2(number1: Number1): Number5 = tail.method3(YBottomNumber2, number1)
}

case class MiddleNumber0(tail: Number3) extends Number3 {
  override def method3(number4: Number4, number1: Number1): Number5 = tail.method3(YMiddleNumber0(number4), number1)
}
case class MiddleNumber1(tail: Number3) extends Number3 {
  override def method3(number4: Number4, number1: Number1): Number5 = tail.method3(YMiddleNumber1(number4), number1)
}
case class MiddleNumber2(tail: Number3) extends Number3 {
  override def method3(number4: Number4, number1: Number1): Number5 = tail.method3(YMiddleNumber2(number4), number1)
}

case object BottomNumber0 extends Number2 with Number3 {
  override def method2(number1: Number1): Number5                   = number1.method1(BottomNumber1)
  override def method3(number4: Number4, number1: Number1): Number5 = YTopNumber0(number4).method2(number1)
}
case object BottomNumber1 extends Number2 with Number3 {
  override def method2(number1: Number1): Number5                   = number1.method1(BottomNumber2)
  override def method3(number4: Number4, number1: Number1): Number5 = YTopNumber1(number4).method2(number1)
}
case object BottomNumber2 extends Number2 with Number3 {
  override def method2(number1: Number1): Number5                   = Number5Positive(number1.method1(TopNumber1(BottomNumber0)))
  override def method3(number4: Number4, number1: Number1): Number5 = YTopNumber2(number4).method2(number1)
}

case class YTopNumber0(tail: Number4) extends Number2 {
  override def method2(number1: Number1): Number5 = tail.method4(BottomNumber1, number1)
}
case class YTopNumber1(tail: Number4) extends Number2 {
  override def method2(number1: Number1): Number5 = tail.method4(BottomNumber2, number1)
}
case class YTopNumber2(tail: Number4) extends Number2 {
  override def method2(number1: Number1): Number5 = tail.method5(BottomNumber0, number1)
}

case class YMiddleNumber0(tail: Number4) extends Number4 {
  override def method4(number3: Number3, number1: Number1): Number5 = tail.method4(MiddleNumber0(number3), number1)
  override def method5(number3: Number3, number1: Number1): Number5 = tail.method4(MiddleNumber1(number3), number1)
}
case class YMiddleNumber1(tail: Number4) extends Number4 {
  override def method4(number3: Number3, number1: Number1): Number5 = tail.method4(MiddleNumber1(number3), number1)
  override def method5(number3: Number3, number1: Number1): Number5 = tail.method4(MiddleNumber2(number3), number1)
}
case class YMiddleNumber2(tail: Number4) extends Number4 {
  override def method4(number3: Number3, number1: Number1): Number5 = tail.method4(MiddleNumber2(number3), number1)
  override def method5(number3: Number3, number1: Number1): Number5 = tail.method5(MiddleNumber0(number3), number1)
}

case object YBottomNumber1 extends Number4 {
  override def method4(number3: Number3, number1: Number1): Number5 = number1.method1(TopNumber1(number3))
  override def method5(number3: Number3, number1: Number1): Number5 = number1.method1(TopNumber2(number3))
}
case object YBottomNumber2 extends Number4 {
  override def method4(number3: Number3, number1: Number1): Number5 = number1.method1(TopNumber2(number3))
  override def method5(number3: Number3, number1: Number1): Number5 = Number5Positive(number1.method1(TopNumber1(MiddleNumber0(number3))))
}

trait Number5 {
  def length: Int
}
case class Number5Positive(tail: Number5) extends Number5 {
  override def length: Int = tail.length + 1
}
case object Number5Zero extends Number5 {
  override def length: Int = 0
}
