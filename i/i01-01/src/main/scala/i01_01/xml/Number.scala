package i01_01.xml

trait Number1 {
  def method1(number2: Number2): String
}
/*trait Number1S extends Number1 {
  val tail: Number1
  val head: Number2
  override def method1(number2: Number2): String
}
trait Number1T extends Number1 {
  override def method1(number2: Number2): String
}*/

trait Number2 {
  def method2(number1: Number1): String
}
/*trait Number2S extends Number2 {
  val tail: Number2
  val head: Number1
  override def method2(number1: Number1): String
}
trait Number2T extends Number2 {
  override def method2(number1: Number1): String
}*/
