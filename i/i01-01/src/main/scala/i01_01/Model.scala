package i01_01

object Model {

  trait Text
  case class TextContent(text: String) extends Text
  case object EmptyText                extends Text

  type HtmlNumber  = Number1[Text, Text]
  type ChildNumber = Number2[Text, Text]

}
