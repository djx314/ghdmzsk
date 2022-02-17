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

    val result2 = Compare.drop(raw)

    assert(result1 == result2)

    for (i <- 0 to 200) {
      val l = (0 to i).to(List).map(s => s"${Random.nextInt(s + 1)}p")

      val l1 = Base.number1FromList(l)
      val l2 = l1.method1(number2s)
      val r1 = Base.numberToList(l2)

      val r2 = Compare.drop(l)

      assert(r1 == r2)
    }
  }
}
