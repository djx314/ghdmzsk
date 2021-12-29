package i01_01

object Runner {

  val number = Number1S(
    TagText("html"),
    Number1S(AttriPro("height"), Number1S(AttriPro("width"), Number1T, Number2T(AttriText("60"))), Number2T(AttriText("60"))),
    Number2S(
      Number2T(EmptyText),
      Number1S(
        TagText("body"),
        Number1S(AttriPro("style"), Number1T, Number2T(AttriText("{ width: 60px; }"))),
        Number2S(
          Number2S(
            Number2S(
              Number2T(EmptyText),
              Number1S(TagText("div"), Number1S(AttriPro("highlight"), Number1T, Number2T(EmptyAttri)), Number2T(PreText("aa")))
            ),
            Number1S(TagText("div"), Number1T, Number2T(PreText("bb")))
          ),
          Number1S(
            TagText("div"),
            Number1S(
              AttriPro("border"),
              Number1S(AttriPro("style"), Number1T, Number2T(AttriText("{ width: 60px; }"))),
              Number2T(AttriText("0px"))
            ),
            Number2S(
              Number2S(
                Number2T(PSText("cc", "ff")),
                Number1S(TagText("span"), Number1S(AttriPro("color"), Number1T, Number2T(AttriText("66ccff"))), Number2T(PreText("dd")))
              ),
              Number1S(EmptyTag, Number1T, Number2T(PreText("ee  ")))
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
