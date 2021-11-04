import sbt._
import sbt.Keys._

object Dependent {

  val catsEffectVersion = "3.2.9"
  val sttpVersion = "3.3.16"

  val catsEffect = Seq("org.typelevel" %% "cats-effect" % catsEffectVersion)
  val sttp = Seq("com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % sttpVersion)

}
