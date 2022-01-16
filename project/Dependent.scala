import sbt._
import sbt.Keys._

object Dependent {

  object versions {
    val catsEffect = "3.3.4"
    val sttp       = "3.3.18"
    val zio        = "1.0.13"
    val zioLogging = "0.5.14"
    val tapir      = "0.20.0-M5"
    val distage    = "1.0.8"
    val http4s         = "0.23.7"
  }

  val catsEffect = Seq("org.typelevel" %% "cats-effect" % versions.catsEffect)
  val sttp       = Seq("com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % versions.sttp)

  val zio = Seq(
    "dev.zio" %% "zio-test"          % versions.zio % "test",
    "dev.zio" %% "zio-test-sbt"      % versions.zio % "test",
    "dev.zio" %% "zio-test-magnolia" % versions.zio % "test" // optional
  )

  val zioLogging = Seq("dev.zio" %% "zio-logging" % versions.zioLogging)

  val tapir = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-zio"                % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-zio-http4s-server"  % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-client"        % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui"         % versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-redoc"              % versions.tapir
  )

  val distage = Seq("io.7mind.izumi" %% "distage-core" % versions.distage)

  val http4s = Seq(
    "org.http4s" %% "http4s-dsl"          % versions.http4s,
    "org.http4s" %% "http4s-blaze-server" % versions.http4s,
    "org.http4s" %% "http4s-blaze-client" % versions.http4s,
    "org.http4s" %% "http4s-circe"        % versions.http4s
  )

}
