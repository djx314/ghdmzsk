package f04

import f06.reverseroutes.ReverseRoutes
import f04.utils.RequestUtils
import f06.models.PlanCountReview
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window, HTMLElement}

import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}

object CountPlanReviewJavaScript {

  val reverseUrl = new ReverseRoutes

  @JSExportTopLevel("initCountPlanReviewPage")
  def initCountPlanReviewPage(): JQuery = jQ { () =>
    {
      val button             = document.getElementById("reviewButton")
      val countPlanAllCount  = document.getElementById("countPlanAllCount")
      val finishedCountCount = document.getElementById("finishedCountCount")
      val waitForCountCount  = document.getElementById("waitForCountCount")
      val countSetCount      = document.getElementById("countSetCount")
      val countMessageEle    = document.getElementById("countMessage")
      val countInfoEle       = document.getElementById("countInfo")
      val reSortedCountSet   = document.getElementById("reSortedCountSet")
      def cleanText(showInfo: Boolean, message: Option[String] = Option.empty) = {
        message.foreach(s => countMessageEle.innerText = s)
        if (showInfo) {
          jQ(countMessageEle).hide()
          jQ(countInfoEle).show()
        } else {
          jQ(countMessageEle).show()
          jQ(countInfoEle).hide()
        }
      }
      def setData(planCountReview: PlanCountReview) = {
        countPlanAllCount.innerText = s"${planCountReview.countPlanAllCount}条"
        finishedCountCount.innerText = s"${planCountReview.finishedCountCount}条"
        waitForCountCount.innerText = s"${planCountReview.waitForCountCount}条"
        countSetCount.innerText = s"${planCountReview.countSetCount}条"
        reSortedCountSet.innerText = s"${planCountReview.reSortedCountSet}条"
      }

      button.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          cleanText(showInfo = false, message = Option("统计中"))
          val request = RequestUtils.planJson(reverseUrl.countAllCountPlan)

          request.onComplete {
            case Failure(exception) =>
              window.alert("查询“计算计划”数量发生异常")
            case Success(value) =>
              setData(value.data)
              cleanText(showInfo = true, message = Option.empty)
          }
        }
      )
    }
  }

}
