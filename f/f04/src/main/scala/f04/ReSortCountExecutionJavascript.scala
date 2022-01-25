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

object ReSortCountExecutionJavascript {

  val reverseUrl = new ReverseRoutes

  @JSExportTopLevel("initReSortCountExecution")
  def initReSortCountExecution(): JQuery = jQ { () =>
    val startSecondSort = document.getElementById("startSecondSortButton").asInstanceOf[HTMLInputElement]
    val executeInfoEle  = document.getElementById("executeInfo").asInstanceOf[HTMLInputElement]

    {
      startSecondSort.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          executeInfoEle.innerText = "执行中"

          startSecondSort.disabled = true

          val request = RequestUtils.planJson(reverseUrl.reSortCount)

          request.onComplete {
            case Failure(exception) =>
              window.alert("查询“计算计划”数量发生异常")
            case Success(value) =>
              executeInfoEle.innerText = value.message.getOrElse("执行完毕")
          }

          request.onComplete { case _ =>
            startSecondSort.disabled = false
          }
        }
      )
    }

  }

}
