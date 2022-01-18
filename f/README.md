# 自然数代码模型概述
## 前置概念
运算，指的是在以下代码中，一个 Number1 的实例使用 method1 函数调用另一个 Number1 实例。所有的运算必然会使程序抛出栈溢出异常，使用特殊的方法，可以统计这些运算的结果，但目前无法在理论上证明此结果即精确值，只能认为给予的堆栈足够大，无论堆栈如何增长，对结果的估算也不会产生影响。
```scala
trait Number1 {
  def method1(number1: Number1): Number2
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = Number2S(() => tail().method1(number1))
}
case class Number1T(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = tail().method1(number1)
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = Number2S(() => number1.method1(tail()))
}
case class Number1V(tail: () => Number1) extends Number1 {
  override def method1(number1: Number1): Number2 = number1.method1(tail())
}

sealed trait Number2
case class Number2S(tail: () => Number2) extends Number2
```
```scala
object Counter {
  def count(number2: () => Number2): Int = {
    val value =
      try Option(number2())
      catch {
        case _: StackOverflowError => Option.empty
      }
    value match {
      case Some(Number2S(tail)) => count(tail) + 1
      case None                 => 0
    }
  }

  def countOpt(number2: () => Number2): Option[Int] = {
    try Option(count(number2)).filter(_ < 500)
    catch {
      case _: StackOverflowError => Option.empty
    }
  }
}
```
如下，则实现了一个简单的自然数加法，符合特定约束的 Number1 实例组成的运算被称为数运算，本文旨在穷举这些数运算的可能情况，并分析他们的特征。
```scala
import Fusion._
for {
  i1 <- 0 to 20
  i2 <- 0 to 20
} {
  val number1 = number1sGen(i1, number1v)
  val number2 = number1sGen(i2, number1v)
  def number3 = number1.method1(number2)
  val count1  = count(() => number3)
  assert(count1 == i1 + i2)
}
```
以下是一些常用的构造数实例的方法，部分已经在上面的自然数加法中被使用了。
```scala
object Fusion {

  lazy val number1s: Number1 = Number1S(() => number1s)
  lazy val number1t: Number1 = Number1T(() => number1t)
  lazy val number1u: Number1 = Number1U(() => number1u)
  lazy val number1v: Number1 = Number1V(() => number1v)

  def number1sGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1sGen(n - 1, zero)) else zero
  def number1tGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => number1tGen(n - 1, zero)) else zero
  def number1uGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1U(() => number1uGen(n - 1, zero)) else zero
  def number1vGen(n: Int, zero: => Number1): Number1 = if (n > 0) Number1V(() => number1vGen(n - 1, zero)) else zero

}
```
