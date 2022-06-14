Settings.settings
name := "f04"

enablePlugins(ScalaJSPlugin, ScalaJSWeb)

// This is an application with a main method
// scalaJSUseMainModuleInitializer := true

libraryDependencies += "io.udash"     %%% "udash-jquery" % Dependent.versions.udashJQuery
libraryDependencies += "org.scala-js" %%% "scalajs-dom"  % Dependent.versions.scalajsDom
