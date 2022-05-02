package i01_03

object Runner {

  def textBlank(deep: Int): String = " " * (deep * 2)

  val xmlPrefix = "<"
  val xmlSuffix = ">"

  case class Number1_2T(text: String) extends Number1_2 {
    override def method2(num2: Number2, deep: Int): Number3_2 = Number3_2U(s"\n${textBlank(deep + 1)}$text")
  }
  case object Number1_2U extends Number1_2 {
    override def method2(num2: Number2, deep: Int): Number3_2 = Number3_2U("")
  }

  lazy val yaya1: Number2     = Number2T(Number2U(Number2V(yaya1Zero)))
  lazy val yaya1Zero: Number2 = Number2S(() => yaya1)

  val emptyText: Number1_2         = Number1_2U
  def text(str: String): Number1_2 = if (str.isEmpty) emptyText else Number1_2T(str)

  val num1 = Number1_1S(
    "html",
    Number1_2S(
      Number1_2S(
        emptyText,
        Number1_1S(
          "head",
          Number1_2S(Number1_2S(emptyText, Number1_1S("title", emptyText)), Number1_1S("meta", emptyText))
        )
      ),
      Number1_1S(
        "body",
        Number1_2S(Number1_2S(emptyText, Number1_1S("div", text("雷真"))), Number1_1S("div", text("夜夜")))
      )
    )
  )

  def main(arr: Array[String]): Unit = {
    println(num1.method1(yaya1, 0).text2)
  }

}
