package f08

import f07._
import scala.collection.compat._

sealed trait ConfirmResult
case class OptResult(r: Option[Int]) extends ConfirmResult
case object NotImplemented           extends ConfirmResult

sealed trait ConfirmPlan {
  def key: String
  def countSetKey: Int
  def firstStart: Int  = 0
  def secondStart: Int = 0
  def count(i1: Int, i2: Int): ConfirmResult

  def cofirm(countSet: List[CountSet]): Boolean = {
    val resultSet = countSet.find(_.index == countSetKey).get.set.split('|').to(List)
    val confirmSet = for (t <- resultSet) yield {
      val List(i1, i2, i3) = t.split(',').to(List)
      val u = count(i1.toInt, i2.toInt) match {
        case OptResult(x) =>
          if (x != i3.toIntOption) {
            println(s"key: ${key}, countSetKey: ${countSetKey}", i1, i2, x, i3.toIntOption)
          }
          x == i3.toIntOption
        case NotImplemented => true
      }
      assert(u)
      u
    }
    confirmSet.forall(identity)
  }
}

case class SimpleMapPlan(
  override val key: String,
  override val countSetKey: Int,
  setColKey: String
) extends ConfirmPlan {
  override def count(i1: Int, i2: Int): ConfirmResult = OptResult(SetColInstance.list.find(_.key == setColKey).get.count(i1, i2))
}

case class CusPlan(override val key: String, override val countSetKey: Int, c: (Int, Int) => ConfirmResult) extends ConfirmPlan {
  override def count(i1: Int, i2: Int): ConfirmResult = c(i1, i2)
}

trait ConfirmCol {
  def confirmCol: Vector[ConfirmPlan]   = cols.to(Vector)
  protected var cols: List[ConfirmPlan] = List.empty

  def add(plan: ConfirmPlan): Unit = cols = plan :: cols

  import scala.language.implicitConversions

  private implicit def cov1(opt: Option[Int]): ConfirmResult = OptResult(opt)
  private implicit def cov2(value: Int): ConfirmResult       = OptResult(Option(value))

  add(CusPlan(key = Tags.Tag001, countSetKey = 590, c = (i1: Int, i2: Int) => 0))
  add(CusPlan(key = Tags.Tag002, countSetKey = 591, c = (i1: Int, i2: Int) => Option.empty))
  add(CusPlan(key = Tags.Tag003, countSetKey = 592, c = (i1: Int, i2: Int) => 2))
  add(CusPlan(key = Tags.Tag004, countSetKey = 593, c = (i1: Int, i2: Int) => 4))
  add(CusPlan(key = Tags.Tag005, countSetKey = 594, c = (i1: Int, i2: Int) => 3))
  add(CusPlan(key = Tags.Tag006, countSetKey = 595, c = (i1: Int, i2: Int) => i2 * 2))
  add(CusPlan(key = Tags.Tag007, countSetKey = 596, c = (i1: Int, i2: Int) => if (i2 == 0) Option(0) else Option.empty))
  add(CusPlan(key = Tags.Tag008, countSetKey = 597, c = (i1: Int, i2: Int) => i2 * 2 + 2))
  add(CusPlan(key = Tags.Tag009, countSetKey = 598, c = (i1: Int, i2: Int) => i2 * 2 + 1))
  add(CusPlan(key = Tags.Tag010, countSetKey = 599, c = (i1: Int, i2: Int) => 1))
  add(CusPlan(key = Tags.Tag011, countSetKey = 600, c = (i1: Int, i2: Int) => i2))
  add(CusPlan(key = Tags.Tag012, countSetKey = 601, c = (i1: Int, i2: Int) => i2 + 2))
  add(CusPlan(key = Tags.Tag013, countSetKey = 602, c = (i1: Int, i2: Int) => i2 + 1))
  add(CusPlan(key = Tags.Tag014, countSetKey = 603, c = (i1: Int, i2: Int) => if (i2 == 0) 0 else 1))
  add(CusPlan(key = Tags.Tag015, countSetKey = 604, c = (i1: Int, i2: Int) => if (i2 == 0) Option.empty else 1))
  add(CusPlan(key = Tags.Tag016, countSetKey = 605, c = (i1: Int, i2: Int) => if (i2 <= 1) 2 - i2 else 1))
  add(CusPlan(key = Tags.Tag017, countSetKey = 606, c = (i1: Int, i2: Int) => if (i2 == 0) Option.empty else 2))
  add(CusPlan(key = Tags.Tag018, countSetKey = 607, c = (i1: Int, i2: Int) => if (i2 == 0) 0 else i2 + 1))
  add(CusPlan(key = Tags.Tag019, countSetKey = 608, c = (i1: Int, i2: Int) => if (i2 == 0) 1 else 0))
  add(CusPlan(key = Tags.Tag020, countSetKey = 609, c = (i1: Int, i2: Int) => if (i2 == 0) Option.empty else 0))
  add(CusPlan(key = Tags.Tag021, countSetKey = 610, c = (i1: Int, i2: Int) => if (i2 == 0) 2 else 0))
  add(CusPlan(key = Tags.Tag022, countSetKey = 611, c = (i1: Int, i2: Int) => if (i2 == 0) 2 else Option.empty))
  add(CusPlan(key = Tags.Tag023, countSetKey = 612, c = (i1: Int, i2: Int) => if (i2 == 0) 1 else Option.empty))
  add(CusPlan(key = Tags.Tag024, countSetKey = 613, c = (i1: Int, i2: Int) => 5))
  add(CusPlan(key = Tags.Tag025, countSetKey = 614, c = (i1: Int, i2: Int) => i2 * 3))
  add(CusPlan(key = Tags.Tag026, countSetKey = 615, c = (i1: Int, i2: Int) => i2 * 3 + 2))
  add(CusPlan(key = Tags.Tag027, countSetKey = 616, c = (i1: Int, i2: Int) => i2 * 3 + 1))
  add(CusPlan(key = Tags.Tag028, countSetKey = 617, c = (i1: Int, i2: Int) => i1 + 2))
  add(CusPlan(key = Tags.Tag029, countSetKey = 618, c = (i1: Int, i2: Int) => i1 + 4))
  add(CusPlan(key = Tags.Tag030, countSetKey = 619, c = (i1: Int, i2: Int) => i1 + 3))
  add(CusPlan(key = Tags.Tag031, countSetKey = 620, c = (i1: Int, i2: Int) => i1 * i2 + 2 * i2))
  add(CusPlan(key = Tags.Tag032, countSetKey = 621, c = (i1: Int, i2: Int) => i2 * i1 + 2 * i2 + 2))
  add(CusPlan(key = Tags.Tag033, countSetKey = 622, c = (i1: Int, i2: Int) => i1 * i2 + 2 * i2 + 1))
  add(CusPlan(key = Tags.Tag034, countSetKey = 623, c = (i1: Int, i2: Int) => i1 + 1))
  add(CusPlan(key = Tags.Tag035, countSetKey = 624, c = (i1: Int, i2: Int) => i1 * i2 + i2))
  add(CusPlan(key = Tags.Tag036, countSetKey = 625, c = (i1: Int, i2: Int) => i1 * i2 + i2 + 2))
  add(CusPlan(key = Tags.Tag037, countSetKey = 626, c = (i1: Int, i2: Int) => i1 * i2 + i2 + 1))
  add(CusPlan(key = Tags.Tag038, countSetKey = 627, c = (i1: Int, i2: Int) => i2 * 2 - (i2 + 1) / 2))
  add(
    CusPlan(
      key = Tags.Tag039,
      countSetKey = 628,
      c = (i1: Int, i2: Int) => if ((i2 + 1) % 2 == 1) i2 + (i2 + 1) / 2 + 2 else i2 + (i2 + 1) / 2 + 1
    )
  )
  add(CusPlan(key = Tags.Tag040, countSetKey = 629, c = (i1: Int, i2: Int) => i2 * 2 - (i2 + 1) / 2 + 1))
  add(CusPlan(key = Tags.Tag041, countSetKey = 630, c = (i1: Int, i2: Int) => i2 / 2))
  add(CusPlan(key = Tags.Tag042, countSetKey = 631, c = (i1: Int, i2: Int) => i2 / 2 + 2))
  add(CusPlan(key = Tags.Tag043, countSetKey = 632, c = (i1: Int, i2: Int) => i2 / 2 + 1))
  add(CusPlan(key = Tags.Tag044, countSetKey = 633, c = (i1: Int, i2: Int) => if (i1 == 0) 2 else 1))
  add(CusPlan(key = Tags.Tag045, countSetKey = 634, c = (i1: Int, i2: Int) => if (i1 == 0) 4 else 3))
  add(CusPlan(key = Tags.Tag046, countSetKey = 635, c = (i1: Int, i2: Int) => if (i1 == 0) 3 else 2))
  add(CusPlan(key = Tags.Tag047, countSetKey = 636, c = (i1: Int, i2: Int) => i2 / (i1 + 1) + i2))
  add(CusPlan(key = Tags.Tag048, countSetKey = 637, c = (i1: Int, i2: Int) => i2 / (i1 + 1) + i2 + 2))
  add(CusPlan(key = Tags.Tag049, countSetKey = 638, c = (i1: Int, i2: Int) => i2 / (i1 + 1) + i2 + 1))
  add(CusPlan(key = Tags.Tag050, countSetKey = 639, c = (i1: Int, i2: Int) => if (i1 == 0) 1 else 0))
  add(CusPlan(key = Tags.Tag051, countSetKey = 640, c = (i1: Int, i2: Int) => i2 / (i1 + 1)))
  add(CusPlan(key = Tags.Tag052, countSetKey = 641, c = (i1: Int, i2: Int) => i2 / (i1 + 1) + 2))
  add(CusPlan(key = Tags.Tag053, countSetKey = 642, c = (i1: Int, i2: Int) => i2 / (i1 + 1) + 1))
  add(CusPlan(key = Tags.Tag054, countSetKey = 643, c = (i1: Int, i2: Int) => if (i2 == 0) 0 else i2 + 1 + 1))
  add(CusPlan(key = Tags.Tag055, countSetKey = 644, c = (i1: Int, i2: Int) => if (i2 == 0) 1 else i2 + 2))
  add(CusPlan(key = Tags.Tag056, countSetKey = 645, c = (i1: Int, i2: Int) => if (i2 == 0) 2 else i2 + 1))
  add(CusPlan(key = Tags.Tag057, countSetKey = 646, c = (i1: Int, i2: Int) => if (i2 == 0) 0 else 3))
  add(CusPlan(key = Tags.Tag058, countSetKey = 647, c = (i1: Int, i2: Int) => if (i2 == 0) Option.empty else 3))
  add(CusPlan(key = Tags.Tag059, countSetKey = 648, c = (i1: Int, i2: Int) => if (i2 == 0) 2 else 3))
  add(CusPlan(key = Tags.Tag060, countSetKey = 649, c = (i1: Int, i2: Int) => if (i2 == 0) 1 else 3))
  add(CusPlan(key = Tags.Tag061, countSetKey = 650, c = (i1: Int, i2: Int) => if (i2 == 0) Option.empty else 4))
  add(CusPlan(key = Tags.Tag062, countSetKey = 651, c = (i1: Int, i2: Int) => i2 + 3))
  add(CusPlan(key = Tags.Tag063, countSetKey = 653, c = (i1: Int, i2: Int) => if (i2 == 0) 0 else 2))
  add(CusPlan(key = Tags.Tag064, countSetKey = 654, c = (i1: Int, i2: Int) => if (i2 == 0) 3 else 2))
  add(CusPlan(key = Tags.Tag065, countSetKey = 655, c = (i1: Int, i2: Int) => if (i2 == 0) 1 else 2))
  add(CusPlan(key = Tags.Tag066, countSetKey = 656, c = (i1: Int, i2: Int) => if (i2 == 0) 1 + 1 else i2))
  add(CusPlan(key = Tags.Tag067, countSetKey = 657, c = (i1: Int, i2: Int) => if (i2 == 0) 1 else i2))
  add(CusPlan(key = Tags.Tag068, countSetKey = 658, c = (i1: Int, i2: Int) => if (i1 >= 1) i1 * 2 - 1 else i1))
  add(CusPlan(key = Tags.Tag069, countSetKey = 659, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 == 1) 1 else 2))
  add(CusPlan(key = Tags.Tag070, countSetKey = 660, c = (i1: Int, i2: Int) => if (i1 <= 1) i1 else Option.empty))
  add(CusPlan(key = Tags.Tag071, countSetKey = 661, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * (1 + 1) + i1 - 1 - 1))
  add(CusPlan(key = Tags.Tag072, countSetKey = 662, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * (i2 + 1) + i1 - i2 - 1))
  add(CusPlan(key = Tags.Tag073, countSetKey = 663, c = (i1: Int, i2: Int) => i1))
  add(CusPlan(key = Tags.Tag074, countSetKey = 664, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else (i1 * 3 - 1) / 2))
  add(
    CusPlan(
      key = Tags.Tag075,
      countSetKey = 665,
      c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 % (i2 + 1) == 0) i1 + i1 / (i2 + 1) - 1 else i1 + i1 / (i2 + 1)
    )
  )
  add(
    CusPlan(
      key = Tags.Tag076,
      countSetKey = 666,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) i2 * 0 / -3 + 0
        else if (i1 == 1) -2 * i1 + 0 * i2 + 3
        else if (i2 == 0) i1 * -3 / -3 + 2
        else if (i2 == 1) 1 * i1 + -1 * i2 + 3
        else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 2
        else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
        else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
    )
  )
  add(
    CusPlan(
      key = Tags.Tag077,
      countSetKey = 667,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) i2 * 0 / -3 + 0
        else if (i1 == 1) -3 * i1 + 0 * i2 + 4
        else if (i2 == 0) i1 * 0 / -3 + 4
        else if (i2 == 1) 0 * i1 + -3 * i2 + 7
        else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3
        else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4
        else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4
    )
  )
  add(
    CusPlan(
      key = Tags.Tag078,
      countSetKey = 668,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) i2 * 0 / -3 + 0
        else if (i1 == 1) -2 * i1 + 0 * i2 + 3
        else if (i2 == 0) i1 * -3 / -3 + 1
        else if (i2 == 1) 1 * i1 + -2 * i2 + 3
        else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
        else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
        else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
    )
  )
  add(CusPlan(key = Tags.Tag079, countSetKey = 669, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 == 1) 1 else 3))
  add(
    CusPlan(
      key = Tags.Tag080,
      countSetKey = 670,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0 * i2 + 0
        else if (i2 == 0) 0 * i1 + 0
        else if (i1 == i2) 0 * i1 + 2 * i2 + -1
        else if (i1 < i2) 2 * i1 + 0 * i2 + -1
        else 0 * i1 + 2 * i2 + 0
    )
  )
  add(
    CusPlan(
      key = Tags.Tag081,
      countSetKey = 671,
      c =
        (i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 2 * i1 + 0 * i2 + -1
    )
  )
  add(
    CusPlan(
      key = Tags.Tag082,
      countSetKey = 672,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) Option(0)
        else if (i1 == 0) Option(0 * i2 + 0)
        else if (i2 == 0) Option.empty
        else if (i1 == i2) Option(0 * i1 + 2 * i2 + -1)
        else if (i1 < i2) Option(2 * i1 + 0 * i2 + -1)
        else Option.empty
    )
  )
  add(
    CusPlan(
      key = Tags.Tag083,
      countSetKey = 673,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) Option(0)
        else if (i1 == 0) Option(i2 * 0 / -3 + 0)
        else if (i2 == 0) Option.empty
        else if (i1 % i2 == 0) Option(2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2)
        else Option(2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1)
    )
  )
  add(
    CusPlan(
      key = Tags.Tag084,
      countSetKey = 678,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0 * i2 + 0
        else if (i2 == 0) 1 * i1 + 1
        else if (i1 == i2) 0 * i1 + 2 * i2 + -1
        else if (i1 < i2) 2 * i1 + 0 * i2 + -1
        else 1 * i1 + 1 * i2 + 1
    )
  )
  add(
    CusPlan(
      key = Tags.Tag085,
      countSetKey = 679,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0 * i2 + 0
        else if (i2 == 0) 0 * i1 + 2
        else if (i1 == i2) 0 * i1 + 2 * i2 + -1
        else if (i1 < i2) 2 * i1 + 0 * i2 + -1
        else 0 * i1 + 2 * i2 + 2
    )
  )
  add(
    CusPlan(
      key = Tags.Tag086,
      countSetKey = 680,
      c = (iii1: Int, iii2: Int) =>
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0
    )
  )
  add(
    CusPlan(
      key = Tags.Tag087,
      countSetKey = 681,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0 * i2 + 0
        else if (i2 == 0) 0 * i1 + 1
        else if (i1 == i2) 0 * i1 + 2 * i2 + -1
        else if (i1 < i2) 2 * i1 + 0 * i2 + -1
        else 0 * i1 + 2 * i2 + 1
    )
  )
  add(CusPlan(key = Tags.Tag088, countSetKey = 682, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else Option.empty))
  add(CusPlan(key = Tags.Tag089, countSetKey = 683, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else 1))
  add(CusPlan(key = Tags.Tag090, countSetKey = 684, c = (i1: Int, i2: Int) => 2 * i1))
  add(CusPlan(key = Tags.Tag091, countSetKey = 685, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else 3 * i1 - 1))
  add(
    CusPlan(
      key = Tags.Tag092,
      countSetKey = 686,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0) 0 else if (i2 == 0) Option.empty else if (i1 % i2 == 0) 2 * i1 + i1 / i2 - 1 else 2 * i1 + i1 / i2
    )
  )
  add(
    CusPlan(
      key = Tags.Tag093,
      countSetKey = 687,
      c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) Option.empty else if (i1 % i2 == 0) i1 + i1 / i2 - 1 else i1 + i1 / i2
    )
  )
  add(CusPlan(key = Tags.Tag094, countSetKey = 688, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + 2))
  add(CusPlan(key = Tags.Tag095, countSetKey = 689, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else 3))
  add(CusPlan(key = Tags.Tag096, countSetKey = 690, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + 1))
  add(CusPlan(key = Tags.Tag097, countSetKey = 691, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else 2))
  add(CusPlan(key = Tags.Tag098, countSetKey = 692, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i2))
  add(CusPlan(key = Tags.Tag099, countSetKey = 693, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * 2 + i2 - 1))
  add(
    CusPlan(
      key = Tags.Tag100,
      countSetKey = 694,
      c = (iii1: Int, iii2: Int) =>
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) iii2 * 0 / -3 + 0
        else if (iii1 == 1) -3 * iii1 + 1 * iii2 + 4
        else if (iii2 == 0) iii1 * -2 / -1 + -1
        else if (iii2 == 1) 3 * iii1 + -3 * iii2 + 2
        else if (iii1 == iii2) -1 * iii1 + 3 * iii2 + iii1 * iii2 * -3 / -3 + -1
        else if (iii1 > iii2) 2 * iii1 + 0 * iii2 + iii1 * iii2 * -3 / -3 + -1
        else 2 * iii1 + 0 * iii2 + iii1 * iii2 * -3 / -3 + -1
    )
  )
  add(CusPlan(key = Tags.Tag101, countSetKey = 696, c = (i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 0 else Option.empty))
  add(CusPlan(key = Tags.Tag102, countSetKey = 697, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + i2 - 1))
  add(
    CusPlan(
      key = Tags.Tag103,
      countSetKey = 698,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) i2 * 0 / -3 + 0
        else if (i1 == 1) -3 * i1 + 1 * i2 + 3
        else if (i2 == 0) i1 * -3 / -3 + -1
        else if (i2 == 1) 2 * i1 + -3 * i2 + 2
        else if (i1 == i2) -2 * i1 + 3 * i2 + i1 * i2 * -3 / -3 + -1
        else if (i1 > i2) 1 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + -1
        else 1 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + -1
    )
  )
  add(CusPlan(key = Tags.Tag104, countSetKey = 700, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + i2 + 1))
  add(CusPlan(key = Tags.Tag105, countSetKey = 701, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i2 + 2))
  add(CusPlan(key = Tags.Tag106, countSetKey = 702, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + i2))
  add(CusPlan(key = Tags.Tag107, countSetKey = 703, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i2 + 1))
  add(CusPlan(key = Tags.Tag108, countSetKey = 704, c = (i1: Int, i2: Int) => if (i1 >= 1) i1 - 1 else 0))
  add(CusPlan(key = Tags.Tag109, countSetKey = 705, c = (i1: Int, i2: Int) => if (i1 <= 1) 0 else 1))
  add(CusPlan(key = Tags.Tag110, countSetKey = 706, c = (i1: Int, i2: Int) => if (i1 >= 1) i1 * 2 - 2 else 0))
  add(CusPlan(key = Tags.Tag111, countSetKey = 709, c = (i1: Int, i2: Int) => if (i1 > 1) Option.empty else 0))
  add(CusPlan(key = Tags.Tag112, countSetKey = 710, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else (i1 - 1) * (i2 + 1)))
  add(
    CusPlan(
      key = Tags.Tag113,
      countSetKey = 713,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) i2 * 0 / -3 + 0
        else if (i1 == 1) -3 * i1 + 0 * i2 + 3
        else if (i2 == 0) i1 * -3 / -3 + 0
        else if (i2 == 1) 1 * i1 + -3 * i2 + 3
        else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 0
        else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 0
        else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 0
    )
  )
  add(CusPlan(key = Tags.Tag114, countSetKey = 714, c = (i1: Int, i2: Int) => if (i1 <= 1) 0 else 2))
  add(
    CusPlan(
      key = Tags.Tag115,
      countSetKey = 715,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0 * i2 + 0
        else if (i2 == 0) 0 * i1 + 0
        else if (i1 == i2) -1 * i1 + 2 * i2 + -1
        else if (i1 < i2) 1 * i1 + 0 * i2 + -1
        else 0 * i1 + 1 * i2 + 0
    )
  )
  add(
    CusPlan(
      key = Tags.Tag116,
      countSetKey = 719,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0
        else if (i2 == 0) Option.empty
        else if (i1 == i2) 2 * i2 - i1 - 1
        else if (i1 < i2) i1 - 1
        else Option.empty
    )
  )
  add(
    CusPlan(
      key = Tags.Tag117,
      countSetKey = 720,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) Option(0)
        else if (i1 == 0) Option(i2 * 0 / -3 + 0)
        else if (i2 == 0) Option.empty
        else if (i1 % i2 == 0) Option(1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2)
        else Option(1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1)
    )
  )
  add(CusPlan(key = Tags.Tag118, countSetKey = 722, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 0 else i1 - 1))
  add(
    CusPlan(
      key = Tags.Tag119,
      countSetKey = 725,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0 * i2 + 0
        else if (i2 == 0) 1 * i1 + 0
        else if (i1 == i2) -1 * i1 + 2 * i2 + -1
        else if (i1 < i2) 1 * i1 + 0 * i2 + -1
        else 1 * i1 + 0 * i2 + 0
    )
  )
  add(
    CusPlan(
      key = Tags.Tag120,
      countSetKey = 726,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 0 * i2 + 0
        else if (i2 == 0) 0 * i1 + 1
        else if (i1 == i2) -1 * i1 + 2 * i2 + -1
        else if (i1 < i2) 1 * i1 + 0 * i2 + -1
        else 0 * i1 + 1 * i2 + 1
    )
  )
  add(CusPlan(key = Tags.Tag121, countSetKey = 727, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else 4))
  add(CusPlan(key = Tags.Tag122, countSetKey = 728, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i2 * 2))
  add(CusPlan(key = Tags.Tag123, countSetKey = 729, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i2 * 2 + 2))
  add(CusPlan(key = Tags.Tag124, countSetKey = 730, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else 2 * i2 + 1))
  add(CusPlan(key = Tags.Tag125, countSetKey = 731, c = (i1: Int, i2: Int) => if (i1 == 0) Option.empty else 0))
  add(CusPlan(key = Tags.Tag126, countSetKey = 732, c = (i1: Int, i2: Int) => if (i1 > 1) 2 else Option.empty))
  add(
    CusPlan(
      key = Tags.Tag127,
      countSetKey = 733,
      c =
        (iii1: Int, iii2: Int) => ((ii1: Int, ii2: Int) => if (ii2 + 1 - ii1 > 0) Option.empty else Option(ii2 * 2 + 1 * 2)).apply(iii1, 1)
    )
  )
  add(SimpleMapPlan(key = Tags.Tag128, countSetKey = 734, setColKey = Tags.Tag177))
  add(SimpleMapPlan(key = Tags.Tag129, countSetKey = 735, setColKey = Tags.Tag1219))
  add(SimpleMapPlan(key = Tags.Tag130, countSetKey = 736, setColKey = Tags.Tag108))
  add(SimpleMapPlan(key = Tags.Tag131, countSetKey = 737, setColKey = Tags.Tag709))
  add(SimpleMapPlan(key = Tags.Tag132, countSetKey = 738, setColKey = Tags.Tag004))
  add(SimpleMapPlan(key = Tags.Tag133, countSetKey = 739, setColKey = Tags.Tag058))
  add(SimpleMapPlan(key = Tags.Tag134, countSetKey = 740, setColKey = Tags.Tag180))
  add(SimpleMapPlan(key = Tags.Tag135, countSetKey = 741, setColKey = Tags.Tag092))
  add(SimpleMapPlan(key = Tags.Tag136, countSetKey = 742, setColKey = Tags.Tag035))
  add(SimpleMapPlan(key = Tags.Tag137, countSetKey = 743, setColKey = Tags.Tag032))
  add(SimpleMapPlan(key = Tags.Tag138, countSetKey = 744, setColKey = Tags.Tag031))
  add(SimpleMapPlan(key = Tags.Tag139, countSetKey = 745, setColKey = Tags.Tag167))
  add(SimpleMapPlan(key = Tags.Tag140, countSetKey = 746, setColKey = Tags.Tag017))
  add(SimpleMapPlan(key = Tags.Tag141, countSetKey = 747, setColKey = Tags.Tag756))
  add(SimpleMapPlan(key = Tags.Tag142, countSetKey = 748, setColKey = Tags.Tag005))
  add(SimpleMapPlan(key = Tags.Tag143, countSetKey = 749, setColKey = Tags.Tag094))
  add(SimpleMapPlan(key = Tags.Tag144, countSetKey = 750, setColKey = Tags.Tag179))
  add(SimpleMapPlan(key = Tags.Tag145, countSetKey = 751, setColKey = Tags.Tag093))
  add(SimpleMapPlan(key = Tags.Tag146, countSetKey = 752, setColKey = Tags.Tag1186))
  add(SimpleMapPlan(key = Tags.Tag147, countSetKey = 753, setColKey = Tags.Tag074))
  add(SimpleMapPlan(key = Tags.Tag148, countSetKey = 754, setColKey = Tags.Tag073))
  add(SimpleMapPlan(key = Tags.Tag149, countSetKey = 755, setColKey = Tags.Tag106))
  add(SimpleMapPlan(key = Tags.Tag150, countSetKey = 756, setColKey = Tags.Tag082))
  add(SimpleMapPlan(key = Tags.Tag151, countSetKey = 757, setColKey = Tags.Tag223))
  add(SimpleMapPlan(key = Tags.Tag152, countSetKey = 758, setColKey = Tags.Tag083))
  add(SimpleMapPlan(key = Tags.Tag153, countSetKey = 759, setColKey = Tags.Tag1617))
  add(SimpleMapPlan(key = Tags.Tag154, countSetKey = 760, setColKey = Tags.Tag1659))
  add(SimpleMapPlan(key = Tags.Tag155, countSetKey = 761, setColKey = Tags.Tag1616))
  add(SimpleMapPlan(key = Tags.Tag156, countSetKey = 765, setColKey = Tags.Tag1618))
  add(SimpleMapPlan(key = Tags.Tag157, countSetKey = 769, setColKey = Tags.Tag657))
  add(SimpleMapPlan(key = Tags.Tag158, countSetKey = 770, setColKey = Tags.Tag100))
  add(SimpleMapPlan(key = Tags.Tag159, countSetKey = 774, setColKey = Tags.Tag706))
  add(SimpleMapPlan(key = Tags.Tag160, countSetKey = 775, setColKey = Tags.Tag014))
  add(SimpleMapPlan(key = Tags.Tag161, countSetKey = 776, setColKey = Tags.Tag637))
  add(SimpleMapPlan(key = Tags.Tag162, countSetKey = 777, setColKey = Tags.Tag098))
  add(SimpleMapPlan(key = Tags.Tag163, countSetKey = 779, setColKey = Tags.Tag621))
  add(SimpleMapPlan(key = Tags.Tag164, countSetKey = 780, setColKey = Tags.Tag034))
  add(SimpleMapPlan(key = Tags.Tag165, countSetKey = 781, setColKey = Tags.Tag660))
  add(SimpleMapPlan(key = Tags.Tag166, countSetKey = 782, setColKey = Tags.Tag015))
  add(SimpleMapPlan(key = Tags.Tag167, countSetKey = 785, setColKey = Tags.Tag103))
  add(SimpleMapPlan(key = Tags.Tag168, countSetKey = 786, setColKey = Tags.Tag593))
  add(SimpleMapPlan(key = Tags.Tag169, countSetKey = 787, setColKey = Tags.Tag079))
  add(SimpleMapPlan(key = Tags.Tag170, countSetKey = 789, setColKey = Tags.Tag882))
  add(SimpleMapPlan(key = Tags.Tag171, countSetKey = 790, setColKey = Tags.Tag105))
  add(SimpleMapPlan(key = Tags.Tag172, countSetKey = 792, setColKey = Tags.Tag173))
  add(SimpleMapPlan(key = Tags.Tag173, countSetKey = 799, setColKey = Tags.Tag1199))
  add(SimpleMapPlan(key = Tags.Tag174, countSetKey = 800, setColKey = Tags.Tag932))
  add(SimpleMapPlan(key = Tags.Tag175, countSetKey = 801, setColKey = Tags.Tag1570))
  add(SimpleMapPlan(key = Tags.Tag176, countSetKey = 802, setColKey = Tags.Tag113))
  add(SimpleMapPlan(key = Tags.Tag177, countSetKey = 803, setColKey = Tags.Tag028))
  add(SimpleMapPlan(key = Tags.Tag178, countSetKey = 804, setColKey = Tags.Tag1601))
  add(SimpleMapPlan(key = Tags.Tag179, countSetKey = 805, setColKey = Tags.Tag611))
  add(SimpleMapPlan(key = Tags.Tag180, countSetKey = 806, setColKey = Tags.Tag634))
  add(SimpleMapPlan(key = Tags.Tag181, countSetKey = 807, setColKey = Tags.Tag037))
  add(SimpleMapPlan(key = Tags.Tag182, countSetKey = 808, setColKey = Tags.Tag613))
  add(SimpleMapPlan(key = Tags.Tag183, countSetKey = 809, setColKey = Tags.Tag987))
  add(SimpleMapPlan(key = Tags.Tag184, countSetKey = 810, setColKey = Tags.Tag767))
  add(CusPlan(key = Tags.Tag185, countSetKey = 811, c = (i1: Int, i2: Int) => if (i1 < 2) 2 else i1))
  add(CusPlan(key = Tags.Tag186, countSetKey = 812, c = (i1: Int, i2: Int) => if (i1 <= i2) i2 + 1 else i2))
  add(SimpleMapPlan(key = Tags.Tag187, countSetKey = 813, setColKey = Tags.Tag678))
  add(SimpleMapPlan(key = Tags.Tag188, countSetKey = 814, setColKey = Tags.Tag761))
  add(SimpleMapPlan(key = Tags.Tag189, countSetKey = 815, setColKey = Tags.Tag600))
  add(SimpleMapPlan(key = Tags.Tag190, countSetKey = 816, setColKey = Tags.Tag607))
  add(SimpleMapPlan(key = Tags.Tag191, countSetKey = 817, setColKey = Tags.Tag667))
  add(SimpleMapPlan(key = Tags.Tag192, countSetKey = 818, setColKey = Tags.Tag619))
  add(SimpleMapPlan(key = Tags.Tag193, countSetKey = 819, setColKey = Tags.Tag1414))
  add(SimpleMapPlan(key = Tags.Tag194, countSetKey = 820, setColKey = Tags.Tag831))
  add(SimpleMapPlan(key = Tags.Tag195, countSetKey = 821, setColKey = Tags.Tag815))
  add(SimpleMapPlan(key = Tags.Tag196, countSetKey = 822, setColKey = Tags.Tag599))
  add(SimpleMapPlan(key = Tags.Tag197, countSetKey = 823, setColKey = Tags.Tag586))
  add(SimpleMapPlan(key = Tags.Tag198, countSetKey = 824, setColKey = Tags.Tag228))
  add(SimpleMapPlan(key = Tags.Tag199, countSetKey = 825, setColKey = Tags.Tag070))
  add(SimpleMapPlan(key = Tags.Tag200, countSetKey = 826, setColKey = Tags.Tag1635))
  add(SimpleMapPlan(key = Tags.Tag201, countSetKey = 827, setColKey = Tags.Tag754))
  add(SimpleMapPlan(key = Tags.Tag202, countSetKey = 828, setColKey = Tags.Tag875))
  add(SimpleMapPlan(key = Tags.Tag203, countSetKey = 829, setColKey = Tags.Tag826))
  add(SimpleMapPlan(key = Tags.Tag204, countSetKey = 830, setColKey = Tags.Tag688))
  add(SimpleMapPlan(key = Tags.Tag205, countSetKey = 831, setColKey = Tags.Tag573))
  add(SimpleMapPlan(key = Tags.Tag206, countSetKey = 832, setColKey = Tags.Tag936))
  add(SimpleMapPlan(key = Tags.Tag207, countSetKey = 833, setColKey = Tags.Tag760))
  add(CusPlan(key = Tags.Tag208, countSetKey = 834, c = (i1: Int, i2: Int) => if (i1 == 1) 3 else i1 + 1))
  add(SimpleMapPlan(key = Tags.Tag209, countSetKey = 835, setColKey = Tags.Tag682))
  add(SimpleMapPlan(key = Tags.Tag210, countSetKey = 836, setColKey = Tags.Tag1573))
  add(SimpleMapPlan(key = Tags.Tag211, countSetKey = 837, setColKey = Tags.Tag1437))
  add(SimpleMapPlan(key = Tags.Tag212, countSetKey = 838, setColKey = Tags.Tag711))
  add(SimpleMapPlan(key = Tags.Tag213, countSetKey = 839, setColKey = Tags.Tag915))
  add(SimpleMapPlan(key = Tags.Tag214, countSetKey = 841, setColKey = Tags.Tag645))
  add(SimpleMapPlan(key = Tags.Tag215, countSetKey = 842, setColKey = Tags.Tag580))
  add(SimpleMapPlan(key = Tags.Tag216, countSetKey = 844, setColKey = Tags.Tag734))
  add(SimpleMapPlan(key = Tags.Tag217, countSetKey = 845, setColKey = Tags.Tag1589))
  add(CusPlan(key = Tags.Tag218, countSetKey = 846, c = (iii1: Int, iii2: Int) => if (iii1 - iii2 > 0) iii2 * 2 + 1 else iii1 * 2 + 1))
  add(SimpleMapPlan(key = Tags.Tag219, countSetKey = 847, setColKey = Tags.Tag099))
  add(SimpleMapPlan(key = Tags.Tag220, countSetKey = 848, setColKey = Tags.Tag865))
  add(SimpleMapPlan(key = Tags.Tag221, countSetKey = 849, setColKey = Tags.Tag351))
  add(SimpleMapPlan(key = Tags.Tag222, countSetKey = 850, setColKey = Tags.Tag819))
  add(SimpleMapPlan(key = Tags.Tag223, countSetKey = 851, setColKey = Tags.Tag836))
  add(SimpleMapPlan(key = Tags.Tag224, countSetKey = 852, setColKey = Tags.Tag378))
  add(CusPlan(key = Tags.Tag225, countSetKey = 853, c = (iii1: Int, iii2: Int) => if (iii1 == 0) 1 else iii1 * 2 + iii2 + 1))
  add(SimpleMapPlan(key = Tags.Tag226, countSetKey = 854, setColKey = Tags.Tag430))
  add(SimpleMapPlan(key = Tags.Tag227, countSetKey = 856, setColKey = Tags.Tag1401))
  add(SimpleMapPlan(key = Tags.Tag228, countSetKey = 857, setColKey = Tags.Tag907))
  add(SimpleMapPlan(key = Tags.Tag229, countSetKey = 858, setColKey = Tags.Tag488))
  add(SimpleMapPlan(key = Tags.Tag230, countSetKey = 860, setColKey = Tags.Tag644))
  add(SimpleMapPlan(key = Tags.Tag231, countSetKey = 861, setColKey = Tags.Tag902))
  add(SimpleMapPlan(key = Tags.Tag232, countSetKey = 862, setColKey = Tags.Tag633))
  add(SimpleMapPlan(key = Tags.Tag233, countSetKey = 863, setColKey = Tags.Tag313))
  add(SimpleMapPlan(key = Tags.Tag234, countSetKey = 864, setColKey = Tags.Tag771))
  add(SimpleMapPlan(key = Tags.Tag235, countSetKey = 865, setColKey = Tags.Tag549))
  add(SimpleMapPlan(key = Tags.Tag236, countSetKey = 866, setColKey = Tags.Tag825))
  add(SimpleMapPlan(key = Tags.Tag237, countSetKey = 867, setColKey = Tags.Tag743))
  add(SimpleMapPlan(key = Tags.Tag238, countSetKey = 868, setColKey = Tags.Tag804))
  add(SimpleMapPlan(key = Tags.Tag239, countSetKey = 869, setColKey = Tags.Tag751))
  add(SimpleMapPlan(key = Tags.Tag240, countSetKey = 870, setColKey = Tags.Tag1377))
  add(SimpleMapPlan(key = Tags.Tag241, countSetKey = 871, setColKey = Tags.Tag741))
  add(SimpleMapPlan(key = Tags.Tag242, countSetKey = 872, setColKey = Tags.Tag352))
  add(SimpleMapPlan(key = Tags.Tag243, countSetKey = 874, setColKey = Tags.Tag707))
  add(SimpleMapPlan(key = Tags.Tag244, countSetKey = 875, setColKey = Tags.Tag924))
  add(SimpleMapPlan(key = Tags.Tag245, countSetKey = 877, setColKey = Tags.Tag1400))
  add(SimpleMapPlan(key = Tags.Tag246, countSetKey = 878, setColKey = Tags.Tag697))
  add(SimpleMapPlan(key = Tags.Tag247, countSetKey = 879, setColKey = Tags.Tag618))
  add(SimpleMapPlan(key = Tags.Tag248, countSetKey = 880, setColKey = Tags.Tag1624))
  add(SimpleMapPlan(key = Tags.Tag249, countSetKey = 881, setColKey = Tags.Tag1030))
  add(SimpleMapPlan(key = Tags.Tag250, countSetKey = 882, setColKey = Tags.Tag1159))
  add(SimpleMapPlan(key = Tags.Tag251, countSetKey = 883, setColKey = Tags.Tag1593))
  add(SimpleMapPlan(key = Tags.Tag252, countSetKey = 884, setColKey = Tags.Tag1034))
  add(SimpleMapPlan(key = Tags.Tag253, countSetKey = 885, setColKey = Tags.Tag030))
  add(SimpleMapPlan(key = Tags.Tag254, countSetKey = 886, setColKey = Tags.Tag1113))
  add(SimpleMapPlan(key = Tags.Tag255, countSetKey = 887, setColKey = Tags.Tag1032))
  add(SimpleMapPlan(key = Tags.Tag256, countSetKey = 888, setColKey = Tags.Tag1631))
  add(SimpleMapPlan(key = Tags.Tag257, countSetKey = 889, setColKey = Tags.Tag1031))
  add(SimpleMapPlan(key = Tags.Tag258, countSetKey = 890, setColKey = Tags.Tag229))
  add(SimpleMapPlan(key = Tags.Tag259, countSetKey = 891, setColKey = Tags.Tag1591))
  add(SimpleMapPlan(key = Tags.Tag260, countSetKey = 892, setColKey = Tags.Tag1035))
  add(SimpleMapPlan(key = Tags.Tag261, countSetKey = 893, setColKey = Tags.Tag232))
  add(SimpleMapPlan(key = Tags.Tag262, countSetKey = 894, setColKey = Tags.Tag233))
  add(SimpleMapPlan(key = Tags.Tag263, countSetKey = 895, setColKey = Tags.Tag239))
  add(SimpleMapPlan(key = Tags.Tag264, countSetKey = 896, setColKey = Tags.Tag312))
  add(SimpleMapPlan(key = Tags.Tag265, countSetKey = 897, setColKey = Tags.Tag1459))
  add(SimpleMapPlan(key = Tags.Tag266, countSetKey = 898, setColKey = Tags.Tag1033))
  add(SimpleMapPlan(key = Tags.Tag267, countSetKey = 899, setColKey = Tags.Tag230))
  add(SimpleMapPlan(key = Tags.Tag268, countSetKey = 900, setColKey = Tags.Tag231))
  add(SimpleMapPlan(key = Tags.Tag269, countSetKey = 901, setColKey = Tags.Tag174))
  add(SimpleMapPlan(key = Tags.Tag270, countSetKey = 902, setColKey = Tags.Tag062))
  add(SimpleMapPlan(key = Tags.Tag271, countSetKey = 903, setColKey = Tags.Tag170))
  add(SimpleMapPlan(key = Tags.Tag272, countSetKey = 904, setColKey = Tags.Tag219))
  add(SimpleMapPlan(key = Tags.Tag273, countSetKey = 905, setColKey = Tags.Tag659))
  add(SimpleMapPlan(key = Tags.Tag274, countSetKey = 906, setColKey = Tags.Tag948))
  add(SimpleMapPlan(key = Tags.Tag275, countSetKey = 907, setColKey = Tags.Tag1266))
  add(SimpleMapPlan(key = Tags.Tag276, countSetKey = 908, setColKey = Tags.Tag1238))
  add(SimpleMapPlan(key = Tags.Tag277, countSetKey = 909, setColKey = Tags.Tag008))
  add(SimpleMapPlan(key = Tags.Tag278, countSetKey = 911, setColKey = Tags.Tag010))
  add(SimpleMapPlan(key = Tags.Tag279, countSetKey = 912, setColKey = Tags.Tag077))
  add(SimpleMapPlan(key = Tags.Tag280, countSetKey = 914, setColKey = Tags.Tag561))
  add(SimpleMapPlan(key = Tags.Tag281, countSetKey = 915, setColKey = Tags.Tag949))
  add(SimpleMapPlan(key = Tags.Tag282, countSetKey = 916, setColKey = Tags.Tag009))
  add(SimpleMapPlan(key = Tags.Tag283, countSetKey = 917, setColKey = Tags.Tag175))
  add(SimpleMapPlan(key = Tags.Tag284, countSetKey = 918, setColKey = Tags.Tag172))
  add(SimpleMapPlan(key = Tags.Tag285, countSetKey = 919, setColKey = Tags.Tag072))
  add(SimpleMapPlan(key = Tags.Tag286, countSetKey = 920, setColKey = Tags.Tag081))
  add(CusPlan(key = Tags.Tag287, countSetKey = 921, c = (iii1: Int, iii2: Int) => if (iii1 == 0) 0 else iii1 * 2 + iii2))
  add(SimpleMapPlan(key = Tags.Tag288, countSetKey = 922, setColKey = Tags.Tag061))
  add(SimpleMapPlan(key = Tags.Tag289, countSetKey = 924, setColKey = Tags.Tag065))
  add(SimpleMapPlan(key = Tags.Tag290, countSetKey = 926, setColKey = Tags.Tag169))
  add(SimpleMapPlan(key = Tags.Tag291, countSetKey = 927, setColKey = Tags.Tag078))
  add(SimpleMapPlan(key = Tags.Tag292, countSetKey = 928, setColKey = Tags.Tag066))
  add(SimpleMapPlan(key = Tags.Tag293, countSetKey = 929, setColKey = Tags.Tag020))
  add(SimpleMapPlan(key = Tags.Tag294, countSetKey = 930, setColKey = Tags.Tag012))
  add(SimpleMapPlan(key = Tags.Tag295, countSetKey = 931, setColKey = Tags.Tag218))
  add(SimpleMapPlan(key = Tags.Tag296, countSetKey = 933, setColKey = Tags.Tag011))
  add(SimpleMapPlan(key = Tags.Tag297, countSetKey = 935, setColKey = Tags.Tag036))
  add(SimpleMapPlan(key = Tags.Tag298, countSetKey = 936, setColKey = Tags.Tag1595))
  add(SimpleMapPlan(key = Tags.Tag299, countSetKey = 937, setColKey = Tags.Tag1094))
  add(SimpleMapPlan(key = Tags.Tag300, countSetKey = 938, setColKey = Tags.Tag366))
  add(SimpleMapPlan(key = Tags.Tag301, countSetKey = 939, setColKey = Tags.Tag673))
  add(SimpleMapPlan(key = Tags.Tag302, countSetKey = 940, setColKey = Tags.Tag662))
  add(SimpleMapPlan(key = Tags.Tag303, countSetKey = 941, setColKey = Tags.Tag759))
  add(SimpleMapPlan(key = Tags.Tag304, countSetKey = 942, setColKey = Tags.Tag057))
  add(SimpleMapPlan(key = Tags.Tag305, countSetKey = 943, setColKey = Tags.Tag716))
  add(SimpleMapPlan(key = Tags.Tag306, countSetKey = 944, setColKey = Tags.Tag652))
  add(CusPlan(key = Tags.Tag307, countSetKey = 945, c = (iii1: Int, iii2: Int) => if (iii1 == 0) Option.empty else Option(iii2 + 3)))
  add(SimpleMapPlan(key = Tags.Tag308, countSetKey = 946, setColKey = Tags.Tag913))
  add(SimpleMapPlan(key = Tags.Tag309, countSetKey = 947, setColKey = Tags.Tag107))
  add(SimpleMapPlan(key = Tags.Tag310, countSetKey = 948, setColKey = Tags.Tag046))
  add(SimpleMapPlan(key = Tags.Tag311, countSetKey = 949, setColKey = Tags.Tag799))
  add(SimpleMapPlan(key = Tags.Tag312, countSetKey = 950, setColKey = Tags.Tag047))
  add(SimpleMapPlan(key = Tags.Tag313, countSetKey = 951, setColKey = Tags.Tag1200))
  add(SimpleMapPlan(key = Tags.Tag314, countSetKey = 952, setColKey = Tags.Tag039))
  add(SimpleMapPlan(key = Tags.Tag315, countSetKey = 953, setColKey = Tags.Tag567))
  add(SimpleMapPlan(key = Tags.Tag316, countSetKey = 954, setColKey = Tags.Tag001))
  add(SimpleMapPlan(key = Tags.Tag317, countSetKey = 955, setColKey = Tags.Tag606))
  add(SimpleMapPlan(key = Tags.Tag318, countSetKey = 956, setColKey = Tags.Tag202))
  add(SimpleMapPlan(key = Tags.Tag319, countSetKey = 957, setColKey = Tags.Tag569))
  add(SimpleMapPlan(key = Tags.Tag320, countSetKey = 958, setColKey = Tags.Tag156))
  add(CusPlan(key = Tags.Tag321, countSetKey = 959, c = (i1: Int, i2: Int) => i1 * i2 + 2 * i2 + i1))
  add(SimpleMapPlan(key = Tags.Tag322, countSetKey = 960, setColKey = Tags.Tag609))
  add(CusPlan(key = Tags.Tag323, countSetKey = 961, c = (i1: Int, i2: Int) => i1 * i2 + 2 * i2 + i1 + 1))
  add(SimpleMapPlan(key = Tags.Tag324, countSetKey = 962, setColKey = Tags.Tag067))
  add(SimpleMapPlan(key = Tags.Tag325, countSetKey = 963, setColKey = Tags.Tag582))
  add(SimpleMapPlan(key = Tags.Tag326, countSetKey = 964, setColKey = Tags.Tag201))
  add(SimpleMapPlan(key = Tags.Tag327, countSetKey = 969, setColKey = Tags.Tag200))
  add(SimpleMapPlan(key = Tags.Tag328, countSetKey = 979, setColKey = Tags.Tag1398))
  add(SimpleMapPlan(key = Tags.Tag329, countSetKey = 982, setColKey = Tags.Tag242))
  add(CusPlan(key = Tags.Tag330, countSetKey = 986, c = (iii1: Int, iii2: Int) => if (iii2 == 0) Option(iii1 + 1) else Option.empty))
  add(SimpleMapPlan(key = Tags.Tag331, countSetKey = 987, setColKey = Tags.Tag225))
  add(SimpleMapPlan(key = Tags.Tag332, countSetKey = 988, setColKey = Tags.Tag1321))
  add(SimpleMapPlan(key = Tags.Tag333, countSetKey = 990, setColKey = Tags.Tag558))
  add(SimpleMapPlan(key = Tags.Tag334, countSetKey = 991, setColKey = Tags.Tag182))
  add(SimpleMapPlan(key = Tags.Tag335, countSetKey = 993, setColKey = Tags.Tag617))
  add(SimpleMapPlan(key = Tags.Tag336, countSetKey = 994, setColKey = Tags.Tag181))
  add(SimpleMapPlan(key = Tags.Tag337, countSetKey = 996, setColKey = Tags.Tag643))
  add(SimpleMapPlan(key = Tags.Tag338, countSetKey = 997, setColKey = Tags.Tag203))
  add(SimpleMapPlan(key = Tags.Tag339, countSetKey = 999, setColKey = Tags.Tag881))
  add(SimpleMapPlan(key = Tags.Tag340, countSetKey = 1000, setColKey = Tags.Tag211))
  add(SimpleMapPlan(key = Tags.Tag341, countSetKey = 1003, setColKey = Tags.Tag900))
  add(SimpleMapPlan(key = Tags.Tag342, countSetKey = 1004, setColKey = Tags.Tag084))
  add(SimpleMapPlan(key = Tags.Tag343, countSetKey = 1005, setColKey = Tags.Tag427))
  add(SimpleMapPlan(key = Tags.Tag344, countSetKey = 1006, setColKey = Tags.Tag1259))
  add(SimpleMapPlan(key = Tags.Tag345, countSetKey = 1007, setColKey = Tags.Tag1302))
  add(SimpleMapPlan(key = Tags.Tag346, countSetKey = 1011, setColKey = Tags.Tag044))
  add(SimpleMapPlan(key = Tags.Tag347, countSetKey = 1012, setColKey = Tags.Tag1082))
  add(CusPlan(key = Tags.Tag348, countSetKey = 1014, c = (iii1: Int, iii2: Int) => if (iii1 <= 2) 0 else iii1 - 2))
  add(SimpleMapPlan(key = Tags.Tag349, countSetKey = 1018, setColKey = Tags.Tag246))
  add(SimpleMapPlan(key = Tags.Tag350, countSetKey = 1021, setColKey = Tags.Tag021))
  add(SimpleMapPlan(key = Tags.Tag351, countSetKey = 1024, setColKey = Tags.Tag002))
  add(SimpleMapPlan(key = Tags.Tag352, countSetKey = 1025, setColKey = Tags.Tag1554))
  add(SimpleMapPlan(key = Tags.Tag353, countSetKey = 1026, setColKey = Tags.Tag112))
  add(SimpleMapPlan(key = Tags.Tag354, countSetKey = 1027, setColKey = Tags.Tag750))
  add(SimpleMapPlan(key = Tags.Tag355, countSetKey = 1028, setColKey = Tags.Tag111))
  add(SimpleMapPlan(key = Tags.Tag356, countSetKey = 1030, setColKey = Tags.Tag702))
  add(SimpleMapPlan(key = Tags.Tag357, countSetKey = 1031, setColKey = Tags.Tag023))
  add(SimpleMapPlan(key = Tags.Tag358, countSetKey = 1033, setColKey = Tags.Tag639))
  add(SimpleMapPlan(key = Tags.Tag359, countSetKey = 1034, setColKey = Tags.Tag226))
  add(SimpleMapPlan(key = Tags.Tag360, countSetKey = 1035, setColKey = Tags.Tag935))
  add(SimpleMapPlan(key = Tags.Tag361, countSetKey = 1036, setColKey = Tags.Tag013))
  add(SimpleMapPlan(key = Tags.Tag362, countSetKey = 1038, setColKey = Tags.Tag727))
  add(SimpleMapPlan(key = Tags.Tag363, countSetKey = 1039, setColKey = Tags.Tag114))
  add(SimpleMapPlan(key = Tags.Tag364, countSetKey = 1041, setColKey = Tags.Tag601))
  add(SimpleMapPlan(key = Tags.Tag365, countSetKey = 1042, setColKey = Tags.Tag042))
  add(SimpleMapPlan(key = Tags.Tag366, countSetKey = 1043, setColKey = Tags.Tag1272))
  add(SimpleMapPlan(key = Tags.Tag367, countSetKey = 1050, setColKey = Tags.Tag166))
  add(SimpleMapPlan(key = Tags.Tag368, countSetKey = 1053, setColKey = Tags.Tag018))
  add(SimpleMapPlan(key = Tags.Tag369, countSetKey = 1054, setColKey = Tags.Tag791))
  add(SimpleMapPlan(key = Tags.Tag370, countSetKey = 1062, setColKey = Tags.Tag1638))
  add(SimpleMapPlan(key = Tags.Tag371, countSetKey = 1064, setColKey = Tags.Tag1588))
  add(
    CusPlan(
      key = Tags.Tag372,
      countSetKey = 1066,
      c = (iii1: Int, iii2: Int) =>
        if (iii1 == 0 && iii2 == 0) 1 else if (iii1 == 0) iii2 + 1 else if (iii2 == 0) iii1 - 1 else iii1 + iii2 - 1
    )
  )
  add(SimpleMapPlan(key = Tags.Tag373, countSetKey = 1067, setColKey = Tags.Tag849))
  add(SimpleMapPlan(key = Tags.Tag374, countSetKey = 1068, setColKey = Tags.Tag1641))
  add(SimpleMapPlan(key = Tags.Tag375, countSetKey = 1069, setColKey = Tags.Tag892))
  add(SimpleMapPlan(key = Tags.Tag376, countSetKey = 1070, setColKey = Tags.Tag914))
  add(SimpleMapPlan(key = Tags.Tag377, countSetKey = 1071, setColKey = Tags.Tag1585))
  add(SimpleMapPlan(key = Tags.Tag378, countSetKey = 1072, setColKey = Tags.Tag642))
  add(SimpleMapPlan(key = Tags.Tag379, countSetKey = 1073, setColKey = Tags.Tag616))
  add(SimpleMapPlan(key = Tags.Tag380, countSetKey = 1074, setColKey = Tags.Tag234))
  add(SimpleMapPlan(key = Tags.Tag381, countSetKey = 1076, setColKey = Tags.Tag625))
  add(SimpleMapPlan(key = Tags.Tag382, countSetKey = 1077, setColKey = Tags.Tag929))
  add(SimpleMapPlan(key = Tags.Tag383, countSetKey = 1078, setColKey = Tags.Tag1649))
  add(SimpleMapPlan(key = Tags.Tag384, countSetKey = 1080, setColKey = Tags.Tag817))
  add(SimpleMapPlan(key = Tags.Tag385, countSetKey = 1082, setColKey = Tags.Tag1597))
  add(SimpleMapPlan(key = Tags.Tag386, countSetKey = 1083, setColKey = Tags.Tag808))
  add(SimpleMapPlan(key = Tags.Tag387, countSetKey = 1086, setColKey = Tags.Tag1101))
  add(SimpleMapPlan(key = Tags.Tag388, countSetKey = 1087, setColKey = Tags.Tag971))
  add(SimpleMapPlan(key = Tags.Tag389, countSetKey = 1088, setColKey = Tags.Tag395))
  add(SimpleMapPlan(key = Tags.Tag390, countSetKey = 1089, setColKey = Tags.Tag846))
  add(SimpleMapPlan(key = Tags.Tag391, countSetKey = 1090, setColKey = Tags.Tag747))
  add(SimpleMapPlan(key = Tags.Tag392, countSetKey = 1091, setColKey = Tags.Tag858))
  add(SimpleMapPlan(key = Tags.Tag393, countSetKey = 1092, setColKey = Tags.Tag235))
  add(SimpleMapPlan(key = Tags.Tag394, countSetKey = 1093, setColKey = Tags.Tag1647))
  add(SimpleMapPlan(key = Tags.Tag395, countSetKey = 1094, setColKey = Tags.Tag1558))
  add(SimpleMapPlan(key = Tags.Tag396, countSetKey = 1095, setColKey = Tags.Tag844))
  add(SimpleMapPlan(key = Tags.Tag397, countSetKey = 1096, setColKey = Tags.Tag532))
  add(SimpleMapPlan(key = Tags.Tag398, countSetKey = 1098, setColKey = Tags.Tag789))
  add(
    CusPlan(
      key = Tags.Tag399,
      countSetKey = 1099,
      c = (i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) Option.empty else if (i1 % i2 == 0) i1 / i2 else i1 / i2 + 1
    )
  )
  add(
    CusPlan(key = Tags.Tag400, countSetKey = 1101, c = (i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) 0 else if (i1 == 0) 2 else 1)
  )
  add(CusPlan(key = Tags.Tag401, countSetKey = 1102, c = (i1: Int, i2: Int) => if (i1 - i2 - 1 >= 0) i1 - i2 else 1))
  add(
    CusPlan(
      key = Tags.Tag402,
      countSetKey = 1103,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 1
        else if (i1 == 0) 0 * i2 + 1
        else if (i2 == 0) 1 * i1 + -1
        else if (i1 == i2) -2 * i1 + 2 * i2 + 1
        else if (i1 < i2) 0 * i1 + 0 * i2 + 1
        else 1 * i1 + -1 * i2 + -1
    )
  )
  add(CusPlan(key = Tags.Tag403, countSetKey = 1105, c = (i1: Int, i2: Int) => if (i2 - i1 >= 0) i2 else i1 - 1))
  add(CusPlan(key = Tags.Tag404, countSetKey = 1106, c = (i1: Int, i2: Int) => if (i1 == 0) i2 else i1 + i2 - 1))
  add(CusPlan(key = Tags.Tag405, countSetKey = 1107, c = (i1: Int, i2: Int) => if (i1 == 1) 0 else 1))
  add(CusPlan(key = Tags.Tag406, countSetKey = 1108, c = (i1: Int, i2: Int) => if (i1 == 0) 1 else if (i1 == 1) 0 else i1 - 2))
  add(
    CusPlan(
      key = Tags.Tag407,
      countSetKey = 1109,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 1 * i2 + 0
        else if (i2 == 0) 1 * i1 + 0
        else if (i1 == i2) -2 * i1 + 2 * i2 + 0
        else if (i1 < i2) -1 * i1 + 1 * i2 + 0
        else 1 * i1 + -1 * i2 + 0
    )
  )
  add(
    CusPlan(
      key = Tags.Tag408,
      countSetKey = 1110,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) 0
        else if (i1 == 0) 1 * i2 + 0
        else if (i2 == 0) 0 * i1 + 1
        else if (i1 == i2) -2 * i1 + 2 * i2 + 0
        else if (i1 < i2) -1 * i1 + 1 * i2 + 0
        else 0 * i1 + 0 * i2 + 1
    )
  )
  add(CusPlan(key = Tags.Tag409, countSetKey = 1111, c = (i1: Int, i2: Int) => if (i1 - i2 - 1 >= 0) i1 - i2 - 1 else i2 - i1))
  add(CusPlan(key = Tags.Tag410, countSetKey = 1112, c = (i1: Int, i2: Int) => if (i1 == 0) i2 else i1 - 1))
  add(CusPlan(key = Tags.Tag411, countSetKey = 1113, c = (i1: Int, i2: Int) => if (i1 % 2 == 0) i1 / 2 else i1 / 2 + 1))
  add(
    CusPlan(key = Tags.Tag412, countSetKey = 1114, c = (i1: Int, i2: Int) => if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) else i1 / (i2 + 1) + 1)
  )
  add(CusPlan(key = Tags.Tag413, countSetKey = 1116, c = (i1: Int, i2: Int) => i1 / (i2 + 1) * i2 + i1 % (i2 + 1)))
  add(CusPlan(key = Tags.Tag414, countSetKey = 1118, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 - i1 >= 0) i1 else i1 - 1))
  add(
    CusPlan(
      key = Tags.Tag415,
      countSetKey = 1119,
      c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) Option.empty else if (i1 % i2 == 0) i1 / i2 else i1 / i2 + 1
    )
  )
  add(CusPlan(key = Tags.Tag416, countSetKey = 1121, c = (i1: Int, i2: Int) => i1 / 2))
  add(
    CusPlan(
      key = Tags.Tag417,
      countSetKey = 1122,
      c = (i1: Int, i2: Int) => if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
    )
  )
  add(CusPlan(key = Tags.Tag418, countSetKey = 1123, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else (i1 - 1) * i2))
  add(CusPlan(key = Tags.Tag419, countSetKey = 1124, c = (i1: Int, i2: Int) => i1 / (i2 + 1)))

  add(CusPlan(key = Tags.Tag420, countSetKey = 965, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 == 1) 4 else i1 + 2))

  add(
    CusPlan(
      key = Tags.Tag421,
      countSetKey = 1058,
      c = (i1: Int, i2: Int) => if (i1 == 0 || i2 == 0 || i1 > i2) 0 else if (i1 == i2) i2 - 1 else NotImplemented
    )
  )

  add(
    CusPlan(
      key = Tags.Tag422,
      countSetKey = 977,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0) 0
        else if (i2 == 0) i1 + 1
        else if (i1 == i2) i1 * 3 + 1
        else if (i1 > i2) i1 + i2 + 1
        else i1 + i2 + i1 * (i2 / i1) + 1
    )
  )

  add(
    CusPlan(
      key = Tags.Tag423,
      countSetKey = 762,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 || i2 == 0) 0
        else if (i1 == i2) i1 * 3
        else if (i1 > i2)
          if (i2 % (i1 + 1) == 0) 2 * i2 - i2 / (i1 + 1) + 1 else 2 * i2 - i2 / (i1 + 1)
        else
          i1 * (i2 / i1) + 2 * i2
    )
  )

  // =================================
  add(
    CusPlan(
      key = Tags.Tag424,
      countSetKey = 873,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1                         % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 + 1
          else (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 - i2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag425,
      countSetKey = 677,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i2 == 0) 1 * i1 + 0
          else if (i1 == i2) 0 * i1 + 2 * i2 + -1
          else if (i1 < i2) 2 * i1 + 0 * i2 + -1
          else 1 * i1 + 1 * i2 + 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i2 == 0) 1 * i1 + 0
          else if (i1 == i2) 0 * i1 + 2 * i2 + -1
          else if (i1 < i2) 2 * i1 + 0 * i2 + -1
          else 1 * i1 + 1 * i2 + 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag426,
      countSetKey = 797,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          2
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1) % (2 * i1) <= i1) (i2 + 1) % (2 * i1) + ((i2 + 1) / (2 * i1)) * i1 + 1
          else i1 + ((i2 + 1) / (2 * i1)) * i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 % (i1 + 1) == 0) i2 / (i1 + 1) + i2 else i2 / (i1 + 1) + i2 + 2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag427,
      countSetKey = 913,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 % (i2 + 1) == 0) i1 * 2 - i1 / (i2 + 1) + 1 else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 % (i2 + 1) == 0) i1 * 2 - i1 / (i2 + 1) + 1 else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 else (i1 / (i2 * 2)) * i2 + i1 + i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag428,
      countSetKey = 1063,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 +
            1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag429,
      countSetKey = 1001,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 *
            i2 + 2 * i1 - i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 * i2 + i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i1 * i2 + i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag430,
      countSetKey = 973,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if (i2 == 0) i1 + 2 else if (i1 == 0) 2 else 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 * 2 + 2
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i2 + i2 * (i1 / i2) + 2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i1 + i2 + i1 * (i2 / i1) + 2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag431,
      countSetKey = 1046,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1)  % (2 * i1) <= i1) i2 + ((i2 + 1) / (2 * i1)) * i1
          else (i2 + 1) % (2 * i1) - i1 + i2 + ((i2 + 1) / (2 * i1)) * i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag432,
      countSetKey = 934,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 0 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + i1 else (i1 / i2) * i2 + i1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag433,
      countSetKey = 1059,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 / i1 * i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 <= i2) Option.empty else 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag434,
      countSetKey = 1055,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          Option.empty
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          Option.empty
        } else if (
          i1 > 0 && i2 > 0 &&
          i1 == i2
        ) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag435,
      countSetKey = 724,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          2
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 % (i1 + 1) == 0) i2 / (i1 + 1) + i2 else i2 / (i1 + 1) + i2 + 2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag436,
      countSetKey = 1013,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag437,
      countSetKey = 1115,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag438,
      countSetKey = 1100,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 >
              i2
          ) Option.empty
          else 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 > i2) Option.empty else 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + 1 else (i1 / i2) * i2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag439,
      countSetKey = 1040,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1
          else if (i2 == 0) i1 + 1
          else 0
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag440,
      countSetKey = 1020,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 1
          else if (i2 == 0) i1 + 1
          else 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag441,
      countSetKey = 966,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if ((1 + 1) % i1 == 0) ((1 + 1) / i1) * i1 + 2 * (1 + 1) else ((1 + 1) / i1) * i1 + i1 + 2 * (1 + 1)
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if ((1 + 1) % i1 == 0) ((1 + 1) / i1) * i1 + 2 * (1 + 1) else ((1 + 1) / i1) * i1 + i1 + 2 * (1 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((1 + 1) % i1 == 0) ((1 + 1) / i1) * i1 + 2 * (1 + 1)
          else ((1 + 1) / i1) * i1 + i1 + 2 * (1 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if ((1 + 1) % i1 == 0) ((1 + 1) / i1) * i1 + 2 * (1 + 1) else ((1 + 1) / i1) * i1 + i1 + 2 * (1 + 1)
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag442,
      countSetKey = 1051,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          Option.empty
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          Option.empty
        } else if (
          i1 > 0 && i2 > 0 &&
          i1 == i2
        ) {
          if (i2 == 0) Option(i1 + 1) else Option.empty
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 <= i2) Option.empty else 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag443,
      countSetKey = 998,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag444,
      countSetKey = 1104,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag445,
      countSetKey = 981,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 - 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0) Option.empty
          else i1 * 2 + i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) i2 + 1 else if (i2 == 0) i1 - 1 else i1 + i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag446,
      countSetKey = 766,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 % (i2 + 1) == 0) i1 * 2 - i1 / (i2 + 1) + 1 else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 + i2 / i1 * i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag447,
      countSetKey = 793,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i2 ==
              0 && i1 == 0
          ) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 % (i1 + 1) == 0) i2 * 2 - i2 / (i1 + 1) + 1 else i2 * 2 - i2 / (i1 + 1)
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag448,
      countSetKey = 925,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 % (i2 + 1) == 0) i1 * 2 - i1 / (i2 + 1) + 1 else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 + 0 * i1 + -1
          else 1 * i2 + 1 * i1 + 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % i2 == 0) (i1 / i2) * i2 + i1 else (i1 / i2) * i2 + i2 + i1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag449,
      countSetKey = 1047,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 1
          else if (i2 == 0) i1 + 1
          else 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag450,
      countSetKey = 798,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1) % (2 * i1) <= i1) (i2 + 1) % (2 * i1) + ((i2 + 1) / (2 * i1)) * i1
          else i1 + ((i2 + 1) / (2 * i1)) * i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag451,
      countSetKey = 1057,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i2 ==
              0 && i1 == 0
          ) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag452,
      countSetKey = 910,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 % (i2 + 1) == 0) i1 * 2 - i1 / (i2 + 1) + 1 else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 % (i2 + 1) == 0) i1 * 2 - i1 / (i2 + 1) + 1 else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 0 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + 2 * i1 else (i1 / i2) * i2 + 2 * i1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag453,
      countSetKey = 778,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag454,
      countSetKey = 788,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag455,
      countSetKey = 1032,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag456,
      countSetKey = 1079,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag457,
      countSetKey = 974,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if (i1 == 1) 3 else i1 + 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (
          i1 > 0 && i2 > 0
          && i1 == i2
        ) {
          if (i1 == 0 && i2 == 0) 1
          else if (i1 == 0) i2 * 0 / -3 + 1
          else if (i1 == 1) 0 * i1 + 0 * i2 + 3
          else if (i2 == 0) i1 * -2 / -1 + 1
          else if (i2 == 1) 1 * i1 + -2 * i2 + 3
          else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
          else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
          else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) 1
          else if (i1 == 0) i2 * 0 / -3 + 1
          else if (i1 == 1) 0 * i1 + 0 * i2 + 3
          else if (i2 == 0) i1 * -2 / -1 + 1
          else if (i2 == 1) 1 * i1 + -2 * i2 + 3
          else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
          else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
          else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 1
          else if (i1 == 0) i2 * 0 / -3 + 1
          else if (i1 == 1) 0 * i1 + 0 * i2 + 3
          else if (i2 == 0) i1 * -2 / -1 + 1
          else if (i2 == 1) 1 * i1 + -2 * i2 + 3
          else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
          else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
          else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag458,
      countSetKey = 1017,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag459,
      countSetKey = 980,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if (i1 < 2) 2 else i1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (
          i1 > 0 && i2 > 0 && i1
            == i2
        ) {
          if (i1 == 1 && i2 == 0) Option.empty else if (i1 == 1) 2 else if (i2 == 0) Option.empty else i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 1 && i2 == 0) Option.empty
          else if (i1 == 1) 2
          else if (i2 == 0) Option.empty
          else i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag460,
      countSetKey = 783,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 % (i1 + 1) == 0) i2 * 2 - i2 / (i1 + 1) + 1 else i2 * 2 - i2 / (i1 + 1)
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag461,
      countSetKey = 989,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          if (i2 == 0) 0 else (i2 * 3 - 1) / 2
        } else if (
          i1 > 0
          && i2 > 0 && i1 == i2
        ) {
          if (i1 == 0) 0 else (i1 * 3 - 1) / 2
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i2 == 0) 0 else (i2 * 3 - 1) / 2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 == 0) 0 else (i2 * 3 - 1) / 2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag462,
      countSetKey = 1075,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          NotImplemented
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag463,
      countSetKey = 1016,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 <=
              2
          ) 0
          else i1 - 2
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 <= 2) 0 else i1 - 2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag464,
      countSetKey = 970,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if (i2 == 0) i1 + 2 else if (i1 == 0) 2 else 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if ((i2 + 1) % i1 == 0) ((i2 + 1) / i1) * i1 + 2 * (i2 + 1) else ((i2 + 1) / i1) * i1 + i1 + 2 * (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1) % i1 == 0)
            ((i2 + 1) / i1) * i1 + 2 *
              (i2 + 1)
          else ((i2 + 1) / i1) * i1 + i1 + 2 * (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i1 + i2 * 2 + 2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag465,
      countSetKey = 985,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 %
              (i2 + 1) == 0
          ) i1 * 2 - i1 / (i2 + 1) + 1
          else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i1 * (i2 / i1)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag466,
      countSetKey = 1060,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 / i1 * i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag467,
      countSetKey = 984,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 + 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 *
            2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i1 * (i2 / i1) + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 1
          else if (i1 == 0) i2 * 0 / -3 + 1
          else if (i1 == 1) 0 * i1 + 0 * i2 + 3
          else if (i2 == 0) i1 * -2 / -1 + 1
          else if (i2 == 1) 1 * i1 + -2 * i2 + 3
          else if (i1 == i2) -i1 * 3 + i2 + i2 / i1 * i1 * 3 + 1
          else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 + 1
          else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag468,
      countSetKey = 721,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) Option.empty else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + i1 - 1 else (i1 / i2) * i2 + i1 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag469,
      countSetKey = 1002,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 0
          else (i1 - 1) * (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag470,
      countSetKey = 674,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i2 == 0) 1 * i1 + 0
          else if (i1 == i2) 0 * i1 + 2 * i2 + -1
          else if (i1 < i2) 2 * i1 + 0 * i2 + -1
          else 1 * i1 + 1 * i2 + 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i2 == 0) 1 * i1 + 0
          else if (i1 == i2) 0 * i1 + 2 * i2 + -1
          else if (i1 < i2) 2 * i1 + 0 * i2 + -1
          else 1 * i1 + 1 * i2 + 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) Option.empty
          else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + 2 * i1 - 1
          else (i1 / i2) * i2 + 2 * i1 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag471,
      countSetKey = 718,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag472,
      countSetKey = 773,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 - 1
        } else if (i1 == 0 && i2 > 0) {
          Option.empty
        } else if (
          i1 > 0 && i2 > 0 && i1 ==
            i2
        ) {
          if (i2 == 0) Option(i1 + 1) else Option.empty
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 <= i2) Option.empty else 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 > i2) Option.empty else 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag473,
      countSetKey = 794,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          2
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 * 2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1) % (i1 * 2) <= i1) ((i2 + 1) / (i1 * 2)) * i1 + (i2 + 1) % (i1 * 2) + i2 + 1
          else ((i2 + 1) / (i1 * 2)) * i1 + i2 + 1 + i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 + 1 - i1 >= 0) i2 + 1 + i1 else i2 * 2 + 2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag474,
      countSetKey = 975,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i2 ==
              0
          ) 0
          else i2 * 2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i2 + i1 * (i2 / i1)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i2 == 0) 1 * i1 + 0
          else if (i1 == i2) 0 * i1 + 2 * i2 + -1
          else if (i1 < i2) 2 * i1 + 0 * i2 + -1
          else 1 * i1 + 1 * i2 + 0
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag475,
      countSetKey = 772,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 - 1
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) i2 + 1 else if (i2 == 0) i1 - 1 else i1 + i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 1
          else if (i1 == 0) i2 + 1
          else if (i2 == 0) i1 - 1
          else i1 + i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag476,
      countSetKey = 711,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          NotImplemented
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 1
          else if (i1 == 0) i2 * 0 / -3 + 1
          else if (i1 == 1) 0 * i1 + 0 * i2 + 3
          else if (i2 == 0) i1 * -2 / -1 + 1
          else if (i2 == 1) 1 * i1 + -2 * i2 + 3
          else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
          else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
          else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag477,
      countSetKey = 843,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 * 2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 + 1
          else (i1 / (i2 * 2)) * i2 + i1 + i2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag478,
      countSetKey = 1125,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 1
          else if (i2 == 0) i1 + 1
          else 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 else (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) - i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag479,
      countSetKey = 764,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 1
          else i1 * 2 + i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 * (i2 / i1) + 2 * i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag480,
      countSetKey = 1048,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) i2 * 2 + 2
          else 2
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1)  % (2 * i1) <= i1) ((i2 + 1) / (2 * i1)) * i1 + 1
          else (i2 + 1) % (2 * i1) - i1 + ((i2 + 1) / (2 * i1)) * i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 <= i2) Option.empty else 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag481,
      countSetKey = 784,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          Option.empty
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (
          i1 > 0 && i2 > 0 && i1 ==
            i2
        ) {
          if (i2 == 0) Option(i1 + 1) else Option.empty
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 <= i2) Option.empty else 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 > i2) Option.empty else 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag482,
      countSetKey = 1117,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) else (i1 / (i2 * 2)) * i2 + i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag483,
      countSetKey = 1029,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 * 2
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i2 - i1 >= 0) i2 * 2 - i1 else i2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag484,
      countSetKey = 1022,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          Option.empty
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag485,
      countSetKey = 932,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 else (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 - i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag486,
      countSetKey = 1061,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          2
        } else if (i1 > 0 && i2 == 0) {
          NotImplemented
        } else if (i1 == 0 && i2 > 0) {
          if (i2 == 0) Option.empty
          else if (
            i1 ==
              0
          ) 2
          else if (i1 == 1) 2
          else 3
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1
          else if (i2 == 0) i1 + 1
          else
            0
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag487,
      countSetKey = 983,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 - 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) Option.empty else i1 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag488,
      countSetKey = 768,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 * 2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 * (i2 / i1) + i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag489,
      countSetKey = 1015,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          NotImplemented
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag490,
      countSetKey = 1044,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0 && i2 == 0
          ) Option(1)
          else if (i1 == 0) Option(0 * i2 + 1)
          else if (i2 == 0) Option.empty
          else Option(1 * i1 + 0 * i2 + 0)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag491,
      countSetKey = 695,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) Option.empty
          else i1 * 2 + i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0) Option.empty else i1 * 2 + i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) Option.empty
          else if (i1 % i2 == 0) (i1 / i2) * i2 + 2 * i1 - 1
          else (i1 / i2) * i2 + i2 + 2 * i1 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag492,
      countSetKey = 675,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 - 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0 && i2 == 0) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i2 == 0) 1 * i1 + 0
          else if (i1 == i2) 0 * i1 + 2 * i2 + -1
          else if (i1 < i2) 2 * i1 + 0 * i2 + -1
          else 1 * i1 + 1 * i2 + 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i2 == 0) 1 * i1 + 0
          else if (i1 == i2) 0 * i1 + 2 * i2 + -1
          else if (i1 < i2) 2 * i1 + 0 * i2 + -1
          else 1 * i1 + 1 * i2 + 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) i2 + 1 else if (i2 == 0) i1 - 1 else i1 + i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag493,
      countSetKey = 795,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 % (i2 + 1) == 0) i1 * 2 - i1 / (i2 + 1) + 1 else i1 * 2 - i1 / (i2 + 1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1) % (2 * i1) <= i1) (i2 + 1) % (2 * i1) + i2 + ((i2 + 1) / (2 * i1)) * i1
          else i1 + i2 + ((i2 + 1) / (2 * i1)) * i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag494,
      countSetKey = 1081,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + 1 else (i1 / (i2 * 2)) * i2 + i2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag495,
      countSetKey = 968,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + i2 *
            2 + i1 * (i2 / i1)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i2 * 2 + i1 * (i2 / i1)
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 == 0) 0 else i2 * 2 + i1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag496,
      countSetKey = 1049,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 >
              i2
          ) Option.empty
          else 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if ((i2 + 1)  % (2 * i1) <= i1) ((i2 + 1) / (2 * i1)) * i1
          else (i2 + 1) % (2 * i1) - i1 + ((i2 + 1) / (2 * i1)) * i1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag497,
      countSetKey = 712,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          NotImplemented
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 == 0) Option.empty else if (i1 == 0) 2 else if (i1 == 1) 2 else 3
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag498,
      countSetKey = 855,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 1
          else i1 * 2 + i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0) 1 else i1 * 2 + i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % i2 == 0) (i1 / i2) * i2 + 2 * i1 + 1 else (i1 / i2) * i2 + i2 + 2 * i1 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag499,
      countSetKey = 707,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          NotImplemented
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag500,
      countSetKey = 971,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 + 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 +
            i2 * 2 + i1 * (i2 / i1) + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i2 * 2 + i1 * (i2 / i1) + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 == 0) 1 else i2 * 2 + i1 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag501,
      countSetKey = 791,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if (i1 <= 1) 3 else 4
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (
          i1 > 0 && i2 > 0 && i1
            == i2
        ) {
          if (i1 <= 1) 3 else 4
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 <= 1) 3 else 4
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          4
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag502,
      countSetKey = 717,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if (i1 >= 1) i1 * 2 - 1 else i1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (
          i1 > 0 && i2
            > 0 && i1 == i2
        ) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == i2 + 1) i1 else i1 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag503,
      countSetKey = 840,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 * 2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + 2 * i1 + 1 else (i1 / i2) * i2 + 2 * i1 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag504,
      countSetKey = 1085,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i2 == 0 && i1 == 0) 1
          else if (i2 == 0) i1 * 0 / -3 + 1
          else if (i2 == 1) 0 * i2 + 0 * i1 + 3
          else if (i1 == 0) i2 * -2 / -1 + 1
          else if (i1 == 1) 1 * i2 + -2 * i1 + 3
          else if (i2 == i1) -3 * i2 + 1 * i1 + i1 / i2 * i2 * -3 / -1 + 1
          else if (i2 > i1) 1 * i2 + 0 * i1 + i1 / i2 * i2 * -3 / -3 + 1
          else 1 * i2 + 0 * i1 + i1 / i2 * i2 * 0 / -3 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % i2 == 0) (i1 / i2) * i2 + 1 else (i1 / i2) * i2 + i2 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag505,
      countSetKey = 1023,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 1
          else if (i2 == 0) i1 + 1
          else 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag506,
      countSetKey = 1052,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          Option.empty
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          Option.empty
        } else if (
          i1 > 0 && i2 > 0 &&
          i1 == i2
        ) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag507,
      countSetKey = 978,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          i1 - 1
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0 && i2 == 0) 0
          else if (i1 == 0) i2 * 0 / -3 + 0
          else if (i1 == 1) -3 * i1 + 1 * i2 + 4
          else if (i2 == 0) i1 * -2 / -1 + -1
          else if (i2 == 1) 3 * i1 + -3 * i2 + 2
          else if (i1 == i2) -1 * i1 + 3 * i2 + i1 * i2 * -3 / -3 + -1
          else if (i1 > i2) 2 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + -1
          else 2 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + -1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag508,
      countSetKey = 1008,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag509,
      countSetKey = 876,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 1 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + i1 + 1 else (i1 / i2) * i2 + i1 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag510,
      countSetKey = 859,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 * 2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i2 + 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % i2 == 0) (i1 / i2) * i2 + i1 + 1 else (i1 / i2) * i2 + i2 + i1 + 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag511,
      countSetKey = 1084,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          1
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i2 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag512,
      countSetKey = 976,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          if (i2 == 0) i1 + 2 else if (i1 == 0) 2 else 1
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + i2 + i1 * (i2 / i1) + 2
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          i1 + i2 + i1 * (i2 / i1) + 2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          i1 + i2 + i1 * (i2 / i1) + 2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag513,
      countSetKey = 1126,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i1 ==
              0
          ) 1
          else if (i2 == 0) i1 + 1
          else 0
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0) 0
          else if (i1 % i2 == 0) (i1 / i2 - 1) * i2
          else
            (i1
              / i2) * i2
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag514,
      countSetKey = 1010,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          NotImplemented
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag515,
      countSetKey = 771,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) i2 + 1 else if (i2 == 0) i1 - 1 else i1 + i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 % (i1 + 1) == 0) i2 * 2 - i2 / (i1 + 1) + 1
          else i2 * 2 - i2 / (i1 + 1)
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag516,
      countSetKey = 699,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (
            i2 ==
              0 && i1 == 0
          ) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) i2 + 1 else if (i2 == 0) i1 - 1 else i1 + i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 % i2 == 0) (i1 / i2) * i2 + i1 - 1
          else (i1 / i2) * i2 + i2 + i1 - 1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag517,
      countSetKey = 652,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 + 3
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i1 == 0) Option.empty
          else Option(i2 + 3)
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i2 == 1) 5 else i2 + 3
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i1 == 0 && i2 == 0) 0
          else if (i1 == 0) 0 * i2 + 0
          else if (i1 == 1) 0 * i1 + 2 * i2 + 3
          else if (i2 == 0) 0 * i1 + 3
          else if (i2 == 1) 0 * i1 + 1 * i2 + 3
          else if (i1 == i2) -1 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 3
          else if (i1 > i2) 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 3
          else 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 3
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag518,
      countSetKey = 995,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          0
        } else if (i1 == 0 && i2 > 0) {
          i2 - 1
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          NotImplemented
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 == 0 && i1 == 0) 0
          else if (i2 == 0) 0 * i1 + 0
          else if (i1 == 0) 1 * i2 + 0
          else if (i2 == i1) 0 * i2 + 2 * i1 + -1
          else if (i2 < i1) 2 * i2 - 1
          else i2 + i1
        } else { throw new Exception("not have value") }
    )
  )

  add(
    CusPlan(
      key = Tags.Tag519,
      countSetKey = 763,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 && i2 == 0) {
          0
        } else if (i1 > 0 && i2 == 0) {
          2
        } else if (i1 == 0 && i2 > 0) {
          0
        } else if (i1 > 0 && i2 > 0 && i1 == i2) {
          i1 + i2 +
            i1 * (i2 / i1) + 2
        } else if (i1 > 0 && i2 > 0 && i1 < i2) {
          if (i2 + 1 == 0) 0
          else if ((i2 + 1) % i1 == 0) ((i2 + 1) / i1 - 1) * i1 + 2 * (i2 + 1)
          else ((i2 + 1) / i1) * i1 + 2 * i2 + 2
        } else if (i1 > 0 && i2 > 0 && i1 > i2) {
          if (i2 + 1 - i1 >= 0) i2 + 1 + i1 else i2 * 2 + 2
        } else { throw new Exception("not have value") }
    )
  )

  // =================================

  add(
    CusPlan(
      key = Tags.Tag520,
      countSetKey = 1045,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0) 0
        else if (i1 > i2) i2 + 1
        else if (i1 == i2) i1 + 2
        else if ((i2 + 1)                          % (i1 * 2) <= i1) ((i2 + 1) / (i1 * 2)) * i1 + (i2 + 1)
        else ((i2 + 1) / (i1 * 2)) * i1 + (i2 + 1) % (i1 * 2) + (i2 + 1) - i1
    )
  )

  add(CusPlan(key = Tags.Tag521, countSetKey = 716, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 < i2) i1 - 1 else i1 * 2 - i2 - 1))

  add(
    CusPlan(
      key = Tags.Tag522,
      countSetKey = 967,
      c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 == 1) 5 else i1 + 3
    )
  )

  add(
    CusPlan(
      key = Tags.Tag523,
      countSetKey = 1097,
      c = (i1: Int, i2: Int) =>
        if (i1 <= i2) 1
        else if (i2 == 0) 0
        else if (i1                  % (i2 * 2) <= i2) i1 / (i2 * 2) * i2 + 1
        else i1 / (i2 * 2) * i2 + i1 % (i2 * 2) - i2 + 1
    )
  )

  add(
    CusPlan(
      key = Tags.Tag524,
      countSetKey = 767,
      c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 <= i2) i2 * 2 - i2 % i1 + 2 else i2 + 2
    )
  )

  add(
    CusPlan(
      key = Tags.Tag525,
      countSetKey = 1056,
      c = (i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i1 >= i2) 0 else NotImplemented
    )
  )

  add(
    CusPlan(
      key = Tags.Tag526,
      countSetKey = 1065,
      c = (i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 1 else if (i2 == 0) 0 else if (i1 == 0) i2 + 1 else i2 - 1
    )
  )

  add(
    CusPlan(
      key = Tags.Tag527,
      countSetKey = 796,
      c = (i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 0 else if (i1 == i2) i2 - 1 else if (i1 > i2) i2 else NotImplemented
    )
  )

  add(
    CusPlan(
      key = Tags.Tag528,
      countSetKey = 1019,
      c = (i1: Int, i2: Int) => if (i1 <= i2) 0 else if (i2 == 0) i1 - 1 else NotImplemented
    )
  )

  add(CusPlan(key = Tags.Tag529, countSetKey = 992, c = (i1: Int, i2: Int) => (i2 - 1) / 2))

  add(CusPlan(key = Tags.Tag530, countSetKey = 723, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 > i2) i1 + 1 else i1 - 1))

  add(
    CusPlan(
      key = Tags.Tag531,
      countSetKey = 1120,
      c = (i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 0 else if (i1 <= i2) i2 else if (i1 % i2 == 0) i1 else i1 / i2 * i2 + i2
    )
  )

  add(
    CusPlan(
      key = Tags.Tag532,
      countSetKey = 1009,
      c = (i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 0 else if (i1 <= i2) i1 - 1 else NotImplemented
    )
  )

  add(
    CusPlan(
      key = Tags.Tag533,
      countSetKey = 923,
      c = (i1: Int, i2: Int) =>
        if (i1 == 0 || i2 == 0) 0 else if (i1 <= i2 || i2 == 0) i1 * 2 + i2 else if (i1 % i2 == 0) i1 * 3 else i1 * 3 + i2 - (i1 % i2)
    )
  )

  add(
    CusPlan(
      key = Tags.Tag534,
      countSetKey = 1037,
      c = (i1: Int, i2: Int) => if (i2 == 0) 0 else if (i1 == 0) i2 * 2 else if (i1 == i2) i1 else if (i1 > i2) i2 - 1 else NotImplemented
    )
  )

  add(
    CusPlan(
      key = Tags.Tag535,
      countSetKey = 676,
      c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) i1 - 1 else if (i1 <= i2) i1 * 2 - 1 else NotImplemented
    )
  )

  add(
    CusPlan(
      key = Tags.Tag536,
      countSetKey = 708,
      c = (i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) i1 - 1 else if (i1 <= i2) i1 * 2 - 2 else NotImplemented
    )
  )

  add(CusPlan(key = Tags.Tag537, countSetKey = 972, c = (i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + 3))

  def aa: (Int, Int) => ConfirmResult = (i1: Int, i2: Int) => {
    if (i1 == 0 && i2 == 0) {
      NotImplemented
    } else if (i1 > 0 && i2 == 0) {
      NotImplemented
    } else if (i1 == 0 && i2 > 0) {
      // Tag670
      Option.empty
      // Tag1637
      1
      // Tag081
      0
    } else if (i1 > 0 && i2 > 0 && i1 == i2) {
      // Tag1495
      i1
      // Tag1184
      (i1 - 1) / 2
      // Tag1408
      0
      // Tag950
      i1 * 2 + 2
      // Tag1666
      if (i1 == 1) 4 else i1 + 2
      // Tag786
      1
      // Tag1327
      i2 - 1
      // Tag853
      i1 * 3 + 1
      // Tag006
      i1 * 3
      // Tag120
      if (i1 == 1) 5 else i1 + 3
      // Tag1327
      if (i2 % (i1 + 1) == 0) i2 / (i1 + 1) * i1 + i2 % (i1 + 1) else i2 / (i1 + 1) * i1 + i2 % (i1 + 1) - 1
      // Tag922
      i1 + 2
    } else if (i1 > 0 && i2 > 0 && i1 > i2) {
      // Tag259
      // if (i1 % i2 == 0) i1 * 3 else i1 * 3 + i2 - (i1 % i2)
      // Tag322
      // if (i1 % i2 == 0) i1 else i1 + i2 - i1 % i2
      // Tag1637
      i1 + 1
      // Tag1184=reverse
      (i2 - 1) / 2
      NotImplemented
      // Tag1495=reverse
      i2
      // Tag1327
      i2 - 1
      // Tag922=reverse
      i2 + 2
      // Tag925
      i1 + 2
      // Tag1293=reverse
      if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + 1 else (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) - i2 + 1
      // Tag1408
      0
      // Tag670
      i1 + i2 + 1
      // Tag842=reverse
      if (i2 % (i1 + 1) == 0) 2 * i2 - i2 / (i1 + 1) + 1 else 2 * i2 - i2 / (i1 + 1)
      // Tag120
      if (i1 == 1) 5 else i1 + 3
      // Tag1324
      if (i1 - i2 >= 1) i1 * 2 - i2 - 1 else i1
      // Tag081=reverse
      i2 + 1
    } else {
      // Tag006
      if (i1 == 0) 0 else i1 * 2 + i2
      // Tag1495=reverse
      if (i2 == 0) 1 else if (i1 == 0) Option.empty else i2
      // Tag1327=reverse
      i1 - 1
      // Tag1184=reverse
      (i2 - 1) / 2
      // Tag1408
      // if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0
      NotImplemented
      // Tag447=reverse
      i2 - 1
      // Tag891
      i2 * 2 - i2 % i1 + 2
      // Tag1666
      if (i1 == 1) 4 else i1 + 2
      // Tag786
      1
      // Tag125
      i1 + i2 + i1 * (i2 / i1) + 1
      // Tag1144
      i1 * (i2 / i1) + 2 * i2
      // Tag120
      if (i1 == 1) 5 else i1 + 3
      // Tag1327=reverse
      if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
      // Tag730
      if ((i2 + 1)                               % (i1 * 2) <= i1) ((i2 + 1) / (i1 * 2)) * i1 + (i2 + 1)
      else ((i2 + 1) / (i1 * 2)) * i1 + (i2 + 1) % (i1 * 2) + (i2 + 1) - i1
    }
  }
}

object ConfirmCol {
  lazy val cols: Vector[ConfirmPlan] = {
    class A extends ConfirmCol
    val a = new A
    a.confirmCol
  }
}
