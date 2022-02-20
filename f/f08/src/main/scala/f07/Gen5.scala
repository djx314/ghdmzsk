package f07

object Gen5 {

  def printlnSingleResult(): Unit = {
    var notConfirm: List[Int] = List.empty

    val sets                     = Runner.col.map(s => (s._1, s._2.mkString("|")))
    val leftSets: List[CountSet] = CountSets.sum.filter(s => sets.forall(t => t._2 != s.set))

    def mapping = {
      val l1_pre = for {
        a <- LazyList.from(-2 to 2)
        b <- LazyList.from(-2 to 2)
        c <- LazyList.from(-3 to 3)
      } yield (s"$a * i1 + $b * i2 + $c", (i1: Int, i2: Int) => Option(a * i1 + b * i2 + c))

      val l1 = ("Option.empty", (i1: Int, i2: Int) => Option.empty) +: l1_pre

      val l2_pre = for {
        a <- LazyList.from(-2 to 2)
        b <- LazyList.from(-3 to 3)
      } yield (s"$a * i2 + $b", (i1: Int, i2: Int) => Option(a * i2 + b))

      val l2 = ("Option.empty", (i1: Int, i2: Int) => Option.empty) +: l2_pre

      val l3_pre = for {
        a <- LazyList.from(-2 to 2)
        b <- LazyList.from(-3 to 3)
      } yield (s"$a * i1 + $b", (i1: Int, i2: Int) => Option(a * i1 + b))

      val l3 = ("Option.empty", (i1: Int, i2: Int) => Option.empty) +: l3_pre

      val l4_pre = for (a <- LazyList.from(-3 to 3)) yield (s"$a", (i1: Int, i2: Int) => Option(a))

      val l4 = ("Option.empty", (i1: Int, i2: Int) => Option.empty) +: l4_pre

      for {
        i1 <- l4
        i2 <- l3
        i3 <- l2
        i4 <- l1
      } yield (i1, i2, i3, i4)
    }

    leftSets.foreach { s =>
      mapping
        .map { t =>
          val r = for {
            i1 <- s.firstStart to 20
            i2 <- s.secondStart to 20
          } yield
            if (i1 == s.firstStart && i2 == s.secondStart) t._1._2(i1, i2)
            else if (i1 == s.firstStart) t._2._2(i1, i2)
            else if (i2 == s.secondStart) t._3._2(i1, i2)
            else t._4._2(i1, i2)
          val str = r.map(u => u.map(_.toString).getOrElse("unlimited"))
          if (s.set == str) {
            // println(s"if(i1 == 0 && i2 == 0) ${t._1._1} else if (i1 == 0) ${t._2._1} else if (i2 == 0) ${t._3._1} else ${t._4._1}")
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
        .map(s => println(s"Tags.Tag${Runner.getCount}.firstart(${s._2}).secondStart(${s._3}).value((i1: Int, i2: Int) => ${s._1})"))
        .getOrElse {
          println(s"id 为 ${s.index} 的 set 无法映射")
          notConfirm = notConfirm.appended(s.index)
        }
    }
    println(notConfirm)

  }

}
