package i01_01

object Counter {

  def count(number: Number1): String = number match {
    case Number1S(TagText(text), tail, head) =>
      s"""<$text${countAttribute(tail)}>${countChildren(head)("")}</$text>"""
    case Number1S(EmptyTag, tail, head) =>
      countChildren(head)("")
    case Number1T => ""
  }

  def countAttribute(number: Number1): String = number match {
    case Number1S(AttriPro(text), tail, Number2T(nText: AttributeText)) =>
      val pro = nText match {
        case AttriText(str) => text + "=\"" + str + "\""
        case EmptyAttri     => text
      }
      " " + pro + countAttribute(tail)
    case Number1T => ""
  }

  def countChildren(number: Number2): String => String = number match {
    case Number2S(tail, head) => s => countChildren(tail)(count(head) + s)
    case Number2T(text: HtmlText) =>
      text match {
        case PreText(str)              => s => str + s
        case SuffText(str)             => s => s + str
        case PSText(preText, suffText) => s => preText + s + suffText
        case EmptyText                 => identity
      }
  }

}
