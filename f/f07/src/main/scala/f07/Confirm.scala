package f07

case class Confirm(setsCol: Vector[SetsList]) {

  def confirm: List[(String, String)] = {
    val dataCol = for {
      mapping1 <- setsCol
    } yield {
      val list = for {
        i1 <- 1 to 20
        i2 <- 1 to 20
      } yield (i1, i2, mapping1.count(i1, i2))
      (mapping1.key, list)
    }

    val dataCol1 = dataCol.filter(_._2.forall(_._3.isDefined)).map(s => (s._1, s._2.map(t => (t._1, t._2, t._3.get))))

    val l = for {
      (key1, data) <- dataCol1
      mapping2     <- setsCol
    } yield {
      val confirmList = for ((i1, i2, v) <- data) yield {
        val (v2_1, v2_2, v2_3, v2_4) = {
          val (v1, v2) =
            if (v > mapping2.firstStart)
              (mapping2.count(v, i1), mapping2.count(v, i2))
            else
              (Option.empty, Option.empty)

          val (v3, v4) =
            if (v > mapping2.secondStart)
              (mapping2.count(i1, v), mapping2.count(i2, v))
            else
              (Option.empty, Option.empty)

          (v1, v2, v3, v4)
        }

        val confirm1 = Option(i1) == v2_2 && Option(i2) == v2_1
        val confirm2 = Option(i1) == v2_4 && Option(i2) == v2_3
        (confirm1, confirm2)
      }

      val (confirmList1, confirmList2) = confirmList.unzip
      val confirm                      = confirmList1.forall(identity) || confirmList2.forall(identity)

      if (confirm) Option((key1, mapping2.key)) else Option.empty
    }

    l.to(List).collect { case Some(s) => s }
  }

}
