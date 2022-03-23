package a05

object Runner {

  def numberGen(next: => Number1): Number1S = {
    def a1 = next
    def a2 = Number1S(() => a1, numberGen(a1).tail2)
    def a3 = Number1S(() => a2, numberGen(a2).tail2)
    Number1S(() => a3, numberGen(a3).tail2)
  }

  lazy val number1: Number1S    = numberGen(number1Zero)
  lazy val number1Zero: Number1 = Number1T(number1.tail2)

  // wip
  def number2(number: Number1S): Number1S = {
    lazy val n1: Number1S      = numberGen(Number1T(() => number))
    lazy val n2: () => Number1 = number2(n1).tail2
    n1
  }
  lazy val number2X: Number1S = number2(number2X)

}
