package i01_01

object Ast {

  import Model._

  object CommonProperty {
    def apply(tail: HtmlNumber, key: String, value: String): HtmlNumber =
      HtmlNumberS(TextContent(key), tail, ChildNumberT(TextContent(value)))

    def unapply(number1: HtmlNumber): Option[(HtmlNumber, String, String)] = number1 match {
      case HtmlNumberS(TextContent(key), tail, ChildNumberT(TextContent(value))) => Option((tail, key, value))
      case _                                                                     => Option.empty
    }
  }

  object SingleProperty {
    def apply(tail: HtmlNumber, key: String): HtmlNumber = HtmlNumberS(TextContent(key), tail, ChildNumberT(EmptyText))

    def unapply(number1: HtmlNumber): Option[(HtmlNumber, String)] = number1 match {
      case HtmlNumberS(TextContent(key), tail, ChildNumberT(EmptyText)) => Option((tail, key))
      case _                                                            => Option.empty
    }
  }

  object CssProperty {
    def apply(tail: HtmlNumber, key: String, value: String): HtmlNumber =
      HtmlNumberS(EmptyText, tail, Number2S(ChildNumberT(EmptyText), CommonProperty(HtmlNumberT, key, value)))

    def unapply(number1: HtmlNumber): Option[(HtmlNumber, String, String)] = {
      number1 match {
        case HtmlNumberS(EmptyText, tail, Number2S(ChildNumberT(EmptyText), CommonProperty(HtmlNumberT, key, value))) =>
          Option((tail, key, value))
        case _ => Option.empty
      }
    }
  }

  object HtmlTag {
    def apply(tag: Text, attribute: HtmlNumber, children: ChildNumber): HtmlNumber   = HtmlNumberS(tag, attribute, children)
    def apply(tag: String, attribute: HtmlNumber, children: ChildNumber): HtmlNumber = apply(TextContent(tag), attribute, children)
    def tag(tag: String): HtmlNumber = apply(TextContent(tag), HtmlNumberT, ChildNumberT(EmptyText))

    def unapply(number1: HtmlNumber): Option[(Text, HtmlNumber, ChildNumber)] = {
      number1 match {
        case Number1S(tag, attr, children) => Option((tag, attr, children))
        case _                             => Option.empty
      }
    }
  }

  object TextHtmlTag {
    def apply(tag: Text, attribute: HtmlNumber, textContent: String): HtmlNumber =
      HtmlNumberS(tag, attribute, ChildNumberT(TextContent(textContent)))
    def apply(tag: String, attribute: HtmlNumber, textContent: String): HtmlNumber = apply(TextContent(tag), attribute, textContent)
    def apply(tag: Text, textContent: String): HtmlNumber   = HtmlNumberS(tag, HtmlNumberT, ChildNumberT(TextContent(textContent)))
    def apply(tag: String, textContent: String): HtmlNumber = apply(TextContent(tag), HtmlNumberT, textContent)
    def apply(textContent: String): HtmlNumber              = HtmlNumberS(EmptyText, HtmlNumberT, ChildNumberT(TextContent(textContent)))

    def unapply(number1: HtmlNumber): Option[(Text, HtmlNumber, Option[String])] = {
      number1 match {
        case HtmlNumberS(tag, attr, ChildNumberT(EmptyText))        => Option((tag, attr, Option.empty))
        case HtmlNumberS(tag, attr, ChildNumberT(TextContent(str))) => Option((tag, attr, Option(str)))
        case _                                                      => Option.empty
      }
    }
  }

  object BlankTag {
    def apply(content: ChildNumber): HtmlNumber = HtmlNumberS(EmptyText, HtmlNumberT, content)

    def unapply(number1: HtmlNumber): Option[ChildNumber] = {
      number1 match {
        case Number1S(EmptyText, _, children) => Option(children)
        case _                                => Option.empty
      }
    }
  }

}
