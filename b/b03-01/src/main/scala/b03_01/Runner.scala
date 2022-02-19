package b03_01

import scala.util.Random

object Runner {
  def main(arr: Array[String]): Unit = {
    lazy val number2s: Number2 = Number2S(Number2S(number2t))
    lazy val number2t: Number2 = Number2T(() => number2s)

    val raw     = (1 to 20).to(List).map(s => s"${s}p")
    val raw1    = Base.number1FromList(raw)
    val raw2    = raw1.method1(number2s)
    val result1 = Base.numberToList(raw2)

    val result2 = Compare.drop(raw, 3)

    assert(result1 == result2)
    assert(result1.size == raw.size / 3 * 2 + raw.size % 3)
    assert(result2.size == raw.size / 3 * 2 + raw.size % 3)

    for {
      i1 <- 0 to 200
      i2 <- 2 to 10
    } {
      val l                      = (0 to i1).to(List).map(s => s"${Random.nextInt(s + 1)}p")
      lazy val num2: Number2     = Base.number2FromInt(i2 - 1, num2Zero)
      lazy val num2Zero: Number2 = Number2T(() => num2)

      val l1 = Base.number1FromList(l)
      val l2 = l1.method1(num2)
      val r1 = Base.numberToList(l2)

      val r2 = Compare.drop(l, i2)

      assert(r1.size == l.size / i2 * (i2 - 1) + l.size % i2)
      assert(r2.size == l.size / i2 * (i2 - 1) + l.size % i2)

      assert(r1 == r2)
    }
  }
}
