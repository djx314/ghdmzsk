Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val bRoot    = rootFile / "b"
val dRoot    = rootFile / "d"

val a01 = project in aRoot / "a01"
val a02 = project in aRoot / "a02"
val a03 = project in aRoot / "a03"

val b01 = project in bRoot / "b01"
val b02 = project in bRoot / "b02"
val b03 = project in bRoot / "b03"
val b04 = project in bRoot / "b04"
val b05 = project in bRoot / "b05"
val b06 = project in bRoot / "b06"
val b07 = project in bRoot / "b07"

val d03 = project in dRoot / "d03"
val d04 = project in dRoot / "d04"
