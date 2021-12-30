package i01_01

object Runner {

  import Ast._
  import Model._

  val number = HtmlTag(
    "html",
    CommonProperty(CommonProperty(HtmlNumberT, "width", "60"), "height", "60"),
    ChildNumberS(
      ChildNumberT(EmptyText),
      HtmlTag(
        "body",
        CommonProperty(HtmlNumberT, "style", "{ width: 60px; }"),
        ChildNumberS(
          ChildNumberS(
            ChildNumberS(
              ChildNumberT(EmptyText),
              TextHtmlTag(
                "div",
                SingleProperty(HtmlNumberT, "highlight"),
                "aa"
              )
            ),
            TextHtmlTag("div", HtmlNumberT, "bb")
          ),
          HtmlTag(
            "div",
            CommonProperty(
              CommonProperty(HtmlNumberT, "style", "{ width: 60px; }"),
              "border",
              "0px"
            ),
            ChildNumberS(
              ChildNumberS(
                ChildNumberS(
                  ChildNumberT(TextContent("cc")),
                  TextHtmlTag(
                    "span",
                    CommonProperty(HtmlNumberT, "color", "66ccff"),
                    "dd"
                  )
                ),
                TextHtmlTag(EmptyText, HtmlNumberT, "ee  ")
              ),
              TextHtmlTag(EmptyText, HtmlNumberT, "ff")
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
