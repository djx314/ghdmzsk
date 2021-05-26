import sbt._
import sbt.Keys._

object Settings {

  val setting1 = scalaVersion := "2.13.4"
  val setting2 = org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile := true

  val settings = Seq(setting1, setting2)

}
