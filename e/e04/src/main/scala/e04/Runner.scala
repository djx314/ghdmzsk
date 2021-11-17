package e04

import e01._

object Runner {
  def numberFromInt[T](n: Int, item: T,zero: => Number[T]): Number[T] = {
    def gen(n1: Int, zero: => Number[T]): Number[T] = if (n1 > 0) NumberS(() => gen(n1 - 1, zero), item) else zero
    lazy val num1: Number[T]                        = gen(n, num2)
    lazy val num2: NumberT[T]                       = NumberT(() => num1)
    (num1, num2)
    if (n > 0) NumberS(() => numberFromInt(n - 1,item, zero), item) else zero
  }

  def countNumber[T](num: Collect[T]): Int = num match {
    case CollectS(tail, head) => countNumber(tail) + 1
    case CollectT()           => 0
  }

  def main(args: Array[String]): Unit = {
    def numFromDi(di: Int): Collect[Item4] = {
      lazy val col11 :Number[Item1]= numberFromInt(di, new Item1,col12)
      lazy val col12 :Number[Item1]= NumberT(() => col11)

      lazy val col21 :Number[Item2]= numberFromInt(di, new Item2,col22)
      lazy val col22 :Number[Item2]= NumberT(() => col21)

      lazy val col31 :Number[Item3]= numberFromInt(di, new Item3,col32)
      lazy val col32 :Number[Item3]= NumberT(() => col31)

      lazy val col41 :Number[Item4]= numberFromInt(di, new Item4,col42)
      lazy val col42 :Number[Item4]= NumberT(() => col41)

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
