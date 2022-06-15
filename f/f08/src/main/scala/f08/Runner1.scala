package f08

import f07.CountPlans
import f08.num.NumberGen

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
  val countSetDataPath       = projectionDataRootPath.resolve("countSet_data.txt")

  def projectionSourceData =
    Using.resource(Source.fromResource("f08/data/projection_data.txt", classOf[Runner1.type].getClassLoader))(source =>
      source.getLines().to(List).map(_.trim).filter(s => !s.isBlank).map { s =>
        val t = s.split('=')
        (t(0), t(1))
      }
    )

  def countSetData =
    Using.resource(Source.fromResource("f08/data/countSet_data.txt", classOf[Runner1.type].getClassLoader))(source =>
      source.getLines().to(List).map(_.trim).filter(s => !s.isBlank).map { s =>
        val t = s.split('=')
        (t(0).toInt, t(1))
      }
    )

  def writeNeedCountSets: Future[Unit] = {
    def names1 = for {
      i1 <- NumberGen.partGen1.map(_._1)
      i2 <- NumberGen.partGen2.map(_._1)
    } yield (i1, i2)

    def names2 = for {
      i1 <- NumberGen.partGen2.map(_._1)
      i2 <- NumberGen.partGen1.map(_._1)
    } yield (i1, i2)

    def names3 = names1 ::: names2

    def sumNames = {
      val names4 = names3
      for {
        (i1, i2) <- names4
        (i3, i4) <- names4
      } yield (i1, i2, i3, i4)
    }

    def sumNameString = for ((i1, i2, i3, i4) <- sumNames) yield s"${i1.toString}${i2.toString}${i3.toString}${i4.toString}"

    def countSetsLeaf = {
      val subStr = sumNameString
      LazyList.from(CountPlans.sum).filter(s => subStr.exists(t => t == s.planInfo)).map(_.set).to(Set)
    }

    Future(
      blocking {
        Files.createDirectories(projectionDataRootPath)
        Using.resource(new PrintWriter(countSetDataPath.toFile)) { writer =>
          val l = countSetsLeaf
          println(l.size)
          l.foreach { set =>
            writer.println(s"${set.index}=${set.set}")
          }
          writer.flush()
        }
      }
    )
  }

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
      // println(s"计算了${i}个结果")

      Try(str.mkString("|")).map(t => (setList.key, t)).toOption
    }

    def dataMap(countSets: List[(Int, String)]): Vector[(String, String)] =
      dataCol
        .collect { case Some(s) => s }
        .filter(t => countSets.exists(u => u._2 == t._2))
        .groupBy(_._2)
        .map(s => (s._2.map(_._1).min, s._1))
        .to(Vector)

    Future(
      blocking {
        Files.createDirectories(projectionDataRootPath)
        Using.resource(new PrintWriter(projectionData.toFile)) { writer =>
          dataMap(countSetData).foreach { case (key, value) =>
            writer.println(s"$key=$value")
          }
          writer.flush()
        }
      }
    )
  }

  def main(arr: Array[String]): Unit = {
    {
      // Await.result(writeNeedCountSets, Duration.Inf)
    }

    {
      Await.result(writeProjectionToFile, Duration.Inf)
    }
  }

}
