package f03.views

import scalatags.Text.all._
import scalatags.Text.{attrs => attr, styles => css}

class ReSortCountExecutionPage(jsDependencies: JsDependencies) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("二次排序计算结果"),
      jsDependencies.jquery,
      jsDependencies.main(),
      script(`type` := "text/javascript")("""
          |(function() {
          |  initReSortCountExecution();
          |})();
          |""".stripMargin)
    ),
    body(
      h2(textAlign.center)("二次排序计算结果"),
      div(textAlign.center)(button(id := "startSecondSortButton")("启动二次排序")),
      div(textAlign.center, paddingTop := 10, paddingBottom := 10)(id := "executeInfo")("请启动计算")
    )
  )

}
