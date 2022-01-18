import sbt.Def.spaceDelimited

Settings.settings
name := "f05"

libraryDependencies ++= Dependent.slick
libraryDependencies ++= Dependent.sqlite


enablePlugins(FlywayPlugin)

flywayUrl := "jdbc:sqlite:./f/f03/db/numberdatabase.db"
flywayLocations += "db/migration"

val gen = inputKey[Unit]("slickCodegen")
gen := (Compile / run).partialInput(" f/f03").evaluated


