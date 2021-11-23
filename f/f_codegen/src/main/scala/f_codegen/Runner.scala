package f_codegen

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Using

object Runner extends App {

  def trimLines(i: String): String = Using.resource(Source.fromString(i)) { s =>
    val col = for (t <- s.getLines() if !t.isBlank) yield t
    col.mkString(System.lineSeparator)
  }

  val filePath  = Paths.get(args(0)).resolve(Paths.get("..", "f01", "src", "resources", "List"))
  val filePath2 = Paths.get(args(0)).resolve(Paths.get("..", "f02", "src", "resources", "f02"))

  val data = List("Number1S", "Number1T", "Number1U", "Number1V")

  for {
    name1 <- data
    name2 <- data
  } {
    Files.createDirectories(filePath)
    Using.resource(new PrintWriter(filePath.resolve(s"${name1}_${name2}_Top.scala").toFile, "utf-8")) { writer =>
      val content = trimLines(txt.f01(name1)(name2)(data).body)
      writer.println(content)
    }
  }

  for {
    name1 <- data
    name2 <- data
    name3 <- data
  } {
    val current = filePath2.resolve(name1)
    Files.createDirectories(current)
    Using.resource(new PrintWriter(current.resolve(s"${name1}_${name2}_${name3}_Top.scala").toFile, "utf-8")) { writer =>
      val content = trimLines(txt.f02(name1)(name2)(name3)(data).body)
      writer.println(content)
    }
  }

}
