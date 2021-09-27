package e04

import e01._

object Runner {
  def numberFromInt[T](n: Int, item: T): (Number[T], NumberT[T]) = {
    def gen(n1: Int, zero: => Number[T]): Number[T] = n1 match {
      case n2 if n2 > 0 => NumberS(() => gen(n2 - 1, zero), item)
      case 0            => zero
    }
    lazy val num1: Number[T]  = gen(n, num2)
    lazy val num2: NumberT[T] = NumberT(() => num1)
    (num1, num2)
  }

  def countNumber[T](num: Collect[T]): Int = num match {
    case CollectS(tail, head) => countNumber(tail) + 1
    case CollectT()           => 0
  }

  def main(args: Array[String]): Unit = {
    def numFromDi(di: Int): Collect[Item4] = {
      val (col11, col12) = numberFromInt(di, new Item1)
      val (col21, col22) = numberFromInt(di, new Item2)
      val (col31, col32) = numberFromInt(di, new Item3)
      val (col41, col42) = numberFromInt(di, new Item4)
      col11.execute(new HList1Context)((), HPositive(col21, HPositive(col31, HPositive(col41, HNil()))))
    }
    {
      val di   = 1
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
    {
      val di   = 3
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
    {
      val di   = 4
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
    {
      val di   = 5
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
    {
      val di   = 6
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
    {
      val di   = 7
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
    {
      val di   = 8
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
    {
      val di   = 9
      val res1 = countNumber(numFromDi(di))
      val res2 = math.pow(di, 4).toInt
      assert(res1 == res2)
    }
  }
}
