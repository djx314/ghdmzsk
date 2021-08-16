package d02

trait Number1
case class Number1S(tail: Number1) extends Number1
case object Number1T               extends Number1

trait Number2
case class Number2S(tail: () => Number2) extends Number2
case class Number2T(tail: () => Number2) extends Number2

sealed trait Number3
case class Number3S(tail: () => Number3) extends Number3
case class Number3T(tail: () => Number3) extends Number3
case class Number3U(tail: () => Number3) extends Number3

trait Context1 {
  def method1(number3: Number3): PartialFunction[Number2, Number1]
}
trait Context2 {
  def receive0(number2: Number2): PartialFunction[Number3, Number1]
  def receive1(number2: Number2): PartialFunction[Number3, Number1]
}
object Context2 {
  def merge(context1: => Context2, context2: => Context2): Context2 = new Context2 {
    override def receive0(number2: Number2): PartialFunction[Number3, Number1] =context1.receive0(number2).orElse(context2.receive0(number2))
    override def receive1(number2: Number2): PartialFunction[Number3, Number1] = context1.receive1(number2).orElse(context2.receive1(number2))
  }
}

case class Context3(other: () => Context2) extends Context1 {
  override def method1(number3: Number3): PartialFunction[Number2, Number1] = {
    case Number2S(tail) => other().receive1(tail())(number3)
    case Number2T(tail) => other().receive0(tail())(number3)
  }
}

case class Context4(other: () => Context1) extends Context2 {
  override def receive0(number2: Number2): PartialFunction[Number3, Number1] = { case Number3U(tail) =>
    Number1T
  }
  override def receive1(number2: Number2): PartialFunction[Number3, Number1] = { case Number3U(tail) =>
    Number1S(other().method1(tail())(number2))
  }
}

case class Context5(selfContext: () => Context2) extends Context2 {
  override def receive0(number2: Number2): PartialFunction[Number3, Number1] = { case Number3S(tail) =>
    Number1S(selfContext().receive0(number2)(tail()))
  }
  override def receive1(number2: Number2): PartialFunction[Number3, Number1] = { case Number3S(tail) =>
    Number1S(selfContext().receive1(number2)(tail()))
  }
}

case class Context6(other: () => Context1) extends Context2 {
  override def receive0(number2: Number2): PartialFunction[Number3, Number1] = { case Number3T(tail) =>
    Number1T
  }
  override def receive1(number2: Number2): PartialFunction[Number3, Number1] = { case Number3T(tail) =>
    other().method1(tail())(number2)
  }
}
