package e01

object NumberGen {

  def numberGen1(number: () => Number1, n: Int): () => Number1 = () => Number1S

  def numberGen2(number: () => Number1, n: Int): () => Number1 = () => Number1T

  def numberGen3(number: () => Number1, n: Int): () => Number1 = () => Number1U

  def numberGen4(number: () => Number1, n: Int): () => Number1 = () => Number1V

  def numberGen5(number: () => Number1, n: Int): () => Number1 = () => Number1W

  def numberGen6(number: () => Number1, n: Int): () => Number1 = () => Number1X

  def numberGen7(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1Y(numberGen7(number, n - 1)) else number

  def numberGen8(number: () => Number1, n: Int): () => Number1 = () => Number1Y(number)

  def numberGen9(number: () => Number1, n: Int): () => Number1 = {
    lazy val n: () => Number1 = () => Number1Y(n)
    n
  }

  def numberGen10(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1Z(numberGen10(number, n - 1)) else number

  def numberGen11(number: () => Number1, n: Int): () => Number1 = () => Number1Z(number)

  def numberGen12(number: () => Number1, n: Int): () => Number1 = {
    lazy val n: () => Number1 = () => Number1Z(n)
    n
  }

  def numberGen13(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1A(numberGen13(number, n - 1)) else number

  def numberGen14(number: () => Number1, n: Int): () => Number1 = () => Number1A(number)

  def numberGen15(number: () => Number1, n: Int): () => Number1 = {
    lazy val n: () => Number1 = () => Number1A(n)
    n
  }

  def numberGen16(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1B(numberGen16(number, n - 1)) else number

  def numberGen17(number: () => Number1, n: Int): () => Number1 = () => Number1B(number)

  def numberGen18(number: () => Number1, n: Int): () => Number1 = {
    lazy val n: () => Number1 = () => Number1B(n)
    n
  }

  def numberGen19(number: () => Number1, n: Int): () => Number1 = () => throw EndException()

  val partGen: List[(Char, (() => Number1, Int) => () => Number1)] = List(
    ('a', numberGen1 _),
    ('b', numberGen2 _),
    ('c', numberGen3 _),
    ('d', numberGen4 _),
    ('e', numberGen5 _),
    ('f', numberGen6 _),
    ('g', numberGen7 _),
    ('h', numberGen8 _),
    ('i', numberGen9 _),
    ('j', numberGen10 _),
    ('k', numberGen11 _),
    ('l', numberGen12 _),
    ('m', numberGen13 _),
    ('n', numberGen14 _),
    ('o', numberGen15 _),
    ('p', numberGen16 _),
    ('q', numberGen17 _),
    ('r', numberGen18 _),
    ('s', numberGen19 _)
  )

  val parGenMap: Map[Char, (() => Number1, Int) => () => Number1] = partGen.to(Map)

  def genNumber(a1: Char, a2: Char, value: Int): () => Number1 = {
    val gen1                     = parGenMap(a1)
    val gen2                     = parGenMap(a2)
    lazy val num1: () => Number1 = gen1(() => num2(), value)
    lazy val num2: () => Number1 = gen2(() => num1(), value)
    num1
  }

  def executeCount(a1: Char, a2: Char, value1: Int, a3: Char, a4: Char, value2: Int): Option[Int] = {
    val num1      = genNumber(a1, a2, value1)
    val num2      = genNumber(a3, a4, value2)
    val exeResult = () => num1().method(num2)()
    count(exeResult)
  }

  def countImpl(num: () => Number1): Int = {
    val next =
      try Option(num())
      catch {
        case _: StackOverflowError => Option.empty
        case EndException()        => Option.empty
      }

    next match {
      case Some(Number1Y(tail)) => countImpl(tail) + 1
      case Some(Number1Z(tail)) => countImpl(tail) + 1
      case Some(Number1A(tail)) => countImpl(tail) + 1
      case Some(Number1B(tail)) => countImpl(tail) + 1
      case Some(Number1S)       => 0
      case Some(Number1T)       => 0
      case Some(Number1U)       => 0
      case Some(Number1V)       => 0
      case Some(Number1W)       => 0
      case Some(Number1X)       => 0
      case None                 => 0
    }
  }

  def count(num: () => Number1): Option[Int] = {
    val r =
      try Option(countImpl(num))
      catch {
        case _: StackOverflowError => Option.empty
      }
    r.filter(_ < 2000)
  }

}
