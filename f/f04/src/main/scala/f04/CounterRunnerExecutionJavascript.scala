package f04

import f04.utils.RequestUtils
import f06.models.ResultSet
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
            case None => window.alert("请填写数字")
            case Some(count) =>
              val plan         = reverseUrl.counterExecutionPlan(count)
              val confirmValue = window.confirm(s"确认执行${count}条计算任务？")
              if (confirmValue) {
                val request =
                  RequestUtils.ajaxJson[ResultSet[Int]](JQueryAjaxSettings(url = plan.url, method = plan.method, timeout = Option(1000000)))

                val action =
                  for (data <- request)
                    yield window.alert(data.message.getOrElse("任务成功"))

                action.onComplete {
                  case Failure(exception) =>
                    window.alert("执行计算任务发生异常")
                  case _ =>
                }
              } else window.alert("执行计划取消")
          }
        }
      )
    }
  }

}
