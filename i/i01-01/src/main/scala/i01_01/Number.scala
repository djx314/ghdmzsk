package i01_01

trait Number1
case class Number1S(text: Tag, tail: Number1, head: Number2) extends Number1
case object Number1T                                         extends Number1

trait Number2
case class Number2S(tail: Number2, head: Number1) extends Number2
case class Number2T(text: Text)                   extends Number2

trait Text
case class TextContent(text: String) extends Text
case object EmptyText                extends Text

trait Tag
case class TagText(text: String) extends Tag
case object EmptyTag             extends Tag
