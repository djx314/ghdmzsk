package i01_01

object Runner {

  import Ast._
  import Model._

  val number = HtmlTag(
    "html",
    CommonProperty(CommonProperty(Number1T(), "width", "60"), "height", "60"),
    Number2S(
      Number2T(EmptyText),
      HtmlTag(
        "body",
        CommonProperty(Number1T(), "style", "{ width: 60px; }"),
        Number2S(
          Number2S(
            Number2S(
              Number2T(EmptyText),
              TextHtmlTag(
                "div",
                SingleProperty(Number1T(), "highlight"),
                "aa"
              )
            ),
            TextHtmlTag("div", Number1T(), "bb")
          ),
          HtmlTag(
            "div",
            CommonProperty(
              CommonProperty(Number1T(), "style", "{ width: 60px; }"),
              "border",
              "0px"
            ),
            Number2S(
              Number2S(
                Number2S(
                  Number2T(TextContent("cc")),
                  TextHtmlTag(
                    "span",
                    CommonProperty(Number1T(), "color", "66ccff"),
                    "dd"
                  )
                ),
                TextHtmlTag(EmptyText, Number1T(), "ee  ")
              ),
              TextHtmlTag(EmptyText, Number1T(), "ff")
            )
          )
        )
      )
    )
  )

  def main(arr: Array[String]): Unit = {
    println(Counter.count(number))
// <html height="60" width="60"><body style="{ width: 60px; }"><div highlight>aa</div><div>bb</div><div border="0px" style="{ width: 60px; }">cc<span color="66ccff">dd</span>ee  ff</div></body></html>
  }

}
