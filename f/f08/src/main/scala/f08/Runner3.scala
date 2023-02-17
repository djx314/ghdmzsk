package f08

import f07.SetColInstance

import java.io.PrintWriter
import java.nio.file.{Files, Path, Paths}
import scala.io.Source
import scala.util.Using

object Runner3 {

  def genSetColFile(writeFile: Path): Unit = {
    Using.resource(new PrintWriter(writeFile.toFile))(writer =>
      for (each <- SetColInstance.list) {
        writer.println(s"${each.key}=${each.genString(20)}")
      }
    )
  }

  def fromColSetFileToMapConfig(writeFile: Path): Map[Int, Map[(Int, Int), Option[Int]]] = for (
    (key, value) <- fromFileToMapConfig(writeFile)
  ) yield key.toInt -> value

  def fromMappingFileToMapConfig(writeFile: Path): Map[Int, String] = for ((key, value) <- fromMappingFileToMapConfigPre(writeFile))
    yield key.toInt -> value

  def fromMappingFileToMapConfigPre(writeFile: Path): Map[String, String] = Using.resource(Source.fromFile(writeFile.toFile)) { source =>
    val lines = source.getLines().to(List).filterNot(_.trim.isBlank)
    val col = for (eachLine <- lines) yield {
      eachLine.split('=').to(List) match {
        case List(i1, i2)     => i1 -> i2
        case List(i1, i2, i3) => i1 -> s"$i2=$i3"
      }
    }
    col.to(Map)
  }

  def fromFileToMapConfig(writeFile: Path): Map[String, Map[(Int, Int), Option[Int]]] = Using.resource(Source.fromFile(writeFile.toFile)) {
    source =>
      val lines = source.getLines().to(List).filterNot(_.trim.isBlank)
      val col = for (eachLine <- lines) yield {
        val List(key, value) = eachLine.split('=').to(List)
        val eachMap = for (eachResult <- value.split('|').to(List)) yield {
          val List(i1, i2, r) = eachResult.split(',').to(List)
          (i1.toInt -> i2.toInt) -> r.toIntOption
        }
        key -> eachMap.to(Map)
      }
      col.to(Map)
  }

  def fromFileToConfig(writeFile: Path): Map[String, PartialFunction[(Int, Int), Option[Int]]] = for (
    (key, value) <- fromFileToMapConfig(writeFile)
  ) yield {
    val parCol = for (eachValue <- value) yield {
      val ((i1, i2), r)                                 = eachValue
      val I1                                            = i1
      val I2                                            = i2
      val par: PartialFunction[(Int, Int), Option[Int]] = { case (I1, I2) => r }
      par
    }
    key -> parCol.reduce((a, b) => a.orElse(b))
  }

  def writeLeft(writeFile: Path, map: Map[String, PartialFunction[(Int, Int), Option[Int]]]): Unit = {
    val parCol = LazyList.from(map.values)
    val list = CountSets.sum.filterNot(eachCountSet => {
      val map1 = eachCountSet.toMap
      parCol.exists(eachPar => {
        map1.forall { case ((i1, i2), v) =>
          val b: Option[Option[Int]] = eachPar.andThen(Option(_)).applyOrElse(i1 -> i2, (_: (Int, Int)) => Option.empty)
          b == Some(v)
        }
      })
    })
    Using.resource(new PrintWriter(writeFile.toFile)) { writer =>
      for (eachSet <- list) {
        writer.println(s"${eachSet.index}=${eachSet.set}")
      }
    }
  }

  case class MapValue(fromKey: Int, confirmKey: String, reverse: Boolean)

  def confirm(
    from: Map[Int, Map[(Int, Int), Option[Int]]],
    confirm: Map[String, PartialFunction[(Int, Int), Option[Int]]],
    cond: (Int, Int) => Boolean
  ): List[MapValue] = {
    val lazyFrom    = LazyList.from(from.to(List))
    val lazyConfirm = LazyList.from(confirm.to(List))
    val data = for ((fromKey, value) <- lazyFrom) yield {
      val innerList = for ((confirmKey, v1) <- lazyConfirm) yield {
        def b1 =
          for (((i1, i2), v) <- value if cond(i1, i2)) yield v1.andThen(t => t == v).applyOrElse(i1 -> i2, (u: (Int, Int)) => false)
        def keep1  = b1.forall(identity)
        def value1 = Option(MapValue(fromKey = fromKey, confirmKey = confirmKey, reverse = false)).filter(_ => keep1)

        def b2 =
          for (((i1, i2), v) <- value if cond(i1, i2)) yield v1.andThen(t => t == v).applyOrElse(i2 -> i1, (u: (Int, Int)) => false)
        def keep2  = b2.forall(identity)
        def value2 = Option(MapValue(fromKey = fromKey, confirmKey = confirmKey, reverse = true)).filter(_ => keep2)

        value1.orElse(value2)
      }
      innerList.collectFirst { case Some(s) => s }
    }
    val keyOpt = data.collect { case Some(t) => t }
    keyOpt.to(List)
  }

  def writeToFile(map: List[MapValue], path: Path): Unit = {
    Using.resource(new PrintWriter(path.toFile)) { writer =>
      for (MapValue(intKey, value, reverse) <- map) {
        val reverseStr = if (reverse) "=reverse" else ""
        writer.println(s"$intKey=$value$reverseStr")
      }
    }
  }

  def main(arr: Array[String]): Unit = {
    val List(path1, path2, path3) = arr.to(List)
    val file1                     = Paths.get(path1)
    val file2                     = Paths.get(path2)
    val dir3                      = Paths.get(path3)
    Files.createDirectories(file1.getParent)
    Files.createDirectories(file2.getParent)
    Files.createDirectories(dir3)

    locally {
      if (false) {
        genSetColFile(file1)
      }
    }
    locally {
      if (false) {
        val map = fromFileToConfig(file1)
        writeLeft(file2, map)
      }
    }
    locally {
      if (false) {
        val config1     = fromColSetFileToMapConfig(file2)
        val config2     = fromFileToConfig(file1)
        val writeMap    = confirm(config1, config2, (i1, i2) => i1 == 0 && i2 == 0)
        val pathToWrite = dir3.resolve("i1 = 0 and i2 = 0.txt")
        writeToFile(writeMap, pathToWrite)
      }
    }
    locally {
      if (false) {
        val config1     = fromColSetFileToMapConfig(file2)
        val config2     = fromFileToConfig(file1)
        val writeMap    = confirm(config1, config2, (i1, i2) => i1 > 0 && i2 == 0)
        val pathToWrite = dir3.resolve("i1 gt 0 and i2 = 0.txt")
        writeToFile(writeMap, pathToWrite)
      }
    }
    locally {
      if (false) {
        val config1     = fromColSetFileToMapConfig(file2)
        val config2     = fromFileToConfig(file1)
        val writeMap    = confirm(config1, config2, (i1, i2) => i1 == 0 && i2 > 0)
        val pathToWrite = dir3.resolve("i1 = 0 and i2 gt 0.txt")
        writeToFile(writeMap, pathToWrite)
      }
    }
    locally {
      if (false) {
        val config1     = fromColSetFileToMapConfig(file2)
        val config2     = fromFileToConfig(file1)
        val writeMap    = confirm(config1, config2, (i1, i2) => i1 > 0 && i2 > 0 && i1 == i2)
        val pathToWrite = dir3.resolve("i1 gt 0 and i2 gt 0 and i1 = i2.txt")
        writeToFile(writeMap, pathToWrite)
      }
    }
    locally {
      if (false) {
        val config1     = fromColSetFileToMapConfig(file2)
        val config2     = fromFileToConfig(file1)
        val writeMap    = confirm(config1, config2, (i1, i2) => i1 > 0 && i2 > 0 && i1 > i2)
        val pathToWrite = dir3.resolve("i1 gt 0 and i2 gt 0 and i1 gt i2.txt")
        writeToFile(writeMap, pathToWrite)
      }
    }
    locally {
      if (false) {
        val config1     = fromColSetFileToMapConfig(file2)
        val config2     = fromFileToConfig(file1)
        val writeMap    = confirm(config1, config2, (i1, i2) => i1 > 0 && i2 > 0 && i1 < i2)
        val pathToWrite = dir3.resolve("i1 gt 0 and i2 gt 0 and i1 lt i2.txt")
        writeToFile(writeMap, pathToWrite)
      }
    }
    locally {
      if (false) {
        val notConfirmKeys = fromMappingFileToMapConfig(file2).keys.to(List)
        val map            = fromFileToConfig(file1)
        val preCount       = CountSets.sum.filterNot(u => notConfirmKeys.exists(n => n == u.index))
        val printlnData = for (eachCount <- preCount) yield {
          val key =
            map
              .find { case (a, b) => eachCount.toMap.forall(u => b.andThen(k => k == u._2).applyOrElse(u._1, (_: (Int, Int)) => false)) }
              .get
              ._1
          (eachCount.index, key)
        }
        var start          = 0
        def tagStr(u: Int) = if (start >= 100) s"Tags.Tag$u" else if (start >= 10) s"Tags.Tag0$u" else s"Tags.Tag00$u"
        for ((intKey, colKey) <- printlnData) {
          start += 1
          val key1 = tagStr(start)
          val key2 = s"Tags.$colKey"
          println(s"add(SimpleMapPlan(key = $key1, countSetKey = $intKey, setColKey = $key2))")
        }
      }
    }
    locally {
      if (false) {
        val notConfirmKeys = fromMappingFileToMapConfig(file2).keys.to(List)

        val map1 = fromMappingFileToMapConfig(dir3.resolve("i1 = 0 and i2 = 0.txt"))
        val map2 = fromMappingFileToMapConfig(dir3.resolve("i1 gt 0 and i2 = 0.txt"))
        val map3 = fromMappingFileToMapConfig(dir3.resolve("i1 = 0 and i2 gt 0.txt"))
        val map4 = fromMappingFileToMapConfig(dir3.resolve("i1 gt 0 and i2 gt 0 and i1 = i2.txt"))
        val map5 = fromMappingFileToMapConfig(dir3.resolve("i1 gt 0 and i2 gt 0 and i1 gt i2.txt"))
        val map6 = fromMappingFileToMapConfig(dir3.resolve("i1 gt 0 and i2 gt 0 and i1 lt i2.txt"))

        var start          = 419
        def tagStr(u: Int) = if (start >= 100) s"Tags.Tag$u" else if (start >= 10) s"Tags.Tag0$u" else s"Tags.Tag00$u"

        Using.resource(new PrintWriter(dir3.resolve("xxxx.txt").toFile)) { writer =>
          for (eachKey <- notConfirmKeys) {
            val str1   = s"`i1 = 0 and i2 = 0` = ${map1.get(eachKey).map(t => s"Option(\"$t\")").getOrElse("Option.empty")}"
            val str2   = s"`i1 gt 0 and i2 = 0` = ${map2.get(eachKey).map(t => s"Option(\"$t\")").getOrElse("Option.empty")}"
            val str3   = s"`i1 = 0 and i2 gt 0` = ${map3.get(eachKey).map(t => s"Option(\"$t\")").getOrElse("Option.empty")}"
            val str4   = s"`i1 gt 0 and i2 gt 0 and i1 = i2` = ${map4.get(eachKey).map(t => s"Option(\"$t\")").getOrElse("Option.empty")}"
            val str5   = s"`i1 gt 0 and i2 gt 0 and i1 gt i2` = ${map5.get(eachKey).map(t => s"Option(\"$t\")").getOrElse("Option.empty")}"
            val str6   = s"`i1 gt 0 and i2 gt 0 and i1 lt i2` = ${map6.get(eachKey).map(t => s"Option(\"$t\")").getOrElse("Option.empty")}"
            val strSeq = List(str1, str2, str3, str4, str5, str6)
            start += 1
            val key1     = tagStr(start)
            val printStr = s"add(MapPlan(key = $key1, countSetKey = $eachKey, ${strSeq.mkString(", ")}))"
            writer.println(printStr)
          }
        }

      }
    }
    locally {
      if (true) {
        var indexSet: Set[Int] = Set.empty
        val set2: Set[Int]     = CountSets.sum.map(_.index).to(Set)
        for (u <- ConfirmCol.cols) {
          val a = u.cofirm(CountSets.sum)
          assert(a)
          indexSet = indexSet + u.countSetKey
          // println(a)
        }
        println(indexSet)
        println(set2)
        println(indexSet == set2)
        println(CountSets.sum.size)
        println(indexSet.size)
        println(CountSets.sum.size == indexSet.size)
      }
    }
  }

}
