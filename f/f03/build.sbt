Settings.settings
name := "f03"

reStart / javaOptions += "-Xss1024k"

libraryDependencies ++= Dependent.tapir
libraryDependencies ++= Dependent.zioLogging
libraryDependencies ++= Dependent.slf4j
libraryDependencies ++= Dependent.distage
libraryDependencies ++= Dependent.scalaTags
libraryDependencies ++= Dependent.jqueryJSSource
libraryDependencies ++= Dependent.slick
libraryDependencies ++= Dependent.circe
libraryDependencies ++= Dependent.zhttp
libraryDependencies ++= Dependent.mysql
libraryDependencies += Dependent.zioInteropReactivestreams

addCompilerPlugin(Dependent.kindProjector)
enablePlugins(SbtWeb)
Assets / pipelineStages := Seq(scalaJSPipeline)

Compile / compile := ((Compile / compile) dependsOn scalaJSPipeline).value

version := "0.0.1"

Compile / unmanagedSourceDirectories += sourceDirectory.value / "main" / "slick"
