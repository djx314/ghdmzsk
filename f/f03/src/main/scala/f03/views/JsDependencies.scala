package f03.views

import f03.mainapp.AppConfig
import scalatags.Text.all._

import java.util.Date

class JsDependencies(appConfig: AppConfig) {

  val jsPathToMain = "/f03/0.0.1/f04-fastopt/main.js"
  def jsPath()     = if (appConfig.isProd) jsPathToMain else s"$jsPathToMain?version=${new Date().getTime}"

  val jquery = script(src := "/jquery/3.6.0/jquery.min.js", `type` := "text/javascript")

  def main() = script(src := jsPath(), `type` := "text/javascript")

}
