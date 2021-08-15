package d02

trait Number1
case class Number1S(tail: Number1) extends Number1
case object Number1T               extends Number1

trait Number2
case class Number2S(tail: Number2) extends Number2
case object Number2T               extends Number2

sealed trait Number3
case class Number3S(tail: Number3)       extends Number3
case class Number3T(tail: () => Number3) extends Number3

trait Context1 {
  def method1(number3: Number3): PartialFunction[Number2, Number1]
}
trait Context2 {
  def method2(number2: Number2): PartialFunction[Number3, Number1]
}

case class Context3(other: () => Context2) extends Context1 {
  override def method1(number3: Number3): PartialFunction[Number2, Number1] = {
    case Number2S(tail) => other().method2(tail)(number3)
    case Number2T       => Number1T
  }
}

case class Context4(other: () => Context1) extends Context2 {
  override def method2(number2: Number2): PartialFunction[Number3, Number1] = { case Number3S(tail) =>
    Number1S(other().method1(tail)(number2))
  }
}

case class Context5(selfContext: () => Context2) extends Context2 {
  override def method2(number2: Number2): PartialFunction[Number3, Number1] = { case Number3S(tail) =>
    Number1S(selfContext().method2(number2)(tail))
  }
}

case class Context6(other: () => Context1) extends Context2 {
  override def method2(number2: Number2): PartialFunction[Number3, Number1] = { case Number3S(tail) =>
    other().method1(tail)(number2)
  }
}

case class Context7(other: () => Context1) extends Context2 {
  override def method2(number2: Number2): PartialFunction[Number3, Number1] = { case Number3T(tail) =>
    Number1S(other().method1(tail())(number2))
  }
}

case class Context8(selfContext: () => Context2) extends Context2 {
  override def method2(number2: Number2): PartialFunction[Number3, Number1] = { case Number3T(tail) =>
    Number1S(selfContext().method2(number2)(tail()))
  }
}

case class Context9(other: () => Context1) extends Context2 {
  override def method2(number2: Number2): PartialFunction[Number3, Number1] = { case Number3T(tail) =>
    other().method1(tail())(number2)
  }
}
