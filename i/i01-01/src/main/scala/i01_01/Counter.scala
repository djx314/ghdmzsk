package i01_01

import scala.io.Source
import scala.util.Using

object Counter {

  def spaceLine(s: String): String = Using.resource(Source.fromString(s))(t => t.getLines().map(r => "  " + r).mkString("\n"))

  def count(number: Number1): String = number match {
    case Number1S(TagText("span"), tail, head) =>
      s"<span${countAttribute(tail)}>" + s"${countChildren(head)}" + s"</span>"
    case Number1S(TagText(text), tail, head) =>
      s"<$text${countAttribute(tail)}>\n" + s"${spaceLine(countChildren(head))}" + s"\n</$text>\n"
    case Number1S(EmptyTag, _, head) =>
      countChildren(head)
    case Number1T => ""
  }

  def countAttribute(number: Number1): String = number match {
    case Number1S(AttriPro(text), tail, Number2T(nText: AttributeText)) =>
      val pro = nText match {
        case AttriText(str) => text + "=\"" + str + "\""
        case EmptyAttri     => text
      }
      countAttribute(tail) + " " + pro
    case Number1T => ""
  }

  def countChildren(number: Number2): String = number match {
    case Number2S(tail, head) => countChildren(tail) + count(head)
    case Number2T(text: HtmlText) =>
      text match {
        case TextContent(str) => str
        case EmptyText        => ""
      }
  }

}
