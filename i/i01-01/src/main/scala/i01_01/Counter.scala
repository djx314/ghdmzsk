package i01_01

import scala.io.Source
import scala.util.Using

object Counter {

  def spaceLine(s: String): String = Using.resource(Source.fromString(s))(t => t.getLines().map(r => "  " + r).mkString("\n"))

  def count(number: Number1): String = number match {
    case Number1S(TagText("span"), tail, head) =>
      s"<span${countAttribute(tail)}>${countChildren(head)}</span>"
    case Number1S(TagText(text), tail, head) =>
      s"<$text${countAttribute(tail)}>\n${spaceLine(countChildren(head))}\n</$text>\n"
    case Number1S(EmptyTag, _, head) =>
      countChildren(head)
    case Number1T => ""
  }

  def countAttribute(number: Number1): String = {
    val str = countAttributeImpl(number)
    if (str._2.isEmpty) str._1
    else str._1 + " " + s"style=\"${str._2.mkString("; ")}\""
  }

  def countAttributeImpl(number: Number1): (String, List[String]) = number match {
    case Number1S(AttriPro("style"), tail, Number2T(nText: AttributeText)) =>
      val att = countAttributeImpl(tail)
      nText match {
        case AttriText(str) => (att._1, str :: att._2)
        case EmptyAttri     => att
      }
    case Number1S(AttriPro(text), tail, Number2T(nText: AttributeText)) =>
      val pro = nText match {
        case AttriText(str) => text + "=\"" + str + "\""
        case EmptyAttri     => text
      }
      val att = countAttributeImpl(tail)
      (att._1 + " " + pro, att._2)
    case Number1S(
          EmptyAttrPro,
          tail,
          Number2S(Number2T(EmptyAttri), Number1S(AttriPro(cssPro), Number1T, Number2T(AttriText(cssValue))))
        ) =>
      val att = countAttributeImpl(tail)
      (att._1, s"$cssPro: $cssValue" :: att._2)
    case Number1T => ("", List.empty)
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
