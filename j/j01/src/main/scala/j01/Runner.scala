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
    def numberv(n: Int, nextNum: NumberA): NumberA = new NumberAS(n, new NumberVContext(nextNum))
    class NumberVContext(override val next: NumberA) extends ContextS[Int, NumberB, MinusResult] {
      override type NextNumber = NumberA
      override def executeMethod1(head: Int, other: NumberB): MinusResult = other.method1((next, head))
    }

    lazy val numbert: NumberA = new NumberAT(new NumberTContext(() => numbert))
    class NumberTContext(val nextFunc: () => NumberA) extends ContextT[NumberB, MinusResult] {
      override type NextNumber = NumberA
      override lazy val next: NumberA                              = nextFunc()
      override def executeMethod1(nextOther: NumberB): MinusResult = MinusZero
    }

    // ================================================================

    def numberv2(nextNum: NumberB): NumberB = new NumberBT(new NumberV2Context(nextNum))
    class NumberV2Context(override val next: NumberB) extends ContextT[(NumberA, Int), MinusResult] {
      override type NextNumber = NumberB
      override def executeMethod1(nextOther: (NumberA, Int)): MinusResult = nextOther._1.method1(next)
    }

    lazy val numberu: NumberB = new NumberBT(new NumberUContext(() => numberu))
    class NumberUContext(val nextFunc: () => NumberB) extends ContextT[(NumberA, Int), MinusResult] {
      override type NextNumber = NumberB
      override lazy val next: NumberB                                     = nextFunc()
      override def executeMethod1(nextOther: (NumberA, Int)): MinusResult = MinusPositive(nextOther._1.method1(next), Item(nextOther._2))
    }

    def numbervt(n: Int): NumberA = if (n > 0) numberv(n, numbervt(n - 1)) else numbert
    def numbervu(n: Int): NumberB = if (n > 0) numberv2(numbervu(n - 1)) else numberu

    println(numbervt(7).method1(numbervu(4)))
  }

}
