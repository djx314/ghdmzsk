package f08

import f07.{CountPlans, CountSet}
import f08.num._

import java.nio.file.Paths
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{blocking, Await, Future}
import scala.io.Source
import scala.util.Using

object Runner2 {

  def projectionSourceValues = Runner1.projectionSourceData.to(Map)
  def projectCountSetData    = Runner1.countSetData

  def confirmAllExists: Future[List[(Int, String)]] = {
    Future(
      blocking {
        val v = projectionSourceValues
        // println(v.filter(t => CountSets.sum.exists(u => u.set == t._2)).keys.to(List).map(_.drop(3).toInt).max)
        projectCountSetData.filter(s => !v.values.exists(t => s._2 == t))
      }
    )
  }

  def countNumberDifferent: Future[Int] = {
    def numCol = for {
      i1 <- 'a' to 's'
      i2 <- 'a' to 's'
    } yield ((i1, i2), tagNum(i1, i2))

    def tagNum(v1: Char, v2: Char): String = {
      var str                     = ""
      var needStop                = false
      var len: Int                = 80
      var countNum: () => Number1 = NumberGen.genNumber(v1, v2, 10)
      while (len > 0 && !needStop) {
        try {
          val next = countNum()
          next match {
            case Number1S =>
              str += "s"
              needStop = true
            case Number1T =>
              str += "t"
              needStop = true
            case Number1U =>
              str += "u"
              needStop = true
            case Number1V =>
              str += "v"
              needStop = true
            case Number1W =>
              str += "w"
              needStop = true
            case Number1X =>
              str += "x"
              needStop = true
            case Number1Y(tail) =>
              countNum = tail
              str += "y"
              len -= 1
            case Number1Z(tail) =>
              countNum = tail
              str += "z"
              len -= 1
            case Number1A(tail) =>
              countNum = tail
              str += "a"
              len -= 1
            case Number1B(tail) =>
              countNum = tail
              str += "b"
              len -= 1
          }
        } catch {
          case _: Throwable =>
            str += "e"
            needStop = true
        }
      }
      str
    }

    Future(blocking(numCol.groupBy(_._2).map(_._2.head).size))
  }

  def printlnCol(index: Int): Future[List[(Int, Int, Option[Int])]] = {
    def countSet = CountSets.sum.find(_.index == index).get
    def values = countSet.set.split('|').to(List).map { t =>
      val u = t.split(',').to(List)
      (u(0).toInt, u(1).toInt, u(2).toIntOption)
    }
    Future(
      blocking {
        values
      }
    )
  }

  def reverseCount: Future[List[(String, String)]] = {
    val countSetValues = projectCountSetData
    val values         = projectionSourceValues
    val keys           = values.to(List).filter(s => countSetValues.exists(_._2 == s._2)).map(_._1)

    println(keys)

    def countCollect = for {
      k1 <- keys
      k2 <- keys
    } yield {
      val set1 = NaturalProjection.getSet(k1)
      val set2 = NaturalProjection.getSet(k2)

      val col = (1 to 20).to(LazyList)

      def confirm1 = for {
        i1 <- col
        i2 <- col
      } yield {
        val r1 = for {
          n1 <- set1.count(i1, i2)
          u2 <- set2.count(n1, i1)
          u1 <- set2.count(n1, i2)
        } yield u2 == i2 && u1 == i1
        r1.getOrElse(false)
      }

      def confirm2 = for {
        i1 <- col
        i2 <- col
      } yield {
        val r1 = for {
          n1 <- set1.count(i1, i2)
          u2 <- set2.count(i1, n1)
          u1 <- set2.count(i2, n1)
        } yield u2 == i2 && u1 == i1
        r1.getOrElse(false)
      }

      (k1, k2, confirm1.forall(identity) || confirm2.forall(identity))
    }

    Future(blocking {
      countCollect.filter(_._3).map(s => (s._1, s._2))
    })
  }

  def main(arr: Array[String]): Unit = {
    {
      val a = Await.result(confirmAllExists, Duration.Inf)
      println(a.map(_._1))
      println(a.size)
      println(projectionSourceValues.size)
    }

    {
      /*val aa = Await.result(countNumberDifferent, Duration.Inf)
      println(aa)*/
    }

    {
      /*val aa = Await.result(printlnCol(675), Duration.Inf)
      aa.foreach(s => println(s"${s._1}, ${s._2} = ${s._3}"))*/
    }

    {
      /*val aa = Await.result(reverseCount, Duration.Inf)
      println(aa)*/
    }
  }

}
