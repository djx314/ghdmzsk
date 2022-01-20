package f03.views

import f03.reverseroutes.ReverseRoutes
import scalatags.Text.all._
import scalatags.Text.{attrs => attr, styles => css}

class HelperView(reverseRoutes: ReverseRoutes) {
  import reverseRoutes._

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("页面导航")
    ),
    body(
      h2(textAlign.center)("页面导航"),
      p(textAlign.center)(
        a(href := Number.index.url, target := "_blank", css.color := "black")("首页")
      )
    )
  )
}
