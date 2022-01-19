package f04

import f04.utils.RequestUtils
import f06.models.{ResultSet, ReverseUrl}

import scala.scalajs.js.annotation.JSExportTopLevel
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.Failure

object IndexViewJavascript {

  val reverseUrl = RequestUtils.getJson[ResultSet[ReverseUrl]]("reverseUrl", null).map(_.data)

  @JSExportTopLevel("initIndexPage")
  def initIndexPage(): Unit = jQ { () =>
    val cleanAllPlanButton = document.getElementById("cleanAllPlanButton")
    cleanAllPlanButton.addEventListener(
      "click",
      { e: dom.MouseEvent =>
        val action = for {
          rUrl      <- reverseUrl
          deleteUrl <- RequestUtils.ajaxJson[ResultSet[Int]](JQueryAjaxSettings(url = rUrl.deleteAllCountPlan, method = "delete"))
        } yield window.alert(s"删除了${deleteUrl.data}条数据")
        action.onComplete {
          case Failure(exception) =>
            window.alert("删除“计算计划”数据发生异常")
          case _ =>
        }
      }
    )

    val reInputPlanButton = document.getElementById("reInputPlanButton")
    reInputPlanButton.addEventListener(
      "click",
      { e: dom.MouseEvent =>
        window.alert(reInputPlanButton.textContent)
      }
    )

  }

}
