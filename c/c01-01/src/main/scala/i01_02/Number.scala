package i01_02

import scala.io.Source
import scala.util.Using

trait Number1_1 {
  def method1(num2: Number2): Number3_2
}
case class Number1_1S(text: String, tail2: Number1_2) extends Number1_1 {
  override def method1(num2: Number2): Number3_2 = num2.method3(tail2, text)
}

trait Number1_2 {
  def method2(num2: Number2): Number3_2
}
case class Number1_2S(tail2: Number1_2, tail1: Number1_1) extends Number1_2 {
  override def method2(num2: Number2): Number3_2 = Number3_2S(tail2.method2(num2), tail1.method1(num2))
}
case class Number1_2T(text: String) extends Number1_2 {
  override def method2(num2: Number2): Number3_2 = Number3_2U(text)
}

// ====
trait Number2 {
  def method3(num1: Number1_2, text: String): Number3_2
}
case class Number2S(tail: Number2) extends Number2 {
  override def method3(num1: Number1_2, text: String): Number3_2 = Number3_2T(s"<$text>", Number3_1S(s"</$text>", tail.method3(num1, text)))
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method3(num1: Number1_2, text: String): Number3_2 = num1.method2(tail())
}

// ====
trait Number3_1 {
  def text1: String
}
case class Number3_1S(text: String, tail: Number3_2) extends Number3_1 {
  def text1: String =
    Using.resource(Source.fromString(tail.text2))(t => t.getLines().to(List).map(r => "  " + r).mkString("\n")) + "\n" + text
}

trait Number3_2 {
  def text2: String
}
case class Number3_2S(tail2_1: Number3_2, tail2_2: Number3_2) extends Number3_2 {
  def text2: String = tail2_1.text2 + tail2_2.text2
}
case class Number3_2T(text: String, tail: Number3_1) extends Number3_2 {
  def text2: String = "\n" + text + tail.text1
}
case class Number3_2U(text: String) extends Number3_2 {
  def text2: String = if (text.isEmpty) text else s"\n$text"
}
