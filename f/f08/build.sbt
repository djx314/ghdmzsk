Settings.settings
name := "f08"

libraryDependencies += "org.scalax.ghdmzsk" %% "ghdmzsk-f07_01" % "0.0.1-SNAPSHOT"

Compile / unmanagedSourceDirectories += sourceDirectory.value / "main" / "codegen"
