package i01_01

object Counter {

  def count(number: Number1): String = number match {
    case Number1S(tail, head, text) =>
      if (text.isBlank) countChildren(head) else s"""<$text${countAttribute(tail)}>${countChildren(head)}</$text>"""
    case Number1T => ""
  }

  def countAttribute(number: Number1): String = number match {
    case Number1S(tail, head, text) =>
      if (text.trim == singleAttribute(head).trim) " " + text else " " + text + "=\"" + singleAttribute(head) + "\"" + countAttribute(tail)
    case Number1T => ""
  }

  def singleAttribute(number: Number2): String = number match {
    case Number2S(tail, _) => singleAttribute(tail)
    case Number2T(text)    => text
  }

  def countChildren(number: Number2): String = number match {
    case Number2S(tail, head) => countChildren(tail) + count(head)
    case Number2T(text)       => text
  }

}
