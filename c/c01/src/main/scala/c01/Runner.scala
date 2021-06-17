package c01

object Runner {

  def number4FromInt(n: Int): Number4 = n match {
    case n1 if n1 > 0 => Number4Positive(number4FromInt(n1 - 1))
    case 0            => Number4Zero
  }

  def main(arr: Array[String]): Unit = {
    val number1 = TopNumber2(MiddleNumber2(MiddleNumber2(MiddleNumber2(BottomNumber2))))
    val number2 = TopNumber1(MiddleNumber2(MiddleNumber1(MiddleNumber0(BottomNumber2))))
    val number3 = TopNumber0(
      MiddleNumber0(MiddleNumber0(MiddleNumber0(MiddleNumber0(MiddleNumber2(MiddleNumber2(MiddleNumber2(MiddleNumber2(BottomNumber2))))))))
    )
    assert(number1.method1 == number4FromInt(242))
    assert(number2.method1 == number4FromInt(146))
    assert(number3.method1 == number4FromInt(242))
  }

}
