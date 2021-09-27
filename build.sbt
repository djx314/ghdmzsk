Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val bRoot    = rootFile / "b"
val cRoot    = rootFile / "c"
val dRoot    = rootFile / "d"

val a01 = project in aRoot / "a01"
val a02 = project in aRoot / "a02"

val b01 = project in bRoot / "b01"
val b02 = project in bRoot / "b02"
val b03 = project in bRoot / "b03"
val b04 = project in bRoot / "b04"
val b05 = project in bRoot / "b05"

val c01 = project in cRoot / "c01"
val c02 = project in cRoot / "c02"
val c03 = project in cRoot / "c03"

val d01 = project in dRoot / "d01"
val d02 = (project in dRoot / "d02").dependsOn(d01)
val d03 = (project in dRoot / "d03").dependsOn(d01)
val d04 = (project in dRoot / "d04").dependsOn(d01)
val d05 = (project in dRoot / "d05").dependsOn(d01)
