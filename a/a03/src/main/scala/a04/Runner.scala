package a04

object Runner {

  lazy val number1: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number1Zero, () => number_2_1), () => number_2_2), () => number_2_3)
  lazy val number1Zero: Number1 = Number1U(() => number_2_3, () => number1)

  lazy val number_2_1: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number_2_1_Zero, () => number_3_1_1), () => number_3_1_2), () => number_3_1_3)
  lazy val number_2_1_Zero: Number1 = Number1U(() => number_3_1_1, () => number_2_1)

  lazy val number_2_2: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number_2_2_Zero, () => number_3_2_1), () => number_3_2_2), () => number_3_2_3)
  lazy val number_2_2_Zero: Number1 = Number1U(() => number_2_1, () => number_2_2)

  lazy val number_2_3: Number1 =
    Number1S(() => Number1S(() => Number1S(() => number_2_3_Zero, () => number_3_3_1), () => number_3_3_2), () => number_3_3_3)
  lazy val number_2_3_Zero: Number1 = Number1U(() => number_2_2, () => number_2_3)

  lazy val number_3_1_1: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_1_1_Zero, () => number_3_1_1_Each), () => number_3_1_1_Each),
      () => number_3_1_1_Each
    )
  lazy val number_3_1_1_Zero: Number1 = Number1U(() => number_2_2 /* */, () => number_3_1_1)
  lazy val number_3_1_1_Each: Number1 = Number1T(() => number_3_1_1)

  lazy val number_3_1_2: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_1_2_Zero, () => number_3_1_2_Each), () => number_3_1_2_Each),
      () => number_3_1_2_Each
    )
  lazy val number_3_1_2_Zero: Number1 = Number1U(() => number_3_1_1, () => number_3_1_2)
  lazy val number_3_1_2_Each: Number1 = Number1T(() => number_3_1_2)

  lazy val number_3_1_3: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_1_3_Zero, () => number_3_1_3_Each), () => number_3_1_3_Each),
      () => number_3_1_3_Each
    )
  lazy val number_3_1_3_Zero: Number1 = Number1U(() => number_3_1_2, () => number_3_1_3)
  lazy val number_3_1_3_Each: Number1 = Number1T(() => number_3_1_3)

  lazy val number_3_2_1: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_2_1_Zero, () => number_3_2_1_Each), () => number_3_2_1_Each),
      () => number_3_2_1_Each
    )
  lazy val number_3_2_1_Zero: Number1 = Number1U(() => number_3_1_3, () => number_3_2_1)
  lazy val number_3_2_1_Each: Number1 = Number1T(() => number_3_2_1)

  lazy val number_3_2_2: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_2_2_Zero, () => number_3_2_2_Each), () => number_3_2_2_Each),
      () => number_3_2_2_Each
    )
  lazy val number_3_2_2_Zero: Number1 = Number1U(() => number_3_2_1, () => number_3_2_2)
  lazy val number_3_2_2_Each: Number1 = Number1T(() => number_3_2_2)

  lazy val number_3_2_3: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_2_3_Zero, () => number_3_2_3_Each), () => number_3_2_3_Each),
      () => number_3_2_3_Each
    )
  lazy val number_3_2_3_Zero: Number1 = Number1U(() => number_3_2_2, () => number_3_2_3)
  lazy val number_3_2_3_Each: Number1 = Number1T(() => number_3_2_3)

  lazy val number_3_3_1: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_3_1_Zero, () => number_3_3_1_Each), () => number_3_3_1_Each),
      () => number_3_3_1_Each
    )
  lazy val number_3_3_1_Zero: Number1 = Number1U(() => number_3_2_3, () => number_3_3_1)
  lazy val number_3_3_1_Each: Number1 = Number1T(() => number_3_3_1)

  lazy val number_3_3_2: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_3_2_Zero, () => number_3_3_2_Each), () => number_3_3_2_Each),
      () => number_3_3_2_Each
    )
  lazy val number_3_3_2_Zero: Number1 = Number1U(() => number_3_3_1, () => number_3_3_2)
  lazy val number_3_3_2_Each: Number1 = Number1T(() => number_3_3_2)

  lazy val number_3_3_3: Number1 =
    Number1S(
      () => Number1S(() => Number1S(() => number_3_3_3_Zero, () => number_3_3_3_Each), () => number_3_3_3_Each),
      () => number_3_3_3_Each
    )
  lazy val number_3_3_3_Zero: Number1 = Number1U(() => number_3_3_2, () => number_3_3_3)
  lazy val number_3_3_3_Each: Number1 = Number1T(() => number_3_3_3)

}
