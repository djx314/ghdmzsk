package a04

trait Runner {

  def number3(zero: Number2): Number2 = {
    lazy val n1Zero: Number2 = Number2T(null)
    lazy val n1: Number2     = number3(n1Zero)

    lazy val n2Zero: Number2 = Number2T(null)
    lazy val n2: Number2     = number3(n2Zero)

    lazy val n3Zero: Number2 = Number2T(null)
    lazy val n3: Number2     = number3(n3Zero)

    Number2S(Number1S(Number2S(Number1S(Number2S(Number1S(zero), n1Zero, () => n1)), n2Zero, () => n2)), n3Zero, () => n3)
  }

  lazy val m1: Number2     = Number2S(Number1S(m1Zero), m2Zero, () => m2)
  lazy val m1Zero: Number2 = Number2T(null)

  lazy val m2: Number2     = Number2S(Number1S(m2Zero), m3Zero, () => m3)
  lazy val m2Zero: Number2 = Number2T(null)

  lazy val m3: Number2     = number3(m3Zero)
  lazy val m3Zero: Number2 = Number2T(null)

}
