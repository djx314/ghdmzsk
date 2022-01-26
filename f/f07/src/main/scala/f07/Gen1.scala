package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Using

object Gen1 {

  val str1 =
    """
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
  def result29(i1: Int, i2: Int): Int         = if (i1 - i2 > 0) 0 else i2 - i1 + 1
  def result30(i1: Int, i2: Int): Int         = if (i1 == 0) i2 else i2 + 1
  def result31(i1: Int, i2: Int): Int         = if (i1 - i2 > 0) i2 * 2 + 1 else i1 + i2
  def result32(i1: Int, i2: Int): Int         = if (i1 - i2 > 0) i2 + 1 else i1
  def result33(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option.empty else Option(i2)
  def result34(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option.empty else Option(0)
  def result35(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option(i2) else Option.empty
  def result36(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option(0) else Option.empty
  def result37(i1: Int, i2: Int): Int         = if (i1 == 0) 0 else i2
  def result38(i1: Int, i2: Int): Int         = if (i1 - i2 > 0) i2 else i1
  def result39(i1: Int, i2: Int): Option[Int] = if (i1 - i2 > 0) Option.empty else Option(0)
  def result40(i1: Int, i2: Int): Int         = if (i1 == 0) i2 + 1 else i2
  def result41(i1: Int, i2: Int): Int         = if (i1 == 0) 1 else 0
  def result42(i1: Int, i2: Int): Int         = if (i1 - i2 > 0) i2 else i2 * 2 - i1 + 1
      """

  val str2 =
    """
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
  def result18(i1: Int, i2: Int): Int = if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) else i1 / (i2 + 1) + 1
  def result19(i1: Int, i2: Int): Int = if (i1 % (i2 + 1) == i2) i1 / (i2 + 1) + 1 else i1 / (i2 + 1)
  def result20(i1: Int, i2: Int): Int = i1 / (i2 + 1) + 1
  def result21(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2 - 1) else Option(i1 / i2)
  def result22(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2) else Option(i1 / i2 + 1)
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
  def result48(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2 - 1) else Option(2 * i1 + i1 / i2)
  def result49(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2) else Option(2 * i1 + i1 / i2 + 1)
  def result50(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(2 * i1 + i1 / i2 + 1)
  def result51(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(2 * i1 + i1 / i2 + 2)
  def result52(i1: Int, i2: Int): Int         = if (i1 != 0 && i2 == 0) 1 else 2 * i1
  def result53(i1: Int, i2: Int): Int         = if (i2 == 0) 0 else 2 * i1 + 1
  def result54(i1: Int, i2: Int): Int         = 2 * i1 - i1 / (i2 + 1)
  def result55(i1: Int, i2: Int): Int         = if (i1 % (i2 + 1) == 0) 2 * i1 - i1 / (i2 + 1) else 2 * i1 - i1 / (i2 + 1) - 1
  def result56(i1: Int, i2: Int): Int         = 2 * i1 - (i1 + 1) / (i2 + 1) + 1
  def result57(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2 - 1) else Option(i1 + i1 / i2)
  def result58(i1: Int, i2: Int): Option[Int] = if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2) else Option(i1 + i1 / i2 + 1)
  def result59(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(i1 + i1 / i2)
  def result60(i1: Int, i2: Int): Option[Int] = if (i2 == 0) Option.empty else Option(i1 + i1 / i2 + 1)
      """

  def genSetsRunner(): Unit = {
    val lines1 = Using.resource(Source.fromString(str1))(_.getLines().to(List))
    val lines2 = Using.resource(Source.fromString(str2))(_.getLines().to(List))
    val lines3 = (lines1 ::: lines2).map(_.trim).filterNot(_.isEmpty)
    val lines4 = for ((each, index) <- lines3.zipWithIndex) yield {
      val i     = index + 1
      val num   = if (i < 10) s"00$i" else if (i < 100) s"0${i}" else i.toString
      val right = each.split('=').drop(1).mkString("=")
      s"Tags.Tag$num.pushTag((i1: Int, i2: Int) => $right)"
    }

    val path = Paths.get(".", "f", "f07", "src", "main", "codegen", "f07")

    Files.createDirectories(path)
    val filePath = path.resolve("Test.txt")
    Using.resource(new PrintWriter(filePath.toFile)) { writer =>
      lines4.foreach(writer.println)
    }
  }

}
