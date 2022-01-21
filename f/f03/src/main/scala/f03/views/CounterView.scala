package f03.views

import scalatags.Text.all._

class CounterView(jsDependencies: JsDependencies) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("执行计算任务"),
      jsDependencies.jquery,
      jsDependencies.main(),
      script(`type` := "text/javascript")("""
          |(function() {
          |  initCountPlanReviewPage();
          |})();
          |""".stripMargin)
    ),
    body(
      h2(textAlign.center)("执行计算任务"),
      div(textAlign.center)(
        div(id := "reviewButton")(paddingTop := 5, paddingBottom := 5)(button("执行"))
      ),
      div(
        table(marginLeft.auto, marginRight.auto)(
          tr(td("执行计算任务"), td(span(id := "countPlanAllCount")))
        )
      )
    )
  )
}
