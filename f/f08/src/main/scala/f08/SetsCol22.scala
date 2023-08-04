package f09

import f07._

import scala.language.implicitConversions

object cc {

  object innerUtils {
    var list: List[MapPlan]      = List.empty
    def add(expr: MapPlan): Unit = list = list.appended(expr)

    case class MapPlan(
      key: String,
      countSetKey: String,
      `i1 = 0 and i2 = 0`: String,
      `i1 gt 0 and i2 = 0x`: String,
      `i1 = 0 and i2 gt 0x`: String,
      `i1 gt 0 and i2 gt 0 and i1 = i2`: String,
      `i1 gt 0 and i2 gt 0 and i1 gt i2`: String,
      `i1 gt 0 and i2 gt 0 and i1 lt i2`: String
    )
  }

  import innerUtils._

  add(
    MapPlan(
      key = """Tags.Tag424""",
      countSetKey = """873""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 % (iii2 * 2) <= iii2) (iii1 / (iii2 * 2)) * iii2 + iii1 + 1
        else (iii1 / (iii2 * 2)) * iii2 + iii1 % (iii2 * 2) + iii1 - iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag425""",
      countSetKey = """677""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag426""",
      countSetKey = """797""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """2""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii2 % (iii1 + 1) == 0) iii2 / (iii1 + 1) + iii2 else iii2 / (iii1 + 1) + iii2 + 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % (2 * iii1) <= iii1) (iii2 + 1) % (2 * iii1) + ((iii2 + 1) / (2 * iii1)) * iii1 + 1
        else iii1 + ((iii2 + 1) / (2 * iii1)) * iii1 + 1"""
    )
  )

  add(
    MapPlan(
      key = """Tags.Tag427""",
      countSetKey = """913""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 % (iii2 * 2) <= iii2) (iii1 / (iii2 * 2)) * iii2 + iii1 % (iii2 * 2) + iii1 else (iii1 / (iii2 * 2)) * iii2 + iii1 + iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag428""",
      countSetKey = """1063""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """i2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag429""",
      countSetKey = """1001""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * iii2 + 2 * iii1 - iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii1 * iii2 + iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 * iii2 + iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag430""",
      countSetKey = """973""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """if (iii2 == 0) iii1 + 2 else if (iii1 == 0) 2 else 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * 2 + 2""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii1 + iii2 + iii1 * (iii2 / iii1) + 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii2 + iii2 * (iii1 / iii2) + 2"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag431""",
      countSetKey = """1046""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % (2 * iii1) <= iii1) iii2 + ((iii2 + 1) / (2 * iii1)) * iii1
        else (iii2 + 1) % (2 * iii1) - iii1 + iii2 + ((iii2 + 1) / (2 * iii1)) * iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag432""",
      countSetKey = """934""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0) 0 else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 + iii1 else (iii1 / iii2) * iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag433""",
      countSetKey = """1059""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 <= iii2) Option.empty else 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 / iii1 * iii1 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag434""",
      countSetKey = """1055""",
      `i1 = 0 and i2 = 0` = """Option.empty""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """Option.empty""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag435""",
      countSetKey = """724""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """2""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii2 % (iii1 + 1) == 0) iii2 / (iii1 + 1) + iii2 else iii2 / (iii1 + 1) + iii2 + 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 % (iii2 + 1) == 0) iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) else iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag436""",
      countSetKey = """1013""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag437""",
      countSetKey = """1115""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag438""",
      countSetKey = """1100""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 > iii2) Option.empty else 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii1 == 0) 1 else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 + 1 else (iii1 / iii2) * iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 > iii2) Option.empty else 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag439""",
      countSetKey = """1040""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag440""",
      countSetKey = """1020""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag441""",
      countSetKey = """966""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """
        if ((1 + 1) % iii1 == 0) ((1 + 1) / iii1) * iii1 + 2 * (1 + 1) else ((1 + 1) / iii1) * iii1 + iii1 + 2 * (1 + 1)""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if ((1 + 1) % iii1 == 0) ((1 + 1) / iii1) * iii1 + 2 * (1 + 1) else ((1 + 1) / iii1) * iii1 + iii1 + 2 * (1 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if ((1 + 1) % iii1 == 0) ((1 + 1) / iii1) * iii1 + 2 * (1 + 1) else ((1 + 1) / iii1) * iii1 + iii1 + 2 * (1 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((1 + 1) % iii1 == 0) ((1 + 1) / iii1) * iii1 + 2 * (1 + 1) else ((1 + 1) / iii1) * iii1 + iii1 + 2 * (1 + 1)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag442""",
      countSetKey = """1051""",
      `i1 = 0 and i2 = 0` = """Option.empty""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """Option.empty""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii2 == 0) Option(iii1 + 1) else Option.empty""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 <= iii2) Option.empty else 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag443""",
      countSetKey = """998""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag444""",
      countSetKey = """1104""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag445""",
      countSetKey = """981""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 - 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) Option.empty else iii1 * 2 + iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 1 else if (iii1 == 0) iii2 + 1 else if (iii2 == 0) iii1 - 1 else iii1 + iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag446""",
      countSetKey = """766""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 + iii2 / iii1 * iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag447""",
      countSetKey = """793""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii2 % (iii1 + 1) == 0) iii2 * 2 - iii2 / (iii1 + 1) + 1 else iii2 * 2 - iii2 / (iii1 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag448""",
      countSetKey = """925""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii1 % iii2 == 0) (iii1 / iii2) * iii2 + iii1 else (iii1 / iii2) * iii2 + iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 + 0 * iii1 + -1
        else 1 * iii2 + 1 * iii1 + 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag449""",
      countSetKey = """1047""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag450""",
      countSetKey = """798""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % (2 * iii1) <= iii1) (iii2 + 1) % (2 * iii1) + ((iii2 + 1) / (2 * iii1)) * iii1
        else iii1 + ((iii2 + 1) / (2 * iii1)) * iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag451""",
      countSetKey = """1057""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag452""",
      countSetKey = """910""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0) 0 else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 + 2 * iii1 else (iii1 / iii2) * iii2 + 2 * iii1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag453""",
      countSetKey = """778""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag454""",
      countSetKey = """788""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag455""",
      countSetKey = """1032""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag456""",
      countSetKey = """1079""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag457""",
      countSetKey = """974""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """if (iii1 == 1) 3 else iii1 + 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) 1
        else if (iii1 == 0) iii2 * 0 / -3 + 1
        else if (iii1 == 1) 0 * iii1 + 0 * iii2 + 3
        else if (iii2 == 0) iii1 * -2 / -1 + 1
        else if (iii2 == 1) 1 * iii1 + -2 * iii2 + 3
        else if (iii1 == iii2) -3 * iii1 + 1 * iii2 + iii2 / iii1 * iii1 * -3 / -1 + 1
        else if (iii1 > iii2) 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * -3 / -3 + 1
        else 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * 0 / -3 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 1
        else if (iii1 == 0) iii2 * 0 / -3 + 1
        else if (iii1 == 1) 0 * iii1 + 0 * iii2 + 3
        else if (iii2 == 0) iii1 * -2 / -1 + 1
        else if (iii2 == 1) 1 * iii1 + -2 * iii2 + 3
        else if (iii1 == iii2) -3 * iii1 + 1 * iii2 + iii2 / iii1 * iii1 * -3 / -1 + 1
        else if (iii1 > iii2) 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * -3 / -3 + 1
        else 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * 0 / -3 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) 1
        else if (iii1 == 0) iii2 * 0 / -3 + 1
        else if (iii1 == 1) 0 * iii1 + 0 * iii2 + 3
        else if (iii2 == 0) iii1 * -2 / -1 + 1
        else if (iii2 == 1) 1 * iii1 + -2 * iii2 + 3
        else if (iii1 == iii2) -3 * iii1 + 1 * iii2 + iii2 / iii1 * iii1 * -3 / -1 + 1
        else if (iii1 > iii2) 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * -3 / -3 + 1
        else 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * 0 / -3 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag458""",
      countSetKey = """1017""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag459""",
      countSetKey = """980""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """if (iii1 < 2) 2 else iii1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` =
        """        if (iii1 == 1 && iii2 == 0) Option.empty else if (iii1 == 1) 2 else if (iii2 == 0) Option.empty else iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` =
        """        if (iii1 == 1 && iii2 == 0) Option.empty else if (iii1 == 1) 2 else if (iii2 == 0) Option.empty else iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag460""",
      countSetKey = """783""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii2 % (iii1 + 1) == 0) iii2 * 2 - iii2 / (iii1 + 1) + 1 else iii2 * 2 - iii2 / (iii1 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag461""",
      countSetKey = """989""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """if (iii2 == 0) 0 else (iii2 * 3 - 1) / 2""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 0 else (iii1 * 3 - 1) / 2""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii2 == 0) 0 else (iii2 * 3 - 1) / 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii2 == 0) 0 else (iii2 * 3 - 1) / 2"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag462""",
      countSetKey = """1075""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """NotImplemented""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag463""",
      countSetKey = """1016""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 <= 2) 0 else iii1 - 2""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 <= 2) 0 else iii1 - 2"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag464""",
      countSetKey = """970""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """if (iii2 == 0) iii1 + 2 else if (iii1 == 0) 2 else 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if ((iii2 + 1) % iii1 == 0) ((iii2 + 1) / iii1) * iii1 + 2 * (iii2 + 1) else ((iii2 + 1) / iii1) * iii1 + iii1 + 2 * (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii1 + iii2 * 2 + 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % iii1 == 0) ((iii2 + 1) / iii1) * iii1 + 2 * (iii2 + 1) else ((iii2 + 1) / iii1) * iii1 + iii1 + 2 * (iii2 + 1)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag465""",
      countSetKey = """985""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii1 * (iii2 / iii1)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag466""",
      countSetKey = """1060""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 / iii1 * iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag467""",
      countSetKey = """984""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 + 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 1
        else if (iii1 == 0) iii2 * 0 / -3 + 1
        else if (iii1 == 1) 0 * iii1 + 0 * iii2 + 3
        else if (iii2 == 0) iii1 * -2 / -1 + 1
        else if (iii2 == 1) 1 * iii1 + -2 * iii2 + 3
        else if (iii1 == iii2) -iii1 * 3 + iii2 + iii2 / iii1 * iii1 * 3 + 1
        else if (iii1 > iii2) 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 + 1
        else 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * 0 / -3 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii1 * (iii2 / iii1) + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag468""",
      countSetKey = """721""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0) Option.empty else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 + iii1 - 1 else (iii1 / iii2) * iii2 + iii1 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 % (iii2 + 1) == 0) iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) else iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag469""",
      countSetKey = """1002""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 0 else (iii1 - 1) * (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag470""",
      countSetKey = """674""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0) Option.empty
        else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 + 2 * iii1 - 1
        else (iii1 / iii2) * iii2 + 2 * iii1 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag471""",
      countSetKey = """718""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 % (iii2 + 1) == 0) iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) else iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag472""",
      countSetKey = """773""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 - 1""",
      `i1 = 0 and i2 gt 0x` = """Option.empty""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii2 == 0) Option(iii1 + 1) else Option.empty""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 > iii2) Option.empty else 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 <= iii2) Option.empty else 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag473""",
      countSetKey = """794""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """2""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii2 + 1 - iii1 >= 0) iii2 + 1 + iii1 else iii2 * 2 + 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % (iii1 * 2) <= iii1) ((iii2 + 1) / (iii1 * 2)) * iii1 + (iii2 + 1) % (iii1 * 2) + iii2 + 1
        else ((iii2 + 1) / (iii1 * 2)) * iii1 + iii2 + 1 + iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag474""",
      countSetKey = """975""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii2 == 0) 0 else iii2 * 2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii2 + iii1 * (iii2 / iii1)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag475""",
      countSetKey = """772""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 - 1""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 1 else if (iii1 == 0) iii2 + 1 else if (iii2 == 0) iii1 - 1 else iii1 + iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) 1 else if (iii1 == 0) iii2 + 1 else if (iii2 == 0) iii1 - 1 else iii1 + iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag476""",
      countSetKey = """711""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """NotImplemented""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 1
        else if (iii1 == 0) iii2 * 0 / -3 + 1
        else if (iii1 == 1) 0 * iii1 + 0 * iii2 + 3
        else if (iii2 == 0) iii1 * -2 / -1 + 1
        else if (iii2 == 1) 1 * iii1 + -2 * iii2 + 3
        else if (iii1 == iii2) -3 * iii1 + 1 * iii2 + iii2 / iii1 * iii1 * -3 / -1 + 1
        else if (iii1 > iii2) 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * -3 / -3 + 1
        else 1 * iii1 + 0 * iii2 + iii2 / iii1 * iii1 * 0 / -3 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag477""",
      countSetKey = """843""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 % (iii2 * 2) <= iii2) (iii1 / (iii2 * 2)) * iii2 + iii1 % (iii2 * 2) + iii1 + 1
        else (iii1 / (iii2 * 2)) * iii2 + iii1 + iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 - iii2 > 0) iii2 * 2 + 1 else iii1 * 2 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag478""",
      countSetKey = """1125""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 % (iii2 * 2) <= iii2) (iii1 / (iii2 * 2)) * iii2 else (iii1 / (iii2 * 2)) * iii2 + iii1 % (iii2 * 2) - iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag479""",
      countSetKey = """764""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else iii1 * 2 + iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 - iii2 > 0) iii2 * 2 + 1 else iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 * (iii2 / iii1) + 2 * iii2 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag480""",
      countSetKey = """1048""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) iii2 * 2 + 2 else 2""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 <= iii2) Option.empty else 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % (2 * iii1) <= iii1) ((iii2 + 1) / (2 * iii1)) * iii1 + 1
        else (iii2 + 1) % (2 * iii1) - iii1 + ((iii2 + 1) / (2 * iii1)) * iii1 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag481""",
      countSetKey = """784""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """Option.empty""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii2 == 0) Option(iii1 + 1) else Option.empty""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 > iii2) Option.empty else 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 <= iii2) Option.empty else 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag482""",
      countSetKey = """1117""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 % (iii2 * 2) <= iii2) (iii1 / (iii2 * 2)) * iii2 + iii1 % (iii2 * 2) else (iii1 / (iii2 * 2)) * iii2 + iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag483""",
      countSetKey = """1029""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """i2 * 2""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii2 - iii1 >= 0) iii2 * 2 - iii1 else iii2"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag484""",
      countSetKey = """1022""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """Option.empty""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag485""",
      countSetKey = """932""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 % (iii2 * 2) <= iii2) (iii1 / (iii2 * 2)) * iii2 + iii1 else (iii1 / (iii2 * 2)) * iii2 + iii1 % (iii2 * 2) + iii1 - iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag486""",
      countSetKey = """1061""",
      `i1 = 0 and i2 = 0` = """2""",
      `i1 gt 0 and i2 = 0x` = """NotImplemented""",
      `i1 = 0 and i2 gt 0x` = """if (iii2 == 0) Option.empty else if (iii1 == 0) 2 else if (iii1 == 1) 2 else 3""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag487""",
      countSetKey = """983""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 - 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 == 0) Option.empty else iii1 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag488""",
      countSetKey = """768""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 * (iii2 / iii1) + iii2 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag489""",
      countSetKey = """1015""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """NotImplemented""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag490""",
      countSetKey = """1044""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) Option(1)
        else if (iii1 == 0) Option(0 * iii2 + 1)
        else if (iii2 == 0) Option.empty
        else Option(1 * iii1 + 0 * iii2 + 0)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag491""",
      countSetKey = """695""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) Option.empty else iii1 * 2 + iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0) Option.empty
        else if (iii1 % iii2 == 0) (iii1 / iii2) * iii2 + 2 * iii1 - 1
        else (iii1 / iii2) * iii2 + iii2 + 2 * iii1 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == 0) Option.empty else iii1 * 2 + iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag492""",
      countSetKey = """675""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 - 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 1 else if (iii1 == 0) iii2 + 1 else if (iii2 == 0) iii1 - 1 else iii1 + iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii2 == 0) 1 * iii1 + 0
        else if (iii1 == iii2) 0 * iii1 + 2 * iii2 + -1
        else if (iii1 < iii2) 2 * iii1 + 0 * iii2 + -1
        else 1 * iii1 + 1 * iii2 + 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag493""",
      countSetKey = """795""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` =
        """        if (iii1 % (iii2 + 1) == 0) iii1 * 2 - iii1 / (iii2 + 1) + 1 else iii1 * 2 - iii1 / (iii2 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 - iii2 > 0) iii2 * 2 + 1 else iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % (2 * iii1) <= iii1) (iii2 + 1) % (2 * iii1) + iii2 + ((iii2 + 1) / (2 * iii1)) * iii1
        else iii1 + iii2 + ((iii2 + 1) / (2 * iii1)) * iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag494""",
      countSetKey = """1081""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 % (iii2 * 2) <= iii2) (iii1 / (iii2 * 2)) * iii2 + iii1 % (iii2 * 2) + 1 else (iii1 / (iii2 * 2)) * iii2 + iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag495""",
      countSetKey = """968""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + iii2 * 2 + iii1 * (iii2 / iii1)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii2 == 0) 0 else iii2 * 2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii2 * 2 + iii1 * (iii2 / iii1)"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag496""",
      countSetKey = """1049""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 > iii2) Option.empty else 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if ((iii2 + 1) % (2 * iii1) <= iii1) ((iii2 + 1) / (2 * iii1)) * iii1
        else (iii2 + 1) % (2 * iii1) - iii1 + ((iii2 + 1) / (2 * iii1)) * iii1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag497""",
      countSetKey = """712""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """NotImplemented""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """        if (iii2 == 0) Option.empty else if (iii1 == 0) 2 else if (iii1 == 1) 2 else 3""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag498""",
      countSetKey = """855""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else iii1 * 2 + iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii1 % iii2 == 0) (iii1 / iii2) * iii2 + 2 * iii1 + 1 else (iii1 / iii2) * iii2 + iii2 + 2 * iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == 0) 1 else iii1 * 2 + iii2 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag499""",
      countSetKey = """707""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """NotImplemented""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag500""",
      countSetKey = """971""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 + 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + iii2 * 2 + iii1 * (iii2 / iii1) + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii2 == 0) 1 else iii2 * 2 + iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii2 * 2 + iii1 * (iii2 / iii1) + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag501""",
      countSetKey = """791""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """if (iii1 <= 1) 3 else 4""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 <= 1) 3 else 4""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """4""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 <= 1) 3 else 4"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag502""",
      countSetKey = """717""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """if (iii1 >= 1) iii1 * 2 - 1 else iii1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == iii2 + 1) iii1 else iii1 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag503""",
      countSetKey = """840""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0) 1 else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 + 2 * iii1 + 1 else (iii1 / iii2) * iii2 + 2 * iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 - iii2 > 0) iii2 * 2 + 1 else iii1 * 2 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag504""",
      countSetKey = """1085""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii1 % iii2 == 0) (iii1 / iii2) * iii2 + 1 else (iii1 / iii2) * iii2 + iii2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii2 == 0 && iii1 == 0) 1
        else if (iii2 == 0) iii1 * 0 / -3 + 1
        else if (iii2 == 1) 0 * iii2 + 0 * iii1 + 3
        else if (iii1 == 0) iii2 * -2 / -1 + 1
        else if (iii1 == 1) 1 * iii2 + -2 * iii1 + 3
        else if (iii2 == iii1) -3 * iii2 + 1 * iii1 + iii1 / iii2 * iii2 * -3 / -1 + 1
        else if (iii2 > iii1) 1 * iii2 + 0 * iii1 + iii1 / iii2 * iii2 * -3 / -3 + 1
        else 1 * iii2 + 0 * iii1 + iii1 / iii2 * iii2 * 0 / -3 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag505""",
      countSetKey = """1023""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0"""
    )
  )

  add(
    MapPlan(
      key = """Tags.Tag506""",
      countSetKey = """1052""",
      `i1 = 0 and i2 = 0` = """Option.empty""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """Option.empty""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 - 1"""
    )
  )

  add(
    MapPlan(
      key = """Tags.Tag507""",
      countSetKey = """978""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """iii1 - 1""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) iii2 * 0 / -3 + 0
        else if (iii1 == 1) -3 * iii1 + 1 * iii2 + 4
        else if (iii2 == 0) iii1 * -2 / -1 + -1
        else if (iii2 == 1) 3 * iii1 + -3 * iii2 + 2
        else if (iii1 == iii2) -1 * iii1 + 3 * iii2 + iii1 * iii2 * -3 / -3 + -1
        else if (iii1 > iii2) 2 * iii1 + 0 * iii2 + iii1 * iii2 * -3 / -3 + -1
        else 2 * iii1 + 0 * iii2 + iii1 * iii2 * -3 / -3 + -1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag508""",
      countSetKey = """1008""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 % (iii2 + 1) == 0) iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) else iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag509""",
      countSetKey = """876""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0) 1 else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 + iii1 + 1 else (iii1 / iii2) * iii2 + iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag510""",
      countSetKey = """859""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 * 2 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii1 % iii2 == 0) (iii1 / iii2) * iii2 + iii1 + 1 else (iii1 / iii2) * iii2 + iii2 + iii1 + 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii2 + 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag511""",
      countSetKey = """1084""",
      `i1 = 0 and i2 = 0` = """1""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag512""",
      countSetKey = """976""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """if (iii2 == 0) iii1 + 2 else if (iii1 == 0) 2 else 1""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + iii2 + iii1 * (iii2 / iii1) + 2""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """iii1 + iii2 + iii1 * (iii2 / iii1) + 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """iii1 + iii2 + iii1 * (iii2 / iii1) + 2"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag513""",
      countSetKey = """1126""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii1 == 0) 0 else if (iii1 % iii2 == 0) (iii1 / iii2 - 1) * iii2 else (iii1 / iii2) * iii2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii1 == 0) 1 else if (iii2 == 0) iii1 + 1 else 0"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag514""",
      countSetKey = """1010""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """NotImplemented""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 % (iii2 + 1) == 0) iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) else iii1 / (iii2 + 1) * iii2 + iii1 % (iii2 + 1) - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag515""",
      countSetKey = """771""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii2 % (iii1 + 1) == 0) iii2 * 2 - iii2 / (iii1 + 1) + 1 else iii2 * 2 - iii2 / (iii1 + 1)""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) 1 else if (iii1 == 0) iii2 + 1 else if (iii2 == 0) iii1 - 1 else iii1 + iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag516""",
      countSetKey = """699""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` =
        """        if (iii1 % iii2 == 0) (iii1 / iii2) * iii2 + iii1 - 1 else (iii1 / iii2) * iii2 + iii2 + iii1 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii1 == 0 && iii2 == 0) 1 else if (iii1 == 0) iii2 + 1 else if (iii2 == 0) iii1 - 1 else iii1 + iii2 - 1"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag517""",
      countSetKey = """652""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """iii2 + 3""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """if (iii1 == 0) Option.empty else Option(iii2 + 3)""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii1 == 0 && iii2 == 0) 0
        else if (iii1 == 0) 0 * iii2 + 0
        else if (iii1 == 1) 0 * iii1 + 2 * iii2 + 3
        else if (iii2 == 0) 0 * iii1 + 3
        else if (iii2 == 1) 0 * iii1 + 1 * iii2 + 3
        else if (iii1 == iii2) -1 * iii1 + 2 * iii2 + iii2 / iii1 * -2 / -2 + 3
        else if (iii1 > iii2) 0 * iii1 + 1 * iii2 + iii2 / iii1 * -2 / -2 + 3
        else 0 * iii1 + 1 * iii2 + iii2 / iii1 * -2 / -2 + 3""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """if (iii2 == 1) 5 else iii2 + 3"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag518""",
      countSetKey = """995""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """0""",
      `i1 = 0 and i2 gt 0x` = """iii2 - 1""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """
        if (iii2 == 0 && iii1 == 0) 0
        else if (iii2 == 0) 0 * iii1 + 0
        else if (iii1 == 0) 1 * iii2 + 0
        else if (iii2 == iii1) 0 * iii2 + 2 * iii1 + -1
        else if (iii2 < iii1) 2 * iii2 - 1
        else iii2 + iii1""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """NotImplemented"""
    )
  )
  add(
    MapPlan(
      key = """Tags.Tag519""",
      countSetKey = """763""",
      `i1 = 0 and i2 = 0` = """0""",
      `i1 gt 0 and i2 = 0x` = """2""",
      `i1 = 0 and i2 gt 0x` = """0""",
      `i1 gt 0 and i2 gt 0 and i1 = i2` = """iii1 + iii2 + iii1 * (iii2 / iii1) + 2""",
      `i1 gt 0 and i2 gt 0 and i1 gt i2` = """if (iii2 + 1 - iii1 >= 0) iii2 + 1 + iii1 else iii2 * 2 + 2""",
      `i1 gt 0 and i2 gt 0 and i1 lt i2` = """
        if (iii2 + 1 == 0) 0
        else if ((iii2 + 1) % iii1 == 0) ((iii2 + 1) / iii1 - 1) * iii1 + 2 * (iii2 + 1)
        else ((iii2 + 1) / iii1) * iii1 + 2 * iii2 + 2"""
    )
  )
}
