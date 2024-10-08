package f02

import scala.collection.mutable.HashMap

object Fusion {

  lazy val number1s: Number1 = Number1S(() => number1s)
  lazy val number1t: Number1 = Number1T(() => number1t)
  lazy val number1u: Number1 = Number1U(() => number1u)
  lazy val number1v: Number1 = Number1V(() => number1v)

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1sGen(n - 1, zero)) else zero
  def number1tGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => number1tGen(n - 1, zero)) else zero
  def number1uGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1U(() => number1uGen(n - 1, zero)) else zero
  def number1vGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1V(() => number1vGen(n - 1, zero)) else zero

}

object Counter {
  def count(number2: () => Number2): Int = {
    val value =
      try Option(number2())
      catch {
        case _: StackOverflowError => Option.empty
      }
    value match {
      case Some(Number2S(tail)) => count(tail) + 1
      case None                 => 0
    }
  }

  def countOpt(number2: () => Number2): Option[Int] = {
    try Option(count(number2)).filter(_ < 500)
    catch {
      case _: StackOverflowError => Option.empty
    }
  }
}

object Result {

  def result1(i1: Int, i2: Int): Option[Int]  = Option.empty
  def result2(i1: Int, i2: Int): Option[Int]  = if (i2 == 0) Option(0) else Option.empty
  def result3(i1: Int, i2: Int): Int          = 0
  def result4(i1: Int, i2: Int): Int          = i1
  def result5(i1: Int, i2: Int): Int          = i1 + i2 + 1
  def result6(i1: Int, i2: Int): Int          = i1 + 1
  def result7(i1: Int, i2: Int): Int          = i1 + i2
  def result8(i1: Int, i2: Int): Option[Int]  = if (i2 == 0) Option.empty else Option(i1 + 1)
  def result9(i1: Int, i2: Int): Option[Int]  = if (i2 == 0) Option.empty else Option(i1 + 2)
  def result10(i1: Int, i2: Int): Int         = if (i2 == 0) 0 else i1 + 1
  def result11(i1: Int, i2: Int): Int         = if (i2 == 0) i1 else i1 + 1
  def result12(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(i1)
  def result13(i1: Int, i2: Int): Int         = if (i2 == 0) 0 else i1
  def result14(i1: Int, i2: Int): Int         = if (i2 == 0) i1 + 1 else i1
  def result15(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option(i1 + 1) else Option.empty
  def result16(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option(i1) else Option.empty
  def result17(i1: Int, i2: Int): Int         = i1 / (i2 + 1)
  // result18 和 result19 没法合并
  def result18(i1: Int, i2: Int): Int = if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) else i1 / (i2 + 1) + 1
  def result19(i1: Int, i2: Int): Int = if (i1 % (i2 + 1) == i2) i1 / (i2 + 1) + 1 else i1 / (i2 + 1)
  def result20(i1: Int, i2: Int): Int = i1 / (i2 + 1) + 1
  def result21(i1: Int, i2: Int): Option[Int] =
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2 - 1) else Option(i1 / i2)
  def result22(i1: Int, i2: Int): Option[Int] =
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2) else Option(i1 / i2 + 1)
  def result23(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(i1 / i2)
  def result24(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(i1 / i2 + 1)
  def result25(i1: Int, i2: Int): Int         = i2 + 1
  def result26(i1: Int, i2: Int): Int         = 1
  def result27(i1: Int, i2: Int): Int         = i2
  def result28(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(1)
  def result29(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(2)
  def result30(i1: Int, i2: Int): Int         = if (i2 == 0) 0 else 1
  def result31(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(0)
  def result32(i1: Int, i2: Int): Int         = if (i2 == 0) 1 else 0
  def result33(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option(1) else Option.empty
  def result34(i1: Int, i2: Int): Option[Int] = if (i1 != 0 && i2 == 0) Option(1) else Option.empty
  def result35(i1: Int, i2: Int): Option[Int] = if (i1 != 0) Option(1) else Option.empty
  def result36(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else Option.empty
  def result37(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else if (i2 == 0) Option(1) else Option.empty
  def result38(i1: Int, i2: Int): Int         = i1 * i2 + 2 * i1
  def result39(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i1 * i2 + 2 * i1 - i2
  def result40(i1: Int, i2: Int): Int         = i1 * i2 + 2 * i1 + i2 + 1
  def result41(i1: Int, i2: Int): Int         = i1 * i2 + 2 * i1 + 1
  def result42(i1: Int, i2: Int): Int         = i1 * i2 + i1
  def result43(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i1 * i2 + i1 - i2
  def result44(i1: Int, i2: Int): Int         = i1 * i2 + i1 + i2
  def result45(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else 1
  def result46(i1: Int, i2: Int): Int         = 2 * i1
  def result47(i1: Int, i2: Int): Int         = 2 * i1 + 1
  def result48(i1: Int, i2: Int): Option[Int] =
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2 - 1) else Option(2 * i1 + i1 / i2)
  def result49(i1: Int, i2: Int): Option[Int] =
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2) else Option(2 * i1 + i1 / i2 + 1)
  def result50(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(2 * i1 + i1 / i2 + 1)
  def result51(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(2 * i1 + i1 / i2 + 2)
  def result52(i1: Int, i2: Int): Int         = if (i1 != 0 && i2 == 0) 1 else 2 * i1
  def result53(i1: Int, i2: Int): Int         = if (i2 == 0) 0 else 2 * i1 + 1
  def result54(i1: Int, i2: Int): Int         = 2 * i1 - i1 / (i2 + 1)
  def result55(i1: Int, i2: Int): Int         = if (i1 % (i2 + 1) == 0) 2 * i1 - i1 / (i2 + 1) else 2 * i1 - i1 / (i2 + 1) - 1
  def result56(i1: Int, i2: Int): Int         = 2 * i1 - (i1 + 1) / (i2 + 1) + 1
  def result57(i1: Int, i2: Int): Option[Int] =
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2 - 1) else Option(i1 + i1 / i2)
  def result58(i1: Int, i2: Int): Option[Int] =
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2) else Option(i1 + i1 / i2 + 1)
  def result59(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(i1 + i1 / i2)
  def result60(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(i1 + i1 / i2 + 1)

  def countResult(i1: Int, i2: Int): List[(Int, Option[Int])] = List((1, Option(1)))

  def someResult(i1: Int, i2: Int): List[(Int, Int)] = countResult(i1, i2).collect { case (a, Some(b)) => (a, b) }

  def main1(arr: Array[String]): Unit = {
    val coll: HashMap[(Int, Int, Int), Int] = HashMap.empty
    for {
      i1                         <- 0 to 19
      i2                         <- 0 to 19
      (key1, value1_1, value1_2) <- someResult(i1, i2).zip(someResult(i2, i1)).map { case ((k1, v1), (k2, v2)) => (k1, v1, v2) }
      (key2, value2_1, value2_2, value2_3, value2_4) <- someResult(value1_1, i1)
        .zip(someResult(i1, value1_1))
        .zip(someResult(value1_2, i1))
        .zip(someResult(i1, value1_2))
        .map { case ((((k1, v1), (k2, v2)), (k3, v3)), (k4, v4)) => (k1, v1, v2, v3, v4) }
      (key3, value3_1, value3_2, value3_3, value3_4) <- someResult(value1_1, i2)
        .zip(someResult(i2, value1_1))
        .zip(someResult(value1_2, i2))
        .zip(someResult(i2, value1_2))
        .map { case ((((k1, v1), (k2, v2)), (k3, v3)), (k4, v4)) => (k1, v1, v2, v3, v4) }
    } {
      val confirm1 = value2_1 == i2 || value2_2 == i2 || value2_3 == i2 || value2_4 == i2
      val confirm2 = value3_1 == i1 || value3_2 == i1 || value3_3 == i1 || value3_4 == i1
      if (confirm1 && confirm2) {
        coll.get((key1, key2, key3)) match {
          case Some(v) => coll.put((key1, key2, key3), v + 1)
          case None    => coll.put((key1, key2, key3), 1)
        }
      }
    }
    println(coll.filter(_._2 >= 400).mkString("\n"))

  }

}
