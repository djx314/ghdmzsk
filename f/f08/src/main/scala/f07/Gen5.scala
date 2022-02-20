package f07

import zio.stream._
import zio._

object Gen5 {

  def printlnSingleResult(): Unit = {
    var notConfirm: Vector[Int] = Vector(3333, 3334, 3358, 3306, 3367, 3307, 3357, 3370, 3123, 3366, 3124, 3122, 3125, 3116, 3355, 3128,
      3361, 3365, 3189, 3350, 3329, 3362, 3356, 3375, 2974, 3378, 2971, 3112, 3368, 3364, 2967, 2976, 3359, 3303, 3330, 2968, 2975, 2962,
      3302, 3117, 3369, 3376, 3377, 2957, 3360, 3349, 3363, 3379, 3381, 3382, 3380, 3448, 3453)

    val sets = Runner.col.map(s => (s._1, s._2.mkString("|")))
    val leftSets: List[CountSet] =
      CountSets.sum.filter(s => sets.forall(t => t._2 != s.set)).filterNot(t => notConfirm.exists(r => t.index == r)).take(80)

    def mapping = {
      val noneMapping = ("Option.empty", (i1: Int, i2: Int) => Option.empty)

      val l1_pre = for (a <- LazyList.from(-3 to 3)) yield (s"$a", (i1: Int, i2: Int) => Option(a))

      val l1 = noneMapping +: l1_pre

      val l2_pre = for {
        a <- LazyList.from(-2 to 2)
        b <- LazyList.from(-3 to 3)
      } yield (s"$a * i2 + $b", (i1: Int, i2: Int) => Option(a * i2 + b))

      val l2 = noneMapping +: l2_pre

      val l3_pre = for {
        a <- LazyList.from(-2 to 2)
        b <- LazyList.from(-3 to 3)
      } yield (s"$a * i1 + $b", (i1: Int, i2: Int) => Option(a * i1 + b))

      val l3 = noneMapping +: l3_pre

      val l4_pre = for {
        a <- LazyList.from(-2 to 2)
        b <- LazyList.from(-2 to 2)
        c <- LazyList.from(-3 to 3)
      } yield (s"$a * i1 + $b * i2 + $c", (i1: Int, i2: Int) => Option(a * i1 + b * i2 + c))

      val l4 = noneMapping +: l4_pre

      for {
        i1 <- l1
        i2 <- l2
        i3 <- l3
        i4 <- l4
      } yield (i1, i2, i3, i4)
    }

    val outerAction = ZStream
      .fromIterable(leftSets)
      .mapMPar(20) { s =>
        blocking.effectBlocking {
          val confirm = mapping
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
            .flatten

          confirm
            .map(s =>
              this.synchronized(
                println(s"Tags.Tag${Runner.getCount}.firstart(${s._2}).secondStart(${s._3}).value((i1: Int, i2: Int) => ${s._1})")
              )
            )
            .getOrElse {
              println(s"id 为 ${s.index} 的 set 无法映射")
              this.synchronized {
                notConfirm = notConfirm.appended(s.index)
                println("当前 notConfirm")
                println(notConfirm.mkString("var notConfirm: Vector[Int] = Vector(", ",", ")"))
              }
            }
        }
      }
      .runCollect

    Runtime.default.unsafeRun(outerAction)
    println("修改新的 notConfirm")
    println(notConfirm.mkString("var notConfirm: Vector[Int] = Vector(", ",", ")"))

  }

}
