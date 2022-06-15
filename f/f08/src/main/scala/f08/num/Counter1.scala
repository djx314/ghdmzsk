package f08.num

case class EndException() extends Exception

trait Number1 {
  def method(num: () => Number1): () => Number1
}

case object Number1S extends Number1 {
  override def method(num: () => Number1): () => Number1 = () => throw EndException()
}

case object Number1T extends Number1 {
  override def method(num: () => Number1): () => Number1 = () => Number1Y(() => throw EndException())
}

case object Number1U extends Number1 {
  override def method(num: () => Number1): () => Number1 = () => Number1S
}

case object Number1V extends Number1 {
  override def method(num: () => Number1): () => Number1 = num
}

case object Number1W extends Number1 {
  override def method(num: () => Number1): () => Number1 = () => Number1Y(() => Number1S)
}

case object Number1X extends Number1 {
  override def method(num: () => Number1): () => Number1 = () => Number1Y(num)
}

case class Number1Y(tail: () => Number1) extends Number1 {
  override def method(num: () => Number1): () => Number1 = tail().method(num)
}

case class Number1Z(tail: () => Number1) extends Number1 {
  override def method(num: () => Number1): () => Number1 = num().method(tail)
}

case class Number1A(tail: () => Number1) extends Number1 {
  override def method(num: () => Number1): () => Number1 = () => Number1Y(tail().method(num))
}

case class Number1B(tail: () => Number1) extends Number1 {
  override def method(num: () => Number1): () => Number1 = () => Number1Y(num().method(tail))
}
