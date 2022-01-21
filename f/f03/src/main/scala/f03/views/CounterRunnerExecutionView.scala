package f03.views

import scalatags.Text.all._
import scalatags.Text.{attrs => attr, styles => css}

class CounterRunnerExecutionView(jsDependencies: JsDependencies) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("执行计算任务"),
      jsDependencies.jquery,
      jsDependencies.main(),
      script(`type` := "text/javascript")("""
          |(function() {
          |  counterRunnerExecutionPage();
          |})();
          |""".stripMargin)
    ),
    body(
      h2(textAlign.center)("执行计算任务"),
      div(
        table(marginLeft.auto, marginRight.auto)(
          tr(td("执行任务条数"), td(input(`type` := "text", name := "executeLineCount"))),
          tr(td(colspan := 2, textAlign.center)(input(id := "executeButton", `type` := "submit", value := "执行")))
        )
      )
    )
  )
}
