package f08

import f07._

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
      count(i1.toInt, i2.toInt) match {
        case OptResult(u)   => u == i3.toIntOption
        case NotImplemented => true
      }
    }
    confirmSet.forall(identity)
  }

}
case class MapPlan(
  override val key: String,
  override val countSetKey: Int,
  `i1 = 0 and i2 = 0`: Option[String],
  `i1 gt 0 and i2 = 0`: Option[String],
  `i1 = 0 and i2 gt 0`: Option[String],
  `i1 gt 0 and i2 gt 0 and i1 = i2`: Option[String],
  `i1 gt 0 and i2 gt 0 and i1 gt i2`: Option[String],
  `i1 gt 0 and i2 gt 0 and i1 lt i2`: Option[String]
) extends ConfirmPlan {

  def resultFromKeyOpt(i1: Int, i2: Int, keyOpt: Option[String]): ConfirmResult = {
    val dOpt = for (currKey <- keyOpt) yield {
      val r = currKey.split('=').to(List) match {
        case List(setColKey)            => SetColInstance.list.find(_.key == setColKey).get.count(i1, i2)
        case List(setColKey, "reverse") => SetColInstance.list.find(_.key == setColKey).get.count(i2, i1)
      }
      OptResult(r)
    }
    dOpt.getOrElse(NotImplemented)
  }

  override def count(i1: Int, i2: Int): ConfirmResult =
    if (i1 == 0 && i2 == 0) resultFromKeyOpt(i1, i2, `i1 = 0 and i2 = 0`)
    else if (i1 > 0 && i2 == 0) resultFromKeyOpt(i1, i2, `i1 gt 0 and i2 = 0`)
    else if (i1 == 0 && i2 > 0) resultFromKeyOpt(i1, i2, `i1 = 0 and i2 gt 0`)
    else if (i1 > 0 && i2 > 0 && i1 == i2) resultFromKeyOpt(i1, i2, `i1 gt 0 and i2 gt 0 and i1 = i2`)
    else if (i1 > 0 && i2 > 0 && i1 > i2) resultFromKeyOpt(i1, i2, `i1 gt 0 and i2 gt 0 and i1 gt i2`)
    else if (i1 > 0 && i2 > 0 && i1 < i2) resultFromKeyOpt(i1, i2, `i1 gt 0 and i2 gt 0 and i1 lt i2`)
    else throw new Exception("No result")

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
  add(SimpleMapPlan(key = Tags.Tag010, countSetKey = 599, setColKey = Tags.Tag049))
  add(SimpleMapPlan(key = Tags.Tag011, countSetKey = 600, setColKey = Tags.Tag050))
  add(SimpleMapPlan(key = Tags.Tag012, countSetKey = 601, setColKey = Tags.Tag191))
  add(SimpleMapPlan(key = Tags.Tag013, countSetKey = 602, setColKey = Tags.Tag048))
  add(SimpleMapPlan(key = Tags.Tag014, countSetKey = 603, setColKey = Tags.Tag053))
  add(SimpleMapPlan(key = Tags.Tag015, countSetKey = 604, setColKey = Tags.Tag051))
  add(SimpleMapPlan(key = Tags.Tag016, countSetKey = 605, setColKey = Tags.Tag198))
  add(SimpleMapPlan(key = Tags.Tag017, countSetKey = 606, setColKey = Tags.Tag052))
  add(SimpleMapPlan(key = Tags.Tag018, countSetKey = 607, setColKey = Tags.Tag502))
  add(SimpleMapPlan(key = Tags.Tag019, countSetKey = 608, setColKey = Tags.Tag055))
  add(SimpleMapPlan(key = Tags.Tag020, countSetKey = 609, setColKey = Tags.Tag054))
  add(SimpleMapPlan(key = Tags.Tag021, countSetKey = 610, setColKey = Tags.Tag560))
  add(SimpleMapPlan(key = Tags.Tag022, countSetKey = 611, setColKey = Tags.Tag188))
  add(SimpleMapPlan(key = Tags.Tag023, countSetKey = 612, setColKey = Tags.Tag056))
  add(SimpleMapPlan(key = Tags.Tag024, countSetKey = 613, setColKey = Tags.Tag192))
  add(SimpleMapPlan(key = Tags.Tag025, countSetKey = 614, setColKey = Tags.Tag1183))
  add(SimpleMapPlan(key = Tags.Tag026, countSetKey = 615, setColKey = Tags.Tag190))
  add(SimpleMapPlan(key = Tags.Tag027, countSetKey = 616, setColKey = Tags.Tag187))
  add(SimpleMapPlan(key = Tags.Tag028, countSetKey = 617, setColKey = Tags.Tag157))
  add(SimpleMapPlan(key = Tags.Tag029, countSetKey = 618, setColKey = Tags.Tag604))
  add(SimpleMapPlan(key = Tags.Tag030, countSetKey = 619, setColKey = Tags.Tag178))
  add(SimpleMapPlan(key = Tags.Tag031, countSetKey = 620, setColKey = Tags.Tag1142))
  add(SimpleMapPlan(key = Tags.Tag032, countSetKey = 621, setColKey = Tags.Tag735))
  add(SimpleMapPlan(key = Tags.Tag033, countSetKey = 622, setColKey = Tags.Tag064))
  add(SimpleMapPlan(key = Tags.Tag034, countSetKey = 623, setColKey = Tags.Tag029))
  add(SimpleMapPlan(key = Tags.Tag035, countSetKey = 624, setColKey = Tags.Tag085))
  add(SimpleMapPlan(key = Tags.Tag036, countSetKey = 625, setColKey = Tags.Tag897))
  add(SimpleMapPlan(key = Tags.Tag037, countSetKey = 626, setColKey = Tags.Tag222))
  add(SimpleMapPlan(key = Tags.Tag038, countSetKey = 627, setColKey = Tags.Tag1150))
  add(SimpleMapPlan(key = Tags.Tag039, countSetKey = 628, setColKey = Tags.Tag603))
  add(SimpleMapPlan(key = Tags.Tag040, countSetKey = 629, setColKey = Tags.Tag185))
  add(SimpleMapPlan(key = Tags.Tag041, countSetKey = 630, setColKey = Tags.Tag1165))
  add(SimpleMapPlan(key = Tags.Tag042, countSetKey = 631, setColKey = Tags.Tag665))
  add(SimpleMapPlan(key = Tags.Tag043, countSetKey = 632, setColKey = Tags.Tag184))
  add(SimpleMapPlan(key = Tags.Tag044, countSetKey = 633, setColKey = Tags.Tag110))
  add(SimpleMapPlan(key = Tags.Tag045, countSetKey = 634, setColKey = Tags.Tag592))
  add(SimpleMapPlan(key = Tags.Tag046, countSetKey = 635, setColKey = Tags.Tag221))
  add(SimpleMapPlan(key = Tags.Tag047, countSetKey = 636, setColKey = Tags.Tag1231))
  add(SimpleMapPlan(key = Tags.Tag048, countSetKey = 637, setColKey = Tags.Tag574))
  add(SimpleMapPlan(key = Tags.Tag049, countSetKey = 638, setColKey = Tags.Tag220))
  add(SimpleMapPlan(key = Tags.Tag050, countSetKey = 639, setColKey = Tags.Tag022))
  add(SimpleMapPlan(key = Tags.Tag051, countSetKey = 640, setColKey = Tags.Tag1257))
  add(SimpleMapPlan(key = Tags.Tag052, countSetKey = 641, setColKey = Tags.Tag918))
  add(SimpleMapPlan(key = Tags.Tag053, countSetKey = 642, setColKey = Tags.Tag043))
  add(SimpleMapPlan(key = Tags.Tag054, countSetKey = 643, setColKey = Tags.Tag1334))
  add(SimpleMapPlan(key = Tags.Tag055, countSetKey = 644, setColKey = Tags.Tag650))
  add(SimpleMapPlan(key = Tags.Tag056, countSetKey = 645, setColKey = Tags.Tag677))
  add(SimpleMapPlan(key = Tags.Tag057, countSetKey = 646, setColKey = Tags.Tag1308))
  add(SimpleMapPlan(key = Tags.Tag058, countSetKey = 647, setColKey = Tags.Tag117))
  add(SimpleMapPlan(key = Tags.Tag059, countSetKey = 648, setColKey = Tags.Tag669))
  add(SimpleMapPlan(key = Tags.Tag060, countSetKey = 649, setColKey = Tags.Tag691))
  add(SimpleMapPlan(key = Tags.Tag061, countSetKey = 650, setColKey = Tags.Tag654))
  add(SimpleMapPlan(key = Tags.Tag062, countSetKey = 651, setColKey = Tags.Tag398))
  add(SimpleMapPlan(key = Tags.Tag063, countSetKey = 653, setColKey = Tags.Tag414))
  add(SimpleMapPlan(key = Tags.Tag064, countSetKey = 654, setColKey = Tags.Tag540))
  add(SimpleMapPlan(key = Tags.Tag065, countSetKey = 655, setColKey = Tags.Tag199))
  add(SimpleMapPlan(key = Tags.Tag066, countSetKey = 656, setColKey = Tags.Tag687))
  add(SimpleMapPlan(key = Tags.Tag067, countSetKey = 657, setColKey = Tags.Tag227))
  add(SimpleMapPlan(key = Tags.Tag068, countSetKey = 658, setColKey = Tags.Tag162))
  add(SimpleMapPlan(key = Tags.Tag069, countSetKey = 659, setColKey = Tags.Tag101))
  add(SimpleMapPlan(key = Tags.Tag070, countSetKey = 660, setColKey = Tags.Tag164))
  add(SimpleMapPlan(key = Tags.Tag071, countSetKey = 661, setColKey = Tags.Tag1373))
  add(SimpleMapPlan(key = Tags.Tag072, countSetKey = 662, setColKey = Tags.Tag1299))
  add(SimpleMapPlan(key = Tags.Tag073, countSetKey = 663, setColKey = Tags.Tag027))
  add(SimpleMapPlan(key = Tags.Tag074, countSetKey = 664, setColKey = Tags.Tag1214))
  add(SimpleMapPlan(key = Tags.Tag075, countSetKey = 665, setColKey = Tags.Tag1325))
  add(SimpleMapPlan(key = Tags.Tag076, countSetKey = 666, setColKey = Tags.Tag1644))
  add(SimpleMapPlan(key = Tags.Tag077, countSetKey = 667, setColKey = Tags.Tag1662))
  add(SimpleMapPlan(key = Tags.Tag078, countSetKey = 668, setColKey = Tags.Tag1619))
  add(SimpleMapPlan(key = Tags.Tag079, countSetKey = 669, setColKey = Tags.Tag1080))
  add(SimpleMapPlan(key = Tags.Tag080, countSetKey = 670, setColKey = Tags.Tag1580))
  add(SimpleMapPlan(key = Tags.Tag081, countSetKey = 671, setColKey = Tags.Tag1430))
  add(SimpleMapPlan(key = Tags.Tag082, countSetKey = 672, setColKey = Tags.Tag1542))
  add(SimpleMapPlan(key = Tags.Tag083, countSetKey = 673, setColKey = Tags.Tag1817))
  add(SimpleMapPlan(key = Tags.Tag084, countSetKey = 678, setColKey = Tags.Tag1587))
  add(SimpleMapPlan(key = Tags.Tag085, countSetKey = 679, setColKey = Tags.Tag1553))
  add(SimpleMapPlan(key = Tags.Tag086, countSetKey = 680, setColKey = Tags.Tag1546))
  add(SimpleMapPlan(key = Tags.Tag087, countSetKey = 681, setColKey = Tags.Tag1556))
  add(SimpleMapPlan(key = Tags.Tag088, countSetKey = 682, setColKey = Tags.Tag059))
  add(SimpleMapPlan(key = Tags.Tag089, countSetKey = 683, setColKey = Tags.Tag068))
  add(SimpleMapPlan(key = Tags.Tag090, countSetKey = 684, setColKey = Tags.Tag069))
  add(SimpleMapPlan(key = Tags.Tag091, countSetKey = 685, setColKey = Tags.Tag171))
  add(SimpleMapPlan(key = Tags.Tag092, countSetKey = 686, setColKey = Tags.Tag071))
  add(SimpleMapPlan(key = Tags.Tag093, countSetKey = 687, setColKey = Tags.Tag080))
  add(SimpleMapPlan(key = Tags.Tag094, countSetKey = 688, setColKey = Tags.Tag666))
  add(SimpleMapPlan(key = Tags.Tag095, countSetKey = 689, setColKey = Tags.Tag096))
  add(SimpleMapPlan(key = Tags.Tag096, countSetKey = 690, setColKey = Tags.Tag165))
  add(SimpleMapPlan(key = Tags.Tag097, countSetKey = 691, setColKey = Tags.Tag095))
  add(SimpleMapPlan(key = Tags.Tag098, countSetKey = 692, setColKey = Tags.Tag019))
  add(SimpleMapPlan(key = Tags.Tag099, countSetKey = 693, setColKey = Tags.Tag1290))
  add(SimpleMapPlan(key = Tags.Tag100, countSetKey = 694, setColKey = Tags.Tag1671))
  add(SimpleMapPlan(key = Tags.Tag101, countSetKey = 696, setColKey = Tags.Tag109))
  add(SimpleMapPlan(key = Tags.Tag102, countSetKey = 697, setColKey = Tags.Tag244))
  add(SimpleMapPlan(key = Tags.Tag103, countSetKey = 698, setColKey = Tags.Tag1673))
  add(SimpleMapPlan(key = Tags.Tag104, countSetKey = 700, setColKey = Tags.Tag577))
  add(SimpleMapPlan(key = Tags.Tag105, countSetKey = 701, setColKey = Tags.Tag583))
  add(SimpleMapPlan(key = Tags.Tag106, countSetKey = 702, setColKey = Tags.Tag007))
  add(SimpleMapPlan(key = Tags.Tag107, countSetKey = 703, setColKey = Tags.Tag033))
  add(SimpleMapPlan(key = Tags.Tag108, countSetKey = 704, setColKey = Tags.Tag160))
  add(SimpleMapPlan(key = Tags.Tag109, countSetKey = 705, setColKey = Tags.Tag797))
  add(SimpleMapPlan(key = Tags.Tag110, countSetKey = 706, setColKey = Tags.Tag1246))
  add(SimpleMapPlan(key = Tags.Tag111, countSetKey = 709, setColKey = Tags.Tag163))
  add(SimpleMapPlan(key = Tags.Tag112, countSetKey = 710, setColKey = Tags.Tag1337))
  add(SimpleMapPlan(key = Tags.Tag113, countSetKey = 713, setColKey = Tags.Tag1626))
  add(SimpleMapPlan(key = Tags.Tag114, countSetKey = 714, setColKey = Tags.Tag1189))
  add(SimpleMapPlan(key = Tags.Tag115, countSetKey = 715, setColKey = Tags.Tag1543))
  add(SimpleMapPlan(key = Tags.Tag116, countSetKey = 719, setColKey = Tags.Tag1545))
  add(SimpleMapPlan(key = Tags.Tag117, countSetKey = 720, setColKey = Tags.Tag1821))
  add(SimpleMapPlan(key = Tags.Tag118, countSetKey = 722, setColKey = Tags.Tag1121))
  add(SimpleMapPlan(key = Tags.Tag119, countSetKey = 725, setColKey = Tags.Tag1538))
  add(SimpleMapPlan(key = Tags.Tag120, countSetKey = 726, setColKey = Tags.Tag1548))
  add(SimpleMapPlan(key = Tags.Tag121, countSetKey = 727, setColKey = Tags.Tag942))
  add(SimpleMapPlan(key = Tags.Tag122, countSetKey = 728, setColKey = Tags.Tag1267))
  add(SimpleMapPlan(key = Tags.Tag123, countSetKey = 729, setColKey = Tags.Tag945))
  add(SimpleMapPlan(key = Tags.Tag124, countSetKey = 730, setColKey = Tags.Tag076))
  add(SimpleMapPlan(key = Tags.Tag125, countSetKey = 731, setColKey = Tags.Tag016))
  add(SimpleMapPlan(key = Tags.Tag126, countSetKey = 732, setColKey = Tags.Tag176))
  add(SimpleMapPlan(key = Tags.Tag127, countSetKey = 733, setColKey = Tags.Tag752))
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
  add(SimpleMapPlan(key = Tags.Tag185, countSetKey = 811, setColKey = Tags.Tag655))
  add(SimpleMapPlan(key = Tags.Tag186, countSetKey = 812, setColKey = Tags.Tag1557))
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
  add(SimpleMapPlan(key = Tags.Tag208, countSetKey = 834, setColKey = Tags.Tag432))
  add(SimpleMapPlan(key = Tags.Tag209, countSetKey = 835, setColKey = Tags.Tag682))
  add(SimpleMapPlan(key = Tags.Tag210, countSetKey = 836, setColKey = Tags.Tag1573))
  add(SimpleMapPlan(key = Tags.Tag211, countSetKey = 837, setColKey = Tags.Tag1437))
  add(SimpleMapPlan(key = Tags.Tag212, countSetKey = 838, setColKey = Tags.Tag711))
  add(SimpleMapPlan(key = Tags.Tag213, countSetKey = 839, setColKey = Tags.Tag915))
  add(SimpleMapPlan(key = Tags.Tag214, countSetKey = 841, setColKey = Tags.Tag645))
  add(SimpleMapPlan(key = Tags.Tag215, countSetKey = 842, setColKey = Tags.Tag580))
  add(SimpleMapPlan(key = Tags.Tag216, countSetKey = 844, setColKey = Tags.Tag734))
  add(SimpleMapPlan(key = Tags.Tag217, countSetKey = 845, setColKey = Tags.Tag1589))
  add(SimpleMapPlan(key = Tags.Tag218, countSetKey = 846, setColKey = Tags.Tag1047))
  add(SimpleMapPlan(key = Tags.Tag219, countSetKey = 847, setColKey = Tags.Tag099))
  add(SimpleMapPlan(key = Tags.Tag220, countSetKey = 848, setColKey = Tags.Tag865))
  add(SimpleMapPlan(key = Tags.Tag221, countSetKey = 849, setColKey = Tags.Tag351))
  add(SimpleMapPlan(key = Tags.Tag222, countSetKey = 850, setColKey = Tags.Tag819))
  add(SimpleMapPlan(key = Tags.Tag223, countSetKey = 851, setColKey = Tags.Tag836))
  add(SimpleMapPlan(key = Tags.Tag224, countSetKey = 852, setColKey = Tags.Tag378))
  add(SimpleMapPlan(key = Tags.Tag225, countSetKey = 853, setColKey = Tags.Tag853))
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
  add(SimpleMapPlan(key = Tags.Tag287, countSetKey = 921, setColKey = Tags.Tag006))
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
  add(SimpleMapPlan(key = Tags.Tag307, countSetKey = 945, setColKey = Tags.Tag578))
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
  add(SimpleMapPlan(key = Tags.Tag321, countSetKey = 959, setColKey = Tags.Tag1280))
  add(SimpleMapPlan(key = Tags.Tag322, countSetKey = 960, setColKey = Tags.Tag609))
  add(SimpleMapPlan(key = Tags.Tag323, countSetKey = 961, setColKey = Tags.Tag063))
  add(SimpleMapPlan(key = Tags.Tag324, countSetKey = 962, setColKey = Tags.Tag067))
  add(SimpleMapPlan(key = Tags.Tag325, countSetKey = 963, setColKey = Tags.Tag582))
  add(SimpleMapPlan(key = Tags.Tag326, countSetKey = 964, setColKey = Tags.Tag201))
  add(SimpleMapPlan(key = Tags.Tag327, countSetKey = 969, setColKey = Tags.Tag200))
  add(SimpleMapPlan(key = Tags.Tag328, countSetKey = 979, setColKey = Tags.Tag1398))
  add(SimpleMapPlan(key = Tags.Tag329, countSetKey = 982, setColKey = Tags.Tag242))
  add(SimpleMapPlan(key = Tags.Tag330, countSetKey = 986, setColKey = Tags.Tag038))
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
  add(SimpleMapPlan(key = Tags.Tag348, countSetKey = 1014, setColKey = Tags.Tag243))
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
  add(SimpleMapPlan(key = Tags.Tag372, countSetKey = 1066, setColKey = Tags.Tag1510))
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
  add(SimpleMapPlan(key = Tags.Tag399, countSetKey = 1099, setColKey = Tags.Tag861))
  add(SimpleMapPlan(key = Tags.Tag400, countSetKey = 1101, setColKey = Tags.Tag1050))
  add(SimpleMapPlan(key = Tags.Tag401, countSetKey = 1102, setColKey = Tags.Tag800))
  add(SimpleMapPlan(key = Tags.Tag402, countSetKey = 1103, setColKey = Tags.Tag1598))
  add(SimpleMapPlan(key = Tags.Tag403, countSetKey = 1105, setColKey = Tags.Tag240))
  add(SimpleMapPlan(key = Tags.Tag404, countSetKey = 1106, setColKey = Tags.Tag237))
  add(SimpleMapPlan(key = Tags.Tag405, countSetKey = 1107, setColKey = Tags.Tag793))
  add(SimpleMapPlan(key = Tags.Tag406, countSetKey = 1108, setColKey = Tags.Tag236))
  add(SimpleMapPlan(key = Tags.Tag407, countSetKey = 1109, setColKey = Tags.Tag1599))
  add(SimpleMapPlan(key = Tags.Tag408, countSetKey = 1110, setColKey = Tags.Tag1555))
  add(SimpleMapPlan(key = Tags.Tag409, countSetKey = 1111, setColKey = Tags.Tag241))
  add(SimpleMapPlan(key = Tags.Tag410, countSetKey = 1112, setColKey = Tags.Tag238))
  add(SimpleMapPlan(key = Tags.Tag411, countSetKey = 1113, setColKey = Tags.Tag161))
  add(SimpleMapPlan(key = Tags.Tag412, countSetKey = 1114, setColKey = Tags.Tag041))
  add(SimpleMapPlan(key = Tags.Tag413, countSetKey = 1116, setColKey = Tags.Tag210))
  add(SimpleMapPlan(key = Tags.Tag414, countSetKey = 1118, setColKey = Tags.Tag245))
  add(SimpleMapPlan(key = Tags.Tag415, countSetKey = 1119, setColKey = Tags.Tag045))
  add(SimpleMapPlan(key = Tags.Tag416, countSetKey = 1121, setColKey = Tags.Tag159))
  add(SimpleMapPlan(key = Tags.Tag417, countSetKey = 1122, setColKey = Tags.Tag212))
  add(SimpleMapPlan(key = Tags.Tag418, countSetKey = 1123, setColKey = Tags.Tag086))
  add(SimpleMapPlan(key = Tags.Tag419, countSetKey = 1124, setColKey = Tags.Tag040))

  add(
    MapPlan(
      key = Tags.Tag420,
      countSetKey = 965,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag121"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1666"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag925"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1666")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag421,
      countSetKey = 1058,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag422,
      countSetKey = 977,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag670"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag853"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag125")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag423,
      countSetKey = 762,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag006"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag842=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1144")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag424,
      countSetKey = 873,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1304=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag081")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag425,
      countSetKey = 677,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1546")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag426,
      countSetKey = 797,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1232=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag922=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag864")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag427,
      countSetKey = 913,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag842"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag215"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag842")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag428,
      countSetKey = 1063,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag670=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1637=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag429,
      countSetKey = 1001,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1213"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1220=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1220=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag430,
      countSetKey = 973,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1383=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag950"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag930"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag930=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag431,
      countSetKey = 1046,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1495=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag357")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag432,
      countSetKey = 934,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag207"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1495")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag433,
      countSetKey = 1059,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1637=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag786=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag869")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag434,
      countSetKey = 1055,
      `i1 = 0 and i2 = 0` = Option("Tag670"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag435,
      countSetKey = 724,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1232=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag922=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag436,
      countSetKey = 1013,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag447=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag437,
      countSetKey = 1115,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1495")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag438,
      countSetKey = 1100,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag786"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag908"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag786")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag439,
      countSetKey = 1040,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1546=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag440,
      countSetKey = 1020,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1408")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag441,
      countSetKey = 966,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag742"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag742"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag742"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag742")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag442,
      countSetKey = 1051,
      `i1 = 0 and i2 = 0` = Option("Tag670"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag038"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag786=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag443,
      countSetKey = 998,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag444,
      countSetKey = 1104,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1546=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1495=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag445,
      countSetKey = 981,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1720"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1198"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1510"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag446,
      countSetKey = 766,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag842"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1495=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag152")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag447,
      countSetKey = 793,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag842=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag448,
      countSetKey = 925,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag842"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag209"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1546=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag449,
      countSetKey = 1047,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag450,
      countSetKey = 798,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1637=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag081=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag149")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag451,
      countSetKey = 1057,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag452,
      countSetKey = 910,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag842"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag289"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag842")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag453,
      countSetKey = 778,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1495=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1495=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag454,
      countSetKey = 788,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1495=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag455,
      countSetKey = 1032,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1546=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag447=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag456,
      countSetKey = 1079,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag081")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag457,
      countSetKey = 974,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag432"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1637")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag458,
      countSetKey = 1017,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag459,
      countSetKey = 980,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag655"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1443"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1443")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag460,
      countSetKey = 783,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag842=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag461,
      countSetKey = 989,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1214=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1214"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1214=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1214=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag462,
      countSetKey = 1075,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option.empty,
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag463,
      countSetKey = 1016,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag243"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag243")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag464,
      countSetKey = 970,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1383=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag699"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag566"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag699")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag465,
      countSetKey = 985,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1546"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag842"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag131")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag466,
      countSetKey = 1060,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag153")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag467,
      countSetKey = 984,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag670"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag931")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag468,
      countSetKey = 721,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1245"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag469,
      countSetKey = 1002,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1337"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag470,
      countSetKey = 674,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1229"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1546")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag471,
      countSetKey = 718,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag472,
      countSetKey = 773,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1720"),
      `i1 = 0 and i2 gt 0` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag038"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag786"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag786=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag473,
      countSetKey = 794,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1232=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag712"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag710")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag474,
      countSetKey = 975,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1546"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag006"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag506")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag475,
      countSetKey = 772,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1720"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1510"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1510")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag476,
      countSetKey = 711,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option.empty,
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag477,
      countSetKey = 843,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1322=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1047")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag478,
      countSetKey = 1125,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1339=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1408")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag479,
      countSetKey = 764,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1637=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag853"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1047"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag144")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag480,
      countSetKey = 1048,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1637=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag961"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag786=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag903")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag481,
      countSetKey = 784,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag670=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag038"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag786"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag786=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag482,
      countSetKey = 1117,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1348=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1495")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag483,
      countSetKey = 1029,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1166=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1326")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag484,
      countSetKey = 1022,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag670=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1408")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag485,
      countSetKey = 932,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1340=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1495")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag486,
      countSetKey = 1061,
      `i1 = 0 and i2 = 0` = Option("Tag961"),
      `i1 gt 0 and i2 = 0` = Option.empty,
      `i1 = 0 and i2 gt 0` = Option("Tag1232"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag487,
      countSetKey = 983,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1720"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag447"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag488,
      countSetKey = 768,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1637=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag081=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag522")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag489,
      countSetKey = 1015,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option.empty,
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag490,
      countSetKey = 1044,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag491,
      countSetKey = 695,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1198"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1261"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1198")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag492,
      countSetKey = 675,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1720"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1510"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1546")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag493,
      countSetKey = 795,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1637=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag842"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1047"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag148")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag494,
      countSetKey = 1081,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1315=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag081")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag495,
      countSetKey = 968,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1546"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1216"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag006=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1216")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag496,
      countSetKey = 1049,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag786"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag413")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag497,
      countSetKey = 712,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option.empty,
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1232"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag498,
      countSetKey = 855,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag853"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag823"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag853")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag499,
      countSetKey = 707,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option.empty,
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag500,
      countSetKey = 971,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag670"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag486"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag853=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag486")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag501,
      countSetKey = 791,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1236"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1236"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1675"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1236")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag502,
      countSetKey = 717,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1321=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag503,
      countSetKey = 840,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag829"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1047")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag504,
      countSetKey = 1085,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag812"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1637=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag505,
      countSetKey = 1023,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1408")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag506,
      countSetKey = 1052,
      `i1 = 0 and i2 = 0` = Option("Tag670"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag447=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag507,
      countSetKey = 978,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1720"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1671"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag508,
      countSetKey = 1008,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag509,
      countSetKey = 876,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag845"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag081")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag510,
      countSetKey = 859,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag855"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag670")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag511,
      countSetKey = 1084,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag447=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag512,
      countSetKey = 976,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1383=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag930"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag930"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag930")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag513,
      countSetKey = 1126,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag206"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1408")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag514,
      countSetKey = 1010,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag515,
      countSetKey = 771,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag842=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1510")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag516,
      countSetKey = 699,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1210"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1510")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag517,
      countSetKey = 652,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag340=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag578"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1612"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag120=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag518,
      countSetKey = 995,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1720=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1546"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1546=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag519,
      countSetKey = 763,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1232=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag930"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag712"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag739")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag520,
      countSetKey = 1045,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1637=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag922"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag081=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag730")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag521,
      countSetKey = 716,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1321=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1324"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag522,
      countSetKey = 967,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag120"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag120"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag120"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag120")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag523,
      countSetKey = 1097,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag786"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1293=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag786")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag524,
      countSetKey = 767,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1232=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag950"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag922=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag891")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag525,
      countSetKey = 1056,
      `i1 = 0 and i2 = 0` = Option("Tag670"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag670"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag526,
      countSetKey = 1065,
      `i1 = 0 and i2 = 0` = Option("Tag1637"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag670=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag447=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag527,
      countSetKey = 796,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1495=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
    )
  )
  add(
    MapPlan(
      key = Tags.Tag528,
      countSetKey = 1019,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag1720"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1408"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1408")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag529,
      countSetKey = 992,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1184=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1184"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1184=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1184=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag530,
      countSetKey = 723,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag670"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1637"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag531,
      countSetKey = 1120,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag322"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1495=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag532,
      countSetKey = 1009,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option.empty,
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag1327=reverse")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag533,
      countSetKey = 923,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag081"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag006"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag259"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option("Tag006")
    )
  )
  add(
    MapPlan(
      key = Tags.Tag534,
      countSetKey = 1037,
      `i1 = 0 and i2 = 0` = Option("Tag081"),
      `i1 gt 0 and i2 = 0` = Option("Tag081=reverse"),
      `i1 = 0 and i2 gt 0` = Option("Tag1166=reverse"),
      `i1 gt 0 and i2 gt 0 and i1 = i2` = Option("Tag1495"),
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = Option("Tag1327"),
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = Option.empty
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

  /** (i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 0 else if (i1 > 0 && i2 == 0) i1 + 3 else if (i1 == 0 && i2 > 0) i1 + (i1 + i2 - 1) / i2
    * else if (i1 > 0 && i2 > 0 && i1 == i2) i2 + 3 else if (i1 > 0 && i2 > 0 && i1 > i2) i1 + 3 else if (i1 > 0 && i2 > 0 && i1 < i2) i1 +
    * i1 / i2 + 3 else ???
    */

}

object ConfirmCol {
  lazy val cols: Vector[ConfirmPlan] = {
    class A extends ConfirmCol
    val a = new A
    a.confirmCol
  }
}
