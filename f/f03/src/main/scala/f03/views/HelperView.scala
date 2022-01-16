package f03.views

import scalatags.Text.all._

object HelperView {
  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("导航页")
    ),
    body("sss")
  )
}
