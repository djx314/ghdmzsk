package f03.views

import scalatags.Text.all._

class IndexView(jsDependencies: JsDependencies) {

  def view = "<!DOCTYPE html>" + html(
    head(
      jsDependencies.jquery,
      jsDependencies.main(),
      script(`type` := "text/javascript")("""
          |(function() {
          |  initIndexPage();
          |})();
          |""".stripMargin)
    ),
    body(
      h1(textAlign.center)("计算计划录入"),
      div(textAlign.center)(
        div(id := "cleanAllPlanButton")(paddingTop := 5, paddingBottom := 5)(button("清空所有计算计划")),
        div(id := "reInputPlanButton")(paddingTop := 5, paddingBottom := 5)(button("录入并覆盖原计算计划，删除不在新计算计划中的项"))
      )
    )
  )
}
