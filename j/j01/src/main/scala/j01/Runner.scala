package j01

object Runner {

  case class Item(n: Int)
  trait MinusResult
  case class MinusPositive(tail: MinusResult, head: Item) extends MinusResult
  case object MinusZero                                   extends MinusResult

  trait NumberA extends Number[NumberB, MinusResult]
  class NumberAS(head: Int, context: ContextS[Int, NumberB, MinusResult])
      extends NumberS[Int, NumberB, MinusResult](head, context)
      with NumberA
  class NumberAT(context: ContextT[NumberB, MinusResult]) extends NumberT[NumberB, MinusResult](context) with NumberA

  trait NumberB                                                  extends Number[(NumberA, Int), MinusResult]
  class NumberBT(context: ContextT[(NumberA, Int), MinusResult]) extends NumberT[(NumberA, Int), MinusResult](context) with NumberB

  def main(arr: Array[String]): Unit = {
    def numberv(n: Int, nextNum: NumberA): NumberA = new NumberAS(n, contextv(nextNum))
    def contextv(nextNum: NumberA): ContextS[Int, NumberB, MinusResult] = new ContextS[Int, NumberB, MinusResult] {
      override type NextNumber = NumberA
      override val next: NumberA                                                         = nextNum
      override def executeMethod1(next: NumberA, head: Int, other: NumberB): MinusResult = other.method1((next, head))
    }

    lazy val numbert: NumberA = new NumberAT(contextt)
    lazy val contextt: ContextT[NumberB, MinusResult] = new ContextT[NumberB, MinusResult] {
      override type NextNumber = NumberA
      override def next: NumberA                                                  = numbert
      override def executeMethod1(next: NumberA, nextOther: NumberB): MinusResult = MinusZero
    }

    // ================================================================

    def numberv2(nextNum: NumberB): NumberB = new NumberBT(contextv2(nextNum))
    def contextv2(nextNum: NumberB): ContextT[(NumberA, Int), MinusResult] = new ContextT[(NumberA, Int), MinusResult] {
      override type NextNumber = NumberB
      override val next: NumberB                                                         = nextNum
      override def executeMethod1(next: NumberB, nextOther: (NumberA, Int)): MinusResult = nextOther._1.method1(next)
    }

    lazy val numberu: NumberB = new NumberBT(contextu)
    lazy val contextu: ContextT[(NumberA, Int), MinusResult] = new ContextT[(NumberA, Int), MinusResult] {
      override type NextNumber = NumberB
      override def next: NumberB = numberu
      override def executeMethod1(next: NumberB, nextOther: (NumberA, Int)): MinusResult =
        MinusPositive(nextOther._1.method1(next), Item(nextOther._2))
    }

    def numbervt(n: Int): NumberA = if (n > 0) numberv(n, numbervt(n - 1)) else numbert
    def numbervu(n: Int): NumberB = if (n > 0) numberv2(numbervu(n - 1)) else numberu

    println(numbervt(7).method1(numbervu(4)))
  }

}
