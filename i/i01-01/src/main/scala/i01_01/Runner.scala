package i01_01

object Runner {

  import Ast._

  val number = Number1S(
    TextContent("html"),
    Number1S(TextContent("height"), Number1S(TextContent("width"), Number1T, Number2T(TextContent("60"))), Number2T(TextContent("60"))),
    Number2S(
      Number2T(EmptyText),
      Number1S(
        TextContent("body"),
        Number1S(TextContent("style"), Number1T, Number2T(TextContent("{ width: 60px; }"))),
        Number2S(
          Number2S(
            Number2S(
              Number2T(EmptyText),
              Number1S(TextContent("div"), Number1S(TextContent("highlight"), Number1T, Number2T(EmptyText)), Number2T(TextContent("aa")))
            ),
            Number1S(TextContent("div"), Number1T, Number2T(TextContent("bb")))
          ),
          Number1S(
            TextContent("div"),
            Number1S(
              TextContent("style"),
              Number1S(TextContent("border"), Number1T, Number2T(TextContent("0px"))),
              Number2T(TextContent("{ width: 60px; }"))
            ),
            Number2S(
              Number2S(
                Number2S(
                  Number2T(TextContent("cc")),
                  Number1S(
                    TextContent("span"),
                    Number1S(TextContent("color"), Number1T, Number2T(TextContent("66ccff"))),
                    Number2T(TextContent("dd"))
                  )
                ),
                Number1S(EmptyText, Number1T, Number2T(TextContent("ee  ")))
              ),
              Number1S(EmptyText, Number1T, Number2T(TextContent("ff")))
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
