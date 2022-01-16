Settings.settings
name := "f03"

javaOptions += "-Xss600k"
fork := true

libraryDependencies ++= Dependent.tapir
libraryDependencies ++= Dependent.zioLogging
libraryDependencies ++= Dependent.distage
libraryDependencies ++= Dependent.http4s
