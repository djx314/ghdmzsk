package f07

object F extends SetsColAbs {

  {
    // 正
    Tags.Tag007.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + i2)
    Tags.Tag030.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 + i2)
    Tags.Tag119.firstart(1).secondStart(0).value((i1: Int, i2: Int) => i1 + i2)

    // 反
    Tags.Tag002.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 - i2 else 0)
    Tags.Tag226.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 - i1 >= 0) i2 - i1 else 0)
  }

  {
    // 正
    Tags.Tag084.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 * i2)

    // 反
    Tags.Tag045
      .firstart(0)
      .secondStart(0)
      .value((i1: Int, i2: Int) =>
        if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2) else Option(i1 / i2 + 1)
      )
    Tags.Tag046.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 / i1))
  }

  {
    // 正
    Tags.Tag067.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 * i2 + i1 + i2)

    // 反
    Tags.Tag040.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 / (i2 + 1))
  }

}
