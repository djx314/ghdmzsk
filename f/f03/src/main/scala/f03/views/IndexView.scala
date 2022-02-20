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
        div(paddingTop := 5, paddingBottom := 5)(button(id := "cleanAllPlanButton")("清空所有计算计划及计算结果")),
        div(paddingTop := 5, paddingBottom := 5)(button(id := "reInputPlanButton")("一次录入所有计算计划，不考虑重复，速度快")),
        div(paddingTop := 5, paddingBottom := 5)(button(id := "insertPlanButton")("增量录入所有计算计划"))
      )
    )
  )
}
