package i01_01

object Builder {

  def tag(tagName: String): Number1 = Number1S(TagText(tagName), Number1T, Number2T(EmptyText))
  val html                          = tag("html")
  val body                          = tag("body")
  val div                           = tag("div")
  val span                          = tag("span")

  def attr(attrName: String): Number1 => Number1 = num =>
    num match {
      case Number1S(tag, attri, children) => Number1S(tag, Number1S(AttriPro(attrName), attri, Number2T(EmptyAttri)), children)
    }

  val width: Number1 => Number1     = attr("width")
  val height: Number1 => Number1    = attr("height")
  val style: Number1 => Number1     = attr("style")
  val highlight: Number1 => Number1 = attr("highlight")
  val border: Number1 => Number1    = attr("border")
  val color: Number1 => Number1     = attr("color")

  trait TranToString[T] {
    def t(n: T): String
  }

  implicit val tImplicit1: TranToString[Int] = new TranToString[Int] {
    override def t(n: Int): String = n.toString
  }
  implicit val tImplicit2: TranToString[String] = new TranToString[String] {
    override def t(n: String): String = n
  }

  implicit class AttriProExtendsion(val attrPro: Number1 => Number1) {
    def :=[T](value: T)(implicit t: TranToString[T]): Number1 => Number1 = num =>
      attrPro(num) match {
        case Number1S(tag, Number1S(pro, num, Number2T(EmptyAttri)), children) =>
          Number1S(tag, Number1S(pro, num, Number2T(AttriText(t.t(value)))), children)
      }
  }

  trait ValueApply[T] {
    def input(number: Number1, t: T): Number1
  }

  implicit val implicit1: ValueApply[Number1 => Number1] = new ValueApply[Number1 => Number1] {
    override def input(number: Number1, t: Number1 => Number1): Number1 = number match {
      case Number1S(text, attribute, children) => Number1S(text, t(attribute), children)
    }
  }

  implicit val implicit2: ValueApply[Number1] = new ValueApply[Number1] {
    override def input(number: Number1, t: Number1): Number1 = number match {
      case Number1S(text, attribute, children) => Number1S(text, attribute, Number2S(children, t))
    }
  }

  implicit val implicit3: ValueApply[String] = new ValueApply[String] {
    override def input(number: Number1, t: String): Number1 = number match {
      case Number1S(text, attribute, Number2T(EmptyText)) => Number1S(text, attribute, Number2T(TextContent(t)))
      case Number1S(text, attribute, Number2T(TextContent(s))) =>
        Number1S(text, attribute, Number2S(Number2T(TextContent(s)), Number1S(EmptyTag, Number1T, Number2T(TextContent(t)))))
      case Number1S(text, attribute, Number2S(tail, head)) =>
        Number1S(text, attribute, Number2S(Number2S(tail, head), Number1S(EmptyTag, Number1T, Number2T(TextContent(t)))))
    }
  }

  implicit val implicit4: ValueApply[Int] = new ValueApply[Int] {
    override def input(number: Number1, t: Int): Number1 = implicitly[ValueApply[String]].input(number, t.toString)
  }

  implicit def implicit5[T](n: T)(implicit valueApply: ValueApply[T]): Number1 => Number1 = num => valueApply.input(num, n)

  implicit class Number1Extendsion(val number: Number1) {
    def apply(value: Number1 => Number1*): Number1 = value.foldLeft(number)((s, t) => t(s))
  }

}
