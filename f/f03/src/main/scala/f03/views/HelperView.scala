package f03.views

import f06.reverseroutes.ReverseRoutes
import scalatags.Text.all._
import scalatags.Text.{attrs => attr, styles => css}

class HelperView(reverseRoutes: ReverseRoutes) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("页面导航")
    ),
    body(
      h2(textAlign.center)("页面导航"),
      p(textAlign.center)(
        a(href := reverseRoutes.index.url, target := "_blank", css.color := "black")("首页")
      ),
      p(textAlign.center)(
        a(href := reverseRoutes.countPlanReviewPage.url, target := "_blank", css.color := "black")("计算计划统计")
      ),
      p(textAlign.center)(
        a(href := reverseRoutes.counterPage.url, target := "_blank", css.color := "black")("执行计算任务")
      )
    )
  )
}
