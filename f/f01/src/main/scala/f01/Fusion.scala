package f01

import scala.collection.mutable.HashMap
import collection.immutable.{List => SList}

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

  def result1(i1: Int, i2: Int): Int          = i1
  def result2(i1: Int, i2: Int): Option[Int]  = Option.empty
  def result3(i1: Int, i2: Int): Int          = i1 + i2 + 1
  def result4(i1: Int, i2: Int): Int          = i1 + 1
  def result5(i1: Int, i2: Int): Int          = i1 + i2 * 2 + 1
  def result6(i1: Int, i2: Int): Int          = i1 + i2
  def result7(i1: Int, i2: Int): Int          = if (i1 - i2 >= 0) i1 - i2 else 0
  def result8(i1: Int, i2: Int): Int          = i2
  def result9(i1: Int, i2: Int): Int          = 0
  def result10(i1: Int, i2: Int): Int         = i2 + 1
  def result11(i1: Int, i2: Int): Int         = 1
  def result12(i1: Int, i2: Int): Int         = i2 * 2 + 1
  def result13(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option.empty else Option(i2 + 1)
  def result14(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option.empty else Option(1)
  def result15(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option(i2 * 2 + 1) else Option.empty
  def result16(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option(i2 + 1) else Option.empty
  def result17(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else Option.empty
  def result18(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i2 + 1
  def result19(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i1 * 2 + i2
  def result20(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i1 + i2
  def result21(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else 1
  def result22(i1: Int, i2: Int): Int         = i1 * 2
  def result23(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option.empty else Option(i1 * 2)
  def result24(i1: Int, i2: Int): Int         = if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2
  def result25(i1: Int, i2: Int): Int         = if (i1 - i2 >= 0) i1 + i2 else i1 * 2
  def result26(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option.empty else Option(i1)
  def result27(i1: Int, i2: Int): Int         = if (i1 - i2 > 0) i2 + 1 else i1
  def result28(i1: Int, i2: Int): Int         = if (i1 - i2 >= 0) i1 * 2 - i2 else i1

  def countResult(i1: Int, i2: Int): SList[(Int, Option[Int])] = SList(
    (1, Option(result1(i1, i2))),
    (2, result2(i1, i2)),
    (3, Option(result3(i1, i2))),
    (4, Option(result4(i1, i2))),
    (5, Option(result5(i1, i2))),
    (6, Option(result6(i1, i2))),
    (7, Option(result7(i1, i2))),
    (8, Option(result8(i1, i2))),
    (9, Option(result9(i1, i2))),
    (10, Option(result10(i1, i2))),
    (11, Option(result11(i1, i2))),
    (12, Option(result12(i1, i2))),
    (13, result13(i1, i2)),
    (14, result14(i1, i2)),
    (15, result15(i1, i2)),
    (16, result16(i1, i2)),
    (17, result17(i1, i2)),
    (18, Option(result18(i1, i2))),
    (19, Option(result19(i1, i2))),
    (20, Option(result20(i1, i2))),
    (21, Option(result21(i1, i2))),
    (22, Option(result22(i1, i2))),
    (23, result23(i1, i2)),
    (24, Option(result24(i1, i2))),
    (25, Option(result25(i1, i2))),
    (26, result26(i1, i2)),
    (27, Option(result27(i1, i2))),
    (28, Option(result28(i1, i2)))
  )

  def someResult(i1: Int, i2: Int): SList[(Int, Int)] = countResult(i1, i2).collect { case (a, Some(b)) => (a, b) }

  def main(arr: Array[String]): Unit = {
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
