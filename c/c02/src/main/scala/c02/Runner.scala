package c02

object Runner {
  val item01 = Item1("Item01")
  val item02 = Item1("Item02")
  val item03 = Item1("Item03")
  val item04 = Item1("Item04")
  val item05 = Item2("Item05")
  val item06 = Item2("Item06")
  val item07 = Item2("Item07")
  val item08 = Item2("Item08")
  val item09 = Item2("Item09")
  val item10 = Item2("Item10")

  val number1 = Number1S(Number1S(Number1S(Number1S(Number1S(Number1T, item01), item02), item03), item04), item04)

  def main(args: Array[String]): Unit = {
    println(number1.method1(N3Bottom_1).length)
  }
}
