package a02_02

import cats.effect.IO
import sttp.client3.asynchttpclient.cats.AsyncHttpClientCatsBackend
import sttp.client3._
import sttp.model.Uri

object Base {

  private def request(uri: Uri) = Number1S(IO(basicRequest.get(uri)))(request =>
    Number1U(AsyncHttpClientCatsBackend.resource[IO]())(backend => Number1S(request.send(backend))(result => Number1T(result.body.merge)))
  )

  private lazy val number2: Number2 = Number2S(() => number2)

  def requestAction(uri: Uri): IO[String] = request(uri).method1(number2)

}

object Compare {

  def request(uri: Uri): IO[String] = {
    val resource = AsyncHttpClientCatsBackend.resource[IO]()
    for {
      request <- IO(basicRequest.get(uri))
      result  <- resource.use(backend => request.send(backend))
    } yield result.body.merge
  }

}
