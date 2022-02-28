package f09

case class Method(tail: () => Method, prefix: Boolean, suffix: Boolean, suffixTag: SuffixTag.Value) {

  def method1(method: Method): Method = {
    def body1 = if (suffix) Method(tail = tail, prefix = prefix, suffix = suffix, suffixTag = suffixTag) else tail()
    def body2 = suffixTag match {
      case SuffixTag.NUMBER1 =>
        method.method1(body1)
      case SuffixTag.NUMBER2 =>
        method.method2(body1)
      case SuffixTag.NUMBER3 =>
        method.method3(body1)
    }

    if (prefix) Method(() => body2, prefix = prefix, suffix = suffix, suffixTag = suffixTag) else body2
  }

  def method2(method: Method): Method = {
    def body1 =
      if (suffix) Method(tail = () => method, prefix = prefix, suffix = suffix, suffixTag = suffixTag) else method
    def body2 = suffixTag match {
      case SuffixTag.NUMBER1 =>
        tail().method1(body1)
      case SuffixTag.NUMBER2 =>
        tail().method2(body1)
      case SuffixTag.NUMBER3 =>
        tail().method3(body1)
    }

    if (prefix) Method(() => body2, prefix = prefix, suffix = suffix, suffixTag = suffixTag) else body2
  }

  def method3(method: Method): Method = {
    def body1 = if (suffix) tail() else method
    if (prefix) Method(tail = () => body1, prefix = prefix, suffix = suffix, suffixTag = suffixTag) else body1
  }

}

object Method {

  def nil(prefix: Boolean, prefixTag: PrefixTag.Value, suffix: Boolean, suffixTag: SuffixTag.Value): Method = {
    lazy val m: Method = Method(tail = () => m, prefix = prefix, suffix = suffix, suffixTag = suffixTag)
    m
  }

}
