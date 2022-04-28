package i01_01

import i01_01.Model.{EmptyText, Text, TextContent}

import scala.io.Source
import scala.util.Using

object XmlGen {

  def spaceLine(s: String): String =
    Using.resource(Source.fromString(s))(t => t.getLines().map(r => "  " + r).filter(!_.isBlank).mkString("", "\n", ""))

  def gen1(num1: Number1[Text, Text]): String = num1 match {
    case Number1S(TextContent(str), num1Tail, num2Tail) =>
      if (str == "span") s"""<$str>${genList1(num2Tail)}</$str>""" else s"""\n<$str>\n${spaceLine(genList1(num2Tail))}\n</$str>"""
    case Number1S(EmptyText, num1Tail, num2Tail) => genList1(num2Tail)
    case Number1T()                              => ""
  }

  def genList1(num2: Number2[Text, Text]): String = num2 match {
    case Number2S(num2Tail, num1Tail) => genList1(num2Tail) + gen1(num1Tail)
    case Number2T(TextContent(str))   => str
    case Number2T(EmptyText)          => ""
  }

}
