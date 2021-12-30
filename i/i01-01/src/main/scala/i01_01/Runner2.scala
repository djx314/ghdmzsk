package i01_01

object Runner2 {

  import Builder._

  val number1 = html(width := 60, height := 60)(
    body(style := "{ width: 60px; }")(
      div(highlight)("aa"),
      div("bb"),
      div(border := "0px", style := "{ width: 60px; }")("cc")(span(color := "66ccff")("dd"))("ee  ")("ff")
    )
  )

  val cssWidth  = css("width")
  val cssHeight = css("height")

  val number2 = html(width := 60, height := 60)(
    body(cssWidth := "60px")(
      div(highlight)("aa"),
      div("bb"),
      div(border := "0px", style := "border: 1px", cssWidth := "60px", cssHeight := "60px")("cc")(span(color := "66ccff")("dd"))("ee  ")(
        "ff"
      )
    )
  )

  def main(arr: Array[String]): Unit = {
    assert(Counter.count(Runner2.number1) == Counter.count(Runner.number))
    println(Counter.count(number1))
    println(Counter.count(Runner.number))
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

    println(Counter.count(number2))
  }

}
