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
    case Number1S(TagText("style"), tail, Number2T(nText: Text)) =>
      val att = countAttributeImpl(tail)
      nText match {
        case TextContent(str) => (att._1, str :: att._2)
        case EmptyText        => att
      }
    case Number1S(TagText(text), tail, Number2T(nText: Text)) =>
      val pro = nText match {
        case TextContent(str) => text + "=\"" + str + "\""
        case EmptyText        => text
      }
      val att = countAttributeImpl(tail)
      (att._1 + " " + pro, att._2)
    case Number1S(
          EmptyTag,
          tail,
          Number2S(Number2T(EmptyText), Number1S(TagText(cssPro), Number1T, Number2T(TextContent(cssValue))))
        ) =>
      val att = countAttributeImpl(tail)
      (att._1, s"$cssPro: $cssValue" :: att._2)
    case Number1T => ("", List.empty)
  }

  def countChildren(number: Number2): String = number match {
    case Number2S(tail, head) => countChildren(tail) + count(head)
    case Number2T(text: Text) =>
      text match {
        case TextContent(str) => str
        case EmptyText        => ""
      }
  }

}
