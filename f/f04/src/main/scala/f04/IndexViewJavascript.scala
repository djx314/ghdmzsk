package f04

import f04.utils.RequestUtils
import f06.models.ResultSet
import f06.reverseroutes.ReverseRoutes

import scala.scalajs.js.annotation.JSExportTopLevel
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.Failure

object IndexViewJavascript {

  val reverseUrl = (new ReverseRoutes).reverseUrl

  @JSExportTopLevel("initIndexPage")
  def initIndexPage(): JQuery = jQ { () =>
    {
      val cleanAllPlanButton = document.getElementById("cleanAllPlanButton")
      cleanAllPlanButton.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          val plan = reverseUrl.deleteAllCountPlan
          val action =
            for (data <- RequestUtils.ajaxJson[ResultSet[Int]](JQueryAjaxSettings(url = plan.url, method = plan.method)))
              yield window.alert(s"删除了${data.data}条数据")
          action.onComplete {
            case Failure(exception) =>
              window.alert("删除“计算计划”数据发生异常")
            case _ =>
          }
        }
      )
    }

    {
      val reInputPlanButton = document.getElementById("reInputPlanButton")
      reInputPlanButton.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          val plan = reverseUrl.resetAllCountPlan
          val action =
            for (data <- RequestUtils.ajaxJson[ResultSet[Option[Int]]](JQueryAjaxSettings(url = plan.url, method = plan.method)))
              yield data.data match {
                case Some(count) => window.alert(s"重置了${count}条数据")
                case _           => window.alert(s"没有重置数据")
              }
          action.onComplete {
            case Failure(exception) =>
              window.alert("重置“计算计划”数据发生异常")
            case _ =>
          }
        }
      )
    }

  }

}
