import djx.sbt.depts.plugins.{PluginsCollection => pluginCol}

addSbtPlugin(pluginCol.`sbt-twirl`)
addSbtPlugin(pluginCol.`sbt-scalajs-crossproject`)
addSbtPlugin("io.github.davidmweber" % "flyway-sbt"               % "7.4.0")
addSbtPlugin("com.vmunier"           % "sbt-web-scalajs"          % "1.2.0")
addSbtPlugin(pluginCol.`sbt-scalajs`)
addSbtPlugin(pluginCol.`sbt-revolver`)
addSbtPlugin(pluginCol.`sbt-scalafmt`)
