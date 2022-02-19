package f07

object Gen5 {

  def printlnSingleResult(): Unit = {
    val sets                     = Runner.col.map(s => (s._1, s._2.mkString("|")))
    val leftSets: List[CountSet] = CountSets.sum.filter(s => sets.forall(t => t._2 != s.set))

    def mapping = {
      def l_pre = for {
        a <- LazyList.from(-2 to 2)
        b <- LazyList.from(-2 to 2)
        c <- LazyList.from(-3 to 3)
      } yield (s"$a * i1 + $b * i2 + $c", (i1: Int, i2: Int) => Option(a * i1 + b * i2 + c))

      def l1 = ("Option.empty", (i1: Int, i2: Int) => Option.empty) +: l_pre
      for {
        i1 <- l1
        i2 <- l1
        i3 <- l1
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
            if (i1 == 0 && i2 == 0) t._1._2(i1, i2)
            else if (i1 == 0) t._2._2(i1, i2)
            else if (i2 == 0) t._3._2(i1, i2)
            else t._4._2(i1, i2)
          val str = r.map(u => u.map(_.toString).getOrElse("unlimited"))
          if (s.set == str) {
            // println(s"if(i1 == 0 && i2 == 0) ${t._1._1} else if (i1 == 0) ${t._2._1} else if (i2 == 0) ${t._3._1} else ${t._4._1}")
            Option(s"if(i1 == 0 && i2 == 0) ${t._1._1} else if (i1 == 0) ${t._2._1} else if (i2 == 0) ${t._3._1} else ${t._4._1}")
          } else Option.empty
        }
        .dropWhile(_.isEmpty)
        .headOption
        .flatten
        .foreach(println)
    }

  }

}
