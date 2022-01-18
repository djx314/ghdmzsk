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

## 自然数的定义
究竟什么样的 Number1 才符合条件成为一个数呢？
### 数的部件
在旧加法中，数分为正数部分和零部分，而零部分是一个单例，正数部分由若干后继组成，但是在目前的加法中，零部分被前驱仍然是它本身的一个特殊的对象代替。加数和被加数都可以看成是对象先呈现出有限次数的一种元素的后继，再呈现出无限次数的另一种元素的后继。在这里，优先呈现的元素种类统一的部分被称为外部，外部呈现完毕后所呈现的元素种类统一的部分称为内部。  
在这里有限和无限形成了不可或缺的元素。  
在旧乘除和新乘除运算中，都出现了一个称为轮状结构的数据结构，正数部分和零部分交替出现，零部分永远为长度为 1 的特定元素后继。  
这样的结构，构成了一和多的对立面。  
在这里，一共有 4 个概念，有限、无限、一、多，如果合并有限和多，再添加一个零的概念，把原来的一更名为点，就会组成新的 4 个概念，零，无限，点，值。其中，值这个概念是多和有限合并后的概念，如果一个数中包含一个称为值的部分，那值的后继数量就是这个自然数的值，这也正是这个命名的缘由。
### 广义轮
上述问题中，加数和被加数的结构类似于射线，是有起始无终点的结构，而轮状结构却是点和值部分循环出现的结构。那有没有办法把这两种结构统一呢？其实是有的，我们可以把无限看成是终点连着数的外部的起点的一种特殊结构，只不过它的终点在无限远处，无法表示，所以才表现为类似射线的结构，那么所有的结构都可以视为一个轮。
### 定义
数的定义，就是给定一个自然数，从零、无限、点、值这 4 个部件中选出可重复的两个部件，一个作为外部，一个作为内部，组成一个互相的终点指向对方的开端的特殊结构。  
在思维上，这种构型类似于两条蛇，互相咬着对方的尾巴，组成一个圆圈。