package i01_02

object Runner {

  lazy val yaya1: Number2     = Number2S(yaya1Zero)
  lazy val yaya1Zero: Number2 = Number2T(() => yaya1)

  val emptyText: Number1_2         = Number1_2T("")
  def text(str: String): Number1_2 = if (str.isEmpty) emptyText else Number1_2T(s"\n$str")

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
    println(num1.method1(yaya1).text2)
  }

}
