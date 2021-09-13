package d05

object Runner {

  def countNumber(num: Result): Int = num match {
    case ResultP(tail, head) => countNumber(tail) + 1
    case ResultO             => 0
  }

  def main(arr: Array[String]): Unit = {

    lazy val n1: Number1 = Number1RightS(Number1RightS(Number1RightS(Number1RightS(n2, new Item), new Item), new Item), new Item)
    lazy val n2: Number1 = Number1RightT(() => n1)

    lazy val n3: Number1 = Number1MiddleS(Number1MiddleS(Number1MiddleS(Number1MiddleS(n4))))
    lazy val n4: Number1 = Number1MiddleT(() => n3)

    val n5: Number1 = Number1LeftS(Number1LeftS(Number1LeftS(Number1LeftS(Number1LeftT))))

    val n6 = n5.左推动(Number1U(Number1U(Number1U(n1, n3), n3), n3))

    println(countNumber(n6))
  }

}
