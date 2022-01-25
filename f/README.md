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

## 自然数的穷举
在上述代码中，一共有 4 种元素可以作为自然数的后继，而组成自然数部件的形式有 4 种，这样一个部件有 16 种组成形式，给定一个自然数值，生成一个自然数 Number1，一共有 256 种形式。让这其中所有的可能互相运算，一共有 65536 种运算，数量十分庞大。  
然而我们也知道，自然数的组成有很多情况重复了，零也不可能同时作为数的外部和数的内部组成一个自然数，还有很多情况 Number1 与自然数的值无关。下面，我们就开始穷举这些情况，缩小需要探索的范围。

### 特殊情况
由于有不少情况会跟这里所列的特殊情况等价，所以把这种情况独立出来。
```scala
lazy val number1s: Number1 = Number1S(() => number1s)
lazy val number1t: Number1 = Number1T(() => number1t)
lazy val number1u: Number1 = Number1U(() => number1u)
lazy val number1v: Number1 = Number1V(() => number1v)
```
这种情况的 4 种元素构造跟上述 object Fusion 的构造方式有所重复，其实是指一条射线，他的所有元素都是一样的。

### 外部为零
零即长度为 0 的部分，相当于把另一部分的首尾连在一起。
#### 1. 内部为零
不存在这样的构造
#### 2. 内部为无限、值、点
都与特殊情况等价。

### 内部为零
#### 1. 外部为零
已讨论
#### 1. 外部为无限、值、点
与特殊情况等价。

综上，只要外部或内部包含零，都与特殊情况等价，后续情况中可以不讨论。

### 外部和内部不为零，外部和内部都为同一元素
这种情况会导致无法区分外部和内部，实际效果亦与特殊情况等价。

### 外部为无限
#### 1. 内部为无限、点、有限
因为内部无法在运算中呈现，亦无法表达在实例上，所以实际情况跟特殊情况一致。

### 外部为点
#### 1. 内部为无限
射线数据结构，与数值无关
#### 2. 内部为点
两个元素交互出现，与数值无关
#### 3. 内部为值
轮状数据结构之一，与数值有关

### 外部为值
#### 1. 内部为无限
射线数据结构，与数值有关，现今加减乘除运算中必然包含这样一个数据结构
#### 2. 内部为点
轮状数据结构之一，与数值有关
#### 3. 内部为值
轮状数据结构之一，两种不同元素，长度相同的链交替出现，与两条蛇互相咬着对方的尾巴神似，与数值有关。

综上，除了外部为点和外部为值的讨论，其他情况都可以归入特殊情况讨论。有 6 种情况需要从 4 种元素种取两种不同的形成数，有 4 种情况独立，给定一个大于等于 2 的自然数，可以有 6 * 12 + 4 = 76 种可能的构造，所以即使 0 和 1 是特例，也应该归入更广泛的讨论中。两个数组成的数运算就有 76 * 76 = 5776 种可能，这样就大大缩减了可能性。

## 运算的筛选
现把这些运算取样，第一个运算数取 0 - 20，第二个运算数取 0 - 20，组成一个 441 个输入和输出的结果集，把运算看成一个黑盒，如果这 441 个输入都能产生相同的输出，则可以认为两个数运算对应同一个映射关系，只要找出其中一个映射函数，即可把这个映射函数应用在另一个数运算上。

穷尽这些运算，由 0 开始，找出 f(x1, x2) = x3, reverse1(x3, x1) = x2, reverse2(x3, x2) = x1，对应这 3 个关系成立的运算，把它称为第一阶运算，f 称为正向运算，reverse1 和 reverse2 称为逆运算。由 1 开始，找出相同的运算，把运算当成黑盒，排除由 1 开始的数据集中，黑盒上来看跟第一阶运算找不出区别的运算，称之为第二阶运算，依照相同的方式区分正向运算和逆运算。

## 数的危机
可以知道，这个体系的极限是表达有理数，它的复合运算可以在一个堆栈运算的前提下表达黑盒的负数，正有理数和负有理数，可以想象，在远古的时期，有人发明了类似的系统，找到了门生，穷尽了 5776 个运算，发现自然一切都归于整数之比，无有例外，因此歌颂并感叹自然之美。  
然后有个萌新跟你说，我这边有个数不是整数之比哦  
。。。  
全场沉默  
弄死他  
没办法，数的发展不是一朝一夕之功，当时理论水平所限，加上数学的地位，不由得人不感觉到恐慌。