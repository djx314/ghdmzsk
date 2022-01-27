package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Using

object Confirm {

  def confirm: List[(String, String)] = {
    val dataCol = for {
      mapping1 <- SetsCol.setsCol
    } yield {
      mapping1 match {
        case CommonSetsList(key, firstStart, secondStart, value) =>
          val list = for {
            i1 <- firstStart to 20
            i2 <- secondStart to 20
          } yield (i1, i2, Option(value(i1, i2)))
          (key, list)
        case OptSetsList(key, firstStart, secondStart, value) =>
          val list = for {
            i1 <- firstStart to 20
            i2 <- secondStart to 20
          } yield (i1, i2, value(i1, i2))
          (key, list)
      }
    }

    val dataCol1 = dataCol.filter(_._2.forall(_._3.isDefined)).map(s => (s._1, s._2.map(t => (t._1, t._2, t._3.get))))

    val l = for {
      (key1, data) <- dataCol1
      mapping2     <- SetsCol.setsCol
    } yield {
      val confirmList = for ((i1, i2, v) <- data) yield {
        val (key2, v2_1, v2_2) = mapping2 match {
          case CommonSetsList(key, firstStart, secondStart, value) =>
            (key, Option(value(v, i1)), Option(value(v, i2)))
          case OptSetsList(key, firstStart, secondStart, value) =>
            (key, value(v, i1), value(v, i2))
        }

        Option(i2) == v2_1 || Option(i1) == v2_2
      }

      if (confirmList.forall(identity)) Option((key1, mapping2.key)) else Option.empty
    }

    l.to(List).collect { case Some(s) => s }
  }

}
