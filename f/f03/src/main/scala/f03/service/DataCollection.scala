package f03.service

import f03.counter.{Counter, Fusion, Number1}
import f03.slick.model.Tables._

trait DataCollection {
  val S             = "s"
  val T             = "t"
  val U             = "u"
  val V             = "v"
  val baseName      = Vector(S, T, U, V)
  val UnlimitedType = "unlimited"
  val PointType     = "point"
  val ValueType     = "value"

  def allCountPlan: Vector[CountPlanRow]

  case class SingleNumber(outerName: String, outerType: String, innerName: String, innerType: String, start: Int)

  def countNumber(countPlan: CountPlanRow, firstValue: Int, secoundValue: Int): Option[Int]
  def genSingleNumber(countPlan: SingleNumber, value: Int): Number1
}

class DataCollectionImpl extends DataCollection {

  val unlimitedRows: Vector[SingleNumber] =
    for (name <- baseName)
      yield SingleNumber(outerName = name, outerType = UnlimitedType, innerName = name, innerType = UnlimitedType, start = 0)

  val otherRows: Vector[SingleNumber] = for {
    outerType <- Vector(PointType, ValueType)
    innerType <- Vector(UnlimitedType, PointType, ValueType)
    outerName <- baseName
    innerName <- baseName.filter(s => s != outerName)
  } yield {
    val start = if (outerType == ValueType && innerType == ValueType) 1 else 0
    SingleNumber(outerName = outerName, outerType = outerType, innerName = innerName, innerType = innerType, start = start)
  }

  override val allCountPlan: Vector[CountPlanRow] = {
    val allNumber = unlimitedRows.appendedAll(otherRows)
    for {
      firstNumber  <- allNumber
      secondNumber <- allNumber
    } yield CountPlanRow(
      id = -1,
      firstOuterName = firstNumber.outerName,
      firstOuterType = firstNumber.outerType,
      firstInnerName = firstNumber.innerName,
      firstInnerType = firstNumber.innerType,
      firstStart = firstNumber.start,
      secondOuterName = secondNumber.outerName,
      secondOuterType = secondNumber.outerType,
      secondInnerName = secondNumber.innerName,
      secondInnerType = secondNumber.innerType,
      secondStart = secondNumber.start
    )
  }

  def numberGen(nType: String, count: Int, zero: => Number1): Number1 = nType match {
    case S => Fusion.number1sGen(count, zero)
    case T => Fusion.number1tGen(count, zero)
    case U => Fusion.number1uGen(count, zero)
    case V => Fusion.number1vGen(count, zero)
  }
  def numberZero(nType: String): Number1 = nType match {
    case S => Fusion.number1s
    case T => Fusion.number1t
    case U => Fusion.number1u
    case V => Fusion.number1v
  }

  override def genSingleNumber(countPlan: SingleNumber, value: Int): Number1 = {
    countPlan.outerType match {
      case UnlimitedType => numberZero(countPlan.outerName)
      case PointType =>
        countPlan.innerType match {
          case UnlimitedType =>
            numberGen(countPlan.outerName, 1, numberZero(countPlan.innerName))
          case PointType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, 1, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, 1, outer)
            outer
          case ValueType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, 1, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, value, outer)
            outer
        }
      case ValueType =>
        countPlan.innerType match {
          case UnlimitedType =>
            numberGen(countPlan.outerName, value, numberZero(countPlan.innerName))
          case PointType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, value, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, 1, outer)
            outer
          case ValueType =>
            lazy val outer: Number1 = numberGen(countPlan.outerName, value, inner)
            lazy val inner: Number1 = numberGen(countPlan.innerName, value, outer)
            outer
        }
    }
  }

  override def countNumber(countPlan: CountPlanRow, firstValue: Int, secoundValue: Int): Option[Int] = {
    val firstNumber = genSingleNumber(
      SingleNumber(
        outerName = countPlan.firstOuterName,
        outerType = countPlan.firstOuterType,
        innerName = countPlan.firstInnerName,
        innerType = countPlan.firstInnerType,
        start = countPlan.firstStart
      ),
      firstValue
    )
    val secondNumber = genSingleNumber(
      SingleNumber(
        outerName = countPlan.secondOuterName,
        outerType = countPlan.secondOuterType,
        innerName = countPlan.secondInnerName,
        innerType = countPlan.secondInnerType,
        start = countPlan.secondStart
      ),
      secoundValue
    )
    def exec = firstNumber.method1(secondNumber)
    Counter.countOpt(() => exec)
  }

}
