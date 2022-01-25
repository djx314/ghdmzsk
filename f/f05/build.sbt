Settings.settings
name := "f05"

libraryDependencies ++= Dependent.slick
libraryDependencies ++= Dependent.mysql
libraryDependencies ++= Dependent.slf4j

enablePlugins(FlywayPlugin)

flywayUrl      := "jdbc:mysql://127.0.0.1:3306/numberdatabase"
flywayUser     := "root"
flywayPassword := "root"
flywayLocations += "db/migration"

val gen = inputKey[Unit]("slickCodegen")
gen := (Compile / run).partialInput(" f/f03").evaluated
