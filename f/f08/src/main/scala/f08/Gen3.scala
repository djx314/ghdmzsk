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
  val W             = "w"
  val X             = "x"
  val Y             = "y"
  val Z             = "z"
  val baseName      = Vector(S, T, U, V)
  val UnlimitedType = "unlimited"
  val PointType     = "point"
  val ValueType     = "value"
  val ZeroType      = "zero"

  case class SingleNumber(outerName: String, outerType: String, innerName: String, innerType: String, start: Int)

  def leftSets: Vector[CountSet] = {
    val sets = for (each <- Runner.setsLeftover()) yield {
      val data = for (t <- each.set.split('|').to(List)) yield {
        val p = t.split(',').to(List)
        (p(0).toInt, p(1).toInt, if (p(2) == UnlimitedType) Option.empty else p(2).toIntOption)
      }
      (each, data, true)
    }
    val b = sets.to(Vector).zipWithIndex
    val tran = b.foldLeft(b) { (zero, item) =>
      val l1 = List(
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + 1))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + 2))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + 3))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + 4))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + 5))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 1))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 2))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 3))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 4))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 5))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._2))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 1))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 2))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 3))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 4))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 5))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 1))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 2))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 3))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 4))),
        item._1._2.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 5)))
      )

      val start1 = item._1._2.head._2
      val start2 = item._1._2.head._1
      val l2_pre = for {
        i1 <- start1 to 20
        i2 <- start2 to 20
      } yield item._1._2.filter(s => s._1 == i2 && s._2 == i1).head
      val l2 = List(
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + 1))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + 2))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + 3))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + 4))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + 5))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 1))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 2))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 3))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 4))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + 5))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._2))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 1))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 2))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 3))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 4))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._2 + 5))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 1))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 2))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 3))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 4))),
        l2_pre.map(t => (t._1, t._2, t._3.map(b => b + t._1 + t._2 + 5)))
      )

      val exists1List = zero.map { case ((raw1, raw2, raw3), raw4) =>
        raw2.dropWhile(t => t._1 < item._1._2.head._1 && t._2 < item._1._2.head._2)
      }
      val exists2List = zero.map { case ((raw1, raw2, raw3), raw4) =>
        raw2.dropWhile(t => t._1 < start1 && t._2 < start2)
      }

      if (exists1List.exists(t => l1.exists(r => t == r)) || exists2List.exists(t => l2.exists(r => t == r))) {
        val ((raw1, raw2, _), raw3) = zero(item._2)
        zero.updated(item._2, ((raw1, raw2, false), raw3))
      } else zero
    }
    tran.filter(_._1._3).map(_._1._1)
  }

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
          case ZeroType =>
            s"""
              val $varName: Number1 = Number1${singleNumber.outerName.toUpperCase}(() => Number1${singleNumber.innerName.toUpperCase})
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
          case ZeroType =>
            s"""
              val $varName: Number1 = Fusion.number1${singleNumber.outerName}Gen($value, Number1${singleNumber.innerName.toUpperCase})
              """
        }
    }
  }

  def genStr(item: CountPlan, isLimited: Boolean): Option[List[String]] = {
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

    def noNeedGen(singleNumber: SingleNumber): Boolean = {
      val c1 = singleNumber.outerName == "point" && singleNumber.innerName == "point"
      val c2 = singleNumber.outerName == "point" && singleNumber.innerName == "unlimited"
      c1 || c2
    }

    val need = noNeedGen(number1) || noNeedGen(number2)

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
    Option(list.map(s => "    " + s)).filter(_ => !need)
  }

  def genRunner(): Unit = {
    val filesStr = leftSets.map(s => (CountPlans.sum.filter(t => t.set.set == s.set).head, !s.set.contains("unlimited")))

    val lines1 = filesStr.map(s => genStr(s._1, s._2))
    val lines2 = lines1.collect { case Some(s) => s }
    println(s"生成数据${lines2.size}条")
    val lines3 = lines2.flatten

    val list = List("package f08.test", "", "import f07._", "object Test {", "  def main(arr: Array[String]): Unit = {") ++: lines3
      .appended("  }")
      .appended("}")

    val path = Paths.get(".", "f", "f08", "src", "main", "codegen", "f08", "test")

    Files.createDirectories(path)
    val filePath = path.resolve("Test.scala")
    Using.resource(new PrintWriter(filePath.toFile)) { writer =>
      list.foreach(writer.println)
    }
  }

}
