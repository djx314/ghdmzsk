package f08

import f07.Tags

sealed trait SetsList {
  def key: String
  def count(i1: Int, i2: Int): Option[Int]
}
case class CommonSetsList(override val key: String, value: (Int, Int) => Int) extends SetsList {
  override def count(i1: Int, i2: Int): Option[Int] = Option(value(i1, i2))
}
case class OptSetsList(
  override val key: String,
  value: (Int, Int) => Option[Int]
) extends SetsList {
  override def count(i1: Int, i2: Int): Option[Int] = value(i1, i2)
}
trait MapParameterSetsList extends SetsList {
  def mapParameter(i1: Int, i2: Int): (Int, Int)
  def countWithSetList(setList: SetsList, i1: Int, i2: Int): Option[Int] = {
    val (v1, v2) = mapParameter(i1, i2)
    setList.count(v1, v2)
  }
  def linkKey: String
}
trait MapResultSetsList extends SetsList {
  def mapResult(i1: Option[Int]): Option[Int]
  def countWithSetList(setList: SetsList, i1: Int, i2: Int): Option[Int] = mapResult(setList.count(i1, i2))
  def linkKey: String
}

trait NaturalProjectionAbs {
  def setsCol: Vector[SetsList]            = setsList
  protected var setsList: Vector[SetsList] = Vector.empty
  def getSet(key: String): SetsList        = setsList.find(s => s.key == key).get

  protected implicit class extract1(key: String) {
    def setValue(v: (Int, Int) => Int): Unit = setsList = setsList.appended(CommonSetsList(key = key, value = v))
  }
  protected implicit class extract2(key: String) {
    def setValue(v: (Int, Int) => Option[Int]): Unit = setsList = setsList.appended(OptSetsList(key = key, value = v))
  }
  protected implicit class extract5(key1: String) {
    def setValue(linkKey1: String, v: (Int, Int) => (Int, Int)): Unit = setsList = setsList.appended(new MapParameterSetsList {
      override def mapParameter(i1: Int, i2: Int): (Int, Int) = v(i1, i2)
      override def linkKey: String                            = linkKey1
      override def key: String                                = key1
      override def count(i1: Int, i2: Int): Option[Int]       = countWithSetList(getSet(linkKey1), i1, i2)
    })
  }
  protected implicit class extract6(key1: String) {
    def mapResult(linkKey1: String, v: Option[Int] => Option[Int]): Unit = setsList = setsList.appended(new MapResultSetsList { self =>
      override def mapResult(i1: Option[Int]): Option[Int] = v(i1)
      override def linkKey: String                         = linkKey1
      override def key: String                             = key1
      override def count(i1: Int, i2: Int): Option[Int]    = countWithSetList(getSet(linkKey1), i1, i2)
    })
  }
}

object NaturalProjection extends NaturalProjectionAbs1 {
  Tags.Tag001.setValue((i1: Int, i2: Int) => i1 + i2 * 2 + 1)
  Tags.Tag002.setValue((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 - i2 else 0)
  Tags.Tag003.setValue((i1: Int, i2: Int) => i2 * 2 + 1)
  Tags.Tag004.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2 * 2 + 1) else Option.empty)
  Tags.Tag005.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2 + 1) else Option.empty)
  Tags.Tag006.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * 2 + i2)
  Tags.Tag007.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + i2)
  Tags.Tag008.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(i1 * 2))
  Tags.Tag009.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2)
  Tags.Tag010.setValue((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 + i2 else i1 * 2)
  Tags.Tag011.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(i1))
  Tags.Tag012.setValue((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 * 2 - i2 else i1)
  Tags.Tag013.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) 0 else i2 - i1 + 1)
  Tags.Tag014.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 1 else i1 + i2)
  Tags.Tag015.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 + 1 else i1)
  Tags.Tag016.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(0))
  Tags.Tag017.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2) else Option.empty)
  Tags.Tag018.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(0) else Option.empty)
  Tags.Tag019.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i2)
  Tags.Tag020.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 else i1)
  Tags.Tag021.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(0))
  Tags.Tag022.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else 0)
  Tags.Tag023.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 else i2 * 2 - i1 + 1)
  Tags.Tag024.setValue((i1: Int, i2: Int) => Option.empty)
  Tags.Tag025.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(0) else Option.empty)
  Tags.Tag026.setValue((i1: Int, i2: Int) => 0)
  Tags.Tag027.setValue((i1: Int, i2: Int) => i1)
  Tags.Tag028.setValue((i1: Int, i2: Int) => i1 + i2 + 1)
  Tags.Tag029.setValue((i1: Int, i2: Int) => i1 + 1)
  Tags.Tag030.setValue((i1: Int, i2: Int) => i1 + i2)
  Tags.Tag031.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + 1))
  Tags.Tag032.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + 2))
  Tags.Tag033.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i2 + 1)
  Tags.Tag034.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else i2 + 1)
  Tags.Tag035.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2))
  Tags.Tag036.setValue((i1: Int, i2: Int) => if (i2 == 0) 0 else i1)
  Tags.Tag037.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 + 1 else i2)
  Tags.Tag038.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(i1 + 1) else Option.empty)
  Tags.Tag039.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(i1) else Option.empty)
  Tags.Tag040.setValue((i1: Int, i2: Int) => i1 / (i2 + 1))
  Tags.Tag041.setValue((i1: Int, i2: Int) => if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) else i1 / (i2 + 1) + 1)
  Tags.Tag042.setValue((i1: Int, i2: Int) => if (i2 % (i1 + 1) == i1) i2 / (i1 + 1) + 1 else i2 / (i1 + 1))
  Tags.Tag043.setValue((i1: Int, i2: Int) => i2 / (i1 + 1) + 1)
  Tags.Tag044.setValue((i1: Int, i2: Int) =>
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2 - 1) else Option(i1 / i2)
  )
  Tags.Tag045.setValue((i1: Int, i2: Int) =>
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2) else Option(i1 / i2 + 1)
  )
  Tags.Tag046.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 / i1))
  Tags.Tag047.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 / i1 + 1))
  Tags.Tag048.setValue((i1: Int, i2: Int) => i2 + 1)
  Tags.Tag049.setValue((i1: Int, i2: Int) => 1)
  Tags.Tag050.setValue((i1: Int, i2: Int) => i2)
  Tags.Tag051.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(1))
  Tags.Tag052.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(2))
  Tags.Tag053.setValue((i1: Int, i2: Int) => if (i2 == 0) 0 else 1)
  Tags.Tag054.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(0))
  Tags.Tag055.setValue((i1: Int, i2: Int) => if (i2 == 0) 1 else 0)
  Tags.Tag056.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(1) else Option.empty)
  Tags.Tag057.setValue((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) Option(1) else Option.empty)
  Tags.Tag058.setValue((i1: Int, i2: Int) => if (i1 != 0) Option(1) else Option.empty)
  Tags.Tag059.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else Option.empty)
  Tags.Tag060.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(1) else Option.empty)
  Tags.Tag061.setValue((i1: Int, i2: Int) => i1 * i2 + i1 * 2)
  Tags.Tag062.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * i2 + 2 * i1 - i2)
  Tags.Tag063.setValue((i1: Int, i2: Int) => i1 * i2 + 2 * i2 + i1 + 1)
  Tags.Tag064.setValue((i1: Int, i2: Int) => i1 * i2 + 2 * i2 + 1)
  Tags.Tag065.setValue((i1: Int, i2: Int) => i1 * i2 + i1)
  Tags.Tag066.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * i2 + i1 - i2)
  Tags.Tag067.setValue((i1: Int, i2: Int) => i1 * i2 + i1 + i2)
  Tags.Tag068.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else 1)
  Tags.Tag069.setValue((i1: Int, i2: Int) => 2 * i1)
  Tags.Tag070.setValue((i1: Int, i2: Int) => 2 * i1 + 1)
  Tags.Tag071.setValue((i1: Int, i2: Int) =>
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2 - 1) else Option(2 * i1 + i1 / i2)
  )
  Tags.Tag072.setValue((i1: Int, i2: Int) =>
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2) else Option(2 * i1 + i1 / i2 + 1)
  )
  Tags.Tag073.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(2 * i2 + i2 / i1 + 1))
  Tags.Tag074.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(2 * i2 + i2 / i1 + 2))
  Tags.Tag075.setValue((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) 1 else 2 * i1)
  Tags.Tag076.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else 2 * i2 + 1)
  Tags.Tag077.setValue((i1: Int, i2: Int) => 2 * i1 - i1 / (i2 + 1))
  Tags.Tag078.setValue((i1: Int, i2: Int) => if (i1 % (i2 + 1) == 0) 2 * i1 - i1 / (i2 + 1) else 2 * i1 - i1 / (i2 + 1) - 1)
  Tags.Tag079.setValue((i1: Int, i2: Int) => 2 * i2 - (i2 + 1) / (i1 + 1) + 1)
  Tags.Tag080.setValue((i1: Int, i2: Int) =>
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2 - 1) else Option(i1 + i1 / i2)
  )
  Tags.Tag081.setValue((i1: Int, i2: Int) =>
    if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2) else Option(i1 + i1 / i2 + 1)
  )
  Tags.Tag082.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + i2 / i1))
  Tags.Tag083.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + i2 / i1 + 1))
  Tags.Tag084.setValue((i1: Int, i2: Int) => i1 * i2)
  Tags.Tag085.setValue((i1: Int, i2: Int) => i1 * i2 + i2)
  Tags.Tag086.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else (i1 - 1) * i2)
  Tags.Tag087.setValue((i1: Int, i2: Int) => Option.empty)
  Tags.Tag088.setValue((i1: Int, i2: Int) => 2)
  Tags.Tag089.setValue((i1: Int, i2: Int) => 3)
  Tags.Tag090.setValue((i1: Int, i2: Int) => Option.empty)
  Tags.Tag091.setValue((i1: Int, i2: Int) => 0)
  Tags.Tag092.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(2))
  Tags.Tag093.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i1 == 1) Option(4) else Option(3))
  Tags.Tag094.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i1 == 1) Option(3) else Option(2))
  Tags.Tag095.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else 2)
  Tags.Tag096.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else 3)
  Tags.Tag097.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else Option.empty)
  Tags.Tag098.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else 2)
  Tags.Tag099.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(1) else Option.empty)
  Tags.Tag100.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i1 == 1) 2 else 3)
  Tags.Tag101.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 == 1) 1 else 2)
  Tags.Tag102.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option(0) else if (i1 != 0 && i2 == 0) Option(1) else Option.empty)
  Tags.Tag103.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option(0) else Option.empty)
  Tags.Tag104.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2) else Option.empty)
  Tags.Tag105.setValue((i1: Int, i2: Int) => if (i2 % (i1 + 1) == i1) i2 - i2 / (i1 + 1) else i2 - i2 / (i1 + 1) + 1)
  Tags.Tag106.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i1 == 1) Option(2) else Option(1))
  Tags.Tag107.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i1 == 1) Option(1) else Option(0))
  Tags.Tag108.setValue((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) Option(0) else Option.empty)
  Tags.Tag109.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) Option(0) else Option.empty)
  Tags.Tag110.setValue((i1: Int, i2: Int) => if (i1 == 0) 2 else 1)
  Tags.Tag111.setValue((i1: Int, i2: Int) => if (i1 == 0) 3 else if (i1 == 1) 2 else 1)
  Tags.Tag112.setValue((i1: Int, i2: Int) => if (i1 == 0) 2 else if (i1 == 1) 1 else 0)
  Tags.Tag113.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option(1) else if (i2 == 0) Option(0) else Option.empty)
  Tags.Tag114.setValue((i1: Int, i2: Int) => if (i2 % (i1 + 1) == i1) i2 + i2 / (i1 + 1) + 1 else i2 + i2 / (i1 + 1))
  Tags.Tag115.setValue((i1: Int, i2: Int) => Option.empty)
  Tags.Tag116.setValue((i1: Int, i2: Int) => i1 + 1)
  Tags.Tag117.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(3))
  Tags.Tag118.setValue((i1: Int, i2: Int) => i1 + 2)
  Tags.Tag119.setValue((i1: Int, i2: Int) => i1 + i2)
  Tags.Tag120.setValue((i1: Int, i2: Int) => if (i1 == 1) 5 else i1 + 3)
  Tags.Tag121.setValue((i1: Int, i2: Int) => if (i1 == 1) 4 else i1 + 2)
  Tags.Tag122.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(i1 + 1) else Option.empty)
  Tags.Tag123.setValue((i1: Int, i2: Int) => i1 + i2 + 1)
  Tags.Tag124.setValue((i1: Int, i2: Int) => i1 + i2 * 2 + i1 * (i2 / i1) + 1)
  Tags.Tag125.setValue((i1: Int, i2: Int) => i1 + i2 + i1 * (i2 / i1) + 1)
  Tags.Tag126.setValue((i1: Int, i2: Int) => i1)
  Tags.Tag127.setValue((i1: Int, i2: Int) => if (i1 == 1) 3 else i1 + 1)
  Tags.Tag128.setValue((i1: Int, i2: Int) => if (i1 == 1) 1 + 1 else i1)
  Tags.Tag129.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(i1) else Option.empty)
  Tags.Tag130.setValue((i1: Int, i2: Int) => i1 + i2 + i1 * (i2 / i1))
  Tags.Tag131.setValue((i1: Int, i2: Int) => i1 + i1 * (i2 / i1))
  Tags.Tag132.setValue((i1: Int, i2: Int) => 1)
  Tags.Tag133.setValue((i1: Int, i2: Int) => 2)
  Tags.Tag134.setValue((i1: Int, i2: Int) => 3)
  Tags.Tag135.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(1) else Option.empty)
  Tags.Tag136.setValue((i1: Int, i2: Int) => i2 + 1)
  Tags.Tag137.setValue((i1: Int, i2: Int) => 2 * i2 + 1)
  Tags.Tag138.setValue((i1: Int, i2: Int) => 0)
  Tags.Tag139.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(0) else Option.empty)
  Tags.Tag140.setValue((i1: Int, i2: Int) => i2)
  Tags.Tag141.setValue((i1: Int, i2: Int) => 0)
  Tags.Tag142.setValue((i1: Int, i2: Int) => if (i1 == 1) 4 else 3)
  Tags.Tag143.setValue((i1: Int, i2: Int) => if (i1 == 1) 3 else 2)
  Tags.Tag144.setValue((i1: Int, i2: Int) => i1 * (i2 / i1) + 2 * i2 + 1)
  Tags.Tag145.setValue((i1: Int, i2: Int) => i1 * (i2 / i1) + i2 + 1)
  Tags.Tag146.setValue((i1: Int, i2: Int) => if (i1 == 1) 2 else 3)
  Tags.Tag147.setValue((i1: Int, i2: Int) => if (i1 == 1) 1 else 2)
  Tags.Tag148.setValue((i1: Int, i2: Int) =>
    if ((i2 + 1) % (2 * i1) <= i1) (i2 + 1) % (2 * i1) + i2 + ((i2 + 1) / (2 * i1)) * i1 else i1 + i2 + ((i2 + 1) / (2 * i1)) * i1
  )
  Tags.Tag149.setValue((i1: Int, i2: Int) =>
    if ((i2 + 1) % (2 * i1) <= i1) (i2 + 1) % (2 * i1) + ((i2 + 1) / (2 * i1)) * i1 else i1 + ((i2 + 1) / (2 * i1)) * i1
  )
  Tags.Tag150.setValue((i1: Int, i2: Int) => if (i1 == 1) 2 else 1)
  Tags.Tag151.setValue((i1: Int, i2: Int) => if (i1 == 1) 1 else 0)
  Tags.Tag152.setValue((i1: Int, i2: Int) => i2 + i2 / i1 * i1)
  Tags.Tag153.setValue((i1: Int, i2: Int) => i2 / i1 * i1)
  Tags.Tag154.setValue((i1: Int, i2: Int) =>
    if ((i2 + 1) % (2 * i1) <= i1) i2 + ((i2 + 1) / (2 * i1)) * i1 else (i2 + 1) % (2 * i1) - i1 + i2 + ((i2 + 1) / (2 * i1)) * i1
  )
  Tags.Tag155.setValue((i1: Int, i2: Int) =>
    if ((i2 + 1) % (2 * i1) <= i1) ((i2 + 1) / (2 * i1)) * i1 else (i2 + 1) % (2 * i1) - i1 + ((i2 + 1) / (2 * i1)) * i1
  )
  Tags.Tag156.setValue((i1: Int, i2: Int) => 2 * i1 + 3)
  Tags.Tag157.setValue((i1: Int, i2: Int) => i1 + 2)
  Tags.Tag158.setValue((i1: Int, i2: Int) => i1)
  Tags.Tag159.setValue((i1: Int, i2: Int) => i1 / 2)
  Tags.Tag160.setValue((i1: Int, i2: Int) => if (i1 >= 1) i1 - 1 else 0)
  Tags.Tag161.setValue((i1: Int, i2: Int) => if (i1 % 2 == 0) i1 / 2 else i1 / 2 + 1)
  Tags.Tag162.setValue((i1: Int, i2: Int) => if (i1 >= 1) i1 * 2 - 1 else i1)
  Tags.Tag163.setValue((i1: Int, i2: Int) => if (i1 > 1) Option.empty else Option(0))
  Tags.Tag164.setValue((i1: Int, i2: Int) => if (i1 > 1) Option.empty else Option(i1))
  Tags.Tag165.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + 1)
  Tags.Tag166.setValue((i1: Int, i2: Int) => if (i1 > 1) Option(0) else Option.empty)
  Tags.Tag167.setValue((i1: Int, i2: Int) => if (i1 > 1) Option(1) else Option.empty)
  Tags.Tag168.setValue((i1: Int, i2: Int) => 2 * i1)
  Tags.Tag169.setValue((i1: Int, i2: Int) => if (i1 % 2 == 0) 2 * i1 - i1 / 2 else 2 * i1 - i1 / 2 - 1)
  Tags.Tag170.setValue((i1: Int, i2: Int) => 2 * i1 - i1 / 2)
  Tags.Tag171.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else 3 * i1 - 1)
  Tags.Tag172.setValue((i1: Int, i2: Int) => 3 * i1)
  Tags.Tag173.setValue((i1: Int, i2: Int) => if (i1 > 1) 3 else i1 * 2)
  Tags.Tag174.setValue((i1: Int, i2: Int) => if (i1 > 1) Option.empty else Option(2 * i1))
  Tags.Tag175.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * 2 + 1)
  Tags.Tag176.setValue((i1: Int, i2: Int) => if (i1 > 1) Option(2) else Option.empty)
  Tags.Tag177.setValue((i1: Int, i2: Int) => if (i1 > 1) Option(3) else Option.empty)
  Tags.Tag178.setValue((i1: Int, i2: Int) => i1 + 3)
  Tags.Tag179.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(1 / i1 + 4))
  Tags.Tag180.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(3))
  Tags.Tag181.setValue((i1: Int, i2: Int) => if (i2 % 2 == 1) i2 / 2 + 1 else i2 / 2)
  Tags.Tag182.setValue((i1: Int, i2: Int) => if (i2 % 2 == 1) i2 + i2 / 2 + 1 else i2 + i2 / 2)
  Tags.Tag183.setValue((i1: Int, i2: Int) => if (i2 == 0) 0 else i2 * 2)
  Tags.Tag184.setValue((i1: Int, i2: Int) => i2 / 2 + 1)
  Tags.Tag185.setValue((i1: Int, i2: Int) => 2 * i2 - (i2 + 1) / 2 + 1)
  Tags.Tag186.setValue((i1: Int, i2: Int) => 4)
  Tags.Tag187.setValue((i1: Int, i2: Int) => 3 * i2 + 1)
  Tags.Tag188.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(2) else Option.empty)
  Tags.Tag189.setValue((i1: Int, i2: Int) => i2 * 2 + 2)
  Tags.Tag190.setValue((i1: Int, i2: Int) => 3 * i2 + 2)
  Tags.Tag191.setValue((i1: Int, i2: Int) => i2 + 2)
  Tags.Tag192.setValue((i1: Int, i2: Int) => 5)
  Tags.Tag193.setValue((i1: Int, i2: Int) => 1)
  Tags.Tag194.setValue((i1: Int, i2: Int) => i2)
  Tags.Tag195.setValue((i1: Int, i2: Int) => i2 + 1)
  Tags.Tag196.setValue((i1: Int, i2: Int) => 2)
  Tags.Tag197.setValue((i1: Int, i2: Int) => i2 + 2)
  Tags.Tag198.setValue((i1: Int, i2: Int) => if (i2 <= 1) 2 - i2 else 1)
  Tags.Tag199.setValue((i1: Int, i2: Int) => if (i2 == 0) 1 else 2)
  Tags.Tag200.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(i1) else Option.empty)
  Tags.Tag201.setValue((i1: Int, i2: Int) => (i2 + 1) * (i1 + 1))
  Tags.Tag202.setValue((i1: Int, i2: Int) => 2 * i1 + 2)
  Tags.Tag203.setValue((i1: Int, i2: Int) => 2 * i2 - i2 / (i1 + 1))
  Tags.Tag204.setValue((i1: Int, i2: Int) => if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 else (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) - i2)
  Tags.Tag205.setValue((i1: Int, i2: Int) => if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) else (i1 / (i2 * 2)) * i2 + i2)
  Tags.Tag206.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 else (i1 / i2) * i2)
  Tags.Tag207.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + i1 else (i1 / i2) * i2 + i1)
  Tags.Tag208.setValue((i1: Int, i2: Int) => if (i1 % i2 == 0) (i1 / i2) * i2 else (i1 / i2) * i2 + i2)
  Tags.Tag209.setValue((i1: Int, i2: Int) => if (i1 % i2 == 0) (i1 / i2) * i2 + i1 else (i1 / i2) * i2 + i2 + i1)
  Tags.Tag210.setValue((i1: Int, i2: Int) => i1 / (i2 + 1) * i2 + i1 % (i2 + 1))
  Tags.Tag211.setValue((i1: Int, i2: Int) => i2 / (i1 + 1) * i1 + i2 % (i1 + 1))
  Tags.Tag212.setValue((i1: Int, i2: Int) =>
    if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) * i2 + i1 % (i2 + 1) else i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1
  )
  Tags.Tag213.setValue((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) 1 else i1)
  Tags.Tag214.setValue((i1: Int, i2: Int) =>
    if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 else (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 - i2
  )
  Tags.Tag215.setValue((i1: Int, i2: Int) =>
    if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 else (i1 / (i2 * 2)) * i2 + i1 + i2
  )
  Tags.Tag216.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + 2 * i1 else (i1 / i2) * i2 + 2 * i1)
  Tags.Tag217.setValue((i1: Int, i2: Int) => if (i1 % i2 == 0) (i1 / i2) * i2 + 2 * i1 else (i1 / i2) * i2 + i2 + 2 * i1)
  Tags.Tag218.setValue((i1: Int, i2: Int) => i1 / (i2 + 1) + i1)
  Tags.Tag219.setValue((i1: Int, i2: Int) => if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) + i1 else i1 / (i2 + 1) + i1 + 1)
  Tags.Tag220.setValue((i1: Int, i2: Int) => i2 / (i1 + 1) + i2 + 1)
  Tags.Tag221.setValue((i1: Int, i2: Int) => 1 / (i1 + 1) + 2)
  Tags.Tag222.setValue((i1: Int, i2: Int) => i1 * i2 + i2 + 1)
  Tags.Tag223.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 / i1 + i2 + 2))
  Tags.Tag224.setValue((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) Option(2) else Option.empty)
  Tags.Tag225.setValue((i1: Int, i2: Int) => if (i2 >= 1) i2 - 1 else 0)
  Tags.Tag226.setValue((i1: Int, i2: Int) => if (i2 - i1 >= 0) i2 - i1 else 0)
  Tags.Tag227.setValue((i1: Int, i2: Int) => if (i2 == 0) 1 else i2)
  Tags.Tag228.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else i1)
  Tags.Tag229.setValue((i1: Int, i2: Int) => if (i1 <= 1) 1 else 2)
  Tags.Tag230.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else i1)
  Tags.Tag231.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else 1)
  Tags.Tag232.setValue((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 else i2)
  Tags.Tag233.setValue((i1: Int, i2: Int) => if (i1 - i2 <= 0) i2 else i2 + 1)
  Tags.Tag234.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else i1 - 1)
  Tags.Tag235.setValue((i1: Int, i2: Int) => if (i1 <= 1) 1 else i1 - 1)
  Tags.Tag236.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i1 == 1) 0 else i1 - 2)
  Tags.Tag237.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else i1 + i2 - 1)
  Tags.Tag238.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else i1 - 1)
  Tags.Tag239.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else 0)
  Tags.Tag240.setValue((i1: Int, i2: Int) => if (i2 - i1 >= 0) i2 else i1 - 1)
  Tags.Tag241.setValue((i1: Int, i2: Int) => if (i1 - i2 - 1 >= 0) i1 - i2 - 1 else i2 - i1)
  Tags.Tag242.setValue((i1: Int, i2: Int) => if (i1 <= 1) i1 else i1 - 1)
  Tags.Tag243.setValue((i1: Int, i2: Int) => if (i1 <= 2) 0 else i1 - 2)
  Tags.Tag244.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + i2 - 1)
  Tags.Tag245.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 - i1 >= 0) i1 else i1 - 1)
  Tags.Tag246.setValue((i1: Int, i2: Int) => if (i1 - i2 - 1 >= 0) i1 - i2 - 1 else 0)
  Tags.Tag247.setValue((i1: Int, i2: Int) => i2 + i2 / i1 * i1)
  Tags.Tag248.setValue((i1: Int, i2: Int) => i2)
  Tags.Tag249.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(0))
  Tags.Tag250.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 + 1 else i1)
  Tags.Tag251.setValue((i1: Int, i2: Int) =>
    if (i1 % (i2 * 2) <= i2) (i1 / (i2 * 2)) * i2 + i1 else (i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 - i2
  )
  Tags.Tag252.setValue((i1: Int, i2: Int) => if (i1 % i2 == 0) (i1 / i2 - 1) * i2 + i1 else (i1 / i2) * i2 + i1)
  Tags.Tag253.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(i1 * 2))
  Tags.Tag254.setValue((i1: Int, i2: Int) => i1 * 2)
  Tags.Tag255.setValue((i1: Int, i2: Int) => Option(1))
  Tags.Tag256.setValue((i1: Int, i2: Int) => i1)
  Tags.Tag257.setValue((i1: Int, i2: Int) => i2 + 1)
  Tags.Tag258.setValue((i1: Int, i2: Int) => if (i1 % i2 == 0) (i1 / i2) * i2 + i1 else (i1 / i2) * i2 + i2 + i1)
  Tags.Tag259.setValue((i1: Int, i2: Int) => if (i1 % i2 == 0) (i1 / i2) * i2 + 2 * i1 else (i1 / i2) * i2 + i2 + 2 * i1)
  Tags.Tag260.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(i1))
  Tags.Tag261.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(1))
  Tags.Tag262.setValue((i1: Int, i2: Int) => if (i2 == 0) i1 else 1)
  Tags.Tag263.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 + 1 else i1)
  Tags.Tag264.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(i1))
  Tags.Tag265.setValue((i1: Int, i2: Int) => i1 / (i2 + 1) + i1)
  Tags.Tag266.setValue((i1: Int, i2: Int) => if (i2 == 0) 1 else i1)
  Tags.Tag267.setValue((i1: Int, i2: Int) =>
    if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2 - 1) else Option(i1 + i1 / i2)
  )
  Tags.Tag268.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2)
  Tags.Tag269.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(i1 * 2))
  Tags.Tag270.setValue((i1: Int, i2: Int) => i1 * 2 - i1 / (i2 + 1))
  Tags.Tag271.setValue((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) 1 else 2 * i1)
  Tags.Tag272.setValue((i1: Int, i2: Int) =>
    if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2 - 1) else Option(2 * i1 + i1 / i2)
  )
  Tags.Tag273.setValue((i1: Int, i2: Int) => i1 * 2)
  Tags.Tag274.setValue((i1: Int, i2: Int) => i1 * i2 + i1)
  Tags.Tag275.setValue((i1: Int, i2: Int) => i1 * i2 + i1 * 2)
  Tags.Tag276.setValue((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 + i2 else i1 * 2)
  Tags.Tag277.setValue((i1: Int, i2: Int) => if (i1 == 1) Option(i1) else Option.empty)
  Tags.Tag278.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2 * 2 + 1) else Option.empty)
  Tags.Tag279.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2 + 1) else Option.empty)
  Tags.Tag280.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2 * 2 + 1) else Option.empty)
  Tags.Tag281.setValue((i1: Int, i2: Int) => if (i1 == 1) Option.empty else Option(2))
  Tags.Tag282.setValue((i1: Int, i2: Int) => if (i1 == 1) Option.empty else Option(3))
  Tags.Tag283.setValue((i1: Int, i2: Int) =>
    if ((i2 + 1) % (2 * i1) <= i1) (i2 + 1) % (2 * i1) + i2 + ((i2 + 1) / (2 * i1)) * i1 else i1 + i2 + ((i2 + 1) / (2 * i1)) * i1
  )
  Tags.Tag284.setValue(Tags.Tag018, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag285.setValue(Tags.Tag066, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag286.setValue(Tags.Tag031, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag287.setValue(Tags.Tag083, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag288.setValue(Tags.Tag079, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag289.setValue(Tags.Tag216, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag290.setValue(Tags.Tag002, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag291.setValue(Tags.Tag040, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag292.setValue(Tags.Tag156, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag293.setValue(Tags.Tag472, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag294.setValue(Tags.Tag034, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag295.setValue(Tags.Tag239, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag296.setValue(Tags.Tag038, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag297.setValue(Tags.Tag185, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag298.setValue(Tags.Tag074, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag299.setValue(Tags.Tag021, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag300.setValue(Tags.Tag009, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag301.setValue(Tags.Tag462, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag302.setValue(Tags.Tag021, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag303.setValue(Tags.Tag001, (i1: Int, i2: Int) => (1, 1))
  Tags.Tag304.setValue(Tags.Tag041, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag305.setValue(Tags.Tag001, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag306.setValue(Tags.Tag455, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag307.setValue(Tags.Tag029, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag308.setValue(Tags.Tag233, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag309.setValue(Tags.Tag020, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag310.setValue(Tags.Tag557, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag311.setValue(Tags.Tag057, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag312.setValue(Tags.Tag470, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag313.setValue(Tags.Tag150, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag314.setValue(Tags.Tag020, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag315.setValue(Tags.Tag063, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag316.setValue(Tags.Tag222, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag317.setValue(Tags.Tag477, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag318.setValue(Tags.Tag034, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag319.setValue(Tags.Tag244, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag320.setValue(Tags.Tag015, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag321.setValue(Tags.Tag188, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag322.setValue(Tags.Tag208, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag323.setValue(Tags.Tag239, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag324.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag325.setValue(Tags.Tag004, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag326.setValue(Tags.Tag013, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag327.setValue(Tags.Tag021, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag328.setValue(Tags.Tag020, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag329.setValue(Tags.Tag178, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag330.setValue(Tags.Tag008, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag331.setValue(Tags.Tag042, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag332.setValue(Tags.Tag447, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag333.setValue(Tags.Tag017, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag334.setValue(Tags.Tag077, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag335.setValue(Tags.Tag011, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag336.setValue(Tags.Tag210, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag337.setValue(Tags.Tag016, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag338.setValue(Tags.Tag017, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag339.setValue(Tags.Tag213, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag340.setValue(Tags.Tag001, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag341.setValue(Tags.Tag031, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag342.setValue(Tags.Tag009, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag343.setValue(Tags.Tag047, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag344.setValue(Tags.Tag039, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag345.setValue(Tags.Tag041, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag346.setValue(Tags.Tag210, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag347.setValue(Tags.Tag011, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag348.setValue(Tags.Tag082, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag349.setValue(Tags.Tag188, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag350.setValue(Tags.Tag473, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag351.setValue(Tags.Tag187, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag352.setValue(Tags.Tag220, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag353.setValue(Tags.Tag213, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag354.setValue(Tags.Tag035, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag355.setValue(Tags.Tag125, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag356.setValue(Tags.Tag063, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag357.setValue(Tags.Tag154, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag358.setValue(Tags.Tag067, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag359.setValue(Tags.Tag001, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag360.setValue(Tags.Tag231, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag361.setValue(Tags.Tag042, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag362.setValue(Tags.Tag032, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag363.setValue(Tags.Tag475, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag364.setValue(Tags.Tag007, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag365.setValue(Tags.Tag038, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag366.setValue(Tags.Tag156, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag367.setValue(Tags.Tag078, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag368.setValue(Tags.Tag033, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag369.setValue(Tags.Tag220, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag370.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag371.setValue(Tags.Tag072, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag372.setValue(Tags.Tag005, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag373.setValue(Tags.Tag001, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag374.setValue(Tags.Tag009, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag375.setValue(Tags.Tag010, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag376.setValue(Tags.Tag035, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag377.setValue(Tags.Tag073, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag378.setValue(Tags.Tag266, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag379.setValue(Tags.Tag220, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag380.setValue(Tags.Tag017, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag381.setValue(Tags.Tag153, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag382.setValue(Tags.Tag001, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag383.setValue(Tags.Tag019, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag384.setValue(Tags.Tag222, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag385.setValue(Tags.Tag149, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag386.setValue(Tags.Tag040, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag387.setValue(Tags.Tag032, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag388.setValue(Tags.Tag072, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag389.setValue(Tags.Tag002, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag390.setValue(Tags.Tag014, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag391.setValue(Tags.Tag031, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag392.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag393.setValue(Tags.Tag001, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag394.setValue(Tags.Tag003, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag395.setValue(Tags.Tag184, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag396.setValue(Tags.Tag079, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag397.setValue(Tags.Tag212, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag398.setValue(Tags.Tag178, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag399.setValue(Tags.Tag003, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag400.setValue(Tags.Tag043, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag401.setValue(Tags.Tag005, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag402.setValue(Tags.Tag468, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag403.setValue(Tags.Tag003, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag404.setValue(Tags.Tag037, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag405.setValue(Tags.Tag076, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag406.setValue(Tags.Tag007, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag407.setValue(Tags.Tag003, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag408.setValue(Tags.Tag061, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag409.setValue(Tags.Tag014, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag410.setValue(Tags.Tag084, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag411.setValue(Tags.Tag002, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag412.setValue(Tags.Tag077, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag413.setValue(Tags.Tag155, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag414.setValue(Tags.Tag095, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag415.setValue(Tags.Tag008, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag416.setValue(Tags.Tag439, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag417.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag418.setValue(Tags.Tag105, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag419.setValue(Tags.Tag219, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag420.setValue(Tags.Tag033, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag421.setValue(Tags.Tag246, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag422.setValue(Tags.Tag114, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag423.setValue(Tags.Tag002, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag424.setValue(Tags.Tag033, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag425.setValue(Tags.Tag006, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag426.setValue(Tags.Tag035, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag427.setValue(Tags.Tag234, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag428.setValue(Tags.Tag019, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag429.setValue(Tags.Tag043, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag430.setValue(Tags.Tag064, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag431.setValue(Tags.Tag034, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag432.setValue(Tags.Tag127, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag433.setValue(Tags.Tag062, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag434.setValue(Tags.Tag074, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag435.setValue(Tags.Tag018, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag436.setValue(Tags.Tag038, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag437.setValue(Tags.Tag064, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag438.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(i2))
  Tags.Tag439.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(i2 + 1))
  Tags.Tag440.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i1))
  Tags.Tag441.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i1))
  Tags.Tag442.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i1 + 1))
  Tags.Tag443.setValue((i1: Int, i2: Int) => if (i2 == 0) i1 * 2 else 1)
  Tags.Tag444.setValue((i1: Int, i2: Int) => if (i2 == 0) Option(i1 * 2) else Option.empty)
  Tags.Tag445.setValue((i1: Int, i2: Int) => if (i2 == 0 && i1 > 0) i1 - 1 else 0)
  Tags.Tag446.setValue((i1: Int, i2: Int) => if (i2 == 0) 0 else if (i1 == 0) i2 else i1 - 1)
  Tags.Tag447.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i1 - 1))
  Tags.Tag448.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i1 - i2 - 1) else Option.empty)
  Tags.Tag449.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i1 - 1) else Option.empty)
  Tags.Tag450.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i1 + i2 - 1))
  Tags.Tag451.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i1 + i2 - 1))
  Tags.Tag452.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i2 == 0) Option(i1 - 1) else Option(0))
  Tags.Tag453.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) Option.empty else Option(0))
  Tags.Tag454.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i2 == 0) Option(0) else Option(i1 - 1))
  Tags.Tag455.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) Option.empty else Option(i1 - 1))
  Tags.Tag456.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i1 - i2 - 1) else Option.empty)
  Tags.Tag457.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i1 - 1) else Option.empty)
  Tags.Tag458.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i1 - 1))
  Tags.Tag459.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else if (i2 == 0) i1 - 1 else 0)
  Tags.Tag460.setValue((i1: Int, i2: Int) => if (i1 <= 1 || i2 == 0) Option.empty else Option(0))
  Tags.Tag461.setValue((i1: Int, i2: Int) => if (i1 <= 1 || i2 == 0) Option.empty else Option(i1 - 2))
  Tags.Tag462.setValue((i1: Int, i2: Int) => if (i1 <= 1 || i2 == 0) Option.empty else Option(1))
  Tags.Tag463.setValue((i1: Int, i2: Int) => if (i1 <= 1 || i2 == 0) Option.empty else Option(i1 - 1))
  Tags.Tag464.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) Option.empty else Option(1))
  Tags.Tag465.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) Option.empty else Option(i1))
  Tags.Tag466.setValue((i1: Int, i2: Int) => if (i1 <= 1) Option.empty else Option(i1 - 2))
  Tags.Tag467.setValue((i1: Int, i2: Int) => if (i1 <= 1) Option.empty else Option(i1 - 1))
  Tags.Tag468.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(i1) else Option(1))
  Tags.Tag469.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) i1 * 2 else 1)
  Tags.Tag470.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else Option(0))
  Tags.Tag471.setValue((i1: Int, i2: Int) => if (i2 == 0) i1 * 2 else i1)
  Tags.Tag472.setValue((i1: Int, i2: Int) => if (i1 > 0 && i2 == 0) Option.empty else Option(i1))
  Tags.Tag473.setValue((i1: Int, i2: Int) => if (i2 == 0) i1 * 2 else if (i1 == 0) 0 else if (i1 == 1) 2 else 3)
  Tags.Tag474.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else Option(1))
  Tags.Tag475.setValue((i1: Int, i2: Int) => if (i1 <= 1 || i2 == 0) i1 else 2)
  Tags.Tag476.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) i1 * 2 else i1 + 1)
  Tags.Tag477.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else Option(2))
  Tags.Tag478.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else Option(i1 + 1))
  Tags.Tag479.setValue(Tags.Tag022, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag480.setValue(Tags.Tag081, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag481.setValue(Tags.Tag013, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag482.setValue(Tags.Tag019, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag483.setValue(Tags.Tag465, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag484.setValue(Tags.Tag006, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag485.setValue(Tags.Tag044, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag486.setValue(Tags.Tag124, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag487.setValue(Tags.Tag045, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag488.setValue(Tags.Tag222, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag489.setValue(Tags.Tag007, (i1: Int, i2: Int) => (1, 1))
  Tags.Tag490.setValue(Tags.Tag012, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag491.setValue(Tags.Tag245, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag492.setValue(Tags.Tag056, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag493.setValue(Tags.Tag064, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag494.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag495.setValue(Tags.Tag078, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag496.setValue(Tags.Tag037, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag497.setValue(Tags.Tag043, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag498.setValue(Tags.Tag215, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag499.setValue(Tags.Tag187, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag500.setValue(Tags.Tag002, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag501.setValue(Tags.Tag184, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag502.setValue(Tags.Tag165, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag503.setValue(Tags.Tag114, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag504.setValue(Tags.Tag018, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag505.setValue(Tags.Tag017, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag506.setValue(Tags.Tag130, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag507.setValue(Tags.Tag037, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag508.setValue(Tags.Tag205, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag509.setValue(Tags.Tag239, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag510.setValue(Tags.Tag460, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag511.setValue(Tags.Tag113, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag512.setValue(Tags.Tag084, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag513.setValue(Tags.Tag144, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag514.setValue(Tags.Tag021, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag515.setValue(Tags.Tag086, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag516.setValue(Tags.Tag023, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag517.setValue(Tags.Tag018, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag518.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag519.setValue(Tags.Tag131, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag520.setValue(Tags.Tag206, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag521.setValue(Tags.Tag200, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag522.setValue(Tags.Tag145, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag523.setValue(Tags.Tag037, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag524.setValue(Tags.Tag227, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag525.setValue(Tags.Tag002, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag526.setValue(Tags.Tag008, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag527.setValue(Tags.Tag046, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag528.setValue(Tags.Tag222, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag529.setValue(Tags.Tag007, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag530.setValue(Tags.Tag474, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag531.setValue(Tags.Tag032, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag532.setValue(Tags.Tag043, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag533.setValue(Tags.Tag023, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag534.setValue(Tags.Tag006, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag535.setValue(Tags.Tag234, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag536.setValue(Tags.Tag032, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag537.setValue(Tags.Tag204, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag538.setValue(Tags.Tag007, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag539.setValue(Tags.Tag020, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag540.setValue(Tags.Tag221, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag541.setValue(Tags.Tag034, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag542.setValue(Tags.Tag062, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag543.setValue(Tags.Tag065, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag544.setValue(Tags.Tag201, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag545.setValue(Tags.Tag224, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag546.setValue(Tags.Tag189, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag547.setValue(Tags.Tag007, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag548.setValue(Tags.Tag003, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag549.setValue(Tags.Tag185, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag550.setValue(Tags.Tag223, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag551.setValue(Tags.Tag064, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag552.setValue(Tags.Tag108, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag553.setValue(Tags.Tag031, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag554.setValue(Tags.Tag102, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag555.setValue(Tags.Tag003, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag556.setValue(Tags.Tag065, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag557.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else if (i2 == 1) Option(3) else Option((1 / i2) * i2 + i2 + 2))
  Tags.Tag558.setValue((i1: Int, i2: Int) => if (i2 % 2 == 0 || i2 == 0) i2 + i2 / 2 + 1 else i2 + i2 / 2 + 2)
  Tags.Tag559.setValue((i1: Int, i2: Int) => if (i2 % 2 == 0 || i2 == 0) i2 + i2 / 2 + 1 else i2 + i2 / 2 + 2)
  Tags.Tag560.setValue((i1: Int, i2: Int) => if (i2 == 0) 2 else 0)
  Tags.Tag561.setValue(Tags.Tag010, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag562.setValue(Tags.Tag450, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag563.setValue(Tags.Tag560, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag564.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag565.setValue(Tags.Tag231, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag566.setValue(Tags.Tag001, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag567.setValue(Tags.Tag001, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag568.setValue(Tags.Tag318, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag569.setValue(Tags.Tag202, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag570.setValue(Tags.Tag296, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag571.setValue(Tags.Tag182, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag572.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag573.setValue(Tags.Tag169, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag574.setValue(Tags.Tag223, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag575.setValue(Tags.Tag010, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag576.setValue(Tags.Tag363, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag577.setValue(Tags.Tag007, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag578.setValue(Tags.Tag032, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag579.setValue(Tags.Tag213, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag580.setValue(Tags.Tag078, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag581.setValue(Tags.Tag014, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag582.setValue(Tags.Tag222, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag583.setValue(Tags.Tag033, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag584.setValue(Tags.Tag339, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag585.setValue(Tags.Tag007, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag586.setValue(Tags.Tag231, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag587.setValue(Tags.Tag201, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag588.setValue(Tags.Tag032, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag589.setValue(Tags.Tag262, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag590.setValue(Tags.Tag363, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag591.setValue(Tags.Tag222, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag592.setValue(Tags.Tag093, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag593.setValue(Tags.Tag203, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag594.setValue(Tags.Tag038, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag595.setValue(Tags.Tag296, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag596.setValue(Tags.Tag496, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag597.setValue(Tags.Tag262, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag598.setValue(Tags.Tag213, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag599.setValue(Tags.Tag230, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag600.setValue(Tags.Tag232, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag601.setValue(Tags.Tag043, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag602.setValue(Tags.Tag184, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag603.setValue(Tags.Tag182, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag604.setValue(Tags.Tag178, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag605.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag606.setValue(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag607.setValue(Tags.Tag560, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag608.setValue(Tags.Tag362, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag609.setValue(Tags.Tag201, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag610.setValue(Tags.Tag362, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag611.setValue(Tags.Tag014, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag612.setValue(Tags.Tag169, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag613.setValue(Tags.Tag237, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag614.setValue(Tags.Tag034, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag615.setValue(Tags.Tag318, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag616.setValue(Tags.Tag238, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag617.setValue(Tags.Tag184, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag618.setValue(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag619.setValue(Tags.Tag239, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag620.setValue(Tags.Tag450, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag621.setValue(Tags.Tag034, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag622.setValue(Tags.Tag043, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag623.setValue(Tags.Tag037, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag624.setValue(Tags.Tag223, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag625.setValue(Tags.Tag395, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag626.setValue(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag627.setValue(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag628.setValue(Tags.Tag038, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag629.setValue(Tags.Tag078, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag630.setValue(Tags.Tag239, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag631.setValue(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag632.setValue(Tags.Tag033, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag633.setValue(Tags.Tag339, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag634.setValue(Tags.Tag128, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag635.setValue(Tags.Tag395, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag636.setValue(Tags.Tag203, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag637.setValue(Tags.Tag100, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag638.setValue(Tags.Tag496, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag639.setValue(Tags.Tag037, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag640.setValue(Tags.Tag295, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag641.setValue(Tags.Tag608, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag642.setValue(Tags.Tag616, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag643.setValue(Tags.Tag580, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag644.setValue(Tags.Tag584, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag645.setValue(Tags.Tag581, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag646.setValue(Tags.Tag619, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag647.setValue(Tags.Tag584, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag648.setValue(Tags.Tag594, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag649.setValue(Tags.Tag618, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag650.setValue(Tags.Tag581, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag651.setValue(Tags.Tag593, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag652.setValue(Tags.Tag578, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag653.setValue(Tags.Tag608, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag654.setValue(Tags.Tag608, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag655.setValue(Tags.Tag600, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag656.setValue(Tags.Tag565, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag657.setValue(Tags.Tag593, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag658.setValue(Tags.Tag562, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag659.setValue(Tags.Tag561, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag660.setValue(Tags.Tag618, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag661.setValue(Tags.Tag619, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag662.setValue(Tags.Tag587, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag663.setValue(Tags.Tag581, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag664.setValue(Tags.Tag618, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag665.setValue(Tags.Tag602, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag666.setValue(Tags.Tag577, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag667.setValue(Tags.Tag599, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag668.setValue(Tags.Tag576, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag669.setValue(Tags.Tag568, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag670.setValue(Tags.Tag562, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag671.setValue(Tags.Tag580, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag672.setValue(Tags.Tag581, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag673.setValue(Tags.Tag569, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag674.setValue(Tags.Tag587, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag675.setValue(Tags.Tag565, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag676.setValue(Tags.Tag602, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag677.setValue(Tags.Tag634, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag678.setValue(Tags.Tag600, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag679.setValue(Tags.Tag576, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag680.setValue(Tags.Tag618, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag681.setValue(Tags.Tag570, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag682.setValue(Tags.Tag644, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag683.setValue(Tags.Tag646, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag684.setValue(Tags.Tag641, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag685.setValue(Tags.Tag656, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag686.setValue(Tags.Tag668, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag687.setValue(Tags.Tag667, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag688.setValue(Tags.Tag645, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag689.setValue(Tags.Tag668, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag690.setValue(Tags.Tag685, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag691.setValue(Tags.Tag682, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag692.setValue(Tags.Tag005, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag693.setValue(Tags.Tag010, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag694.setValue(Tags.Tag072, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag695.setValue(Tags.Tag009, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag696.setValue(Tags.Tag350, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag697.setValue(Tags.Tag233, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag698.setValue(Tags.Tag443, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag699.setValue(Tags.Tag217, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag700.setValue(Tags.Tag062, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag701.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag702.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag703.setValue(Tags.Tag444, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag704.setValue(Tags.Tag448, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag705.setValue(Tags.Tag444, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag706.setValue(Tags.Tag010, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag707.setValue(Tags.Tag005, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag708.setValue(Tags.Tag004, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag709.setValue(Tags.Tag008, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag710.setValue(Tags.Tag215, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag711.setValue(Tags.Tag004, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag712.setValue(Tags.Tag010, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag713.setValue(Tags.Tag216, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag714.setValue(Tags.Tag218, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag715.setValue(Tags.Tag476, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag716.setValue(Tags.Tag072, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag717.setValue(Tags.Tag217, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag718.setValue(Tags.Tag008, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag719.setValue(Tags.Tag008, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag720.setValue(Tags.Tag004, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag721.setValue(Tags.Tag214, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag722.setValue(Tags.Tag449, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag723.setValue(Tags.Tag471, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag724.setValue(Tags.Tag009, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag725.setValue(Tags.Tag443, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag726.setValue(Tags.Tag448, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag727.setValue(Tags.Tag218, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag728.setValue(Tags.Tag233, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag729.setValue(Tags.Tag215, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag730.setValue(Tags.Tag214, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag731.setValue(Tags.Tag005, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag732.setValue(Tags.Tag350, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag733.setValue(Tags.Tag008, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag734.setValue(Tags.Tag009, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag735.setValue(Tags.Tag062, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag736.setValue(Tags.Tag449, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag737.setValue(Tags.Tag004, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag738.setValue(Tags.Tag005, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag739.setValue(Tags.Tag216, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag740.setValue(Tags.Tag009, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag741.setValue(Tags.Tag701, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag742.setValue(Tags.Tag699, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag743.setValue(Tags.Tag692, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag744.setValue(Tags.Tag701, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag745.setValue(Tags.Tag692, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag746.setValue(Tags.Tag698, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag747.setValue(Tags.Tag722, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag748.setValue(Tags.Tag722, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag749.setValue(Tags.Tag708, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag750.setValue(Tags.Tag701, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag751.setValue(Tags.Tag697, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag752.setValue(Tags.Tag709, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag753.setValue(Tags.Tag722, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag754.setValue(Tags.Tag708, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag755.setValue(Tags.Tag692, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag756.setValue(Tags.Tag692, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag757.setValue(Tags.Tag697, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag758.setValue(Tags.Tag704, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag759.setValue(Tags.Tag694, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag760.setValue(Tags.Tag695, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag761.setValue(Tags.Tag697, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag762.setValue(Tags.Tag692, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag763.setValue(Tags.Tag723, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag764.setValue(Tags.Tag703, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag765.setValue(Tags.Tag709, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag766.setValue(Tags.Tag748, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag767.setValue(Tags.Tag757, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag768.setValue(Tags.Tag748, (i1: Int, i2: Int) => (i1 + 1, i2 + 1))
  Tags.Tag769.setValue(Tags.Tag748, (i1: Int, i2: Int) => (i1 + 1, i2 + 1))
  Tags.Tag770.setValue(Tags.Tag722, (i1: Int, i2: Int) => (i1 + 1, i2 + 1))
  Tags.Tag771.setValue(Tags.Tag741, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag772.setValue(Tags.Tag722, (i1: Int, i2: Int) => (i1 + 1, i2 + 1))
  Tags.Tag773.setValue(Tags.Tag768, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag774.setValue(Tags.Tag770, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag775.mapResult(Tags.Tag060, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag776.mapResult(Tags.Tag696, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag777.mapResult(Tags.Tag696, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag778.mapResult(Tags.Tag703, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag779.mapResult(Tags.Tag293, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag780.mapResult(Tags.Tag044, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag781.mapResult(Tags.Tag723, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag782.mapResult(Tags.Tag008, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag783.mapResult(Tags.Tag698, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag784.mapResult(Tags.Tag703, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag785.mapResult(Tags.Tag698, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag786.mapResult(Tags.Tag008, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag787.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag788.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag789.mapResult(Tags.Tag008, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag790.mapResult(Tags.Tag715, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag791.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag792.mapResult(Tags.Tag101, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag793.mapResult(Tags.Tag147, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag794.mapResult(Tags.Tag103, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag795.mapResult(Tags.Tag008, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag796.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => 1))
  Tags.Tag797.mapResult(Tags.Tag229, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag798.mapResult(Tags.Tag086, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag799.mapResult(Tags.Tag047, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag800.mapResult(Tags.Tag246, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag801.mapResult(Tags.Tag131, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag802.mapResult(Tags.Tag794, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag803.mapResult(Tags.Tag217, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag804.mapResult(Tags.Tag066, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag805.mapResult(Tags.Tag445, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag806.mapResult(Tags.Tag205, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag807.mapResult(Tags.Tag207, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag808.mapResult(Tags.Tag045, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag809.mapResult(Tags.Tag084, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag810.mapResult(Tags.Tag205, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag811.mapResult(Tags.Tag105, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag812.mapResult(Tags.Tag208, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag813.mapResult(Tags.Tag102, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag814.mapResult(Tags.Tag530, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag815.mapResult(Tags.Tag231, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag816.mapResult(Tags.Tag554, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag817.mapResult(Tags.Tag210, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag818.mapResult(Tags.Tag465, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag819.mapResult(Tags.Tag072, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag820.mapResult(Tags.Tag081, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag821.mapResult(Tags.Tag043, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag822.mapResult(Tags.Tag511, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag823.mapResult(Tags.Tag217, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag824.mapResult(Tags.Tag113, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag825.mapResult(Tags.Tag078, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag826.mapResult(Tags.Tag062, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag827.mapResult(Tags.Tag521, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag828.mapResult(Tags.Tag684, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag829.mapResult(Tags.Tag216, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag830.mapResult(Tags.Tag214, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag831.mapResult(Tags.Tag230, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag832.mapResult(Tags.Tag215, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag833.mapResult(Tags.Tag471, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag834.mapResult(Tags.Tag155, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag835.mapResult(Tags.Tag472, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag836.mapResult(Tags.Tag081, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag837.mapResult(Tags.Tag223, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag838.mapResult(Tags.Tag301, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag839.mapResult(Tags.Tag444, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag840.mapResult(Tags.Tag209, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag841.mapResult(Tags.Tag208, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag842.mapResult(Tags.Tag078, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag843.mapResult(Tags.Tag200, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag844.mapResult(Tags.Tag002, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag845.mapResult(Tags.Tag207, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag846.mapResult(Tags.Tag212, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag847.mapResult(Tags.Tag452, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag848.mapResult(Tags.Tag002, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag849.mapResult(Tags.Tag236, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag850.mapResult(Tags.Tag474, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag851.mapResult(Tags.Tag145, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag852.mapResult(Tags.Tag226, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag853.mapResult(Tags.Tag006, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag854.mapResult(Tags.Tag446, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag855.mapResult(Tags.Tag209, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag856.mapResult(Tags.Tag463, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag857.mapResult(Tags.Tag224, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag858.mapResult(Tags.Tag086, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag859.mapResult(Tags.Tag758, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag860.mapResult(Tags.Tag476, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag861.mapResult(Tags.Tag044, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag862.mapResult(Tags.Tag214, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag863.mapResult(Tags.Tag448, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag864.mapResult(Tags.Tag149, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag865.mapResult(Tags.Tag175, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag866.mapResult(Tags.Tag206, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag867.mapResult(Tags.Tag454, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag868.mapResult(Tags.Tag211, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag869.mapResult(Tags.Tag153, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag870.mapResult(Tags.Tag215, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag871.mapResult(Tags.Tag246, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag872.mapResult(Tags.Tag443, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag873.mapResult(Tags.Tag210, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag874.mapResult(Tags.Tag317, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag875.mapResult(Tags.Tag171, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag876.mapResult(Tags.Tag459, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag877.mapResult(Tags.Tag216, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag878.mapResult(Tags.Tag363, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag879.mapResult(Tags.Tag066, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag880.mapResult(Tags.Tag047, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag881.mapResult(Tags.Tag211, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag882.mapResult(Tags.Tag105, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag883.mapResult(Tags.Tag007, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag884.mapResult(Tags.Tag213, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag885.mapResult(Tags.Tag222, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag886.mapResult(Tags.Tag743, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag887.mapResult(Tags.Tag084, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag888.mapResult(Tags.Tag545, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag889.mapResult(Tags.Tag013, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag890.mapResult(Tags.Tag060, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag891.mapResult(Tags.Tag145, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag892.mapResult(Tags.Tag241, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag893.mapResult(Tags.Tag704, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag894.mapResult(Tags.Tag773, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag895.mapResult(Tags.Tag477, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag896.mapResult(Tags.Tag363, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag897.mapResult(Tags.Tag222, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag898.mapResult(Tags.Tag072, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag899.mapResult(Tags.Tag212, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag900.mapResult(Tags.Tag084, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag901.mapResult(Tags.Tag464, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag902.mapResult(Tags.Tag244, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag903.mapResult(Tags.Tag155, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag904.mapResult(Tags.Tag062, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag905.mapResult(Tags.Tag378, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag906.mapResult(Tags.Tag045, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag907.mapResult(Tags.Tag007, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag908.mapResult(Tags.Tag206, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag909.mapResult(Tags.Tag350, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag910.mapResult(Tags.Tag204, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag911.mapResult(Tags.Tag469, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag912.mapResult(Tags.Tag293, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag913.mapResult(Tags.Tag223, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag914.mapResult(Tags.Tag226, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag915.mapResult(Tags.Tag071, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag916.mapResult(Tags.Tag204, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag917.mapResult(Tags.Tag149, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag918.mapResult(Tags.Tag043, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag919.mapResult(Tags.Tag041, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag920.mapResult(Tags.Tag704, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag921.mapResult(Tags.Tag153, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag922.mapResult(Tags.Tag219, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag923.mapResult(Tags.Tag301, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag924.mapResult(Tags.Tag080, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag925.mapResult(Tags.Tag478, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag926.mapResult(Tags.Tag350, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag927.mapResult(Tags.Tag448, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag928.mapResult(Tags.Tag339, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag929.mapResult(Tags.Tag041, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag930.mapResult(Tags.Tag125, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag931.mapResult(Tags.Tag131, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag932.mapResult(Tags.Tag751, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag933.mapResult(Tags.Tag244, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag934.mapResult(Tags.Tag125, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag935.mapResult(Tags.Tag013, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag936.mapResult(Tags.Tag219, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag937.mapResult(Tags.Tag483, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag938.mapResult(Tags.Tag363, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag939.mapResult(Tags.Tag838, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag940.mapResult(Tags.Tag015, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag941.mapResult(Tags.Tag838, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag942.mapResult(Tags.Tag095, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag943.mapResult(Tags.Tag477, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag944.mapResult(Tags.Tag362, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag945.mapResult(Tags.Tag033, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag946.mapResult(Tags.Tag363, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag947.mapResult(Tags.Tag015, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag948.mapResult(Tags.Tag101, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag949.mapResult(Tags.Tag015, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag950.mapResult(Tags.Tag033, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag951.mapResult(Tags.Tag901, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag952.mapResult(Tags.Tag060, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag953.mapResult(Tags.Tag472, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag954.mapResult(Tags.Tag565, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag955.mapResult(Tags.Tag838, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag956.mapResult(Tags.Tag101, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag957.mapResult(Tags.Tag563, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag958.mapResult(Tags.Tag545, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag959.mapResult(Tags.Tag856, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag960.mapResult(Tags.Tag619, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag961.mapResult(Tags.Tag565, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag962.mapResult(Tags.Tag847, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag963.mapResult(Tags.Tag867, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag964.mapResult(Tags.Tag827, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag965.mapResult(Tags.Tag619, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag966.mapResult(Tags.Tag286, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag967.mapResult(Tags.Tag200, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag968.mapResult(Tags.Tag901, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag969.mapResult(Tags.Tag468, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag970.mapResult(Tags.Tag015, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag971.mapResult(Tags.Tag111, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag972.mapResult(Tags.Tag818, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag973.mapResult(Tags.Tag937, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag974.mapResult(Tags.Tag262, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag975.mapResult(Tags.Tag827, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag976.mapResult(Tags.Tag838, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag977.mapResult(Tags.Tag966, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag978.mapResult(Tags.Tag954, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag979.mapResult(Tags.Tag944, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag980.mapResult(Tags.Tag969, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag981.mapResult(Tags.Tag954, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag982.mapResult(Tags.Tag964, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag983.mapResult(Tags.Tag948, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag984.mapResult(Tags.Tag969, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag985.mapResult(Tags.Tag967, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag986.mapResult(Tags.Tag959, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag987.mapResult(Tags.Tag971, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag988.setValue(Tags.Tag860, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag989.setValue(Tags.Tag978, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag990.setValue(Tags.Tag980, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag991.setValue(Tags.Tag980, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag992.setValue(Tags.Tag978, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag993.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i2 == 0) Option(2) else Option(3))
  Tags.Tag994.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) Option.empty else if (i1 == 1) Option(6) else Option(5))
  Tags.Tag995.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i2 == 0) Option(3) else Option(2))
  Tags.Tag996.setValue((i1: Int, i2: Int) => if (i1 == 0) Option.empty else if (i2 == 0) Option(1) else Option(2))
  Tags.Tag997.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else Option(3))
  Tags.Tag998.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 1 else 2)
  Tags.Tag999.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 2 else 1)
  Tags.Tag1000.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else if (i1 == 0) Option(4) else Option(3))
  Tags.Tag1001.setValue((i1: Int, i2: Int) => if (i2 == 0) 2 else if (i1 == 0) i2 * 2 + 2 else i2 + 1)
  Tags.Tag1002.setValue((i1: Int, i2: Int) => if (i2 == 0) 2 else if (i1 == 0) i2 + 2 else i2)
  Tags.Tag1003.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i1 == 0) Option(4) else Option(2))
  Tags.Tag1004.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i2 == 0) Option(1) else Option(2))
  Tags.Tag1005.setValue((i1: Int, i2: Int) => if (i2 == 0) 2 else if (i1 == 0) i2 + 2 else i2 + 1)
  Tags.Tag1006.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else Option(3))
  Tags.Tag1007.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i1 == 0) Option(4) else Option(3))
  Tags.Tag1008.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i2 == 0) Option(3) else Option(1))
  Tags.Tag1009.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i2 == 0) Option(3) else Option(2))
  Tags.Tag1010.setValue((i1: Int, i2: Int) => if (i2 == 0) Option.empty else if (i1 == 0) Option(2) else Option(3))
  Tags.Tag1011.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 == 0) Option(1) else Option(2)
  )
  Tags.Tag1012.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 1 else i2 - 1)
  Tags.Tag1013.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else if (i2 == 0) 0 else i2 + 1)
  Tags.Tag1014.setValue((i1: Int, i2: Int) => if (i2 == 0) 1 else if (i1 == 0) 1 else i2)
  Tags.Tag1015.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i1 == 0) Option(1) else Option(2))
  Tags.Tag1016.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 0 else 1)
  Tags.Tag1017.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else if (i2 == 0) 0 else 2)
  Tags.Tag1018.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else if (i2 == 0) 1 else 2)
  Tags.Tag1019.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option(0) else if (i1 == 0) Option.empty else Option(i2 + 1))
  Tags.Tag1020.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0) else if (i1 == 0) Option.empty else if (i2 == 0) Option(1) else Option(2)
  )
  Tags.Tag1021.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else 2)
  Tags.Tag1022.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 == 1) 1 else if (i2 == 0) i1 else 3)
  Tags.Tag1023.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) i1 else 2)
  Tags.Tag1024.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 + 2 else i1)
  Tags.Tag1025.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 0 else if (i1 == 0 || i2 == 0) 1 else 2)
  Tags.Tag1026.setValue((i1: Int, i2: Int) =>
    if (i2 == 0) Option.empty else if (i1 == 0) Option(2) else if (i1 == 1) Option(3) else Option(4)
  )
  Tags.Tag1027.setValue((i1: Int, i2: Int) => if (i2 == 0) i1 else if (i1 == 0) 1 else if (i1 == 1) 2 else 3)
  Tags.Tag1028.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 else if (i2 == 0) 2 else 1)
  Tags.Tag1029.setValue((i1: Int, i2: Int) => if (i2 == 0) i1 else if (i1 == 0) 1 else 2)
  Tags.Tag1030.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i1 == 1) 2 else 4)
  Tags.Tag1031.setValue((i1: Int, i2: Int) => if (i1 <= 1) 1 else 3)
  Tags.Tag1032.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else i2 + 2)
  Tags.Tag1033.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else 2)
  Tags.Tag1034.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 2 else i1 + i2)
  Tags.Tag1035.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 + 2 else i2)
  Tags.Tag1036.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2) else if (i2 == 0) Option(2) else Option.empty)
  Tags.Tag1037.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 else if (i2 == 0) 2 else 1)
  Tags.Tag1038.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2) else if (i2 == 0) Option.empty else Option(2))
  Tags.Tag1039.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i1 + i2 + 1) else Option.empty)
  Tags.Tag1040.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2 + 1) else if (i2 == 0) Option(2) else Option.empty)
  Tags.Tag1041.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2 + 1) else if (i2 == 0) Option.empty else Option(1))
  Tags.Tag1042.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2 + 1) else if (i2 == 0) Option(2) else Option(1))
  Tags.Tag1043.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2 + 1) else if (i2 == 0) Option.empty else Option(2))
  Tags.Tag1044.setValue((i1: Int, i2: Int) => if (i2 == 0) 1 else i1 * 2 + 1)
  Tags.Tag1045.setValue((i1: Int, i2: Int) => if (i2 == 0) 1 else if (i1 == 0) 1 else 2)
  Tags.Tag1046.setValue((i1: Int, i2: Int) =>
    if (i2 == 0) Option(i1 * 2 + 1) else if (i1 == 0) Option(1) else if (i1 == 1) Option(3) else Option.empty
  )
  Tags.Tag1047.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2 + 1)
  Tags.Tag1048.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) 2 else 1)
  Tags.Tag1049.setValue((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2 + 1)
  Tags.Tag1050.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) 0 else if (i1 == 0) 2 else 1)
  Tags.Tag1051.mapResult(Tags.Tag1026, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1052.mapResult(Tags.Tag1005, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1053.mapResult(Tags.Tag994, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1054.mapResult(Tags.Tag1039, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1055.mapResult(Tags.Tag995, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1056.mapResult(Tags.Tag1040, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1057.mapResult(Tags.Tag1006, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1058.mapResult(Tags.Tag1026, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1059.mapResult(Tags.Tag1046, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1060.mapResult(Tags.Tag1026, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1061.mapResult(Tags.Tag996, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1062.mapResult(Tags.Tag1046, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1063.mapResult(Tags.Tag1011, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1064.mapResult(Tags.Tag998, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1065.mapResult(Tags.Tag1003, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1066.mapResult(Tags.Tag999, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1067.mapResult(Tags.Tag1008, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1068.mapResult(Tags.Tag1041, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1069.mapResult(Tags.Tag999, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1070.mapResult(Tags.Tag1039, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1071.mapResult(Tags.Tag1046, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1072.mapResult(Tags.Tag1043, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1073.mapResult(Tags.Tag1000, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1074.mapResult(Tags.Tag1003, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1075.mapResult(Tags.Tag994, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1076.mapResult(Tags.Tag994, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1077.mapResult(Tags.Tag1042, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1078.mapResult(Tags.Tag1015, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1079.mapResult(Tags.Tag1010, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1080.mapResult(Tags.Tag1030, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1081.mapResult(Tags.Tag1008, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1082.mapResult(Tags.Tag1014, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1083.mapResult(Tags.Tag1023, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1084.mapResult(Tags.Tag1009, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1085.mapResult(Tags.Tag1000, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1086.mapResult(Tags.Tag1006, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1087.mapResult(Tags.Tag994, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1088.mapResult(Tags.Tag1046, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1089.mapResult(Tags.Tag1016, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1090.mapResult(Tags.Tag1007, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1091.setValue(Tags.Tag1022, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1092.setValue(Tags.Tag1047, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1093.setValue(Tags.Tag1024, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1094.setValue(Tags.Tag1024, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1095.setValue(Tags.Tag1013, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1096.setValue(Tags.Tag022, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1097.setValue(Tags.Tag1048, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1098.setValue(Tags.Tag1014, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1099.setValue(Tags.Tag474, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1100.setValue(Tags.Tag1012, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1101.setValue(Tags.Tag1012, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1102.setValue(Tags.Tag1047, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1103.setValue(Tags.Tag1035, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1104.setValue(Tags.Tag1028, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1105.setValue(Tags.Tag363, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1106.setValue(Tags.Tag1039, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1107.setValue(Tags.Tag1019, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1108.setValue(Tags.Tag1028, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1109.setValue(Tags.Tag1026, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1110.setValue(Tags.Tag1024, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1111.setValue(Tags.Tag530, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1112.setValue(Tags.Tag1034, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1113.setValue(Tags.Tag034, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1114.setValue(Tags.Tag110, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1115.setValue(Tags.Tag046, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1116.setValue(Tags.Tag1046, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1117.setValue(Tags.Tag1044, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1118.setValue(Tags.Tag1012, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1119.mapResult(Tags.Tag1095, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1120.mapResult(Tags.Tag1053, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1121.mapResult(Tags.Tag1098, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1122.mapResult(Tags.Tag1062, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1123.mapResult(Tags.Tag1097, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1124.mapResult(Tags.Tag1053, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1125.mapResult(Tags.Tag1065, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1126.mapResult(Tags.Tag1053, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1127.mapResult(Tags.Tag1053, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1128.mapResult(Tags.Tag1073, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1129.mapResult(Tags.Tag1062, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1130.mapResult(Tags.Tag1083, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1131.mapResult(Tags.Tag1120, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1132.mapResult(Tags.Tag1120, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1133.mapResult(Tags.Tag1119, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1134.mapResult(Tags.Tag1125, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1135.setValue(Tags.Tag1054, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1136.setValue(Tags.Tag1052, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1137.setValue(Tags.Tag1054, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1138.mapResult(Tags.Tag1136, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1139.mapResult(Tags.Tag1135, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1140.mapResult(Tags.Tag1135, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1141.mapResult(Tags.Tag128, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1142.mapResult(Tags.Tag064, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1143.mapResult(Tags.Tag184, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1144.mapResult(Tags.Tag144, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1145.mapResult(Tags.Tag015, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1146.mapResult(Tags.Tag938, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1147.mapResult(Tags.Tag969, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1148.mapResult(Tags.Tag295, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1149.mapResult(Tags.Tag160, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1150.mapResult(Tags.Tag185, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1151.mapResult(Tags.Tag027, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1152.mapResult(Tags.Tag581, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1153.mapResult(Tags.Tag015, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1154.mapResult(Tags.Tag1004, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1155.mapResult(Tags.Tag561, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1156.mapResult(Tags.Tag776, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1157.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1158.mapResult(Tags.Tag363, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1159.mapResult(Tags.Tag581, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1160.mapResult(Tags.Tag020, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1161.mapResult(Tags.Tag1024, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1162.mapResult(Tags.Tag011, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1163.mapResult(Tags.Tag317, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1164.mapResult(Tags.Tag170, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1165.mapResult(Tags.Tag050, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1166.mapResult(Tags.Tag295, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1167.mapResult(Tags.Tag073, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1168.mapResult(Tags.Tag1062, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1169.mapResult(Tags.Tag1010, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1170.mapResult(Tags.Tag003, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1171.mapResult(Tags.Tag1015, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1172.mapResult(Tags.Tag181, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1173.mapResult(Tags.Tag080, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1174.mapResult(Tags.Tag1022, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1175.mapResult(Tags.Tag043, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1176.mapResult(Tags.Tag245, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1177.mapResult(Tags.Tag239, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1178.mapResult(Tags.Tag220, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1179.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1180.mapResult(Tags.Tag161, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1181.mapResult(Tags.Tag1023, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1182.mapResult(Tags.Tag1024, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1183.mapResult(Tags.Tag187, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1184.mapResult(Tags.Tag160, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1185.mapResult(Tags.Tag988, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1186.mapResult(Tags.Tag073, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1187.mapResult(Tags.Tag443, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1188.mapResult(Tags.Tag185, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1189.mapResult(Tags.Tag797, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1190.mapResult(Tags.Tag171, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1191.mapResult(Tags.Tag007, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1192.mapResult(Tags.Tag104, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1193.mapResult(Tags.Tag050, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1194.mapResult(Tags.Tag144, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1195.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1196.mapResult(Tags.Tag697, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1197.mapResult(Tags.Tag075, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1198.mapResult(Tags.Tag006, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1199.mapResult(Tags.Tag142, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1200.mapResult(Tags.Tag001, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1201.mapResult(Tags.Tag036, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1202.mapResult(Tags.Tag940, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1203.mapResult(Tags.Tag757, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1204.mapResult(Tags.Tag987, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1205.mapResult(Tags.Tag041, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1206.mapResult(Tags.Tag213, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1207.mapResult(Tags.Tag852, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1208.mapResult(Tags.Tag402, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1209.mapResult(Tags.Tag184, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1210.mapResult(Tags.Tag209, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1211.mapResult(Tags.Tag1026, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1212.mapResult(Tags.Tag872, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1213.mapResult(Tags.Tag062, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1214.mapResult(Tags.Tag171, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1215.mapResult(Tags.Tag262, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1216.mapResult(Tags.Tag124, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1217.mapResult(Tags.Tag008, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1218.mapResult(Tags.Tag009, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1219.mapResult(Tags.Tag004, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1220.mapResult(Tags.Tag065, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1221.mapResult(Tags.Tag064, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1222.mapResult(Tags.Tag239, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1223.mapResult(Tags.Tag350, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1224.mapResult(Tags.Tag159, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1225.mapResult(Tags.Tag104, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1226.mapResult(Tags.Tag581, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1227.mapResult(Tags.Tag771, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1228.mapResult(Tags.Tag637, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1229.mapResult(Tags.Tag216, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1230.mapResult(Tags.Tag226, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1231.mapResult(Tags.Tag220, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1232.mapResult(Tags.Tag1058, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1233.mapResult(Tags.Tag020, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1234.mapResult(Tags.Tag246, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1235.mapResult(Tags.Tag003, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1236.mapResult(Tags.Tag767, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1237.mapResult(Tags.Tag066, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1238.mapResult(Tags.Tag036, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1239.mapResult(Tags.Tag063, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1240.mapResult(Tags.Tag187, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1241.mapResult(Tags.Tag181, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1242.mapResult(Tags.Tag790, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1243.mapResult(Tags.Tag001, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1244.mapResult(Tags.Tag1059, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1245.mapResult(Tags.Tag207, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1246.mapResult(Tags.Tag160, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1247.mapResult(Tags.Tag039, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1248.mapResult(Tags.Tag124, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1249.mapResult(Tags.Tag020, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1250.mapResult(Tags.Tag219, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1251.mapResult(Tags.Tag231, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1252.mapResult(Tags.Tag235, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1253.mapResult(Tags.Tag776, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1254.mapResult(Tags.Tag521, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1255.mapResult(Tags.Tag009, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1256.mapResult(Tags.Tag634, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1257.mapResult(Tags.Tag043, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1258.mapResult(Tags.Tag019, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1259.mapResult(Tags.Tag160, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1260.mapResult(Tags.Tag792, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1261.mapResult(Tags.Tag217, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1262.mapResult(Tags.Tag162, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1263.mapResult(Tags.Tag011, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1264.mapResult(Tags.Tag414, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1265.mapResult(Tags.Tag008, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1266.mapResult(Tags.Tag020, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1267.mapResult(Tags.Tag019, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1268.mapResult(Tags.Tag050, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1269.mapResult(Tags.Tag071, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1270.mapResult(Tags.Tag940, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1271.mapResult(Tags.Tag069, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1272.mapResult(Tags.Tag150, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1273.mapResult(Tags.Tag233, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1274.mapResult(Tags.Tag659, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1275.mapResult(Tags.Tag159, (i1: Option[Int]) => i1.map(b => b * 2))
  Tags.Tag1276.mapResult(Tags.Tag183, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1277.mapResult(Tags.Tag061, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1278.mapResult(Tags.Tag444, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1279.mapResult(Tags.Tag800, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1280.mapResult(Tags.Tag063, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1281.setValue(Tags.Tag443, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1282.setValue(Tags.Tag041, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1283.setValue(Tags.Tag215, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1284.setValue(Tags.Tag820, (i1: Int, i2: Int) => (i2, i1 + 1))
  Tags.Tag1285.setValue(Tags.Tag081, (i1: Int, i2: Int) => (i2, i1 + 1))
  Tags.Tag1286.setValue(Tags.Tag646, (i1: Int, i2: Int) => (i2, 1))
  Tags.Tag1287.setValue(Tags.Tag561, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1288.setValue(Tags.Tag010, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1289.setValue(Tags.Tag825, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1290.setValue(Tags.Tag244, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1291.setValue(Tags.Tag804, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1292.setValue(Tags.Tag741, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1293.setValue(Tags.Tag910, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1294.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1295.setValue(Tags.Tag861, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1296.setValue(Tags.Tag583, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1297.setValue(Tags.Tag830, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1298.setValue(Tags.Tag043, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1299.setValue(Tags.Tag066, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1300.setValue(Tags.Tag212, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1301.setValue(Tags.Tag215, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1302.setValue(Tags.Tag044, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1303.setValue(Tags.Tag078, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1304.setValue(Tags.Tag830, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1305.setValue(Tags.Tag076, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1306.setValue(Tags.Tag205, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1307.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1308.setValue(Tags.Tag076, (i1: Int, i2: Int) => (i2, 1))
  Tags.Tag1309.setValue(Tags.Tag076, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1310.setValue(Tags.Tag806, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1311.setValue(Tags.Tag006, (i1: Int, i2: Int) => (i2, 1))
  Tags.Tag1312.setValue(Tags.Tag081, (i1: Int, i2: Int) => (i2, i1 + 1))
  Tags.Tag1313.setValue(Tags.Tag783, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1314.setValue(Tags.Tag041, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1315.setValue(Tags.Tag806, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1316.setValue(Tags.Tag846, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1317.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i2 + 1, i1 + 1))
  Tags.Tag1318.setValue(Tags.Tag078, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1319.setValue(Tags.Tag014, (i1: Int, i2: Int) => (i1 + 1, i2))
  Tags.Tag1320.setValue(Tags.Tag846, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1321.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i2, 1))
  Tags.Tag1322.setValue(Tags.Tag832, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1323.setValue(Tags.Tag741, (i1: Int, i2: Int) => (i2, 1))
  Tags.Tag1324.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1325.setValue(Tags.Tag080, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1326.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1327.setValue(Tags.Tag212, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1328.setValue(Tags.Tag832, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1329.setValue(Tags.Tag583, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1330.setValue(Tags.Tag825, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1331.setValue(Tags.Tag902, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1332.setValue(Tags.Tag043, (i1: Int, i2: Int) => (i1, i1 + i2))
  Tags.Tag1333.setValue(Tags.Tag924, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1334.setValue(Tags.Tag577, (i1: Int, i2: Int) => (i2, 1))
  Tags.Tag1335.setValue(Tags.Tag214, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1336.setValue(Tags.Tag853, (i1: Int, i2: Int) => (i2, 1))
  Tags.Tag1337.setValue(Tags.Tag086, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1338.setValue(Tags.Tag041, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1339.setValue(Tags.Tag204, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1340.setValue(Tags.Tag214, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1341.setValue(Tags.Tag741, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1342.setValue(Tags.Tag910, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1343.setValue(Tags.Tag204, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1344.setValue(Tags.Tag820, (i1: Int, i2: Int) => (i2, i1 + 1))
  Tags.Tag1345.setValue(Tags.Tag997, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1346.setValue(Tags.Tag798, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1347.setValue(Tags.Tag012, (i1: Int, i2: Int) => (i2 + 1, i1 + 1))
  Tags.Tag1348.setValue(Tags.Tag205, (i1: Int, i2: Int) => (i2, i1))
  Tags.Tag1349.mapResult(Tags.Tag1282, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1350.mapResult(Tags.Tag1172, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1351.mapResult(Tags.Tag1143, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1352.mapResult(Tags.Tag1143, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1353.mapResult(Tags.Tag1149, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1354.mapResult(Tags.Tag1232, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1355.mapResult(Tags.Tag1214, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1356.mapResult(Tags.Tag1224, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1357.mapResult(Tags.Tag1345, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1358.mapResult(Tags.Tag1224, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1359.mapResult(Tags.Tag1172, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1360.mapResult(Tags.Tag1274, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1361.mapResult(Tags.Tag1204, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1362.mapResult(Tags.Tag1323, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1363.mapResult(Tags.Tag1308, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1364.mapResult(Tags.Tag1180, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1365.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 2 else 3)
  Tags.Tag1366.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 1 else 0)
  Tags.Tag1367.setValue(Tags.Tag1161, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1368.setValue(Tags.Tag1295, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1369.setValue(Tags.Tag1307, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1370.setValue(Tags.Tag1223, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1371.setValue(Tags.Tag1307, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1372.setValue(Tags.Tag1161, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1373.setValue(Tags.Tag1299, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1374.setValue(Tags.Tag1302, (i1: Int, i2: Int) => (i1 + i2, i2))
  Tags.Tag1375.setValue(Tags.Tag1292, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1376.setValue(Tags.Tag1292, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1377.setValue(Tags.Tag1161, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1378.setValue(Tags.Tag1291, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1379.setValue(Tags.Tag1161, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1380.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 3 else 2)
  Tags.Tag1381.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(3) else Option.empty)
  Tags.Tag1382.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 + 1 else if (i2 == 0) 1 else 0)
  Tags.Tag1383.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 + 2 else if (i2 == 0) 2 else 1)
  Tags.Tag1384.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 2 * i2 + 2 else 1)
  Tags.Tag1385.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i2 == 0) Option(3) else Option(0))
  Tags.Tag1386.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 2 * i2 else 3)
  Tags.Tag1387.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 2 * i2 else i2 + 2)
  Tags.Tag1388.setValue((i1: Int, i2: Int) => if (i1 == 0) 2 * i2 else 3)
  Tags.Tag1389.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 2 * i1 - 1 else 1)
  Tags.Tag1390.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(2 * i1 - 1) else Option.empty)
  Tags.Tag1391.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 2 * i1 - 1 else 2)
  Tags.Tag1392.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) 1 else 2 * i1 - 1)
  Tags.Tag1393.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(1) else if (i2 == 0) Option(2 * i1) else Option.empty)
  Tags.Tag1394.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) 2 * i1 else 2)
  Tags.Tag1395.setValue((i1: Int, i2: Int) => if (i1 == 0 || i2 == 0) 1 else 2 * i1)
  Tags.Tag1396.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) 2 * i1 else 1)
  Tags.Tag1397.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i2 == 0) i1 - 1 else 1)
  Tags.Tag1398.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(i1 - 1) else Option.empty)
  Tags.Tag1399.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 2 else if (i1 == 0 || i2 == 0) 1 else 0)
  Tags.Tag1400.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) 0 else i1 + 1)
  Tags.Tag1401.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(1) else if (i2 == 0) Option(0) else Option.empty)
  Tags.Tag1402.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) 0 else i1)
  Tags.Tag1403.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2 + 1) else if (i2 == 0) Option(0) else Option.empty)
  Tags.Tag1404.setValue((i1: Int, i2: Int) => if (i1 == 0) i2 + 1 else if (i2 == 0) 0 else 1)
  Tags.Tag1405.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(i2 + 1) else if (i2 == 0) Option.empty else Option(0))
  Tags.Tag1406.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(1) else if (i2 == 0) Option(i1) else Option.empty)
  Tags.Tag1407.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) i1 else 0)
  Tags.Tag1408.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) i1 + 1 else 0)
  Tags.Tag1409.setValue((i1: Int, i2: Int) => if (i1 == 0) Option(1) else if (i2 == 0) Option.empty else Option(0))
  Tags.Tag1410.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i1 == 0) Option(0) else Option(i2))
  Tags.Tag1411.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i1 == 0) Option(1) else Option(i2))
  Tags.Tag1412.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i1 == 0) Option(2) else Option(i2))
  Tags.Tag1413.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option.empty else if (i1 == 0) Option(3) else Option(0))
}

trait NaturalProjectionAbs1 extends NaturalProjectionAbs {
  Tags.Tag1414.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) Option(1) else if (i1 == 0) Option.empty else Option(0))
  Tags.Tag1415.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1) else if (i1 == 0) Option.empty else if (i2 == 0) Option(0) else Option(i2)
  )
  Tags.Tag1416.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 2 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1417.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1418.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 2 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1419.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(2) else if (i1 == 0) Option.empty else if (i2 == 0) Option(0 * i1 + 0) else Option(0 * i1 + 1 * i2 + 0)
  )
  Tags.Tag1420.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(2) else if (i1 == 0) Option.empty else if (i2 == 0) Option(0 * i1 + 0) else Option(0 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1421.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1422.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2 else if (i1 == 0) 0 * i2 + 2 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1423.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2 else if (i1 == 0) 1 * i2 + 2 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1424.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2 else if (i1 == 0) 2 * i2 + 2 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1425.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(0 * i2 + 0)
    else if (i2 == 0) Option(0 * i1 + 1)
    else Option(0 * i1 + 1 * i2 + 1)
  )
  Tags.Tag1426.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(0 * i2 + 1)
    else if (i2 == 0) Option(0 * i1 + 1)
    else Option(0 * i1 + 1 * i2 + 1)
  )
  Tags.Tag1427.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(0 * i2 + 2)
    else if (i2 == 0) Option(0 * i1 + 1)
    else Option(0 * i1 + 1 * i2 + 1)
  )
  Tags.Tag1428.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1429.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 1 else 0 * i1 + 1 * i2 + 1
  )
  Tags.Tag1430.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 2 * i1 + 0 * i2 + -1
  )
  Tags.Tag1431.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 2 * i1 + -1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1432.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 0 * i1 + 1 * i2 + 1
  )
  Tags.Tag1433.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 2 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 0 * i1 + 1 * i2 + 1
  )
  Tags.Tag1434.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 1 else 0 * i1 + 1 * i2 + 1
  )
  Tags.Tag1435.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(2) else if (i1 == 0) Option.empty else if (i2 == 0) Option(0 * i1 + 1) else Option(0 * i1 + 1 * i2 + 1)
  )
  Tags.Tag1436.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(2) else if (i1 == 0) Option.empty else if (i2 == 0) Option(0 * i1 + 1) else Option(0 * i1 + 0 * i2 + 1)
  )
  Tags.Tag1437.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 2 * i1 + 0 * i2 + 1
  )
  Tags.Tag1438.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 2 * i1 + 0 * i2 + 0
  )
  Tags.Tag1439.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 2 * i1 + 1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1440.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 2 * i1 + 0 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1441.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0) else if (i1 == 0) Option(0 * i2 + 1) else if (i2 == 0) Option.empty else Option(2 * i1 + 0 * i2 + 1)
  )
  Tags.Tag1442.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) Option.empty
    else if (i1 == 1) Option(0 * i2 + 3)
    else if (i2 == 0) Option.empty
    else Option(1 * i1 + 0 * i2 + 1)
  )
  Tags.Tag1443.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) Option.empty
    else if (i1 == 1) Option(0 * i2 + 2)
    else if (i2 == 0) Option.empty
    else Option(1 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1444.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1 else if (i1 == 1) 0 * i2 + 1 else if (i2 == 0) 2 * i1 + 0 else 2 * i1 + 0 * i2 + 0
  )
  Tags.Tag1445.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1 else if (i1 == 1) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 1 else 2 * i1 + 0 * i2 + -1
  )
  Tags.Tag1446.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2 else if (i1 == 1) 0 * i2 + 2 else if (i2 == 0) 2 * i1 + 1 else 2 * i1 + 0 * i2 + 1
  )
  Tags.Tag1447.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 3 else if (i1 == 1) 0 * i2 + 3 else if (i2 == 0) 2 * i1 + 0 else 2 * i1 + 0 * i2 + 0
  )
  Tags.Tag1448.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0 else if (i1 == 1) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1449.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) Option(0) else if (i1 == 1) Option(0 * i2 + 0) else if (i2 == 0) Option(0 * i1 + 0) else Option.empty
  )
  Tags.Tag1450.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) Option(1) else if (i1 == 1) Option(0 * i2 + 1) else if (i2 == 0) Option(0 * i1 + 1) else Option.empty
  )
  Tags.Tag1451.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1 else if (i1 == 1) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1452.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2 else if (i1 == 1) 0 * i2 + 2 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1453.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(0 * i1 + 1)
    else Option(1 * i1 + 0 * i2 + 1)
  )
  Tags.Tag1454.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(0 * i1 + 2)
    else Option(1 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1455.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(0 * i1 + 1)
    else Option(1 * i1 + 0 * i2 + 2)
  )
  Tags.Tag1456.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(1 * i1 + 1)
    else Option(1 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1457.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(1 * i1 + 1)
    else Option(0 * i1 + 0 * i2 + 1)
  )
  Tags.Tag1458.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 3 else if (i1 == 1) 0 * i2 + 3 else if (i2 == 0) 2 * i1 + 1 else 0 * i1 + 0 * i2 + 2
  )
  Tags.Tag1459.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1460.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1461.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1462.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1463.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 0 else 0 * i1 + 0 * i2 + 2
  )
  Tags.Tag1464.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 2
  )
  Tags.Tag1465.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 0 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1466.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1467.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 0 * i1 + 2 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1468.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + 2
  )
  Tags.Tag1469.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0) else if (i1 == 0) Option(0 * i2 + 0) else if (i2 == 0) Option.empty else Option(1 * i1 + 0 * i2 + 2)
  )
  Tags.Tag1470.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 0 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1471.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1472.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 0 * i1 + 2 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1473.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1474.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 2
  )
  Tags.Tag1475.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 2 * i1 + -1 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1476.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 2 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1477.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 2 * i1 + -1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1478.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 2 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1479.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1480.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 0 else 0 * i1 + 0 * i2 + 2
  )
  Tags.Tag1481.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1482.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 0 * i1 + 2 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1483.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 0 * i1 + 2 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1484.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1485.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 0 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1486.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(0 * i1 + 0)
    else Option(1 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1487.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1488.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(0 * i1 + 1)
    else Option(1 * i1 + 0 * i2 + -1)
  )
  Tags.Tag1489.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(0 * i1 + 0)
    else Option(1 * i1 + 0 * i2 + 1)
  )
  Tags.Tag1490.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(1 * i1 + 0)
    else Option(1 * i1 + 0 * i2 + -1)
  )
  Tags.Tag1491.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 2
  )
  Tags.Tag1492.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(1 * i1 + 0)
    else Option(0 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1493.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 2 * i1 + 1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1494.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 2 * i1 + 0 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1495.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1) else if (i1 == 0) Option(0 * i2 + 1) else if (i2 == 0) Option.empty else Option(1 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1496.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + 2
  )
  Tags.Tag1497.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 1 * i1 + 0 else 0 * i1 + 0 * i2 + 2
  )
  Tags.Tag1498.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 2 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1499.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 2 * i1 + 0 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1500.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 1 * i1 + 0 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1501.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1502.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + -1 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1503.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0) else if (i1 == 0) Option(0 * i2 + 0) else if (i2 == 0) Option.empty else Option(1 * i1 + 0 * i2 + -1)
  )
  Tags.Tag1504.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 0 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1505.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 0 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1506.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1507.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + -1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1508.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1509.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1510.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + -1 else 1 * i1 + 1 * i2 + -1
  )
  Tags.Tag1511.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + -1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1512.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 1 * i1 + -1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1513.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1514.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1515.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 0 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1516.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 0 * i1 + 1 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1517.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 0 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1518.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + -1 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1519.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + -1 else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1520.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(0 * i1 + 0)
    else Option(1 * i1 + 0 * i2 + 2)
  )
  Tags.Tag1521.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option.empty
    else if (i2 == 0) Option(1 * i1 + 1)
    else Option(0 * i1 + 0 * i2 + 0)
  )
  Tags.Tag1522.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1523.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 1 * i1 + 0 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1524.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 1 * i1 + 1 else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1525.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 1 * i1 + -1 else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1526.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 1 * i1 + -1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1527.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1) else if (i1 == 0) Option(0 * i2 + 1) else if (i2 == 0) Option.empty else Option(1 * i1 + 0 * i2 + -1)
  )
  Tags.Tag1528.setValue((i1: Int, i2: Int) => if (i1 == 0) 1 else if (i2 == 0) i1 - 1 else i1)
  Tags.Tag1529.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1530.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + 2
  )
  Tags.Tag1531.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 1 * i2 + 0 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1532.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 * i2 + 0 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1533.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 0 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + 2
  )
  Tags.Tag1534.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 0 * i1 + 0 else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1535.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 * i2 + 1 else if (i2 == 0) 1 * i1 + 1 else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1536.mapResult(Tags.Tag1446, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1537.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 2 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 2 * i1 + 0 * i2 + 0
  )
  Tags.Tag1538.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 1 * i1 + 0
    else if (i1 == i2) -1 * i1 + 2 * i2 + -1
    else if (i1 < i2) 1 * i1 + 0 * i2 + -1
    else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1539.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 2 * i2 + 1
    else if (i2 == 0) 0 * i1 + 2
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 2 * i2 + 1
    else 0 * i1 + 2 * i2 + 2
  )
  Tags.Tag1540.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1)
    else if (i1 == 0) Option(0 * i2 + 1)
    else if (i2 == 0) Option.empty
    else if (i1 == i2) Option(0 * i1 + 2 * i2 + 0)
    else if (i1 < i2) Option(2 * i1 + 0 * i2 + 0)
    else Option.empty
  )
  Tags.Tag1541.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 2
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 0 * i1 + 2 * i2 + 2
  )
  Tags.Tag1542.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(0 * i2 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == i2) Option(0 * i1 + 2 * i2 + -1)
    else if (i1 < i2) Option(2 * i1 + 0 * i2 + -1)
    else Option.empty
  )
  Tags.Tag1543.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) -1 * i1 + 2 * i2 + -1
    else if (i1 < i2) 1 * i1 + 0 * i2 + -1
    else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1544.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 0 * i1 + 2 * i2 + 1
  )
  Tags.Tag1545.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(0 * i2 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == i2) Option(-1 * i1 + 2 * i2 + -1)
    else if (i1 < i2) Option(1 * i1 + 0 * i2 + -1)
    else Option.empty
  )
  Tags.Tag1546.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 1 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 1 * i1 + 1 * i2 + 0
  )
  Tags.Tag1547.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 1 * i1 + 1 * i2 + 1
  )
  Tags.Tag1548.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + -1
    else if (i1 < i2) 1 * i1 + 0 * i2 + -1
    else 0 * i1 + 1 * i2 + 1
  )
  Tags.Tag1549.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 2 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 2 * i1 + 0 * i2 + 1
    else 2 * i1 + 0 * i2 + 0
  )
  Tags.Tag1550.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 2 * i2 + 0
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 0 * i1 + 2 * i2 + 0
    else 0 * i1 + 2 * i2 + 1
  )
  Tags.Tag1551.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 2 * i1 + -1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 2 * i1 + 0 * i2 + -1
  )
  Tags.Tag1552.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 2 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 2 * i1 + 0 * i2 + 1
  )
  Tags.Tag1553.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 0 * i1 + 2
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 0 * i1 + 2 * i2 + 2
  )
  Tags.Tag1554.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) -2 * i1 + 2 * i2 + 0
    else if (i1 < i2) 0 * i1 + 0 * i2 + 0
    else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1555.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 1 * i2 + 0
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) -2 * i1 + 2 * i2 + 0
    else if (i1 < i2) -1 * i1 + 1 * i2 + 0
    else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1556.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 0 * i1 + 2 * i2 + 1
  )
  Tags.Tag1557.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 1 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) -1 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 1 * i2 + 1
    else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1558.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) -2 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 0 * i2 + 1
    else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1559.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 == 1) 1 * i2 + -1
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) -2 * i1 + 2 * i2 + 0
    else if (i1 < i2) -1 * i1 + 1 * i2 + 0
    else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1560.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 1) 0 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 0 * i2 + 0
    else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1561.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 0 * i2 + 0
    else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1562.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 0 * i2 + 0
    else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1563.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 0 * i2 + 0
    else 0 * i1 + 1 * i2 + 1
  )
  Tags.Tag1564.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1)
    else if (i1 == 0) Option(0 * i2 + 1)
    else if (i2 == 0) Option.empty
    else if (i1 == i2) Option(-1 * i1 + 2 * i2 + 0)
    else if (i1 < i2) Option(1 * i1 + 0 * i2 + 0)
    else Option.empty
  )
  Tags.Tag1565.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 == 1) 0 * i2 + 0
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) -2 * i1 + 2 * i2 + 0
    else if (i1 < i2) 0 * i1 + 0 * i2 + 0
    else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1566.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 == 1) 0 * i2 + 0
    else if (i2 == 0) 1 * i1 + 0
    else if (i1 == i2) -1 * i1 + 2 * i2 + -1
    else if (i1 < i2) 1 * i1 + 0 * i2 + -1
    else 1 * i1 + 0 * i2 + 0
  )
  Tags.Tag1567.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2
    else if (i1 == 0) 2 * i2 + 2
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 2
    else if (i1 < i2) 0 * i1 + 2 * i2 + 2
    else 0 * i1 + 2 * i2 + 1
  )
  Tags.Tag1568.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i1 == 1) 1 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) -1 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 1 * i2 + 1
    else 0 * i1 + 1 * i2 + 0
  )
  Tags.Tag1569.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2
    else if (i1 == 0) 1 * i2 + 2
    else if (i2 == 0) 0 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 2
    else if (i1 < i2) 0 * i1 + 1 * i2 + 2
    else 0 * i1 + 1 * i2 + 1
  )
  Tags.Tag1570.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 1 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 1 * i1 + 1 * i2 + 1
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1571.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 2 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 2 * i2 + 1
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1572.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 == 1) 0 * i2 + 2
    else if (i2 == 0) 2 * i1 + -1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 2 * i1 + 0 * i2 + -1
  )
  Tags.Tag1573.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 2 * i1 + 0 * i2 + 1
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1574.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 1) 0 * i1 + 2
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1575.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2
    else if (i1 == 1) 0 * i2 + 1
    else if (i2 == 0) 2 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 2 * i1 + 0 * i2 + 0
  )
  Tags.Tag1576.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2
    else if (i1 == 1) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 2
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 0 * i1 + 2 * i2 + 2
  )
  Tags.Tag1577.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 3
    else if (i1 == 1) 0 * i2 + 2
    else if (i2 == 0) 2 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 2 * i1 + 0 * i2 + 1
  )
  Tags.Tag1578.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1579.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2
    else if (i1 == 1) 0 * i2 + 3
    else if (i2 == 0) 2 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 2 * i1 + 0 * i2 + 1
    else 2 * i1 + 0 * i2 + 0
  )
  Tags.Tag1580.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1581.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 1) 1
    else if (i1 == 1) 0 * i2 + 1
    else if (i2 == 1) 0 * i1 + 0
    else if (i1 == i2) -2 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 0 * i2 + 1
    else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1582.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2
    else if (i1 == 1) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 0 * i2 + 0
    else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1583.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i1 == 1) 0 * i2 + 1
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) -2 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 0 * i2 + 1
    else 0 * i1 + 0 * i2 + 0
  )
  Tags.Tag1584.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 1) 0
    else if (i1 == 1) 0 * i2 + 0
    else if (i2 == 1) 0 * i1 + 1
    else if (i1 == i2) -2 * i1 + 2 * i2 + 0
    else if (i1 < i2) 0 * i1 + 0 * i2 + 0
    else 0 * i1 + 0 * i2 + 1
  )
  Tags.Tag1585.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 1 * i2 + 1
    else if (i2 == 0) 1 * i1 + -1
    else if (i1 == i2) -2 * i1 + 2 * i2 + 1
    else if (i1 < i2) -1 * i1 + 1 * i2 + 1
    else 1 * i1 + -1 * i2 + -1
  )
  Tags.Tag1586.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 1) 1 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 1 * i1 + 1 * i2 + 0
  )
  Tags.Tag1587.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 1 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + -1
    else if (i1 < i2) 2 * i1 + 0 * i2 + -1
    else 1 * i1 + 1 * i2 + 1
  )
  Tags.Tag1588.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 1 * i2 + 1
    else if (i2 == 0) 1 * i1 + -1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 1 * i2 + 1
    else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1589.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 2 * i1 + 0 * i2 + 1
    else 1 * i1 + 1 * i2 + 0
  )
  Tags.Tag1590.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 1) 3
    else if (i1 == 1) 0 * i2 + 3
    else if (i2 == 1) 0 * i1 + 2
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 2 * i1 + 0 * i2 + 1
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1591.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 1 * i2 + 0
    else if (i2 == 0) 1 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 0 * i1 + 1 * i2 + 0
    else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1592.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i1 == 1) 0 * i2 + 3
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 2 * i1 + 0 * i2 + 1
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1593.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 1 * i2 + 0
    else if (i2 == 0) 1 * i1 + 1
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 1 * i2 + 0
    else 1 * i1 + 1 * i2 + 1
  )
  Tags.Tag1594.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i1 == 1) 1 * i2 + 2
    else if (i2 == 0) 0 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 1 * i1 + 1 * i2 + 1
    else 0 * i1 + 2 * i2 + 0
  )
  Tags.Tag1595.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i2 == 0) 1 * i1 + 1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 0 * i2 + 0
    else 1 * i1 + 0 * i2 + 1
  )
  Tags.Tag1596.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + -1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 0
    else if (i1 < i2) 1 * i1 + 0 * i2 + 0
    else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1597.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + -1
    else if (i1 == i2) -1 * i1 + 2 * i2 + 1
    else if (i1 < i2) 1 * i1 + 0 * i2 + 1
    else 1 * i1 + 0 * i2 + -1
  )
  Tags.Tag1598.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + -1
    else if (i1 == i2) -2 * i1 + 2 * i2 + 1
    else if (i1 < i2) 0 * i1 + 0 * i2 + 1
    else 1 * i1 + -1 * i2 + -1
  )
  Tags.Tag1599.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 1 * i2 + 0
    else if (i2 == 0) 1 * i1 + 0
    else if (i1 == i2) -2 * i1 + 2 * i2 + 0
    else if (i1 < i2) -1 * i1 + 1 * i2 + 0
    else 1 * i1 + -1 * i2 + 0
  )
  Tags.Tag1600.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 0 * i2 + 1
    else if (i2 == 0) 1 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 0
    else if (i1 < i2) 2 * i1 + 0 * i2 + 0
    else 1 * i1 + 1 * i2 + 0
  )
  Tags.Tag1601.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) 1 * i2 + 1
    else if (i2 == 0) 1 * i1 + 0
    else if (i1 == i2) 0 * i1 + 2 * i2 + 1
    else if (i1 < i2) 1 * i1 + 1 * i2 + 1
    else 1 * i1 + 1 * i2 + 0
  )
  Tags.Tag1602.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 == 1) i2 * 3 + 3 else i2 * 2 + i2 / i1 + 3)
  Tags.Tag1603.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(0 * i2 + 0)
    else if (i1 == 1) Option(-2 * i1 + 0 * i2 + 3)
    else if (i2 == 0) Option(2 * i1 + -1)
    else if (i2 == 1) Option.empty
    else if (i1 == i2) Option.empty
    else if (i1 > i2) Option.empty
    else Option.empty
  )
  Tags.Tag1604.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1)
    else if (i1 == 0) Option(0 * i2 + 1)
    else if (i1 == 1) Option(-1 * i1 + 0 * i2 + 3)
    else if (i2 == 0) Option(2 * i1 + 0)
    else if (i2 == 1) Option.empty
    else if (i1 == i2) Option.empty
    else if (i1 > i2) Option.empty
    else Option.empty
  )
  Tags.Tag1605.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(0 * i2 + 0)
    else if (i1 == 1) Option(-2 * i1 + 0 * i2 + 2)
    else if (i2 == 0) Option(0 * i1 + 0)
    else if (i2 == 1) Option.empty
    else if (i1 == i2) Option.empty
    else if (i1 > i2) Option.empty
    else Option.empty
  )
  Tags.Tag1606.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(0 * i2 + 0)
    else if (i1 == 1) Option(-2 * i1 + 0 * i2 + 2)
    else if (i2 == 0) Option(1 * i1 + -1)
    else if (i2 == 1) Option.empty
    else if (i1 == i2) Option.empty
    else if (i1 > i2) Option.empty
    else Option.empty
  )
  Tags.Tag1607.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1)
    else if (i1 == 0) Option(0 * i2 + 1)
    else if (i1 == 1) Option(-2 * i1 + 0 * i2 + 3)
    else if (i2 == 0) Option(0 * i1 + 1)
    else if (i2 == 1) Option.empty
    else if (i1 == i2) Option.empty
    else if (i1 > i2) Option.empty
    else Option.empty
  )
  Tags.Tag1608.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1)
    else if (i1 == 0) Option(0 * i2 + 1)
    else if (i1 == 1) Option(-2 * i1 + 0 * i2 + 3)
    else if (i2 == 0) Option(1 * i1 + 0)
    else if (i2 == 1) Option.empty
    else if (i1 == i2) Option.empty
    else if (i1 > i2) Option.empty
    else Option.empty
  )
  Tags.Tag1609.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i1 == 1) -1 * i1 + 2 * i2 + 3
    else if (i2 == 0) 0 * i1 + 2
    else if (i2 == 1) 0 * i1 + 0 * i2 + 3
    else if (i1 == i2) -1 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 2
    else if (i1 > i2) 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 2
    else 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 2
  )
  Tags.Tag1610.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i1 == 1) -1 * i1 + 1 * i2 + 3
    else if (i2 == 0) 0 * i1 + 2
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -2 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 2
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -2 / -2 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * -2 / -2 + 2
  )
  Tags.Tag1611.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i1 == 1) -2 * i1 + 1 * i2 + 3
    else if (i2 == 0) 0 * i1 + 1
    else if (i2 == 1) 0 * i1 + -2 * i2 + 3
    else if (i1 == i2) -2 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 1
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -2 / -2 + 1
    else 0 * i1 + 0 * i2 + i2 / i1 * -2 / -2 + 1
  )
  Tags.Tag1612.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i1 == 1) 0 * i1 + 2 * i2 + 3
    else if (i2 == 0) 0 * i1 + 3
    else if (i2 == 1) 0 * i1 + 1 * i2 + 3
    else if (i1 == i2) -1 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 3
    else if (i1 > i2) 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 3
    else 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 3
  )
  Tags.Tag1613.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i1 == 1) -2 * i1 + 2 * i2 + 3
    else if (i2 == 0) 0 * i1 + 1
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -1 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 1
    else if (i1 > i2) 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 1
    else 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 1
  )
  Tags.Tag1614.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i1 == 1) -2 * i1 + 2 * i2 + 2
    else if (i2 == 0) 0 * i1 + 0
    else if (i2 == 1) 0 * i1 + -2 * i2 + 3
    else if (i1 == i2) -1 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 0
    else if (i1 > i2) 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 0
    else 0 * i1 + 1 * i2 + i2 / i1 * -2 / -2 + 0
  )
  Tags.Tag1615.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) 0 * i2 + 0
    else if (i1 == 1) -2 * i1 + 1 * i2 + 2
    else if (i2 == 0) 0 * i1 + 0
    else if (i2 == 1) 0 * i1 + -2 * i2 + 2
    else if (i1 == i2) -2 * i1 + 2 * i2 + i2 / i1 * -2 / -2 + 0
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -2 / -2 + 0
    else 0 * i1 + 0 * i2 + i2 / i1 * -2 / -2 + 0
  )
  Tags.Tag1616.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) 1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 3
    else if (i2 == 1) 0 * i1 + 0 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 3
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 3
  )
  Tags.Tag1617.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) 0 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 2
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1618.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i2 == 1) 0 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1619.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 1
    else if (i2 == 1) 1 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1620.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i2 == 1) 2 * i1 + -3 * i2 + 2
    else if (i1 == i2) -3 * i1 + 2 * i2 + i2 / i1 * i1 * -3 / -1 + -1
    else if (i1 > i2) 2 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -1
  )
  Tags.Tag1621.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i2 == 1) 0 * i1 + 0 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 3
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 3
  )
  Tags.Tag1622.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + 1
    else if (i2 == 1) 2 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 2 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 2 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 2 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1623.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + 0
    else if (i2 == 1) 0 * i1 + 0 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 3
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 3
  )
  Tags.Tag1624.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 2
    else if (i2 == 1) 1 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1625.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) 0 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + 0
    else if (i2 == 1) 2 * i1 + -3 * i2 + 3
    else if (i1 == i2) -3 * i1 + 2 * i2 + i2 / i1 * i1 * -3 / -1 + 0
    else if (i1 > i2) 2 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 0
  )
  Tags.Tag1626.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 0
    else if (i2 == 1) 1 * i1 + -3 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 0
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 0
  )
  Tags.Tag1627.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + -1
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1628.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + -1
    else if (i2 == 1) 0 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1629.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i2 == 1) 0 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1630.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 1
    else if (i2 == 1) 0 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1631.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 1
    else if (i2 == 1) 1 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1632.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i2 == 1) 0 * i1 + -3 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 0
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 0
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 0
  )
  Tags.Tag1633.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 0
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1634.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1635.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) 0 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 2
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1636.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) 0 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + 1
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1637.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) 0 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + 1
    else if (i2 == 1) 1 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1638.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2
    else if (i1 == 0) i2 * 0 / -3 + 2
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + -1
    else if (i2 == 1) 1 * i1 + -3 * i2 + 2
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -1
  )
  Tags.Tag1639.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + 0
    else if (i2 == 1) 1 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1640.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + 0
    else if (i2 == 1) 1 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1641.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2
    else if (i1 == 0) i2 * 0 / -3 + 2
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + -2
    else if (i2 == 1) 1 * i1 + -3 * i2 + 1
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -2
  )
  Tags.Tag1642.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i2 == 1) 1 * i1 + -3 * i2 + 1
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -2
  )
  Tags.Tag1643.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 0
    else if (i2 == 1) 1 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1644.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 2
    else if (i2 == 1) 1 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1645.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + -1
    else if (i2 == 1) 1 * i1 + -3 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 0
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 0
  )
  Tags.Tag1646.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 1
    else if (i2 == 1) 1 * i1 + -3 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 0
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 0
  )
  Tags.Tag1647.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + -2
    else if (i2 == 1) 1 * i1 + -3 * i2 + 1
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -2
  )
  Tags.Tag1648.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 1
    else if (i2 == 1) 1 * i1 + -3 * i2 + 2
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -1
  )
  Tags.Tag1649.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -1 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + -1
    else if (i2 == 1) 1 * i1 + -3 * i2 + 2
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -1
  )
  Tags.Tag1650.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i2 == 1) 1 * i1 + -1 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 2
  )
  Tags.Tag1651.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i2 == 1) 1 * i1 + -2 * i2 + 3
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + 1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + 1
  )
  Tags.Tag1652.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 0
    else if (i2 == 1) 1 * i1 + -3 * i2 + 2
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -1
  )
  Tags.Tag1653.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i2 == 1) 1 * i1 + -3 * i2 + 1
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -2
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -2
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -2
  )
  Tags.Tag1654.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i2 == 1) 1 * i1 + -3 * i2 + 2
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -1
  )
  Tags.Tag1655.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -2 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * -3 / -3 + 0
    else if (i2 == 1) 1 * i1 + -3 * i2 + 2
    else if (i1 == i2) -3 * i1 + 1 * i2 + i2 / i1 * i1 * -3 / -1 + -1
    else if (i1 > i2) 1 * i1 + 0 * i2 + i2 / i1 * i1 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i2 / i1 * i1 * 0 / -3 + -1
  )
  Tags.Tag1656.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 3 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i2 == 1) 0 * i1 + 0 * i2 + 3
    else if (i1 == i2) -1 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 1
    else if (i1 > i2) 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 1
    else 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 1
  )
  Tags.Tag1657.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 3 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i2 == 1) 0 * i1 + -1 * i2 + 3
    else if (i1 == i2) -1 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 0
    else if (i1 > i2) 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 0
    else 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 0
  )
  Tags.Tag1658.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else i2 * 2 + i2 / i1 + 2)
  Tags.Tag1659.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -2 * i1 + 0 * i2 + 7
    else if (i2 == 0) i1 * 0 / -3 + 4
    else if (i2 == 1) 0 * i1 + -3 * i2 + 7
    else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4
    else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4
  )
  Tags.Tag1660.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(i2 * 0 / -3 + 4)
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 4)
    else if (i2 == 0) Option(i1 * 0 / -3 + 1)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 4)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 0)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 1)
    else Option(0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 1)
  )
  Tags.Tag1661.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -1 * i1 + 0 * i2 + 7
    else if (i2 == 0) i1 * 0 / -3 + 5
    else if (i2 == 1) 0 * i1 + -2 * i2 + 7
    else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 4
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 5
    else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 5
  )
  Tags.Tag1662.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 4
    else if (i2 == 0) i1 * 0 / -3 + 4
    else if (i2 == 1) 0 * i1 + -3 * i2 + 7
    else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4
    else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4
  )
  Tags.Tag1663.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 4
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i2 == 1) 0 * i1 + -3 * i2 + 7
    else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4
    else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4
  )
  Tags.Tag1664.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -3 * i1 + 0 * i2 + 5
    else if (i2 == 0) i1 * -2 / -1 + 0
    else if (i2 == 1) 0 * i1 + -3 * i2 + 7
    else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4
    else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4
  )
  Tags.Tag1665.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 == 1) i2 * 0 / -3 + 1
    else if (i1 == 2) -1 * i1 + 0 * i2 + 6
    else if (i2 == 0) i1 * 0 / -3 + 4
    else if (i2 == 1) 0 * i1 + -3 * i2 + 7
    else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4
    else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4
  )
  Tags.Tag1666.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) Option.empty
    else if (i1 == 1) Option(i2 * 0 / -3 + 4)
    else if (i2 == 0) Option.empty
    else if (i1 == i2) Option(-2 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 1)
    else if (i1 > i2) Option(1 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 2)
    else Option(1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 2)
  )
  Tags.Tag1667.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) Option.empty
    else if (i1 == 1) Option(i2 * 0 / -3 + 5)
    else if (i2 == 0) Option.empty
    else if (i1 == i2) Option(-2 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 2)
    else if (i1 > i2) Option(1 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 3)
    else Option(1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 3)
  )
  Tags.Tag1668.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) Option.empty
    else if (i1 == 1) Option(i2 * 0 / -3 + 6)
    else if (i2 == 0) Option.empty
    else if (i1 == i2) Option(-2 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3)
    else if (i1 > i2) Option(1 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4)
    else Option(1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4)
  )
  Tags.Tag1669.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 == 1) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i1 == i2) -3 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + 3
    else if (i1 > i2) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 4
    else 0 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 4
  )
  Tags.Tag1670.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 1) 1
    else if (i1 == 1) i2 * 0 / -3 + 1
    else if (i2 == 1) i1 * 0 / -3 + 4
    else if (i1 == i2) -1 * i1 + 3 * i2 + i2 / i1 * -3 / -3 + -2
    else if (i1 > i2) 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 2
    else 2 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + -1
  )
  Tags.Tag1671.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 1 * i2 + 4
    else if (i2 == 0) i1 * -2 / -1 + -1
    else if (i2 == 1) 3 * i1 + -3 * i2 + 2
    else if (i1 == i2) -1 * i1 + 3 * i2 + i1 * i2 * -3 / -3 + -1
    else if (i1 > i2) 2 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + -1
  )
  Tags.Tag1672.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -3 * i1 + 1 * i2 + 5
    else if (i2 == 0) i1 * -2 / -1 + 0
    else if (i2 == 1) 3 * i1 + -3 * i2 + 3
    else if (i1 == i2) -1 * i1 + 3 * i2 + i1 * i2 * -3 / -3 + 0
    else if (i1 > i2) 2 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + 0
  )
  Tags.Tag1673.setValue((i1: Int, i2: Int) =>
    if (i1 == 0) 0
    else if (i1 == 1) -3 * i1 + i2 + 3
    else if (i2 == 0) i1 - 1
    else if (i2 == 1) 2 * i1 - 3 * i2 + 2
    else if (i1 == i2) -2 * i1 + 3 * i2 + i1 * i2 - 1
    else i1 + i1 * i2 - 1
  )
  Tags.Tag1674.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i1 == 1) -3 * i1 + 1 * i2 + 4
    else if (i2 == 0) i1 * -3 / -3 + 0
    else if (i2 == 1) 2 * i1 + -3 * i2 + 3
    else if (i1 == i2) -2 * i1 + 3 * i2 + i1 * i2 * -3 / -3 + 0
    else if (i1 > i2) 1 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i1 * i2 * -3 / -3 + 0
  )
  Tags.Tag1675.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-2 * i1 + 0 * i2 + 7)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 7)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 3)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 4)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 4)
  )
  Tags.Tag1676.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 7)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 6)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 2)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 3)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 3)
  )
  Tags.Tag1677.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-1 * i1 + 0 * i2 + 7)
    else if (i2 == 1) Option(0 * i1 + -2 * i2 + 7)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 4)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 5)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 5)
  )
  Tags.Tag1678.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 5)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 4)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 0)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 1)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1)
  )
  Tags.Tag1679.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 6)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 5)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 1)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 2)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2)
  )
  Tags.Tag1680.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(i2 * 0 / -3 + 3)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 5)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 4)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 0)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 1)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1)
  )
  Tags.Tag1681.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(i2 * 0 / -3 + 4)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 6)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 5)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 1)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 2)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2)
  )
  Tags.Tag1682.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(i2 * 0 / -3 + 2)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 4)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 3)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + -1)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 0)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0)
  )
  Tags.Tag1683.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(i2 * 0 / -3 + 4)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 7)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 6)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 2)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 3)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 3)
  )
  Tags.Tag1684.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(i2 * 0 / -3 + 3)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 6)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 5)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 1)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 2)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2)
  )
  Tags.Tag1685.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option.empty
    else if (i1 == 0) Option(i2 * 0 / -3 + 2)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 5)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 4)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 0)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 1)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1)
  )
  Tags.Tag1686.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 4)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 5)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 1)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 2)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2)
  )
  Tags.Tag1687.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 4)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 3)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + -1)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 0)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0)
  )
  Tags.Tag1688.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 1)
    else if (i2 == 0) Option.empty
    else if (i1 == 1) Option(-3 * i1 + 0 * i2 + 5)
    else if (i2 == 1) Option(0 * i1 + -3 * i2 + 6)
    else if (i1 == i2) Option(-3 * i1 + 3 * i2 + i1 / i2 * -3 / -3 + 2)
    else if (i1 > i2) Option(0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 3)
    else Option(0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 3)
  )
  Tags.Tag1689.setValue((i1: Int, i2: Int) => if (i2 % (i1 + 1) == 0) i2 * 2 + 2 else i2 * 2 + 1)
  Tags.Tag1690.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 4
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i2 == 1) 2 * i1 + -3 * i2 + 2
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1691.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 4
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i2 == 1) 3 * i1 + -3 * i2 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
  )
  Tags.Tag1692.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i2 == 1) 1 * i1 + -3 * i2 + 2
    else if (i1 % i2 == 0) 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1693.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i1 == 1) -3 * i1 + 0 * i2 + 3
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i2 == 1) 2 * i1 + -3 * i2 + 1
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
  )
  Tags.Tag1694.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 2 * i2 + i1 * i2 * 0 / -3 + 1
    else 0 * i1 + 2 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1695.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 2
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 / i2 * -3 / -3 + 1
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1696.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 3
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 2 * i2 + i1 / i2 * -3 / -3 + 1
    else 0 * i1 + 2 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1697.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 * i2 * 0 / -3 + 1
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1698.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 4
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 2 * i2 + i1 / i2 * -3 / -3 + 2
    else 0 * i1 + 2 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1699.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 2
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 * i2 * 0 / -3 + 2
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1700.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 3
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 / i2 * -3 / -3 + 2
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1701.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 2
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 2 * i2 + i1 / i2 * -3 / -3 + 0
    else 0 * i1 + 2 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1702.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 * i2 * 0 / -3 + 0
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1703.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 2 * i2 + i1 * i2 * 0 / -3 + 1
    else 0 * i1 + 2 * i2 + i2 / i1 * 0 / -3 + 2
  )
  Tags.Tag1704.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 2 * i2 + i1 * i2 * 0 / -3 + 0
    else 0 * i1 + 2 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1705.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 2
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 / i2 * -3 / -3 + 1
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 2
  )
  Tags.Tag1706.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 * i2 * 0 / -3 + 1
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 2
  )
  Tags.Tag1707.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 1
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 1 * i2 + i1 / i2 * -3 / -3 + 0
    else 0 * i1 + 1 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1708.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 3
    else if (i2 % (i1 + 1) == 0) 0 * i1 + 2 * i2 + i1 / i2 * -3 / -3 + 1
    else 0 * i1 + 2 * i2 + i2 / i1 * 0 / -3 + 2
  )
  Tags.Tag1709.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 % (i2 + 1) == 0) 2 * i1 + 0 * i2 + i1 * i2 * 0 / -3 + 0
    else 2 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + -1
  )
  Tags.Tag1710.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 % (i2 + 1) == 0) 2 * i1 + 0 * i2 + i1 * i2 * 0 / -3 + 1
    else 2 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1711.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 % (i2 + 1) == 0) 1 * i1 + 0 * i2 + i1 * i2 * 0 / -3 + 0
    else 1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + -1
  )
  Tags.Tag1712.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 % (i2 + 1) == 0) 1 * i1 + 0 * i2 + i1 * i2 * 0 / -3 + 1
    else 1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1713.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 % (i2 + 1) == 0) 0 * i1 + 0 * i2 + i1 / i2 * 0 / -3 + 0
    else 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 0
  )
  Tags.Tag1714.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 % (i2 + 1) == 0) 0 * i1 + 2 * i2 + i2 / (i1 + 1) * -2 / 1 + 0
    else 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 0
  )
  Tags.Tag1715.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 % (i2 + 1) == 0) 0 * i1 + 1 * i2 + i2 / (i1 + 1) * -3 / 3 + 0
    else 0 * i1 + 1 * i2 + i2 / i1 * -3 / -3 + 0
  )
  Tags.Tag1716.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2
    else if (i1 % (i2 + 1) == 0) 2 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + -1
  )
  Tags.Tag1717.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 % (i2 + 1) == 0) 2 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1718.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2
    else if (i1 % (i2 + 1) == 0) 2 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1719.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 3
    else if (i1 % (i2 + 1) == 0) 2 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 1
    else 2 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1720.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i1 % (i2 + 1) == 0) 1 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1721.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 % (i2 + 1) == 0) 1 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 1
  )
  Tags.Tag1722.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i1 % (i2 + 1) == 0) 1 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + -1
  )
  Tags.Tag1723.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 2
    else if (i1 % (i2 + 1) == 0) 1 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i2 / i1 * 0 / -3 + 0
  )
  Tags.Tag1724.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1725.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1726.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1727.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1728.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1729.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1730.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1731.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1732.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1733.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1734.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1735.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
  )
  Tags.Tag1736.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1737.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1738.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1739.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1740.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1741.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1742.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1743.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1744.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1745.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1746.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1747.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
  )
  Tags.Tag1748.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1749.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 2
  )
  Tags.Tag1750.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1751.setValue((i1: Int, i2: Int) =>
    if (i1 == 1 && i2 == 0) 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
  )
  Tags.Tag1752.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 2
    else 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 2
  )
  Tags.Tag1753.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 1 * i2 + i2 / i1 * -3 / -3 + 2
    else 0 * i1 + 1 * i2 + i2 / i1 * -3 / -3 + 2
  )
  Tags.Tag1754.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 2
    else 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 2
  )
  Tags.Tag1755.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 1 * i2 + i2 / i1 * -3 / -3 + 1
    else 0 * i1 + 1 * i2 + i2 / i1 * -3 / -3 + 1
  )
  Tags.Tag1756.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 1
    else 0 * i1 + 0 * i2 + i2 / i1 * -3 / -3 + 1
  )
  Tags.Tag1757.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 3
    else 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 3
  )
  Tags.Tag1758.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 1 * i2 + i2 / i1 * -3 / -3 + 3
    else 0 * i1 + 1 * i2 + i2 / i1 * -3 / -3 + 3
  )
  Tags.Tag1759.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 1) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 % i1 == 0) 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 1
    else 0 * i1 + 2 * i2 + i2 / i1 * -3 / -3 + 1
  )
  Tags.Tag1760.mapResult(Tags.Tag1544, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1761.mapResult(Tags.Tag1537, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1762.mapResult(Tags.Tag1566, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1763.mapResult(Tags.Tag1543, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1764.mapResult(Tags.Tag1567, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1765.mapResult(Tags.Tag1580, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1766.mapResult(Tags.Tag1567, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1767.mapResult(Tags.Tag1545, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1768.mapResult(Tags.Tag1541, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1769.mapResult(Tags.Tag1542, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1770.mapResult(Tags.Tag1550, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1771.mapResult(Tags.Tag1540, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1772.mapResult(Tags.Tag1557, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1773.mapResult(Tags.Tag1546, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1774.mapResult(Tags.Tag1539, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1775.mapResult(Tags.Tag1571, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1776.mapResult(Tags.Tag1556, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1777.mapResult(Tags.Tag1556, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1778.mapResult(Tags.Tag1553, (i1: Option[Int]) => i1.map(b => b / 2))
  Tags.Tag1779.mapResult(Tags.Tag1542, (i1: Option[Int]) => i1.map(b => b + 1))
  Tags.Tag1780.mapResult(Tags.Tag1547, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1781.setValue(Tags.Tag1553, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1782.setValue(Tags.Tag1597, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1783.setValue(Tags.Tag1720, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1784.setValue(Tags.Tag1554, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1785.setValue(Tags.Tag1555, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1786.setValue(Tags.Tag1720, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1787.setValue(Tags.Tag1704, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1788.setValue(Tags.Tag1694, (i1: Int, i2: Int) => (1, i1))
  Tags.Tag1789.setValue(Tags.Tag1695, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1790.setValue(Tags.Tag1573, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1791.setValue(Tags.Tag1694, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1792.setValue(Tags.Tag1717, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1793.setValue(Tags.Tag1717, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1794.setValue(Tags.Tag1703, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1795.setValue(Tags.Tag1702, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1796.setValue(Tags.Tag1702, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1797.setValue(Tags.Tag1717, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1798.setValue(Tags.Tag1711, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1799.setValue(Tags.Tag1589, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1800.setValue(Tags.Tag1703, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1801.setValue(Tags.Tag1709, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1802.setValue(Tags.Tag1689, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1803.setValue(Tags.Tag1711, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1804.setValue(Tags.Tag1701, (i1: Int, i2: Int) => (1, i1))
  Tags.Tag1805.setValue(Tags.Tag1554, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1806.setValue(Tags.Tag1596, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1807.setValue(Tags.Tag1709, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1808.setValue(Tags.Tag1701, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1809.setValue(Tags.Tag1695, (i1: Int, i2: Int) => (i1, i2 + 1))
  Tags.Tag1810.setValue(Tags.Tag1709, (i1: Int, i2: Int) => (i1, 1))
  Tags.Tag1811.setValue(Tags.Tag1587, (i1: Int, i2: Int) => (i1, i2))
  Tags.Tag1812.setValue(Tags.Tag1689, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1813.setValue(Tags.Tag1709, (i1: Int, i2: Int) => (i2 + 1, i1))
  Tags.Tag1814.setValue(Tags.Tag1694, (i1: Int, i2: Int) => (1, i2))
  Tags.Tag1815.setValue(Tags.Tag1694, (i1: Int, i2: Int) => (1, i1))
  Tags.Tag1816.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1817.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 % i2 == 0) Option(2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2)
    else Option(2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1)
  )
  Tags.Tag1818.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1819.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1820.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1821.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(0)
    else if (i1 == 0) Option(i2 * 0 / -3 + 0)
    else if (i2 == 0) Option.empty
    else if (i1 % i2 == 0) Option(1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2)
    else Option(1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1)
  )
  Tags.Tag1822.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1)
    else if (i1 == 0) Option(i2 * 0 / -3 + 1)
    else if (i2 == 0) Option.empty
    else if (i1 % i2 == 0) Option(2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1)
    else Option(2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0)
  )
  Tags.Tag1823.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 1
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1824.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1825.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -2
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
  )
  Tags.Tag1826.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 0 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1827.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1828.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 0
    else if (i1 == 0) i2 * 0 / -3 + 0
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
  )
  Tags.Tag1829.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 2 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1830.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) 1
    else if (i1 == 0) i2 * 0 / -3 + 1
    else if (i2 == 0) i1 * 0 / -3 + 0
    else if (i1 % i2 == 0) 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0
    else 1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 1
  )
  Tags.Tag1831.setValue((i1: Int, i2: Int) =>
    if (i1 == 0 && i2 == 0) Option(1)
    else if (i1 == 0) Option(i2 * 0 / -3 + 1)
    else if (i2 == 0) Option.empty
    else if (i1 % i2 == 0) Option(1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + -1)
    else Option(1 * i1 + 0 * i2 + i1 / i2 * -3 / -3 + 0)
  )
  Tags.Tag1832.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 else if (i1 % 2 == 0) 2 * i1 else 2 * i1 + 1)
  Tags.Tag1833.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 else if (i1 % 2 == 0) 2 * i1 - 1 else 2 * i1)
  Tags.Tag1834.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 0 else if (i1 == 0) 0 else if (i1 % 2 == 0) i1 - 1 else i1)
  Tags.Tag1835.setValue((i1: Int, i2: Int) => if (i1 == 0 && i2 == 0) 1 else if (i1 == 0) 1 else if (i1 % 2 == 0) i1 else i1 + 1)
  Tags.Tag1836.mapResult(Tags.Tag1801, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1837.mapResult(Tags.Tag1801, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1838.mapResult(Tags.Tag1793, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1839.mapResult(Tags.Tag1793, (i1: Option[Int]) => i1.map(b => b - 1).filter(_ >= 0))
  Tags.Tag1840.setValue((i1: Int, i2: Int) => if (i2 == 0) 0 else i2 + 3)
  Tags.Tag1841.setValue((i1: Int, i2: Int) => if (i1 == 0) 0 else if (i1 > i2) i1 + i2 - 1 else 2 * i1 - 1)
}
