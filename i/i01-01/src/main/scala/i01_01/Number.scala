package i01_01


trait Number1[T1, T2]   {
   def method1(number2: Number2[T1, T2]): String
}
case class Number1S[T1, T2](text: T1, tail: Number1[T1, T2], head: Number2[T1, T2]) extends Number1[T1, T2]{
  override def method1(number2: Number2[T1, T2]): String
}
case class Number1T[T1, T2]() extends Number1[T1, T2]{
  override def method1(number2: Number2[T1, T2]): String = ""
}

trait Number2[T1, T2]  {
   def method2(number1: Number1[T1, T2]): String
}
case class Number2S[T1, T2](tail: Number2[T1, T2], head: Number1[T1, T2]) extends Number2[T1, T2] {
  override def method2(number1: Number1[T1, T2]): String
}
case class Number2T[T1, T2](text: T2) extends Number2[T1, T2] {
  override def method2(number1: Number1[T1, T2]): String
}

