package a01_01

object Runner {

  def main(args: Array[String]): Unit = {
    {
      val str1    = "rjfgkoser ti0er jteosrj t90 j034jmq3o-24 k5'pwojmt ioh45n6ti9u j45hn6y75n46b 4ejiko4b6y45u94596 h4"
      val list1   = str1.to(List)
      val number1 = Base.numberFromList(list1)
      val number2 = number1.method1(Number1T)
      val list2   = Base.listToNumber(number2)
      val list3   = Compare.reverseList(list1)
      assert(list1.reverse == list2)
      assert(list1.reverse == list3)
    }
  }

}
