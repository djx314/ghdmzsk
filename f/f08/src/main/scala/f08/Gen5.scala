package f07

import zio.stream._
import zio._

object Gen5 {

  def printlnSingleResult(): Unit = {
    var notConfirm: Vector[Int] = Vector(3333, 3334, 3358, 3306, 3367, 3307, 3357, 3370, 3123, 3366, 3124, 3122, 3125, 3116, 3355, 3128,
      3361, 3365, 3189, 3350, 3329, 3362, 3356, 3375, 2974, 3378, 2971, 3112, 3368, 3364, 2967, 2976, 3359, 3303, 3330, 2968, 2975, 2962,
      3302, 3117, 3369, 3376, 3377, 2957, 3360, 3349, 3363, 3379, 3381, 3382, 3380, 3448, 3453, 3455, 3488, 3483, 3490, 3454, 3513, 3475,
      3459, 3481, 3463, 3482, 3489, 3486, 3498, 3487, 3484, 3514, 3516, 3515, 3550, 3552, 3549, 3527, 3529, 3610, 3547, 3546, 3551, 3532,
      3548, 3540, 3517, 3528, 3525, 3522, 3520, 3612, 3617, 3613, 3621, 3661, 3677, 3678, 3679, 3680, 3681, 3684, 3636, 3651, 3647, 3642,
      3644, 3645, 3649, 3648, 3650, 3643, 3715, 3712, 3717, 3722, 3686, 3713, 3706, 3714, 3711, 3716, 3698, 3693, 3691, 3689, 3692, 3725,
      3763, 3775, 3724, 3769, 3723, 3755, 3758, 3761, 3759, 3768, 3743, 3767, 3762, 3765, 3781, 3746, 3760, 3782, 3783, 3784, 3810, 3809,
      3807, 3808, 3806, 3805, 3800, 3804, 3811, 3796, 3785, 3787, 3792, 3793, 3794, 3790, 3817, 3818, 3819, 3840, 3874, 3875, 3846, 3879,
      3870, 3859, 3871, 3877, 3878, 3876, 3857, 3881, 3883, 3885, 3910, 3909, 3891, 3914, 3907, 3900, 3904, 3902, 3898, 3901, 3911, 3899,
      3923, 3918, 3929, 3926, 3925, 3928, 3927, 3924, 3922, 4012, 4005, 4004, 4010, 4017, 4015, 4009, 4003, 4016, 4018, 4021, 4022, 4020,
      4031, 4030, 4042, 4037, 4041, 4024, 4023, 4019, 4034, 4036, 4040, 4035, 4083, 4082, 4080, 4079, 4043, 4104, 4111, 4081, 4084, 4078,
      4067, 4068, 4077, 4115, 4103, 4102, 4101, 4100, 4113, 4112, 4114, 4135, 4116, 4284, 4290, 4134, 4117, 4120, 4118, 4133, 4119, 4272,
      4268, 4264, 4292, 4291)

    val sets = Runner.col.map(s => (s._1, s._2.mkString("|")))
    val leftSetsPre: List[CountSet] =
      CountSets.sum.filter(s => sets.forall(t => t._2 != s.set)).filterNot(t => notConfirm.exists(r => t.index == r))
    val leftSets = leftSetsPre.take(2000)

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

    var count1 = 0

    println(leftSetsPre.size)
    println(leftSetsPre.size)
    println(leftSetsPre.size)

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
              // println(s"id 为 ${s.index} 的 set 无法映射")
              this.synchronized {
                count1 += 1
                notConfirm = notConfirm.appended(s.index)
                if (count1 % 20 == 0) {
                  println("当前 notConfirm")
                  println(notConfirm.mkString("var notConfirm: Vector[Int] = Vector(", ",", ")"))
                }
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
