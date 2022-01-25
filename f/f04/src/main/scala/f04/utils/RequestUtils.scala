package f04.utils

import f06.models.RequestPlan
import io.circe.Decoder
import io.udash.wrappers.jquery._
import org.scalajs.dom.console

import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.JSON

object RequestUtils {

  def getJson[T: Decoder](url: String, data: js.Any): Future[T] = {
    val resultP = Promise[T]()
    jQ.getJSON[js.Any](
      url,
      null,
      { (data, status, xhr) =>
        val json = io.circe.parser.parse(JSON.stringify(data))
        json.flatMap(_.as[T]).fold(_ => resultP.tryFailure(new Exception("响应数据格式错误。")), s => resultP.trySuccess(s))
      }
    ).fail { (xhr, status, errorThrown) =>
      console.error(errorThrown)
      resultP.tryFailure(new Exception("请求发生异常。"))
    }
    resultP.future
  }

  def ajaxJson[T: Decoder](settings: JQueryAjaxSettings): Future[T] = {
    val setter = settings.asInstanceOf[js.Dynamic]
    setter.contentType = "application/json;charset=utf-8"
    setter.dataType = "json"
    val promise = Promise[T]()

    val success: js.Any => Unit = { data =>
      val json = io.circe.parser.parse(JSON.stringify(data))
      json.flatMap(_.as[T]).fold(_ => promise.tryFailure(new Exception("响应数据格式错误。")), s => promise.trySuccess(s))
    }
    val successDo: (js.Any, String, JQueryXHR) => js.Any = { (data, status, xhr) =>
      success(data)
    }

    val fail: (JQueryXHR, String, String) => Unit = { (xhr, textStatus, errorThrown) =>
      console.log(s"$textStatus, $errorThrown")
      promise.tryFailure(new Exception("请求发生异常。"))
    }
    val failDo: (JQueryXHR, String, String) => js.Any = { (xhr, textStatus, errorThrown) =>
      fail(xhr, textStatus, errorThrown)
    }

    setter.success = successDo
    setter.error = failDo
    val newSettings = setter.asInstanceOf[JQueryAjaxSettings]

    jQ.ajax(newSettings)

    promise.future
  }

  def planJson[ErrOut, Out: Decoder](plan: RequestPlan[ErrOut, Out]): Future[Out] = {
    ajaxJson[Out](JQueryAjaxSettings(url = plan.url, method = plan.method))
  }

}
