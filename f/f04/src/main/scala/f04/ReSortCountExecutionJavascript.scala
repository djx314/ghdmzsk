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
          executeInfoEle.innerText = "执行完毕"
        }
      )
    }

  }

}
