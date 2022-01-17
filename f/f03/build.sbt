Settings.settings
name := "f03"

reStart / javaOptions += "-Xss600k"

libraryDependencies ++= Dependent.tapir
libraryDependencies ++= Dependent.zioLogging
libraryDependencies ++= Dependent.http4s
libraryDependencies ++= Dependent.catsEffect
libraryDependencies ++= Dependent.macwire
libraryDependencies ++= Dependent.slf4j
libraryDependencies ++= Dependent.distage
libraryDependencies ++= Dependent.scalaTags
libraryDependencies ++= Dependent.jqueryJSSource
libraryDependencies ++= Dependent.sqlite

addCompilerPlugin(Dependent.kindProjector)
enablePlugins(SbtWeb)
Assets / pipelineStages := Seq(scalaJSPipeline)

Compile / compile := ((Compile / compile) dependsOn scalaJSPipeline).value

version := "0.0.1"

enablePlugins(FlywayPlugin)

flywayUrl := "jdbc:sqlite:./f/f03/db/numberdatabase.db"
flywayLocations += "db/migration"