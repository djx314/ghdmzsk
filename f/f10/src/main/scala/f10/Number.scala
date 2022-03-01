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

case class MNumber(t: () => MNumber, tag: List[Int] = List.empty) extends Number {
  self =>
  override def tail: MNumber = t()

  def method(MNumber: MNumber): Number       = pair(tail, MNumber)
  def pair(m1: MNumber, m2: MNumber): Number = m1.method(m2)
}

object MNumber {

  implicit class MNumberExtra(val m: MNumber) extends AnyVal {
    def takeTail: MNumber = new MNumber(() => m.tail.tail, 1 :: m.tag) {
      override def pair(m1: MNumber, m2: MNumber): Number = m.pair(m1, m2)
    }
    def resultPre: MNumber = new MNumber(m.t, 2 :: m.tag) {
      override def pair(m1: MNumber, m2: MNumber): Number = new Number {
        override def tail: Number = m.pair(m1, m2)
      }
    }
    def reverse: MNumber = new MNumber(m.t, 3 :: m.tag) {
      override def pair(m1: MNumber, m2: MNumber): Number = m.pair(m2, m1)
    }
  }

  def countImpl(m: () => MNumber): Int = {
    var init: List[Int] = null
    def c(m1: () => MNumber): Int = {
      val num =
        try {
          Option(m1())
        } catch {
          case _: Exception => Option.empty
        }
      num match {
        case Some(s) =>
          val dTag = s.tag.distinct
          if (init == null) {
            init = dTag
            c(() => s.tail) + 1
          } else if (init == dTag)
            c(() => s.tail) + 1
          else 0
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
