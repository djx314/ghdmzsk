package f10

trait Number {
  def tail: Number
}

object Number {

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
    case _: StackOverflowError => Option.empty
  }

}

abstract class MNumber() extends Number {
  val tag: List[Int]
  override def tail: MNumber
  def next(MNumber: => MNumber): MNumber = MNumber.tail
  def end(number: => Number): Number     = number
  def method(m: MNumber): Number
}

case class MNumberS(t: () => MNumber) extends MNumber {
  override val tag: List[Int]             = List(1)
  override def tail: MNumber              = t()
  override def method(m: MNumber): Number = end(next(this).method(m))
}

case class MNumberT(t: () => MNumber) extends MNumber {
  override val tag: List[Int]             = List(2)
  override def tail: MNumber              = t()
  override def method(m: MNumber): Number = end(m.method(next(this)))
}

object MNumber {

  implicit class MNumberSExtra(val m: MNumberS) extends AnyVal {
    def takeTail: MNumberS = new MNumberS(m.t) {
      override val tag: List[Int]                     = m.tag
      override def next(MNumber: => MNumber): MNumber = super.next(m.next(MNumber))
      override def end(number: => Number): Number     = m.end(number)
    }
    def resultPre: MNumberS = new MNumberS(m.t) {
      override val tag: List[Int]                     = 3 :: m.tag
      override def next(MNumber: => MNumber): MNumber = m.next(MNumber)
      override def end(number: => Number): Number = new Number {
        override def tail: Number = number
      }
    }
  }

  implicit class MNumberTExtra(val m: MNumberT) extends AnyVal {
    def takeTail: MNumberT = new MNumberT(m.t) {
      override val tag: List[Int]                     = m.tag
      override def next(MNumber: => MNumber): MNumber = super.next(m.next(MNumber))
      override def end(number: => Number): Number     = m.end(number)
    }
    def resultPre: MNumberT = new MNumberT(m.t) {
      override val tag: List[Int]                     = 3 :: m.tag
      override def next(MNumber: => MNumber): MNumber = m.next(MNumber)
      override def end(number: => Number): Number = new Number {
        override def tail: Number = number
      }
    }
  }

  def countImpl(m: () => MNumber): Int = {
    var init: List[Int] = List.empty

    def c(m1: () => MNumber): Int = {
      val num =
        try {
          Option(m1())
        } catch {
          case _: Exception => Option.empty
        }
      num match {
        case Some(s) =>
          val dTag = s.tag
          if (init == List.empty) {
            init = dTag
            c(() => s.tail) + 1
          } else if (init == dTag) {
            c(() => s.tail) + 1
          } else 0
        case None => 0
      }
    }
    c(m)
  }

  def count(m: () => MNumber): Int = try countImpl(m)
  catch {
    case _: StackOverflowError => 0
  }

}
