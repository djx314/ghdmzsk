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
  println("11" + reverseUrl.countAllCountPlan.url)

  @JSExportTopLevel("initCountPlanReviewPage")
  def initCountPlanReviewPage() = jQ { () =>
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
          val action = for {
            rUrl <- GlobalValues.reverseUrl
            plan = rUrl.countAllCountPlan
            data <- RequestUtils.ajaxJson[ResultSet[Int]](JQueryAjaxSettings(url = plan.url, method = plan.method))
          } yield countPlanAllCount.innerText = s"${data.data}条"
          action.onComplete {
            case Failure(exception) =>
              window.alert("删除“计算计划”数据发生异常")
            case _ =>
          }
        }
      )
    }
  }

}
