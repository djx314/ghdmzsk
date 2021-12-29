package i01_01

import scala.language.implicitConversions

object Builder {

  def tag(tagName: String): Number1 = Number1S(TagText(tagName), Number1T, Number2T(EmptyText))
  val html                          = tag("html")
  val body                          = tag("body")
  val div                           = tag("div")
  val span                          = tag("span")

  case class attr(attrName: String) {
    def :=[T](value: T)(implicit t: TranToString[T]): Number1 => Number1 = num =>
      num match {
        case Number1S(tag, attri, children) => Number1S(tag, attri.appendProperty(attrName, value), children)
      }
  }

  val width: attr     = attr("width")
  val height: attr    = attr("height")
  val style: attr     = attr("style")
  val highlight: attr = attr("highlight")
  val border: attr    = attr("border")
  val color: attr     = attr("color")

  case class css(pro: String) {
    def :=(value: String): Number1 => Number1 = num =>
      num match {
        case Number1S(tag, attri, children) => Number1S(tag, attri.appendCssProperty(pro, value), children)
      }
  }

  trait TranToString[T] {
    def t(n: T): String
  }

  implicit val tImplicit1: TranToString[Int] = new TranToString[Int] {
    override def t(n: Int): String = n.toString
  }
  implicit val tImplicit2: TranToString[String] = new TranToString[String] {
    override def t(n: String): String = n
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

  implicit val implicit3: ValueApply[attr] = new ValueApply[attr] {
    override def input(number: Number1, t: attr): Number1 = number match {
      case Number1S(text, attribute, children) => Number1S(text, Number1S(AttriPro(t.attrName), attribute, Number2T(EmptyAttri)), children)
    }
  }

  implicit val implicit4: ValueApply[String] = new ValueApply[String] {
    override def input(number: Number1, t: String): Number1 = number match {
      case Number1S(text, attribute, Number2T(EmptyText)) => Number1S(text, attribute, Number2T(TextContent(t)))
      case Number1S(text, attribute, Number2T(TextContent(s))) =>
        Number1S(text, attribute, Number2S(Number2T(TextContent(s)), Number1S(EmptyTag, Number1T, Number2T(TextContent(t)))))
      case Number1S(text, attribute, Number2S(tail, head)) =>
        Number1S(text, attribute, Number2S(Number2S(tail, head), Number1S(EmptyTag, Number1T, Number2T(TextContent(t)))))
    }
  }

  implicit val implicit5: ValueApply[Int] = new ValueApply[Int] {
    override def input(number: Number1, t: Int): Number1 = implicitly[ValueApply[String]].input(number, t.toString)
  }

  implicit def vImplicit1[T](n: T)(implicit valueApply: ValueApply[T]): Number1 => Number1 = num => valueApply.input(num, n)

  implicit class Number1Extendsion(val number: Number1) {
    def apply(value: Number1 => Number1*): Number1 = value.foldLeft(number)((s, t) => t(s))
  }

  implicit private[this] class Number1ExtendsionPrivate(val number: Number1) {
    def appendProperty[T](pro: String, value: T)(implicit v: TranToString[T]): Number1 =
      Number1S(AttriPro(pro), number, Number2T(AttriText(v.t(value))))
    def appendCssProperty[T](pro: String, value: T)(implicit v: TranToString[T]): Number1 =
      Number1S(EmptyAttrPro, number, Number2S(Number2T(EmptyAttri), Number1T.appendProperty(pro, value)))
  }

}
