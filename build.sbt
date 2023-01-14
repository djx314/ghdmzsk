Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val a1Root   = rootFile / "a1"
val a2Root   = rootFile / "a2"
val bRoot    = rootFile / "b"
val bnRoot   = rootFile / "b1"
val cRoot    = rootFile / "c"
val cnRoot   = rootFile / "c1"
val dRoot    = rootFile / "d"
val eRoot    = rootFile / "e"
val fRoot    = rootFile / "f"
val gRoot    = rootFile / "g"
val hRoot    = rootFile / "h"
val iRoot    = rootFile / "i"
val jRoot    = rootFile / "j"

val a01 = project in aRoot / "a01"
val a02 = project in aRoot / "a02"
val a03 = project in aRoot / "a03"

val b01 = project in bRoot / "b01"
val b02 = project in bRoot / "b02"
val b03 = project in bRoot / "b03"
val b04 = project in bRoot / "b04"
val b05 = project in bRoot / "b05"

val an01 = project in a2Root / "a01"
val an02 = project in a2Root / "a02"
val an03 = project in a2Root / "a03"
val an04 = project in a2Root / "a04"

val a101_01 = project in a1Root / "a01-01"

val a102_01 = project in a1Root / "a02-01"
val a102_02 = project in a1Root / "a02-02"

val bn01    = project in bnRoot / "b01"
val bn03    = project in bnRoot / "b03"
val bn03_01 = project in bnRoot / "b03-01"

val cn01 = project in cnRoot / "c01"
val cn02 = project in cnRoot / "c02"

val d01 = project in dRoot / "d01"1
val d02 = project in dRoot / "d02"
val d03 = project in dRoot / "d03"

val e01    = project in eRoot / "e01"
val e01_01 = project in eRoot / "e01-01"
val e01_02 = project in eRoot / "e01-02"
val e02    = (project in eRoot / "e02").dependsOn(e01)
val e03    = (project in eRoot / "e03").dependsOn(e01)
val e04    = (project in eRoot / "e04").dependsOn(e01)
val e05    = (project in eRoot / "e05").dependsOn(e01)
val e06    = (project in eRoot / "e06").dependsOn(e01)

val f01      = (project in fRoot / "f01").dependsOn(b03)
val f02      = (project in fRoot / "f02").dependsOn(b01)
lazy val f03 = (project in fRoot / "f03").settings(scalaJSProjects := Seq(f04)).dependsOn(f06.jvm)
lazy val f04 = (project in fRoot / "f04").dependsOn(f06.js)
val f05      = project in fRoot / "f05"
lazy val f06 = crossProject(JSPlatform, JVMPlatform)
  .in(fRoot / "f06")
  .settings(
    Settings.settings,
    name    := "f06",
    version := "0.0.1"
  )
  .jvmSettings(libraryDependencies ++= Dependent.tapirSttp ++: Dependent.circe ++: Dependent.scalajsStubs)
  .jsSettings(
    libraryDependencies ++= Seq(
      "io.circe"                    %%% "circe-core"        % Dependent.versions.circe,
      "io.circe"                    %%% "circe-generic"     % Dependent.versions.circe,
      "io.circe"                    %%% "circe-parser"      % Dependent.versions.circe,
      "com.softwaremill.sttp.tapir" %%% "tapir-sttp-client" % Dependent.versions.tapir,
      "com.softwaremill.sttp.tapir" %%% "tapir-json-circe"  % Dependent.versions.tapir
    )
  )
val f07 = project in fRoot / "f07"
val f08 = project in fRoot / "f08"
val f09 = project in fRoot / "f09"
val f10 = project in fRoot / "f10"
val f11 = project in fRoot / "f11"
val f12 = project in fRoot / "f12"
val f13 = project in fRoot / "f13"

val f_codegen_path    = fRoot / "f_codegen"
val f_codegen_project = project in f_codegen_path

// addCommandAlias("f_codegen", s"f_codegen_project/runMain f_codegen.Runner ${f_codegen_path.getAbsolutePath}")

val f_codegen = inputKey[Unit]("f_codegen")
f_codegen := (f_codegen_project / Compile / runMain).partialInput(" f_codegen.Runner").partialInput(s" ${f_codegen_path.getAbsolutePath}")

val count = inputKey[Unit]("count")
f08 / count := {
  (f08 / Compile / runMain).partialInput(" f08.Runner1").evaluated
  (f08 / Compile / runMain).partialInput(" f08.Runner2").evaluated
}

val g01 = project in gRoot / "g01"
val g02 = project in gRoot / "g02"

val h01 = project in hRoot / "h01"
val h02 = project in hRoot / "h02"
val h03 = project in hRoot / "h03"

val c01    = project in cRoot / "c01"
val c01_01 = project in cRoot / "c01-01"
