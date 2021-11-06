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

val a01    = project in aRoot / "a01"
val a01_01 = project in aRoot / "a01-01"
val a01_02 = project in aRoot / "a01-02"

val a02    = project in aRoot / "a02"
val a02_01 = project in aRoot / "a02-01"

val b01 = project in bRoot / "b01"
val b02 = project in bRoot / "b02"
val b03 = project in bRoot / "b03"
val b04 = project in bRoot / "b04"

val c01 = project in cRoot / "c01"
val c02 = project in cRoot / "c02"

val d01 = project in dRoot / "d01"
val d02 = project in dRoot / "d02"
val d03 = project in dRoot / "d03"

val e01 = project in eRoot / "e01"
val e02 = (project in eRoot / "e02").dependsOn(e01)
val e03 = (project in eRoot / "e03").dependsOn(e01)
val e04 = (project in eRoot / "e04").dependsOn(e01)
val e05 = (project in eRoot / "e05").dependsOn(e01)

val f01 = (project in fRoot / "f01").dependsOn(a02)
val f02 = (project in fRoot / "f02").dependsOn(b01, b02)
val f03 = (project in fRoot / "f03").dependsOn(b03, b04)
val f04 = (project in fRoot / "f04").dependsOn(c01)
val f05 = (project in fRoot / "f05").dependsOn(c02)

val g01 = project in gRoot / "g01"
val g02 = project in gRoot / "g02"

val h01 = project in hRoot / "h01"
