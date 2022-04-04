package a04

trait Number1

case class Number1S(tail: Number2) extends Number1
case object Number1T               extends Number1

trait Number2

case class Number2S(tail1: Number1, tail2: Number1 => Number2, var tail3: Number2) extends Number2
case object Number2T                                                               extends Number2
