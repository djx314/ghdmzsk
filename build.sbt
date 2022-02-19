Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val bRoot    = rootFile / "b"
val cRoot    = rootFile / "c"
val dRoot    = rootFile / "d"
val eRoot    = rootFile / "e"
val fRoot    = rootFile / "f"
val gRoot    = rootFile / "g"
val hRoot    = rootFile / "h"
val iRoot    = rootFile / "i"
val jRoot    = rootFile / "j"

val a01    = project in aRoot / "a01"
val a01_01 = project in aRoot / "a01-01"

val a02    = project in aRoot / "a02"
val a02_01 = project in aRoot / "a02-01"
val a02_02 = project in aRoot / "a02-02"

val b01 = project in bRoot / "b01"
val b02 = project in bRoot / "b02"
val b03 = project in bRoot / "b03"
val b03_01 = project in bRoot / "b03-01"
val b04 = project in bRoot / "b04"

val c01 = project in cRoot / "c01"
val c02 = project in cRoot / "c02"

val d01 = project in dRoot / "d01"
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

val f01      = (project in fRoot / "f01").dependsOn(a02)
val f02      = (project in fRoot / "f02").dependsOn(b01, b02)
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

val f_codegen_path = fRoot / "f_codegen"
val f_codegen      = project in f_codegen_path

addCommandAlias("f_codegen", s"f_codegen/runMain f_codegen.Runner ${f_codegen_path.getAbsolutePath}")

val g01 = project in gRoot / "g01"
val g02 = project in gRoot / "g02"

val h01 = project in hRoot / "h01"

val i01    = project in iRoot / "i01"
val i01_01 = project in iRoot / "i01-01"
