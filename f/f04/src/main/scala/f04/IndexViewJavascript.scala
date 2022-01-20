package f04

import f04.utils.RequestUtils
import f06.models.ResultSet

import scala.scalajs.js.annotation.JSExportTopLevel
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.Failure

object IndexViewJavascript {

  @JSExportTopLevel("initIndexPage")
  def initIndexPage(): Unit = jQ { () =>
    {
      val cleanAllPlanButton = document.getElementById("cleanAllPlanButton")
      cleanAllPlanButton.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          val action = for {
            rUrl <- GlobalValues.reverseUrl
            plan = rUrl.deleteAllCountPlan
            data <- RequestUtils.ajaxJson[ResultSet[Int]](JQueryAjaxSettings(url = plan.url, method = plan.method))
          } yield window.alert(s"删除了${data.data}条数据")
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
          val action = for {
            rUrl <- GlobalValues.reverseUrl
            plan = rUrl.resetAllCountPlan
            data <- RequestUtils.ajaxJson[ResultSet[Option[Int]]](JQueryAjaxSettings(url = plan.url, method = plan.method))
          } yield data.data match {
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
