package f04.utils

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

}
