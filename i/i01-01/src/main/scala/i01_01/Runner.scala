package i01_01

object Runner {

  val number = Number1S(
    Number1S(Number1S(Number1T, Number2T("60"), "width"), Number2T("60"), "height"),
    Number2S(
      Number2T(""),
      Number1S(
        Number1S(Number1T, Number2T("{ width: 60px; }"), "style"),
        Number2S(
          Number2S(
            Number2S(
              Number2T(""),
              Number1S(
                Number1S(Number1T, Number2T("highlight"), "highlight"),
                Number2T("aa"),
                "div"
              )
            ),
            Number1S(
              Number1T,
              Number2T("bb"),
              "div"
            )
          ),
          Number1S(
            Number1S(Number1S(Number1T, Number2T("{ width: 60px; }"), "style"), Number2T("0px"), "border"),
            Number2S(
              Number2S(
                Number2S(Number2T("cc"), Number1S(Number1S(Number1T, Number2T("66ccff"), "color"), Number2T("dd"), "span")),
                Number1S(Number1T, Number2T("ee  "), "")
              ),
              Number1S(Number1T, Number2T("ff"), "")
            ),
            "div"
          )
        ),
        "body"
      )
    ),
    "html"
  )

  def main(arr: Array[String]): Unit = {
    println(Counter.count(number))
// <html height="60" width="60"><body style="{ width: 60px; }"><div highlight>aa</div><div>bb</div><div border="0px" style="{ width: 60px; }">cc<span color="66ccff">dd</span>ee  ff</div></body></html>
  }

}
