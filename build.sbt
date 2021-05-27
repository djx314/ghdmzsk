Settings.settings

val rootFile = file(".")
val aRoot    = rootFile / "a"
val bRoot    = rootFile / "b"

val a01 = project in aRoot / "a01"
val a02 = project in aRoot / "a02"
val a03 = project in aRoot / "a03"

val b01 = project in bRoot / "b01"
val b02 = project in bRoot / "b02"
