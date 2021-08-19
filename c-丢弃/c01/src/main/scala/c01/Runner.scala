package c01

object Runner {
  def number1FromInt(n: Int): Number1 = n match {
    case n1 if n1 > 0 => Number1Positive(number1FromInt(n1 - 1))
    case 0            => Number1Zero
  }
  def number5FromInt(n: Int): Number5 = n match {
    case n1 if n1 > 0 => Number5Positive(number5FromInt(n1 - 1))
    case 0            => Number5Zero
  }
  def main(args: Array[String]): Unit = {
    assert(number1FromInt(0).method1(BottomNumber0) == number5FromInt(1))
    assert(number1FromInt(1).method1(BottomNumber0) == number5FromInt(3))
    assert(number1FromInt(2).method1(BottomNumber0) == number5FromInt(9))
    assert(number1FromInt(3).method1(BottomNumber0) == number5FromInt(27))
    assert(number1FromInt(4).method1(BottomNumber0) == number5FromInt(81))
    assert(number1FromInt(5).method1(BottomNumber0) == number5FromInt(243))
    assert(number1FromInt(6).method1(BottomNumber0) == number5FromInt(729))
    assert(number1FromInt(7).method1(BottomNumber0) == number5FromInt(2187))
    assert(number1FromInt(8).method1(BottomNumber0) == number5FromInt(6561))
    assert(number1FromInt(9).method1(BottomNumber0) == number5FromInt(19683))  // 3 ^ 9 = 19683
    assert(number1FromInt(10).method1(BottomNumber0) == number5FromInt(59049)) // 3 ^ 10 = 59049
  }
}
