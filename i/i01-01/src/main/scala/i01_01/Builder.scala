package i01_01

import scala.language.implicitConversions

object Builder {

  import Ast._
  import Model._

  def tag(tagName: String): HtmlNumber = HtmlTag.tag(tagName)
  val html                             = tag("html")
  val body                             = tag("body")
  val div                              = tag("div")
  val span                             = tag("span")

  trait AttributeAppendable {
    def append(number: HtmlNumber): HtmlNumber
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
    def input(number: HtmlNumber, t: T): HtmlNumber
  }
  object ValueApply {
    def apply[T](implicit t: ValueApply[T]): ValueApply[T] = t
  }

  implicit val implicit1: ValueApply[AttributeAppendable] = new ValueApply[AttributeAppendable] {
    override def input(number: HtmlNumber, t: AttributeAppendable): HtmlNumber = number match {
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute = t.append(attribute), children)
    }
  }

  implicit val implicit2: ValueApply[HtmlNumber] = new ValueApply[HtmlNumber] {
    override def input(number: HtmlNumber, t: HtmlNumber): HtmlNumber = number match {
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute = attribute, Number2S(children, t))
    }
  }

  implicit val implicit3: ValueApply[attr] = new ValueApply[attr] {
    override def input(number: HtmlNumber, t: attr): HtmlNumber = number match {
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute = SingleProperty(attribute, t.attrName), children)
    }
  }

  implicit val implicit4: ValueApply[String] = new ValueApply[String] {
    override def input(number: HtmlNumber, t: String): HtmlNumber = number match {
      case TextHtmlTag(text, attribute, None)    => TextHtmlTag(text, attribute, textContent = t)
      case TextHtmlTag(text, attribute, Some(s)) => TextHtmlTag(text, attribute, textContent = s + t)
      case HtmlTag(text, attribute, Number2S(tail, TextHtmlTag(EmptyText, Number1T(), Some(s)))) =>
        HtmlTag(text, attribute, Number2S(tail, TextHtmlTag(textContent = s + t)))
      case HtmlTag(text, attribute, children) => HtmlTag(text, attribute, Number2S(children, TextHtmlTag(t)))
    }
  }

  implicit val implicit5: ValueApply[Int] = new ValueApply[Int] {
    override def input(number: HtmlNumber, t: Int): HtmlNumber = ValueApply[String].input(number, t.toString)
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

  implicit class Number1Extendsion(val number: HtmlNumber) {
    def apply(value: HtmlInputAbs*): HtmlNumber = value.foldLeft(number)((s, t) => t.valueApply.input(s, t.n))
  }

}
