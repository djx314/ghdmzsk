package f04

import scala.scalajs.js.annotation.JSExportTopLevel
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window}

object IndexViewJavascript {

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
  }

}
