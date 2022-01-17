package f03.views

import f03.mainapp.AppConfig
import scalatags.Text.all._

import java.util.Date

class JsDependencies(appConfig: AppConfig) {

  def jsPath(version: String) = if (appConfig.isProd)
    s"/f03/$version/f04-fastopt/main.js"
  else
    s"/f03/$version/f04-fastopt/main.js?version=${new Date().getTime}"

  val jquery = script(src := "/jquery/3.6.0/jquery.min.js", `type` := "text/javascript")

  def main = script(src := jsPath("0.0.1"), `type` := "text/javascript")

}

class IndexView(jsDependencies: JsDependencies) {

  def view = "<!DOCTYPE html>" + html(
    head(
      jsDependencies.jquery,
      jsDependencies.main,
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
