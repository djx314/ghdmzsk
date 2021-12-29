package i01_01

object Runner2 {

  import Builder._

  val number = html(width := 60, height := 60)(
    body(style := "{ width: 60px; }")(
      div(highlight)("aa"),
      div("bb"),
      div(border := "0px", style := "{ width: 60px; }")("cc")(span(color := "66ccff")("dd"))("ee  ")("ff")
    )
  )

  def main(arr: Array[String]): Unit = {
    assert(Runner2.number == Runner.number)
    println(Counter.count(number))
    /*<html width="60" height="60">
      <body style="{ width: 60px; }">
        <div highlight>
          aa
        </div>
        <div>
          bb
        </div>
        <div border="0px" style="{ width: 60px; }">
          cc<span color="66ccff">dd</span>ee  ff
        </div>
      </body>
    </html>*/

  }

}
