package f03.views

import scalatags.Text.all._
import scalatags.Text.{attrs => attr, styles => css}

class CountPlanReview(jsDependencies: JsDependencies) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("计算计划统计"),
      jsDependencies.jquery,
      jsDependencies.main(),
      script(`type` := "text/javascript")("""
          |(function() {
          |  initCountPlanReviewPage();
          |})();
          |""".stripMargin)
    ),
    body(
      h2(textAlign.center)("计算计划统计"),
      div(textAlign.center)(
        div(id := "reviewButton")(paddingTop := 5, paddingBottom := 5)(button("统计"))
      ),
      div(
        table(marginLeft.auto, marginRight.auto)(
          tr(td("计算计划数量"), td(span(id := "countPlanAllCount")))
        )
      )
    )
  )
}
