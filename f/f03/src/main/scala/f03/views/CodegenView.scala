package f03.views

import scalatags.Text.all._

class CodegenView(jsDependencies: JsDependencies) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("代码生成"),
      jsDependencies.jquery,
      jsDependencies.main(),
      script(`type` := "text/javascript")("""
          |(function() {
          |  initCodegen();
          |})();
          |""".stripMargin)
    ),
    body(
      h2(textAlign.center)("代码生成"),
      div(textAlign.center)(
        div(id := "codegenButton")(paddingTop := 5, paddingBottom := 5)(button("生成"))
      ),
      div(textAlign.center)(id := "codegenInfo")(paddingTop := 5)("等待生成")
    )
  )

}
