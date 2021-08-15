package c02

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
    assert(number1FromInt(1).method1(BottomNumber0) == number5FromInt(0))
    assert(number1FromInt(2).method1(BottomNumber0) == number5FromInt(0))
    assert(number1FromInt(3).method1(BottomNumber0) == number5FromInt(1))
    assert(number1FromInt(4).method1(BottomNumber0) == number5FromInt(1))
    assert(number1FromInt(5).method1(BottomNumber0) == number5FromInt(1))
    assert(number1FromInt(6).method1(BottomNumber0) == number5FromInt(1))
    assert(number1FromInt(7).method1(BottomNumber0) == number5FromInt(1))
    assert(number1FromInt(8).method1(BottomNumber0) == number5FromInt(1))
    assert(number1FromInt(9).method1(BottomNumber0) == number5FromInt(2))
    assert(number1FromInt(10).method1(BottomNumber0) == number5FromInt(2))

    assert(number1FromInt(26).method1(BottomNumber0) == number5FromInt(2))
    assert(number1FromInt(27).method1(BottomNumber0) == number5FromInt(3))
    assert(number1FromInt(28).method1(BottomNumber0) == number5FromInt(3))

    assert(number1FromInt(80).method1(BottomNumber0) == number5FromInt(3))
    assert(number1FromInt(81).method1(BottomNumber0) == number5FromInt(4))
    assert(number1FromInt(82).method1(BottomNumber0) == number5FromInt(4))

    assert(number1FromInt(242).method1(BottomNumber0) == number5FromInt(4))
    assert(number1FromInt(243).method1(BottomNumber0) == number5FromInt(5))
    assert(number1FromInt(244).method1(BottomNumber0) == number5FromInt(5))

    assert(number1FromInt(728).method1(BottomNumber0) == number5FromInt(5))
    assert(number1FromInt(729).method1(BottomNumber0) == number5FromInt(6))
    assert(number1FromInt(730).method1(BottomNumber0) == number5FromInt(6))

    assert(number1FromInt(2186).method1(BottomNumber0) == number5FromInt(6))
    assert(number1FromInt(2187).method1(BottomNumber0) == number5FromInt(7))
    assert(number1FromInt(2188).method1(BottomNumber0) == number5FromInt(7))

    assert(number1FromInt(6560).method1(BottomNumber0) == number5FromInt(7))
    assert(number1FromInt(6561).method1(BottomNumber0) == number5FromInt(8))
    assert(number1FromInt(6562).method1(BottomNumber0) == number5FromInt(8))

    assert(number1FromInt(19682).method1(BottomNumber0) == number5FromInt(8))
    assert(number1FromInt(19683).method1(BottomNumber0) == number5FromInt(9))
    assert(number1FromInt(19684).method1(BottomNumber0) == number5FromInt(9))

    assert(number1FromInt(59048).method1(BottomNumber0) == number5FromInt(9))
    assert(number1FromInt(59049).method1(BottomNumber0) == number5FromInt(10))
    assert(number1FromInt(59050).method1(BottomNumber0) == number5FromInt(10))
  }
}
