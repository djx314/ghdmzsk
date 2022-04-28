package i01_01

trait Number1[T1, T2]
case class Number1S[T1, T2](text: T1, tail: Number1[T1, T2], head: Number2[T1, T2]) extends Number1[T1, T2]
case class Number1T[T1, T2]()                                                       extends Number1[T1, T2]

trait Number2[T1, T2]
case class Number2S[T1, T2](tail: Number2[T1, T2], head: Number1[T1, T2]) extends Number2[T1, T2]
case class Number2T[T1, T2](text: T2)                                     extends Number2[T1, T2]
