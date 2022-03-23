package a05

trait Number1
case class Number1S(tail: () => Number1, tail2: () => Number1) extends Number1
case class Number1T(tail: () => Number1)                       extends Number1
case class Number1U(tail: () => Number1, tail2: () => Number1) extends Number1
