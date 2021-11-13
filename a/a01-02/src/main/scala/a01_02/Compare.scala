package a01_02

import cats.effect._
import cats.implicits._
import sttp.client3.asynchttpclient.cats.AsyncHttpClientCatsBackend
import sttp.client3._
import sttp.model.Uri

object Base {

  private def request(uri: Uri) =
    Number1S(new NFlatMap[Request[Either[String, String], Any]] {
      override def f[T](t: Request[Either[String, String], Any] => IO[T]): IO[T] = IO(basicRequest.get(uri)).flatMap(t)
    })(request =>
      Number1S(new NFlatMap[SttpBackend[IO, Any]] {
        override def f[T](t: SttpBackend[IO, Any] => IO[T]): IO[T] = AsyncHttpClientCatsBackend.resource[IO]().use(t)
      })(backend =>
        Number1S(new NFlatMap[Response[Either[String, String]]] {
          override def f[T](t: Response[Either[String, String]] => IO[T]): IO[T] = request.send(backend).flatMap(t)
        })(result => Number1T(result.body.merge))
      )
    )

  def requestAction(uri: Uri): IO[String] = request(uri).method1(Number2T)

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
