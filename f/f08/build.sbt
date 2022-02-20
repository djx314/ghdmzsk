Settings.settings
name := "f08"

libraryDependencies += "org.scalax.ghdmzsk" %% "ghdmzsk-f07_01" % "0.0.1-SNAPSHOT"
libraryDependencies += Dependent.zioInteropReactivestreams
libraryDependencies ++= Dependent.zioLogging

Compile / unmanagedSourceDirectories += sourceDirectory.value / "main" / "codegen"
