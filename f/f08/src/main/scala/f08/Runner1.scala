package f08

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.concurrent.{blocking, Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Try, Using}

object Runner1 {

  val projectionDataRootPath = Paths.get(".", "f08", "src", "main", "codegen", "f08", "data")
  val projectionData         = projectionDataRootPath.resolve("projection_data.txt")

  def writeProjectionToFile: Future[Unit] = {
    def dataCol = for (setList <- NaturalProjection.setsCol) yield {
      def str = for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } yield {
        val r = setList.count(i1, i2).getOrElse("unlimited")
        s"$i1,$i2,$r"
      }
      Try(str.mkString("|")).map(s => (setList.key, s)).toOption
    }

    def dataMap: Map[String, String] = dataCol.collect { case Some(s) => s }.groupBy(_._2).map(s => (s._2.head._1, s._1))

    Future(
      blocking {
        Files.createDirectories(projectionDataRootPath)
        Using.resource(new PrintWriter(projectionData.toFile)) { writer =>
          dataMap.foreach { case (key, value) =>
            writer.println(s"$key=$value")
          }
          writer.flush()
        }
      }
    )
  }

  def main(arr: Array[String]): Unit = {
    // Await.result(writeProjectionToFile, Duration.Inf)
  }

}
