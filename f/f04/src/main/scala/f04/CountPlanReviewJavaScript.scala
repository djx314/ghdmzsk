package f04

import io.udash.wrappers.jquery._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.JSExportTopLevel

object CountPlanReviewJavaScript {

  @JSExportTopLevel("initCountPlanReviewPage")
  def initCountPlanReviewPage() = jQ {
    document.getElementById("countPlanAllCount").innerText = "sfsjkarbewirbwbr"
  }

}
