package f04

import f04.utils.RequestUtils
import f06.models.{ResultSet, ReverseUrl}

import scala.scalajs.js.annotation.JSExportTopLevel
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

object IndexViewJavascript {

  val reverseUrl = RequestUtils.getJson[ResultSet[ReverseUrl]]("reverseUrl", null).map(_.data)

  @JSExportTopLevel("initIndexPage")
  def initIndexPage(): Unit = jQ { () =>
    val cleanAllPlanButton = document.getElementById("cleanAllPlanButton")
    cleanAllPlanButton.addEventListener(
      "click",
      { e: dom.MouseEvent =>
        window.alert(cleanAllPlanButton.textContent)
      }
    )

    val reInputPlanButton = document.getElementById("reInputPlanButton")
    reInputPlanButton.addEventListener(
      "click",
      { e: dom.MouseEvent =>
        window.alert(reInputPlanButton.textContent)
      }
    )

    reverseUrl.map(s => window.alert(s.deleteAllCountPlan))
  }

}
