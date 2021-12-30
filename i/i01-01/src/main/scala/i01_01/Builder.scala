package i01_01

import i01_01.Ast.{CommonProperty, CssProperty, EmtpyTag, HtmlTag, SingleProperty, TextHtmlTag}

import scala.language.implicitConversions

object Builder {

  def tag(tagName: String): Number1 = EmtpyTag(tagName)
  val html                          = tag("html")
  val body                          = tag("body")
  val div                           = tag("div")
  val span                          = tag("span")

  trait AttributeAppendable {
    def append(number: Number1): Number1
  }

  case class attr(attrName: String) {
    def :=[T](value: T)(implicit t: TranToString[T]): AttributeAppendable = num => CommonProperty(num, attrName, t.t(value))
  }

  val width: attr     = attr("width")
  val height: attr    = attr("height")
  val style: attr     = attr("style")
  val highlight: attr = attr("highlight")
  val border: attr    = attr("border")
  val color: attr     = attr("color")

  case class css(pro: String) {
    def :=(value: String): AttributeAppendable = num => CssProperty(num, pro, value)
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
  object ValueApply {
    def apply[T](implicit t: ValueApply[T]): ValueApply[T] = t
  }

  implicit val implicit1: ValueApply[AttributeAppendable] = new ValueApply[AttributeAppendable] {
    override def input(number: Number1, t: AttributeAppendable): Number1 = number match {
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute = t.append(attribute), children)
    }
  }

  implicit val implicit2: ValueApply[Number1] = new ValueApply[Number1] {
    override def input(number: Number1, t: Number1): Number1 = number match {
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute = attribute, Number2S(children, t))
    }
  }

  implicit val implicit3: ValueApply[attr] = new ValueApply[attr] {
    override def input(number: Number1, t: attr): Number1 = number match {
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute = SingleProperty(attribute, t.attrName), children)
    }
  }

  implicit val implicit4: ValueApply[String] = new ValueApply[String] {
    override def input(number: Number1, t: String): Number1 = number match {
      case EmtpyTag(tagName, attr)         => TextHtmlTag(tagName, attr, textContent = t)
      case TextHtmlTag(text, attribute, s) => TextHtmlTag(text, attribute, textContent = s + t)
      case HtmlTag(text, attribute, Number2S(tail, TextHtmlTag(EmptyTag, Number1T, s))) =>
        HtmlTag(text, attribute, Number2S(tail, TextHtmlTag(textContent = s + t)))
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute, Number2S(children, TextHtmlTag(t)))
    }
  }

  implicit val implicit5: ValueApply[Int] = new ValueApply[Int] {
    override def input(number: Number1, t: Int): Number1 = ValueApply[String].input(number, t.toString)
  }

  trait HtmlInputAbs {
    type Model
    val n: Model
    val valueApply: ValueApply[Model]
  }
  case class HtmlInput[T](override val n: T, override val valueApply: ValueApply[T]) extends HtmlInputAbs {
    override type Model = T
  }

  implicit def vImplicit1[T](n: T)(implicit valueApply: ValueApply[T]): HtmlInputAbs = HtmlInput(n = n, valueApply = valueApply)

  implicit class Number1Extendsion(val number: Number1) {
    def apply(value: HtmlInputAbs*): Number1 = value.foldLeft(number)((s, t) => t.valueApply.input(s, t.n))
  }

}
