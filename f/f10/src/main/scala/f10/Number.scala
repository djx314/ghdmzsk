package f10

trait Number {
  def tail: Number
}

object Number {
  def zero: Number = zero

  def count(number: () => Number): Int = {
    val num =
      try Option(number())
      catch {
        case _: StackOverflowError => Option.empty
      }

    num match {
      case Some(n) => count(() => n.tail) + 1
      case None    => 0
    }
  }

  def countOpt(number: () => Number): Option[Int] = try Option(count(number)).filter(_ < 1024)
  catch {
    case _: Exception => Option.empty
  }
}

abstract class MNumber(t: () => MNumber) extends Number {
  self =>
  override def tail: MNumber               = t()
  def current(number: => MNumber): MNumber = number
  def result(number: => Number): Number    = number

  def currentTail: MNumber = new MNumber(t) {
    override def tail: MNumber                        = self.tail
    override def current(number: => MNumber): MNumber = self.current(number.tail)
    override def result(number: => Number): Number    = self.result(number)
    override def method(MNumber: MNumber): Number     = self.method(MNumber)
  }
  def resultPre: MNumber = new MNumber(t) {
    override def tail: MNumber                        = self.tail
    override def current(number: => MNumber): MNumber = self.current(number)
    override def result(number: => Number): Number = new Number {
      override def tail: Number = self.result(number)
    }
    override def method(MNumber: MNumber): Number = self.method(MNumber)
  }

  def method(MNumber: MNumber): Number
}

case class MNumberS(t: () => MNumber) extends MNumber(t) {
  def method(MNumber: MNumber): Number = result(current(tail).method(MNumber))
}
case class MNumberT(t: () => MNumber) extends MNumber(t) {
  def method(MNumber: MNumber): Number = result(MNumber.method(current(tail)))
}
case class MNumberU(t: () => MNumber) extends MNumber(t) {
  def method(MNumber: MNumber): Number = result(Number.zero)
}
