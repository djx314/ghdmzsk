package i01_01

object Ast {

  object CommonProperty {
    def apply(tail: Number1, key: String, value: String): Number1 = Number1S(TagText(key), tail, Number2T(TextContent(value)))

    def unapply(number1: Number1): Option[(Number1, String, String)] = number1 match {
      case Number1S(TagText(key), tail, Number2T(TextContent(value))) => Option((tail, key, value))
      case _                                                          => Option.empty
    }
  }

  object SingleProperty {
    def apply(tail: Number1, key: String): Number1 = Number1S(TagText(key), tail, Number2T(EmptyText))

    def unapply(number1: Number1): Option[(Number1, String)] = number1 match {
      case Number1S(TagText(key), tail, Number2T(EmptyText)) => Option((tail, key))
      case _                                                 => Option.empty
    }
  }

  object CssProperty {
    def apply(tail: Number1, key: String, value: String): Number1 =
      Number1S(EmptyTag, tail, Number2S(Number2T(EmptyText), CommonProperty(Number1T, key, value)))

    def unapply(number1: Number1): Option[(Number1, String, String)] = {
      number1 match {
        case Number1S(EmptyTag, tail, Number2S(Number2T(EmptyText), CommonProperty(Number1T, key, value))) =>
          Option((tail, key, value))
        case _ => Option.empty
      }
    }
  }

  object HtmlTag {
    def apply(tag: Tag, attribute: Number1, children: Number2): Number1    = Number1S(tag, attribute, children)
    def apply(tag: String, attribute: Number1, children: Number2): Number1 = apply(TagText(tag), attribute, children)

    def unapply(number1: Number1): Option[(Tag, Number1, Number2)] = {
      number1 match {
        case Number1S(tag, attr, children) => Option((tag, attr, children))
        case _                             => Option.empty
      }
    }
  }

  object TextHtmlTag {
    def apply(tag: Tag, attribute: Number1, textContent: String): Number1    = Number1S(tag, attribute, Number2T(TextContent(textContent)))
    def apply(tag: String, attribute: Number1, textContent: String): Number1 = apply(TagText(tag), attribute, textContent)
    def apply(tag: Tag, textContent: String): Number1                        = Number1S(tag, Number1T, Number2T(TextContent(textContent)))
    def apply(tag: String, textContent: String): Number1                     = apply(TagText(tag), Number1T, textContent)
    def apply(textContent: String): Number1 = Number1S(EmptyTag, Number1T, Number2T(TextContent(textContent)))

    def unapply(number1: Number1): Option[(Tag, Number1, String)] = {
      number1 match {
        case Number1S(tag, attr, Number2T(TextContent(str))) => Option((tag, attr, str))
        case _                                               => Option.empty
      }
    }
  }

  object EmtpyTag {
    def apply(tag: String): Number1 = Number1S(TagText(tag), Number1T, Number2T(EmptyText))

    def unapply(number1: Number1): Option[(String, Number1)] = {
      number1 match {
        case Number1S(TagText(tag), attr, Number2T(EmptyText)) => Option((tag, attr))
        case _                                                 => Option.empty
      }
    }
  }

}
