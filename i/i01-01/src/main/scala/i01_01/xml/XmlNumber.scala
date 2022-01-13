package i01_01.xml

import scala.io.Source
import scala.util.Using

case class HtmlTag(text: String, tail: Number1, head: Number2) extends Number1 {
  override def method1(number2: Number2): String =
    s"<$text ${tail.method1(number2)}>" + head.method2(Models.model1) + s"</$text>"
}

case class HtmlTagDeal(tail: Number2, head: Number1) extends Number2 {
  def spaceLine(s: String): String = {
    val end = if (s.endsWith("\n")) "\n" else ""
    Using.resource(Source.fromString(s))(t => t.getLines().map(r => "  " + r).mkString("", "\n", end))
  }

  override def method2(number1: Number1): String = spaceLine(
    List(tail.method2(number1), head.method1(Models.model2)).map(_.trim).mkString("\n", "\n", "\n")
  )
}

case class EndHtmlTag(str: String) extends Number2 {
  override def method2(number1: Number1): String = str
}

case object EndInfoTag extends Number1 {
  override def method1(number2: Number2): String = ""
}

case class HtmlTagHelper1(tail: () => Number1, head: () => Number2) extends Number1 {
  override def method1(number2: Number2): String = "11"
}

case class HtmlTagHelper2(tail: () => Number2, head: () => Number1) extends Number2 {
  override def method2(number1: Number1): String = "11"
}

object Models {
  lazy val model1: Number1 = HtmlTagHelper1(() => model1, () => model2)
  lazy val model2: Number2 = HtmlTagHelper2(() => model2, () => model1)
}
