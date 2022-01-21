package f04

import f06.reverseroutes.ReverseRoutes
import f04.utils.RequestUtils
import f06.models.ResultSet
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window}

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.Failure

object CountPlanReviewJavaScript {

  val reverseUrl = (new ReverseRoutes).reverseUrl

  @JSExportTopLevel("initCountPlanReviewPage")
  def initCountPlanReviewPage(): JQuery = jQ { () =>
    {
      val button            = document.getElementById("reviewButton")
      val countPlanAllCount = document.getElementById("countPlanAllCount")
      def cleanText() = {
        countPlanAllCount.innerText = ""
      }

      button.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          cleanText()
          val plan    = reverseUrl.countAllCountPlan
          val request = RequestUtils.ajaxJson[ResultSet[Int]](JQueryAjaxSettings(url = plan.url, method = plan.method))

          val action =
            for (data <- request)
              yield countPlanAllCount.innerText = s"${data.data}条"

          action.onComplete {
            case Failure(exception) =>
              window.alert("查询“计算计划”数量发生异常")
            case _ =>
          }
        }
      )
    }
  }

}
