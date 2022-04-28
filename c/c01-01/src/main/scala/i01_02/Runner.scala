package i01_02

object Runner {

  lazy val yaya1: Yaya1 = Yaya1T(() => yaya1)

  val num1 = Number1S(
    "html",
    Number1T,
    Number2S(
      Number2S(
        Number2T(""),
        Number1S(
          "head",
          Number1T,
          Number2S(Number2S(Number2T(""), Number1S("title", Number1T, Number2T(""))), Number1S("meta", Number1T, Number2T("")))
        )
      ),
      Number1S(
        "body",
        Number1T,
        Number2S(Number2S(Number2T(""), Number1S("div", Number1T, Number2T("雷真"))), Number1S("div", Number1T, Number2T("夜夜")))
      )
    )
  )

  def main(arr: Array[String]): Unit = {
    println(num1.method1(yaya1))
  }

}
