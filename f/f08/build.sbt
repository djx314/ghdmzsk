Settings.settings
name := "f08"

libraryDependencies += "org.scalax.ghdmzsk" %% "ghdmzsk-f07_01" % "0.0.3"
libraryDependencies += Dependent.zioInteropReactivestreams
libraryDependencies ++= Dependent.zioLogging

Compile / unmanagedSourceDirectories += sourceDirectory.value / "main" / "codegen"

val r1        = inputKey[Unit]("Execute Run.")
val inputStr1 = settingKey[String]("Input str")
val inputStr2 = settingKey[String]("Input str")
val inputStr3 = settingKey[String]("Input str")

inputStr1 := {
  val file = sourceDirectory.value / "main" / "resources" / "f08" / "data" / "sets_col_data.txt"
  file.getCanonicalPath
}

inputStr2 := {
  val file = sourceDirectory.value / "main" / "resources" / "f08" / "data" / "left_not_confirm_data.txt"
  file.getCanonicalPath
}

inputStr3 := {
  val file = sourceDirectory.value / "main" / "resources" / "f08" / "data" / "temp"
  file.getCanonicalPath
}

r1 := {
  (Compile / runMain).inputTaskValue
    .partialInput(s" f08.Runner3")
    .partialInput(s" ${inputStr1.value}")
    .partialInput(s" ${inputStr2.value}")
    .partialInput(s" ${inputStr3.value}")
    .evaluated
}
