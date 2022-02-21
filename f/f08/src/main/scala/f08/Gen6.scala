package f08

import f07.{CountSet, CountSets, Runner}
import zio._
import zio.stream._

object Gen6 {

  def printlnSingleResult(): Unit = {
    var notConfirm: Vector[Int] = Vector.empty

    case class SetsColMapping(optStr: String, noneOptString: String, isOpt: Boolean, mapping: (Int, Int) => Option[Int])
    case class ParMapping(par: (Int, Int) => Boolean, var mapping: Vector[SetsColMapping], tiaojian: String)

    val sets = Runner.col.map(s => (s._1, s._2.mkString("|")))
    val leftSets: List[CountSet] =
      CountSets.sum.filter(s => sets.forall(t => t._2 != s.set)).filterNot(t => notConfirm.exists(r => t.index == r))

    def mapping(firstStart: Int, secondStart: Int) = {
      val noneMapping = SetsColMapping("Option.empty", "Option.empty", isOpt = true, (i1: Int, i2: Int) => Option.empty)

      val l1_pre = for (a <- (0 to 7).to(Vector)) yield SetsColMapping(s"Option($a)", s"$a", false, (i1: Int, i2: Int) => Option(a))

      val l1   = noneMapping +: l1_pre
      val par1 = ParMapping((i1, i2) => i1 == firstStart && i2 == secondStart, l1, s"i1 == $firstStart && i2 == $secondStart")

      val l2_pre = for {
        a <- (-3 to 3).to(Vector)
        b <- (-7 to 7).to(Vector)
        c <- (-3 to 3).to(Vector).filter(s => s != 0)
      } yield SetsColMapping(s"Option(i2 * $a / $c + $b)", s"i2 * $a / $c + $b", false, (i1: Int, i2: Int) => Option(i2 * a / c + b))

      val l2   = noneMapping +: l2_pre
      val par2 = ParMapping((i1, i2) => i1 == firstStart, l2, s"i1 == $firstStart")

      val l2_3_Pre = for {
        a <- (-3 to 3).to(Vector)
        b <- (-3 to 3).to(Vector)
        c <- (-7 to 7).to(Vector)
      } yield SetsColMapping(
        s"Option($a * i1 + $b * i2 + $c)",
        s"$a * i1 + $b * i2 + $c",
        false,
        (i1: Int, i2: Int) => Option(a * i1 + b * i2 + c)
      )

      val l2_2   = noneMapping +: l2_3_Pre
      val par2_2 = ParMapping((i1, i2) => i1 == firstStart + 1, l2_2, s"i1 == ${firstStart + 1}")

      val l3_pre = for {
        a <- (-3 to 3).to(Vector)
        b <- (-7 to 7).to(Vector)
        c <- (-3 to 3).to(Vector).filter(s => s != 0)
      } yield SetsColMapping(s"Option(i1 * $a / $c + $b)", s"i1 * $a / $c + $b", false, (i1: Int, i2: Int) => Option(i1 * a / c + b))

      val l3   = noneMapping +: l3_pre
      val par3 = ParMapping((i1, i2) => i2 == secondStart, l3, s"i2 == $secondStart")

      val par3_2 = ParMapping((i1, i2) => i2 == secondStart + 1, l2_2, s"i2 == ${secondStart + 1}")

      val l4_pre = for {
        a <- (-3 to 3).to(Vector)
        b <- (-3 to 3).to(Vector)
        d <- (-3 to 3).to(Vector)
        e <- (-3 to 3).to(Vector).filter(s => s != 0)
        c <- (-7 to 7).to(Vector)
      } yield Vector(
        SetsColMapping(
          s"Option($a * i1 + $b * i2 + i2 / i1 * $d / $e + $c)",
          s"$a * i1 + $b * i2 + i2 / i1 * $d / $e + $c",
          false,
          (i1: Int, i2: Int) => Option(a * i1 + b * i2 + i2 / i1 * d / e + c)
        ),
        SetsColMapping(
          s"Option($a * i1 + $b * i2 + i1 / i2 * $d / $e + $c)",
          s"$a * i1 + $b * i2 + i1 / i2 * $d / $e + $c",
          false,
          (i1: Int, i2: Int) => Option(a * i1 + b * i2 + i1 / i2 * d / e + c)
        ),
        SetsColMapping(
          s"Option($a * i1 + $b * i2 + i1 * i2 * $d / $e + $c)",
          s"$a * i1 + $b * i2 + i1 * i2 * $d / $e + $c",
          false,
          (i1: Int, i2: Int) => Option(a * i1 + b * i2 + i1 * i2 * d / e + c)
        ),
        SetsColMapping(
          s"Option($a * i1 + $b * i2 + i1 /(i2 + 1) * $d / $e + $c)",
          s"$a * i1 + $b * i2 + i1 /(i2 + 1) * $d / $e + $c",
          false,
          (i1: Int, i2: Int) => Option(a * i1 + b * i2 + i1 / (i2 + 1) * d / e + c)
        ),
        SetsColMapping(
          s"Option($a * i1 + $b * i2 + (i1 + 1)/ i2 * $d / $e + $c)",
          s"$a * i1 + $b * i2 + (i1 + 1)/ i2 * $d / $e + $c",
          false,
          (i1: Int, i2: Int) => Option(a * i1 + b * i2 + (i1 + 1) / i2 * d / e + c)
        ),
        SetsColMapping(
          s"Option($a * i1 + $b * i2 + i2 /(i1 + 1) * $d / $e + $c)",
          s"$a * i1 + $b * i2 + i2 /(i1 + 1) * $d / $e + $c",
          false,
          (i1: Int, i2: Int) => Option(a * i1 + b * i2 + i2 / (i1 + 1) * d / e + c)
        ),
        SetsColMapping(
          s"Option($a * i1 + $b * i2 + (i2 + 1)/ i1 * $d / $e + $c)",
          s"$a * i1 + $b * i2 + (i2 + 1)/ i1 * $d / $e + $c",
          false,
          (i1: Int, i2: Int) => Option(a * i1 + b * i2 + (i2 + 1) / i1 * d / e + c)
        )
      )

      /*val l4_pre_1 = for {
        a <- (-3 to 3).to(Vector)
        b <- (-3 to 3).to(Vector)
        d <- (-3 to 3).to(Vector)
        e <- (-3 to 3).to(Vector).filter(s => s != 0)
        c <- (-7 to 7).to(Vector)
      } yield SetsColMapping(
        s"Option($a * i1 + $b * i2 + i1 * i2 * $d / $e + $c)",
        s"$a * i1 + $b * i2 + i1 * i2 * $d / $e + $c",
        false,
        (i1: Int, i2: Int) => Option(a * i1 + b * i2 + i1 * i2 * d / e + c)
      )*/

      val l4   = noneMapping +: l4_pre.flatten
      val par4 = ParMapping((i1, i2) => i2 % (i1 + 1) == 0, l4, "i2 % (i1 + 1) == 0")
      val par5 = ParMapping((i1, i2) => i2 % (i1 + 1) > 0, l4, "i2 % (i1 + 1) > 0")
      // val par6 = ParMapping((i1, i2) => i1 < i2, l4, "i1 < i2")

      Vector(par1, /*par2, par2_2, par3, par3_2,*/ par4, par5 /*, par6*/ )
    }

    var count1 = 0

    val outerAction = ZStream
      .fromIterable(leftSets)
      .mapMPar(20) { s =>
        blocking.effectBlocking {
          val parM = s.set
            .split('|')
            .to(Vector)
            .map { t =>
              val u = t.split(',')

              { case (i1: Int, i2: Int) if i1 == u(0).toInt && i2 == u(1).toInt => u(2).toIntOption }: PartialFunction[(Int, Int), Option[
                Int
              ]]
            }
            .reduce((i1, i2) => i1.orElse(i2))

          val sMapping = mapping(s.firstStart, s.secondStart)

          for {
            i1 <- s.firstStart to 20
            i2 <- s.secondStart to 20
          } {

            val value         = parM(i1, i2)
            val findedMapping = sMapping.find(parMapping => parMapping.par(i1, i2)).get
            val mappingList   = findedMapping.mapping
            val update = mappingList.filter(eachMapping =>
              try { eachMapping.mapping(i1, i2) == value }
              catch {
                case e: Exception =>
                  false
              }
            )
            findedMapping.mapping = update

          }

          val confirm = if (sMapping.map(t => t.mapping.headOption).forall(_.isDefined)) {
            val isOpt = sMapping.map(t => t.mapping.head).exists(t => t.isOpt)
            val str = sMapping.zipWithIndex
              .map { case (t, index) =>
                val body = if (isOpt) t.mapping.head.optStr else t.mapping.head.noneOptString
                if (index == sMapping.size - 1) body else s"if (${t.tiaojian}) $body"
              }
              .mkString(" else ")
            Option(str)
          } else Option.empty

          /*val confirm = mapping
            .map { t =>
              val r = for {
                i1 <- s.firstStart to 20
                i2 <- s.secondStart to 20
              } yield {
                val v =
                  if (i1 == s.firstStart && i2 == s.secondStart) t._1._2(i1, i2)
                  else if (i1 == s.firstStart) t._2._2(i1, i2)
                  else if (i2 == s.secondStart) t._3._2(i1, i2)
                  else t._4._2(i1, i2)

                s"$i1,$i2,${v.map(_.toString).getOrElse("unlimited")}"
              }
              if (s.set == r.mkString("|")) {
                Option(
                  s"if(i1 == ${s.firstStart} && i2 == ${s.secondStart}) ${t._1._1} else if (i1 == ${s.firstStart}) ${t._2._1} else if (i2 == ${s.secondStart}) ${t._3._1} else ${t._4._1}",
                  s.firstStart,
                  s.secondStart
                )
              } else Option.empty
            }
            .dropWhile(_.isEmpty)
            .headOption
            .flatten*/

          confirm
            .map(t =>
              this.synchronized(
                println(
                  s"Tags.Tag${Runner.getCount}.firstart(${s.firstStart}).secondStart(${s.secondStart}).value((i1: Int, i2: Int) => $t)"
                )
              )
            )
            .getOrElse {
              // println(s"id 为 ${s.index} 的 set 无法映射")
              this.synchronized {
                count1 += 1
                notConfirm = notConfirm.appended(s.index)
                /*if (count1 % 20 == 0) {
                  println("当前 notConfirm")
                  println(notConfirm.mkString("var notConfirm: Vector[Int] = Vector(", ",", ")"))
                }*/
              }
            }
        }
      }
      .runCollect

    Runtime.default.unsafeRun(outerAction)
    // println("修改新的 notConfirm")
    // println(notConfirm.mkString("var notConfirm: Vector[Int] = Vector(", ",", ")"))

  }

}
