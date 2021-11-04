package a02_01

object Runner {

  def main(args: Array[String]): Unit = {
    {
      val str1    = "rjfgkoser ti0er jteosrj t90 j034jmq3o-24 k5'pwojmt ioh45n6ti9u j45hn6y75n46b 4ejiko4b6y45u94596 h4"
      val list1   = str1.to(List)
      val number1 = Base.number1FromList(list1)
      val number2 = Base.number2FromItem('4')
      val number3 = number1.method1(number2)
      val list2   = Base.listToNumber(number3)
      val list3   = Compare.dropFirstSameItem(list1, '4')
      assert(list2 == list3)
    }
  }

}
