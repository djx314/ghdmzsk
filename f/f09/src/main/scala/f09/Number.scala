package f09

sealed trait Number {
  def method(number: Number): Number
}

case class Number1(tail: () => Number, prefix: Boolean, prefixTag: PrefixTag.Value, suffix: Boolean, suffixTag: SuffixTag.Value)
    extends Number {

  override def method(number: Number): Number = {
    def body = if (suffix)
      suffixTag match {
        case SuffixTag.NUMBER1 =>
          number.method(Number1(tail = tail, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag))
        case SuffixTag.NUMBER2 =>
          number.method(Number2(tail = tail, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag))
        case SuffixTag.NUMBER3 =>
          number.method(Number3(prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag))
      }
    else
      number.method(tail())

    if (prefix)
      prefixTag match {
        case PrefixTag.NUMBER1 =>
          Number1(() => body, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
        case PrefixTag.NUMBER2 =>
          Number2(() => body, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
        case PrefixTag.NUMBER3 =>
          Number3(prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
      }
    else
      body
  }

}

case class Number2(tail: () => Number, prefix: Boolean, prefixTag: PrefixTag.Value, suffix: Boolean, suffixTag: SuffixTag.Value)
    extends Number {

  override def method(number: Number): Number = {
    def body = if (suffix)
      suffixTag match {
        case SuffixTag.NUMBER1 =>
          tail().method(Number1(tail = () => number, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag))
        case SuffixTag.NUMBER2 =>
          tail().method(Number2(tail = () => number, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag))
        case SuffixTag.NUMBER3 =>
          tail().method(Number3(prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag))
      }
    else
      number.method(tail())

    if (prefix)
      prefixTag match {
        case PrefixTag.NUMBER1 =>
          Number1(() => body, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
        case PrefixTag.NUMBER2 =>
          Number2(() => body, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
        case PrefixTag.NUMBER3 =>
          Number3(prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
      }
    else
      body
  }

}

case class Number3(prefix: Boolean, prefixTag: PrefixTag.Value, suffix: Boolean, suffixTag: SuffixTag.Value) extends Number {

  override def method(number: Number): Number = if (prefix)
    prefixTag match {
      case PrefixTag.NUMBER1 =>
        Number1(() => number, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
      case PrefixTag.NUMBER2 =>
        Number2(() => number, prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
      case PrefixTag.NUMBER3 =>
        Number3(prefix = prefix, prefixTag = prefixTag, suffix = suffix, suffixTag = suffixTag)
    }
  else
    number

}
