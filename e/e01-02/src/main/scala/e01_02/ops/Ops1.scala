package e01_02.ops

import e01_02._

object Ops1 {

  class ops_:::[A] extends Context[Number[A], Number[A], A] {
    override def bindS(t: Number[A], current: () => Number[A], head: A): Number[A] =
      NumberS(() => current().execute(this)(t), head)
    override def bindT(t: Number[A], current: () => Number[A]): Number[A] = t
  }

  class ops_collect[A, B] extends Context[PartialFunction[A, B], Number[B], A] {
    override def bindS(t: PartialFunction[A, B], current: () => Number[A], head: A): Number[B] = {
      val nextCollect = () => current().execute(this)(t)
      t.andThen(b => NumberS(nextCollect, b)).applyOrElse(head, (a: A) => nextCollect())
    }
    override def bindT(t: PartialFunction[A, B], current: () => Number[A]): Number[B] = NumberOps.empty
  }

  class ops_contains[A, A1 >: A] extends Context[A1, Boolean, A] {
    override def bindS(t: A1, current: () => Number[A], head: A): Boolean =
      if (t == head) true else current().execute(this)(t)
    override def bindT(t: A1, current: () => Number[A]): Boolean = false
  }

  class ops_corresponds1[A, B] extends Context[(Number[B], (A, B) => Boolean), Boolean, A] {
    override def bindS(t: (Number[B], (A, B) => Boolean), current: () => Number[A], head: A): Boolean =
      t._1.execute(new ops_corresponds2[A, B])((current(), t._2, head))
    override def bindT(t: (Number[B], (A, B) => Boolean), current: () => Number[A]): Boolean = t._1.execute(new ops_corresponds3)(())
  }

  class ops_corresponds2[A, B] extends Context[(Number[A], (A, B) => Boolean, A), Boolean, B] {
    override def bindS(t: (Number[A], (A, B) => Boolean, A), current: () => Number[B], head: B): Boolean =
      if (t._2(t._3, head))
        t._1.execute(new ops_corresponds1[A, B])((current(), t._2))
      else
        false
    override def bindT(t: (Number[A], (A, B) => Boolean, A), current: () => Number[B]): Boolean = false
  }

  class ops_corresponds3[B] extends Context[Unit, Boolean, B] {
    override def bindS(t: Unit, current: () => Number[B], head: B): Boolean = false
    override def bindT(t: Unit, current: () => Number[B]): Boolean          = true
  }

  class ops_headOption[A] extends Context[Unit, Option[A], A] {
    override def bindS(t: Unit, current: () => Number[A], head: A): Option[A] = Option(head)
    override def bindT(t: Unit, current: () => Number[A]): Option[A]          = Option.empty
  }

  class ops_head[A] extends Context[Unit, A, A] {
    override def bindS(t: Unit, current: () => Number[A], head: A): A = head
    override def bindT(t: Unit, current: () => Number[A]): A          = throw new NoSuchElementException("head of empty list")
  }

  class ops_equals1[A, B] extends Context[Number[B], Boolean, A] {
    override def bindS(t: Number[B], current: () => Number[A], head: A): Boolean =
      t.execute(new ops_equals2[A, B])((current(), head))
    override def bindT(t: Number[B], current: () => Number[A]): Boolean = t.execute(new ops_equals3[B])(())
  }

  class ops_equals2[A, B] extends Context[(Number[A], A), Boolean, B] {
    override def bindS(t: (Number[A], A), current: () => Number[B], head: B): Boolean =
      if (t._2 == head)
        t._1.execute(new ops_equals1[A, B])(current())
      else
        false
    override def bindT(t: (Number[A], A), current: () => Number[B]): Boolean = false
  }

  class ops_equals3[B] extends Context[Unit, Boolean, B] {
    override def bindS(t: Unit, current: () => Number[B], head: B): Boolean = false
    override def bindT(t: Unit, current: () => Number[B]): Boolean          = true
  }

  class existsContext[A] extends Context[A => Boolean, Boolean, A] {
    override def bindS(t: A => Boolean, current: () => Number[A], head: A): Boolean = if (t(head)) true else current().execute(this)(t)
    override def bindT(t: A => Boolean, current: () => Number[A]): Boolean          = false
  }

}
