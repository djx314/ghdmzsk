package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Using

object Gen3 {

  val S             = "s"
  val T             = "t"
  val U             = "u"
  val V             = "v"
  val baseName      = Vector(S, T, U, V)
  val UnlimitedType = "unlimited"
  val PointType     = "point"
  val ValueType     = "value"

  case class SingleNumber(outerName: String, outerType: String, innerName: String, innerType: String, start: Int)

  def genSingleNumber(varName: String, singleNumber: SingleNumber, value: String): String = {
    singleNumber.outerType match {
      case UnlimitedType => s"val $varName = Fusion.number1${singleNumber.outerName}"
      case PointType =>
        singleNumber.innerType match {
          case UnlimitedType =>
            s"val $varName = Number1${singleNumber.outerName.toUpperCase}(() => Fusion.number1${singleNumber.innerName})"
          case PointType =>
            s"""
              lazy val $varName: Number1 = Number1${singleNumber.outerName.toUpperCase}(() => inner_$varName)
              lazy val inner_$varName: Number1 = Number1${singleNumber.innerName.toUpperCase}(() => $varName)
              """
          case ValueType =>
            s"""
              lazy val $varName: Number1 = Number1${singleNumber.outerName.toUpperCase}(() => inner_$varName)
              lazy val inner_$varName: Number1 = Fusion.number1${singleNumber.innerName}Gen($value, $varName)
              """
        }
      case ValueType =>
        singleNumber.innerType match {
          case UnlimitedType =>
            s"val $varName = Fusion.number1${singleNumber.outerName}Gen($value, Fusion.number1${singleNumber.innerName})"
          case PointType =>
            s"""
            lazy val $varName: Number1 = Fusion.number1${singleNumber.outerName}Gen($value, inner_$varName)
            lazy val inner_$varName: Number1 = Number1${singleNumber.innerName.toUpperCase}(() => $varName)
            """
          case ValueType =>
            s"""
            lazy val $varName: Number1 = Fusion.number1${singleNumber.outerName}Gen($value, inner_$varName)
            lazy val inner_$varName: Number1 = Fusion.number1${singleNumber.innerName}Gen($value, $varName)
            """
        }
    }
  }

  def genStr(item: CountPlan, isLimited: Boolean): List[String] = {
    val number1 = SingleNumber(
      outerName = item.firstOuterName,
      outerType = item.firstOuterType,
      innerName = item.firstInnerName,
      innerType = item.firstInnerType,
      start = item.firstStart
    )
    val number2 = SingleNumber(
      outerName = item.secondOuterName,
      outerType = item.secondOuterType,
      innerName = item.secondInnerName,
      innerType = item.secondInnerType,
      start = item.secondStart
    )
    val counter = if (isLimited) "Counter.count(() => count)" else "Counter.countOpt(() => count)"
    val str1 = s"""
      ${genSingleNumber("number1", number1, "i1")}
      ${genSingleNumber("number2", number2, "i2")}
      def count = number1.method1(number2)
      def r1 = $counter
      val r2 = true
      assert(true == r2)
       """
    val str2 = Using.resource(Source.fromString(str1))(r => r.getLines().to(List)).map(_.trim).filterNot(_.isEmpty).map(s => "  " + s)

    val list = List("for {", s"  i1 <- ${item.firstStart} to 20", s"  i2 <- ${item.secondStart} to 20", "} yield {") ::: str2.appended("}")
    list.map(s => "    " + s)
  }

  def genRunner(): Unit = {
    val filesStr = Runner.setsLeftover().map(s => (CountPlans.sum.filter(t => t.set.set == s.set).head, !s.set.contains("unlimited")))

    val list = List("package f07.test", "", "import f07._", "object Test {", "  def main(arr: Array[String]): Unit = {") ::: filesStr
      .flatMap(s => genStr(s._1, s._2))
      .appended("  }")
      .appended("}")

    val path = Paths.get(".", "f", "f07", "src", "main", "codegen", "f07", "test")

    Files.createDirectories(path)
    val filePath = path.resolve("Test.scala")
    Using.resource(new PrintWriter(filePath.toFile)) { writer =>
      list.foreach(writer.println)
    }
  }

}
