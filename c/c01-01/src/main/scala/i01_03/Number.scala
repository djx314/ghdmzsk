package i01_03

trait Number1_1 {
  def method1(num2: Number2, deep: Int): Number3_2
}
case class Number1_1S(text: String, tail1: Number1_1, tail2: Number1_2) extends Number1_1 {
  override def method1(num2: Number2, deep: Int): Number3_2 = num2.method3(tail2, text, deep)
}

trait Number1_2 {
  def method2(num2: Number2, deep: Int): Number3_2
}
case class Number1_2S(tail2: Number1_2, tail1: Number1_1) extends Number1_2 {
  override def method2(num2: Number2, deep: Int): Number3_2 = Number3_2S(tail2.method2(num2, deep), tail1.method1(num2, deep))
}

// ====
trait Number2 {
  def method3(num1: Number1_2, text: String, deep: Int): Number3_2
}
case class Number2S(tail: () => Number2) extends Number2 {
  override def method3(num1: Number1_2, text: String, deep: Int): Number3_2 = num1.method2(tail(), deep + 1)
}
import Runner._
case class Number2T(tail: Number2) extends Number2 {
  override def method3(num1: Number1_2, text: String, deep: Int): Number3_2 = {
    val prefix = if (deep > 0) s"\n${textBlank(deep)}$xmlPrefix" else xmlPrefix
    Number3_2T(prefix, Number3_1S(xmlSuffix, tail.method3(num1, text, deep)))
  }
}
case class Number2U(tail: Number2) extends Number2 {
  override def method3(num1: Number1_2, text: String, deep: Int): Number3_2 =
    Number3_2T(text, Number3_1S(text, tail.method3(num1, text, deep)))
}
case class Number2V(tail: Number2) extends Number2 {
  override def method3(num1: Number1_2, text: String, deep: Int): Number3_2 = {
    val suffix = s"\n${textBlank(deep)}$xmlPrefix/"
    Number3_2T(xmlSuffix, Number3_1S(suffix, tail.method3(num1, text, deep)))
  }
}

// ====
trait Number3_1 {
  def text1: String
}
case class Number3_1S(text: String, tail: Number3_2) extends Number3_1 {
  def text1: String = tail.text2 + text
}

trait Number3_2 {
  def text2: String
}
case class Number3_2S(tail2_1: Number3_2, tail2_2: Number3_2) extends Number3_2 {
  def text2: String = tail2_1.text2 + tail2_2.text2
}
case class Number3_2T(text: String, tail: Number3_1) extends Number3_2 {
  def text2: String = text + tail.text1
}
case class Number3_2U(text: String) extends Number3_2 {
  def text2: String = text
}
