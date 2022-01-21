package f03.views

import f06.models.ReverseUrl
import scalatags.Text.all._
import scalatags.Text.{attrs => attr, styles => css}

class HelperView(reverseUrl: ReverseUrl) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("页面导航")
    ),
    body(
      h2(textAlign.center)("页面导航"),
      p(textAlign.center)(
        a(href := reverseUrl.index.url, target := "_blank", css.color := "black")("首页")
      ),
      p(textAlign.center)(
        a(href := reverseUrl.countPlanReviewPage.url, target := "_blank", css.color := "black")("计算计划统计")
      )
    )
  )
}
