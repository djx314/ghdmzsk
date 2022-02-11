package f04

import f04.utils.RequestUtils
import f06.models.ResultSet
import f06.reverseroutes.ReverseRoutes
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window, HTMLInputElement}

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.util.{Failure, Success}

object CounterRunnerExecutionJavascript {

  val reverseUrl = new ReverseRoutes

  @JSExportTopLevel("counterRunnerExecutionPage")
  def initCountPlanReviewPage(): JQuery = jQ { () =>
    {
      val button                = document.getElementById("executeButton").asInstanceOf[HTMLInputElement]
      val countNumberEle        = document.getElementsByName("executeLineCount").collectFirst { case input: HTMLInputElement => input }
      val executeAutoButton     = document.getElementById("executeAuto").asInstanceOf[HTMLInputElement]
      val stopAutoExecuteButton = document.getElementById("stopAutoExecute").asInstanceOf[HTMLInputElement]
      val autoExecuteMessageDiv = document.getElementById("autoExecuteMessage")

      def touchAllStartButton(disable: Boolean) = {
        button.disabled = disable
        executeAutoButton.disabled = disable
      }

      {
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
                  touchAllStartButton(true)
                  val request =
                    RequestUtils.ajaxJson[ResultSet[Int]](
                      JQueryAjaxSettings(url = plan.url, method = plan.method, timeout = Option(1000000))
                    )

                  val action =
                    for (data <- request)
                      yield window.alert(data.message.getOrElse(s"任务成功"))

                  action.onComplete {
                    case Failure(exception) =>
                      window.alert("执行计算任务发生异常")
                      touchAllStartButton(false)
                    case _ =>
                      touchAllStartButton(false)
                  }
                } else window.alert("执行计划取消")
            }
          }
        )
      }

      {
        var stop = false
        def ele  = document.createElement("div")

        executeAutoButton.addEventListener(
          "click",
          { e: dom.MouseEvent =>
            val confirmValue = window.confirm("确认开始计算？")

            if (confirmValue) {
              touchAllStartButton(true)

              val startEle = ele
              startEle.innerText = "开始计算"
              autoExecuteMessageDiv.append(startEle)

              val plan = reverseUrl.counterExecutionPlan(200)
              def request =
                RequestUtils.ajaxJson[ResultSet[Int]](
                  JQueryAjaxSettings(url = plan.url, method = plan.method, timeout = Option(1000000))
                )

              def action: Future[String] =
                for {
                  data      <- request
                  appendEle <- Future(ele)
                  _         <- Future(appendEle.innerText = s"完成一组（最多 200 个）数据的计算，剩余项 ${data.data} 条")
                  _         <- Future(autoExecuteMessageDiv.append(appendEle))
                  next      <- if (stop) Future.successful("计算已停止") else if (data.data > 0) action else Future.successful("所有计算任务执行完毕")
                } yield next

              def reset(message: String) = {
                stop = false
                val finishEle = ele
                finishEle.innerText = message
                autoExecuteMessageDiv.append(finishEle)
                touchAllStartButton(false)
              }

              val executeAction = action
              executeAction.onComplete {
                case Success(value) =>
                  reset(value)
                case Failure(exception) =>
                  reset("计算过程发生异常，已停止")
              }
            } else window.alert("执行请求已取消。")
          }
        )

        stopAutoExecuteButton.addEventListener(
          "click",
          { e: dom.MouseEvent =>
            stop = true
          }
        )
      }
    }
  }

}
