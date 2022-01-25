package f04

import f04.utils.RequestUtils
import f06.reverseroutes.ReverseRoutes
import io.udash.wrappers.jquery._
import org.scalajs.dom
import org.scalajs.dom.{document, window, HTMLInputElement}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.util.{Failure, Success}

object CodegenJavascript {

  val reverseUrl = new ReverseRoutes

  @JSExportTopLevel("initCodegen")
  def initCodegen(): JQuery = jQ { () =>
    val codegenButton = document.getElementById("codegenButton").asInstanceOf[HTMLInputElement]
    val codegenInfo   = document.getElementById("codegenInfo").asInstanceOf[HTMLInputElement]

    {
      codegenButton.addEventListener(
        "click",
        { e: dom.MouseEvent =>
          codegenInfo.innerText = "执行中"

          codegenButton.disabled = true

          val request = RequestUtils.planJson(reverseUrl.codegen)

          request.onComplete {
            case Failure(exception) =>
              window.alert("codegen 发生异常")
            case Success(value) =>
              codegenButton.innerText = value.message.getOrElse("执行完毕")
          }

          request.onComplete { case _ =>
            codegenButton.disabled = false
          }
        }
      )
    }

  }

}
