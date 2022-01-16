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

addCompilerPlugin(Dependent.kindProjector)
