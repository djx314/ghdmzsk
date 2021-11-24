package e01_02

import e01_02.ops._
import scala.language.implicitConversions

object NumberOps {

  implicit def number_convert[A](number: Number[A]): NumberOps[A] = new NumberOps[A](number)

  class NumberOps[A](number: Number[A]) {
    def ::[B >: A](elem: B): Number[B]                                 = NumberS(() => number, head = elem)
    def :::[B >: A](numberB: Number[B]): Number[B]                     = numberB.execute(Ops1.ops_:::)((), number)
    def appendAll[B >: A](numberB: Number[B]): Number[B]               = (number: Number[B]).execute(Ops1.ops_:::)((), numberB)
    final def collect[B](pf: PartialFunction[A, B]): Number[B]         = number.execute(Ops1.ops_collect[A, B])(pf, ())
    final def contains[A1 >: A](elem: A1): Boolean                     = number.execute(Ops1.ops_contains[A, A1])(elem, ())
    def corresponds[B](that: Number[B])(p: (A, B) => Boolean): Boolean = number.execute(Ops1.ops_corresponds1[A, B])(p, that)
    def headOption: Option[A]                                          = number.execute(Ops1.ops_headOption)((), ())
    def head: A                                                        = number.execute(Ops1.ops_head)((), ())
    // def equals[B](b: Number[B]): Boolean                               = number.execute(Ops1.ops_head)((), ())
  }

}
