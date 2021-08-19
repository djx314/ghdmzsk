import sbt._
import sbt.Keys._
import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile

object Settings {

  val setting1 = scalaVersion      := "2.13.6"
  val setting2 = scalafmtOnCompile := true

  val settings = Seq(setting1, setting2)

}
