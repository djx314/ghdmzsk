package c02

object Runner {
  def number1FromInt(n: Int): Number100 = n match {
    case n1 if n1 > 0 => Number100Positive(number1FromInt(n - 1))
    case 0            => Number100Zero
  }
  def number5FromInt(n: Int): Number4 = n match {
    case n1 if n1 > 0 => Number4Positive(number5FromInt(n1 - 1))
    case 0            => Number4Zero
  }
  def main(args: Array[String]): Unit = {
    assert(number1FromInt(0).method100(BottomNumber1) == number5FromInt(1))
    assert(number1FromInt(1).method100(BottomNumber1) == number5FromInt(3))
    assert(number1FromInt(2).method100(BottomNumber1) == number5FromInt(9))
    assert(number1FromInt(3).method100(BottomNumber1) == number5FromInt(27))
    assert(number1FromInt(4).method100(BottomNumber1) == number5FromInt(81))
    assert(number1FromInt(5).method100(BottomNumber1) == number5FromInt(243))
    assert(number1FromInt(6).method100(BottomNumber1) == number5FromInt(729))
    assert(number1FromInt(7).method100(BottomNumber1) == number5FromInt(2187))
    assert(number1FromInt(8).method100(BottomNumber1) == number5FromInt(6561))
    assert(number1FromInt(9).method100(BottomNumber1) == number5FromInt(19683))  // 3 ^ 9 = 19683
    assert(number1FromInt(10).method100(BottomNumber1) == number5FromInt(59049)) // 3 ^ 10 = 59049
  }
}
