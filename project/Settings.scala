import sbt._
import sbt.Keys._
import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile

object Settings {

  val setting1 = scalaVersion      := "2.13.6"
  val setting2 = scalafmtOnCompile := true


  val setting3 = scalaVersion := "3.1.0"
  val setting4 = scalacOptions ++= Seq("-feature")

  val settings = Seq(setting1, setting2,setting4)
  val scala3Settings = Seq(setting3, setting2,setting4)

}
