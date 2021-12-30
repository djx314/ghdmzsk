package i01_01

import scala.io.Source
import scala.util.Using

object Counter {
  import Ast._
  import Model._

  def spaceLine(s: String): String = Using.resource(Source.fromString(s))(t => t.getLines().map(r => "  " + r).mkString("\n"))

  def count(number: Number1[Text, Text]): String = number match {
    case HtmlTag(TextContent("span"), tail, head) =>
      s"<span${countAttribute(tail)}>${countChildren(head)}</span>"
    case HtmlTag(TextContent(text), tail, head) =>
      s"<$text${countAttribute(tail)}>\n${spaceLine(countChildren(head))}\n</$text>\n"
    case BlankTag(head) => countChildren(head)
    case HtmlNumberT    => ""
  }

  def countAttribute(number: Number1[Text, Text]): String = {
    val (property, css) = countAttributeImpl(number)
    if (css.isEmpty) property
    else property + " " + s"style=\"${css.mkString("; ")}\""
  }

  def countAttributeImpl(number: Number1[Text, Text]): (String, List[String]) = number match {
    case notEmpty @ HtmlNumberS(_, tail, _) =>
      val (pro, css) = countAttributeImpl(tail)
      notEmpty match {
        case CommonProperty(_, "style", value) =>
          (pro, value :: css)
        case CommonProperty(_, propertyName, value) =>
          val proStr = propertyName + "=\"" + value + "\""
          (pro + " " + proStr, css)
        case SingleProperty(_, propertyName) =>
          (pro + " " + propertyName, css)
        case CssProperty(_, cssPro, cssValue) =>
          (pro, s"$cssPro: $cssValue" :: css)
      }
    case HtmlNumberT => ("", List.empty)
  }

  def countChildren(number: Number2[Text, Text]): String = number match {
    case ChildNumberS(tail, head) => countChildren(tail) + count(head)
    case ChildNumberT(text: Text) =>
      text match {
        case TextContent(str) => str
        case EmptyText        => ""
      }
  }

}
