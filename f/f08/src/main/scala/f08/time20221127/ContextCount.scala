package f08.time20221127

import f08.{ConfirmResult => CR}

object Utils {

  // NotImplemented 按 Option[Int] 算，toTxt 按 NotImplemented 算，只在计算过程中抛特定异常，让 counter catch 到。

  object Utils_1 {
    trait FuncExec[T] extends ((Int, Int) => T)

    trait ContextF[T] {
      def value: T
    }

    object ContextF {
      def apply[T](t: T): ContextF[T] = new ContextF[T] {
        override def value: T = t
      }
    }

    trait ContextExec[T, R] {
      def input(context: ContextF[T]): R

      def toTxt: String
    }

    trait CountContext[R] extends ContextExec[(Int, Int), R] {
      override def input(context: ContextF[(Int, Int)]): R

      override def toTxt: String
    }

    trait FormNumber[R] extends FuncExec[R] with CountContext[R] {
      override def apply(i1: Int, i2: Int): R = input(ContextF((i1, i2)))

      override def input(context: ContextF[(Int, Int)]): R

      override def toTxt: String
    }

    def merge_Option_and_Option[T](i1_in: FormNumber[Option[Int]], i2_in: FormNumber[Option[Int]]): FormNumber[Option[Int]] =
      new FormNumber[Option[Int]] {
        override def input(context: ContextF[(Int, Int)]): Option[Int] = for (i1_x <- i1_in.input(context); i2_x <- i2_in.input(context))
          yield i1_x * i2_x

        override def toTxt: String = s"${i1_in.toTxt: String} × ${i2_in.toTxt: String}"
      }
  }

  trait UpToOptForm[T] {
    def input(t: T): Utils_1.FormNumber[Option[Int]]
  }
  object UpToOptForm {
    def get[T: UpToOptForm]: UpToOptForm[T] = implicitly[UpToOptForm[T]]

    implicit val i1nnn: UpToOptForm[Utils_1.FormNumber[Int]] = new UpToOptForm[Utils_1.FormNumber[Int]] {
      override def input(t: Utils_1.FormNumber[Int]): Utils_1.FormNumber[Option[Int]] = new Utils_1.FormNumber[Option[Int]] {
        override def input(context: Utils_1.ContextF[(Int, Int)]): Option[Int] = Option(t.input(context))
        override def toTxt: String                                             = t.toTxt
      }
    }
    implicit val ienrower: UpToOptForm[Int] = new UpToOptForm[Int] {
      override def input(t: Int): Utils_1.FormNumber[Option[Int]] = new Utils_1.FormNumber[Option[Int]] {
        override def input(context: Utils_1.ContextF[(Int, Int)]): Option[Int] = Option(t)

        override def toTxt: String = t.toString
      }
    }
    implicit val sfertwefsd: UpToOptForm[Utils_1.FormNumber[Option[Int]]] = new UpToOptForm[Utils_1.FormNumber[Option[Int]]] {
      override def input(t: Utils_1.FormNumber[Option[Int]]): Utils_1.FormNumber[Option[Int]] = t
    }
  }

  object API {
    val i1 = new Utils_1.FormNumber[Int] {
      override def input(context: Utils_1.ContextF[(Int, Int)]): Int = context.value._1
      override def toTxt: String                                     = " i1 "
    }
    val i2 = new Utils_1.FormNumber[Int] {
      override def input(context: Utils_1.ContextF[(Int, Int)]): Int = context.value._2
      override def toTxt: String                                     = " i2 "
    }

    implicit class Ext1[T: UpToOptForm](private val iInt: T) {
      def ×[T1: UpToOptForm](i: T1): Utils_1.FormNumber[Option[Int]] = {
        val form1 = UpToOptForm.get[T]
        val form2 = UpToOptForm.get[T1]
        Utils_1.merge_Option_and_Option(form1.input(iInt), form2.input(i))
      }
    }
  }

}
