package f12.do1

import f07._

object Shi {

  type ShiType = PartialFunction[(Int, Int), Option[Int]]

  val shi1: ShiType  = { case (i1, i2) => Option.empty }
  val shi2: ShiType  = { case (i1, i2) => Option(0) }
  val shi3: ShiType  = { case (i1, i2) => Option(i1 + i2) }
  val shi4: ShiType  = { case (i1, i2) => Option(i1 - i2) }
  val shi5: ShiType  = { case (i1, i2) => Option(i1 / (i2 + 1)) }
  val shi6: ShiType  = { case (i1, i2) => Option(i1 * i2) }
  val shi7: ShiType  = { case (i1, i2) if i2 > 0 => Option(i1 / i2) }
  val shi8: ShiType  = { case (i1, i2) if i2 > 0 => Option((i1 + i2 - 1) / i2) }
  val shi9: ShiType  = { case (i1, i2) => Option(i2 * 2 + 1) }
  val shi10: ShiType = { case (i1, i2) => Option(i1 * 2 + i2) }
  val shi11: ShiType = { case (i1, i2) => Option(i1 + 1) }
  val shi12: ShiType = { case (i1, i2) => Option(i1 * 2) }
  val shi13: ShiType = { case (i1, i2) => Option(i1 * 2 - i2) }
  val shi14: ShiType = { case (i1, i2) => Option(i1 - i2 + 1) }
  val shi15: ShiType = { case (i1, i2) => Option(1) }
  val shi16: ShiType = { case (i1, i2) => Option(2) }
  val shi17: ShiType = { case (i1, i2) => Option(3) }
  val shi18: ShiType = { case (i1, i2) => Option(i1 * i2 + i1 - i2) }
  val shi19: ShiType = { case (i1, i2) => Option(i1 * i2 + i1 + i2) }
  val shi20: ShiType = { case (i1, i2) => Option(2 * i1 - i1 / (i2 + 1)) }
  val shi21: ShiType = { case (i1, i2) if i2 > 0 => Option(i1 + i1 / i2 - 1) }
  val shi22: ShiType = { case (i1, i2) => Option(2 * i1 - i1 / (i2 + 1) - 1) }
  val shi23: ShiType = { case (i1, i2) => Option(2 * i2 - (i2 + 1) / (i1 + 1) + 1) }
  val shi24: ShiType = { case (i1, i2) if i2 > 0 => Option(i1 + i1 / i2) }
  val shi25: ShiType = { case (i1, i2) if i2 > 0 => Option(i1 + i1 / i2 + 1) }
  val shi26: ShiType = { case (i1, i2) => Option(i1 * i2 + i2) }
  val shi27: ShiType = { case (i1, i2) => Option(i1 * i2 + i2 + 1) }
  val shi28: ShiType = { case (i1, i2) => Option((i1 - 1) * i2) }
  val shi29: ShiType = { case (i1, i2) => Option(4) }
  val shi30: ShiType = { case (i1, i2) => Option(i1 - i1 / (i2 + 1)) }
  val shi31: ShiType = { case (i1, i2) => Option(i1 - i1 / (i2 + 1) + 1) }
  val shi32: ShiType = { case (i1, i2) => Option(i1 + 2) }
  val shi33: ShiType = { case (i1, i2) => Option(i1 + 3) }
  val shi34: ShiType = { case (i1, i2) => Option(i1 + i2 + 1) }
  val shi35: ShiType = { case (i1, i2) if i1 > 0 => Option(i1 + i2 * 2 + i1 * (i2 / i1) + 1) }
  val shi36: ShiType = { case (i1, i2) if i1 > 0 => Option(i1 + i2 * 2 + i1 * (i2 / i1)) }
  val shi37: ShiType = { case (i1, i2) if i1 > 0 => Option(i1 + i2 + i1 * (i2 / i1) + 1) }
  val shi38: ShiType = { case (i1, i2) if i1 > 0 => Option(i1 + i2 + i1 * (i2 / i1)) }
  val shi39: ShiType = { case (i1, i2) => Option(i2 * 2 - (i2 + 1) / 2 + 1) }
  val shi40: ShiType = { case (i1, i2) => Option(i2 * 2 - (i2 + 1) / 2) }
  val shi41: ShiType = { case (i1, i2) => Option(i1 * 3 + 1) }
  val shi42: ShiType = { case (i1, i2) => Option(i1 * 3 + 2) }
  val shi43: ShiType = { case (i1, i2) => Option(i1 * 3) }
  val shi44: ShiType = { case (i1, i2) => Option((i1 + 1) * (i2 + 1)) }
  val shi45: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) - i2) }
  val shi46: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / (i2 * 2)) * i2 + i1 % (i2 * 2)) }
  val shi47: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / (i2 * 2)) * i2 + i2) }
  val shi48: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / i2) * i2) }
  val shi49: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / i2 - 1) * i2) }
  val shi50: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / i2) * i2 + i1) }
  val shi51: ShiType = { case (i1, i2) => Option(i1 / (i2 + 1) * i2 + i1 % (i2 + 1) - 1) }
  val shi52: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 - i2) }
  val shi53: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / (i2 * 2)) * i2 + i1 % (i2 * 2) + i1 + i2) }
  val shi54: ShiType = { case (i1, i2) => Option(5) }
  val shi55: ShiType = { case (i1, i2) if i1 > 0 => Option(i2 / i1 + i2 + 2) }
  val shi56: ShiType = { case (i1, i2) => Option(i1 - 1) }
  val shi57: ShiType = { case (i1, i2) => Option(i1) }
  val shi58: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / i2 - 1) * i2 + i1) }
  val shi59: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / i2) * i2 + 2 * i1) }
  val shi60: ShiType = { case (i1, i2) if i2 > 0 => Option((i1 / i2) * i2 + i2 + 2 * i1) }
  val shi61: ShiType = { case (i1, i2) if i2 > 0 => Option(2 * i1 + i1 / i2 - 1) }
  val shi62: ShiType = { case (i1, i2) if i2 > 0 => Option(2 * i1 + i1 / i2) }
  val shi63: ShiType = { case (i1, i2) => Option(i1 * i2 + i1 * 2) }
  val shi64: ShiType = { case (i1, i2) if i1 > 0 => Option((i2 + 1) % (2 * i1) + i2 + ((i2 + 1) / (2 * i1)) * i1) }
  val shi65: ShiType = { case (i1, i2) if i1 > 0 => Option(i1 + i2 + ((i2 + 1) / (2 * i1)) * i1) }
  val shi66: ShiType = { case (i1, i2) => Option(i2 + i2 / 2 + 2) }
  val shi67: ShiType = { case (i1, i2) if i1 > 0 => Option(2 * i2 + i2 / i1 + 1) }
  val shi68: ShiType = { case (i1, i2) if i1 > 0 => Option(2 * i2 + i2 / i1 + 2) }
  val shi69: ShiType = { case (i1, i2) if i1 > 0 => Option(5) }
  val shi70: ShiType = { case (i1, i2) if i1 > 0 => Option(5) }

  val sumImpl: List[ShiType] =
    List(
      shi1,
      shi2,
      shi3,
      shi4,
      shi5,
      shi6,
      shi7,
      shi8,
      shi9,
      shi10,
      shi11,
      shi12,
      shi13,
      shi14,
      shi15,
      shi16,
      shi17,
      shi18,
      shi19,
      shi20,
      shi21,
      shi22,
      shi23,
      shi24,
      shi25,
      shi26,
      shi27,
      shi28,
      shi29,
      shi30,
      shi31,
      shi32,
      shi33,
      shi34,
      shi35,
      shi36,
      shi37,
      shi38,
      shi39,
      shi40,
      shi41,
      shi42,
      shi43,
      shi44,
      shi45,
      shi46,
      shi47,
      shi48,
      shi49,
      shi50,
      shi51,
      shi52,
      shi53,
      shi54,
      shi55,
      shi56,
      shi57,
      shi58,
      shi59,
      shi60,
      shi61,
      shi62,
      shi63,
      shi64,
      shi65,
      shi66,
      shi67,
      shi68,
      shi69,
      shi70
    )
  val sumImpl1: List[ShiType] = sumImpl.map(t => { case (i1, i2) if t.isDefinedAt((i1 + 1, i2)) => t(i1 + 1, i2) }: ShiType)
  val sumImpl2: List[ShiType] = sumImpl1 ::: sumImpl1.map(t => { case (i1, i2) if t.isDefinedAt((i1 + 1, i2)) => t(i1 + 1, i2) }: ShiType)
  val sumImpl3: List[ShiType] = sumImpl2 ::: sumImpl2.map(t => { case (i1, i2) if t.isDefinedAt((i1 + 1, i2)) => t(i1 + 1, i2) }: ShiType)

  val sumImpl4: List[ShiType] = sumImpl3 ::: sumImpl3.map(t => { case (i1, i2) if t.isDefinedAt((i1, i2 + 1)) => t(i1, i2 + 1) }: ShiType)
  val sumImpl5: List[ShiType] = sumImpl4 ::: sumImpl4.map(t => { case (i1, i2) if t.isDefinedAt((i1, i2 + 1)) => t(i1, i2 + 1) }: ShiType)
  val sumImpl6: List[ShiType] = sumImpl5 ::: sumImpl5.map(t => { case (i1, i2) if t.isDefinedAt((i1, i2 + 1)) => t(i1, i2 + 1) }: ShiType)

  val sumImpl7A: List[ShiType] = sumImpl6 ::: sumImpl6.map(t => { case (i1, i2) if t.isDefinedAt((i1 * 2, i2)) => t(i1 * 2, i2) }: ShiType)
  val sumImpl7B: List[ShiType] =
    sumImpl7A ::: sumImpl7A.map(t => { case (i1, i2) if t.isDefinedAt((i1 * 2, i2)) => t(i1 * 2, i2) }: ShiType)

  val sumImpl7C: List[ShiType] =
    sumImpl7B ::: sumImpl7B.map(t => { case (i1, i2) if t.isDefinedAt((i1 / 2, i2)) => t(i1 / 2, i2) }: ShiType)
  val sumImpl7D: List[ShiType] =
    sumImpl7C ::: sumImpl7C.map(t => { case (i1, i2) if t.isDefinedAt((i1 / 2, i2)) => t(i1 / 2, i2) }: ShiType)

  val sumImpl8 = sumImpl7D ::: sumImpl7D.map(t => { case (i1, i2) if t.isDefinedAt(i2, i1) => t(i2, i1) }: ShiType)

  val sumImpl9  = sumImpl8 ::: sumImpl8.map(t => { case (i1, i2) if t.isDefinedAt(i1, i2) => t(i1, i2).map(_ / 2) }: ShiType)
  val sumImpl10 = sumImpl9 ::: sumImpl9.map(t => { case (i1, i2) if t.isDefinedAt(i1, i2) => t(i1, i2).map(_ + 1) }: ShiType)
  val sumImpl11 = sumImpl10 ::: sumImpl10.map(t => { case (i1, i2) if t.isDefinedAt(i1, i2) => t(i1, i2).map(_ - 1) }: ShiType)
  val sumImpl12 = sumImpl11 ::: sumImpl11.map(t => { case (i1, i2) if t.isDefinedAt(i1, i2) => t(i1, i2).map(_ * 2) }: ShiType)

  val sum = sumImpl12 ::: sumImpl12.map(t => { case (i1, i2) if t.isDefinedAt(i2, i1) => t(i2, i1) }: ShiType)

}
