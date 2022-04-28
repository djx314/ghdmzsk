package i01_01

object Ast {

  import Model._

  object CommonProperty {
    def apply(tail: HtmlNumber, key: String, value: String): HtmlNumber =
      Number1S(TextContent(key), tail, Number2T(TextContent(value)))

    def unapply(number1: HtmlNumber): Option[(HtmlNumber, String, String)] = number1 match {
      case Number1S(TextContent(key), tail, Number2T(TextContent(value))) => Option((tail, key, value))
      case _                                                              => Option.empty
    }
  }

  object SingleProperty {
    def apply(tail: HtmlNumber, key: String): HtmlNumber = Number1S(TextContent(key), tail, Number2T(EmptyText))

    def unapply(number1: HtmlNumber): Option[(HtmlNumber, String)] = number1 match {
      case Number1S(TextContent(key), tail, Number2T(EmptyText)) => Option((tail, key))
      case _                                                     => Option.empty
    }
  }

  object CssProperty {
    def apply(tail: HtmlNumber, key: String, value: String): HtmlNumber =
      Number1S(EmptyText, tail, Number2S(Number2T(EmptyText), CommonProperty(Number1T(), key, value)))

    def unapply(number1: HtmlNumber): Option[(HtmlNumber, String, String)] = {
      number1 match {
        case Number1S(EmptyText, tail, Number2S(Number2T(EmptyText), CommonProperty(Number1T(), key, value))) =>
          Option((tail, key, value))
        case _ => Option.empty
      }
    }
  }

  object HtmlTag {
    def apply(tag: Text, attribute: HtmlNumber, children: ChildNumber): HtmlNumber   = Number1S(tag, attribute, children)
    def apply(tag: String, attribute: HtmlNumber, children: ChildNumber): HtmlNumber = apply(TextContent(tag), attribute, children)
    def tag(tag: String): HtmlNumber = apply(TextContent(tag), Number1T(), Number2T(EmptyText))

    def unapply(number1: HtmlNumber): Option[(Text, HtmlNumber, ChildNumber)] = {
      number1 match {
        case Number1S(tag, attr, children) => Option((tag, attr, children))
        case _                             => Option.empty
      }
    }
  }

  object TextHtmlTag {
    def apply(tag: Text, attribute: HtmlNumber, textContent: String): HtmlNumber =
      Number1S(tag, attribute, Number2T(TextContent(textContent)))
    def apply(tag: String, attribute: HtmlNumber, textContent: String): HtmlNumber = apply(TextContent(tag), attribute, textContent)
    def apply(tag: Text, textContent: String): HtmlNumber   = Number1S(tag, Number1T(), Number2T(TextContent(textContent)))
    def apply(tag: String, textContent: String): HtmlNumber = apply(TextContent(tag), Number1T(), textContent)
    def apply(textContent: String): HtmlNumber              = Number1S(EmptyText, Number1T(), Number2T(TextContent(textContent)))

    def unapply(number1: HtmlNumber): Option[(Text, HtmlNumber, Option[String])] = {
      number1 match {
        case Number1S(tag, attr, Number2T(EmptyText))        => Option((tag, attr, Option.empty))
        case Number1S(tag, attr, Number2T(TextContent(str))) => Option((tag, attr, Option(str)))
        case _                                               => Option.empty
      }
    }
  }

  object BlankTag {
    def apply(content: ChildNumber): HtmlNumber = Number1S(EmptyText, Number1T(), content)

    def unapply(number1: HtmlNumber): Option[ChildNumber] = {
      number1 match {
        case Number1S(EmptyText, _, children) => Option(children)
        case _                                => Option.empty
      }
    }
  }

}
