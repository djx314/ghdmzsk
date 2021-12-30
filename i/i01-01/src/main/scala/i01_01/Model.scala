package i01_01

object Model {

  trait Text
  case class TextContent(text: String) extends Text
  case object EmptyText                extends Text

  type HtmlNumber = Number1[Text, Text]
  object HtmlNumberS {
    def apply(text: Text, tail: HtmlNumber, head: ChildNumber): HtmlNumber = Number1S(text, tail, head)
    def unapply(number1: Number1S[Text, Text]): Option[(Text, Number1[Text, Text], Number2[Text, Text])] = Option(
      (number1.text, number1.tail, number1.head)
    )
  }
  val HtmlNumberT: Number1[Text, Text] = Number1T()

  type ChildNumber = Number2[Text, Text]
  object ChildNumberS {
    def apply(tail: ChildNumber, head: HtmlNumber): ChildNumber                                    = Number2S(tail, head)
    def unapply(number2: Number2S[Text, Text]): Option[(Number2[Text, Text], Number1[Text, Text])] = Option((number2.tail, number2.head))
  }
  object ChildNumberT {
    def apply(text: Text): Number2T[Text, Text]              = Number2T(text)
    def unapply(number2: Number2T[Text, Text]): Option[Text] = Number2T.unapply(number2)
  }

}
