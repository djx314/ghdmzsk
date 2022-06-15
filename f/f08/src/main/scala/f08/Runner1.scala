package f08

import f07.CountSet

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.concurrent.{blocking, Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source
import scala.util.{Try, Using}

object Runner1 {

  val projectionDataRootPath = Paths.get(".", "f", "f08", "src", "main", "resources", "f08", "data")
  val projectionData         = projectionDataRootPath.resolve("projection_data.txt")

  def writeProjectionToFile: Future[Unit] = {
    var i: Int = 0

    def dataCol = for (setList <- NaturalProjection.setsCol) yield {
      def str = for {
        i1 <- 0 to 15
        i2 <- 0 to 15
      } yield {
        val r = setList.count(i1, i2).getOrElse("unlimited")
        s"$i1,$i2,$r"
      }

      i += 1
      println(s"计算了${i}个结果")

      Try(str.mkString("|")).map(t => (setList.key, t)).toOption
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

  def confirmAllExists: Future[List[CountSet]] = {
    def data = Using.resource(Source.fromResource("f08/data/projection_data.txt", classOf[Runner1.type].getClassLoader))(source =>
      source.getLines().to(List)
    )
    def values = data.map(_.split('=')(1))
    Future(
      blocking {
        val v = values
        CountSets.sum.filter(s => !v.exists(t => s.set == t))
      }
    )
  }

  def main(arr: Array[String]): Unit = {
    // Await.result(writeProjectionToFile, Duration.Inf)
    val a = Await.result(confirmAllExists, Duration.Inf)
    println(a.map(_.index))
    println(a.size)
  }

}
