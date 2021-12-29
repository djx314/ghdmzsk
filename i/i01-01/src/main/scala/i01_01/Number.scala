package i01_01

trait Number1
case class Number1S(text: Tag, tail: Number1, head: Number2) extends Number1
case object Number1T                                         extends Number1

trait Number2
case class Number2S(tail: Number2, head: Number1) extends Number2
case class Number2T(text: Text)                   extends Number2

trait Text

sealed trait AttributeText         extends Text
case class AttriText(text: String) extends AttributeText
case object EmptyAttri             extends AttributeText

sealed trait HtmlText                                extends Text
case class PreText(text: String)                     extends HtmlText
case class SuffText(text: String)                    extends HtmlText
case class PSText(preText: String, suffText: String) extends HtmlText
case object EmptyText                                extends HtmlText

trait Tag

sealed trait HtmlTag             extends Tag
case class TagText(text: String) extends HtmlTag
case object EmptyTag             extends HtmlTag

sealed trait AttributeTag         extends Tag
case class AttriPro(text: String) extends AttributeTag
