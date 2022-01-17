package f03.views

import f03.mainapp.AppConfig
import scalatags.Text.all._

import java.util.Date

class IndexView(appConfig: AppConfig) {
  def jsPath(version: String) = if (appConfig.isProd)
    s"/f03/$version/f04-fastopt/main.js"
  else
    s"/f03/$version/f04-fastopt/main.js?version=${new Date().getTime}"

  def view = "<!DOCTYPE html>" + html(
    head(
      script(src := jsPath("0.0.1"), `type` := "text/javascript")
    ),
    body(h1("sdfjsioerweiohriwejbtuierbtuierbteribterjitberjitb"))
  )
}
