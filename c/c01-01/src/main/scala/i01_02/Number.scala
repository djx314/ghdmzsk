package i01_02

import scala.io.Source
import scala.util.Using

trait Number1 {
  def method1(yaya1: Yaya1): String
}
case class Number1S(text: String, tail: Number1, head: Number2) extends Number1 {
  override def method1(yaya1: Yaya1): String = yaya1.method1(head, text)
}
case object Number1T extends Number1 {
  override def method1(yaya1: Yaya1): String = ""
}

trait Number2 {
  def method2(yaya1: Yaya1): String
}
case class Number2S(tail: Number2, head: Number1) extends Number2 {
  override def method2(yaya1: Yaya1): String = s"${tail.method2(yaya1)}${head.method1(yaya1)}\n"
}
case class Number2T(text: String) extends Number2 {
  override def method2(yaya1: Yaya1): String = text
}

trait Yaya1 {
  def method1(number2: Number2, text: String): String
}
case class Yaya1T(tail1: () => Yaya1) extends Yaya1 {
  override def method1(number2: Number2, text: String): String = {
    val t = number2.method2(tail1())
    val u = Using.resource(Source.fromString(t))(n => n.getLines().to(List).map(p => "  " + p))
    val i = if (u.isEmpty) "" else s"\n${u.mkString("\n")}\n"
    s"<$text>$i</$text>"
  }
}
