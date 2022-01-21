package f04

import f04.utils.RequestUtils
import f06.reverseroutes.ReverseRoutes
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window, HTMLInputElement}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.util.Failure

object CounterRunnerExecutionJavascript {

  val reverseUrl = new ReverseRoutes

  @JSExportTopLevel("counterRunnerExecutionPage")
  def initCountPlanReviewPage(): JQuery = jQ { () =>
    {
      val button         = document.getElementById("executeButton")
      val countNumberEle = document.getElementsByName("executeLineCount").collectFirst { case input: HTMLInputElement => input }

      button.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          val countOpt = countNumberEle.map(s => s.value).flatMap(_.toIntOption)
          countOpt match {
            case None        => window.alert("请填写数字")
            case Some(count) =>
            /*val request = RequestUtils.planJson(reverseUrl.countAllCountPlan)

        val action =
          for (data <- request)
            yield countPlanAllCount.innerText = s"${data.data}条"

        action.onComplete {
          case Failure(exception) =>
            window.alert("查询“计算计划”数量发生异常")
          case _ =>
        }*/
          }

        }
      )
    }
  }

}
