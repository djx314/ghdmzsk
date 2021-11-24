Settings.settings
name := "e01-02"

libraryDependencies ++= Dependent.catsEffect
libraryDependencies ++= Dependent.sttp
libraryDependencies ++= Dependent.zio

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
