Settings.settings
name         := "ghdmzsk-f07_01"
organization := "org.scalax.ghdmzsk"
version      := "0.0.3"

Compile / packageDoc / publishArtifact := false

Compile / unmanagedSourceDirectories += sourceDirectory.value / "main" / "codegen"
