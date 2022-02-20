package f03.service

import f03.counter.{Counter, Fusion, Number1}
import f03.slick.model.Tables._

case class CountResult(i1: Int, i2: Int, result: Option[Int])

trait DataCollection {
  val S             = "s"
  val T             = "t"
  val U             = "u"
  val V             = "v"
  val W             = "w"
  val X             = "x"
  val Y             = "y"
  val Z             = "z"
  val A             = "a"
  val B             = "b"
  val C             = "c"
  val D             = "d"
  val E             = "e"
  val F             = "f"
  val baseName      = Vector(S, T, U, V, Y, Z, C, D, E, F)
  val UnlimitedType = "unlimited"
  val PointType     = "point"
  val ValueType     = "value"
  val ZeroType      = "zero"

  def allCountPlan: Vector[CountPlanRow]

  case class SingleNumber(outerName: String, outerType: String, innerName: String, innerType: String, start: Int)

  def genString(result: List[CountResult]): String
  def fromString(str: String): List[CountResult]

  def countNumber(countPlan: CountPlanRow, firstValue: Int, secoundValue: Int): CountResult
  def genSingleNumber(countPlan: SingleNumber, value: Int): Number1
}

class DataCollectionImpl extends DataCollection {

  val singleZeroRows: Vector[SingleNumber] =
    for (name <- Vector(W, X, A, B))
      yield SingleNumber(outerName = name, outerType = UnlimitedType, innerName = name, innerType = UnlimitedType, start = 0)

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

  val zeroRows: Vector[SingleNumber] = for {
    outerType <- Vector(PointType, ValueType)
    outerName <- baseName
    innerName <- Vector(W, X, A, B)
  } yield SingleNumber(outerName = outerName, outerType = outerType, innerName = innerName, innerType = ZeroType, start = 0)

  override val allCountPlan: Vector[CountPlanRow] = {
    val allNumber = unlimitedRows.appendedAll(otherRows).appendedAll(zeroRows).appendedAll(singleZeroRows)

    val n1 = for {
      s <- singleZeroRows
      t <- allNumber
    } yield (s, t)
    val n2 = for {
      s <- allNumber
      t <- singleZeroRows
    } yield (s, t)

    for {
      (firstNumber, secondNumber) <- n1 ++: n2
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
    // for {
    //   firstNumber  <- allNumber
    //   secondNumber <- allNumber
    // } yield CountPlanRow(
    //   id = -1,
    //   firstOuterName = firstNumber.outerName,
    //   firstOuterType = firstNumber.outerType,
    //   firstInnerName = firstNumber.innerName,
    //   firstInnerType = firstNumber.innerType,
    //   firstStart = firstNumber.start,
    //   secondOuterName = secondNumber.outerName,
    //   secondOuterType = secondNumber.outerType,
    //   secondInnerName = secondNumber.innerName,
    //   secondInnerType = secondNumber.innerType,
    //   secondStart = secondNumber.start
    // )
  }

  def numberGen(nType: String, count: Int, zero: => Number1): Number1 = nType match {
    case S => Fusion.number1sGen(count, zero)
    case T => Fusion.number1tGen(count, zero)
    case U => Fusion.number1uGen(count, zero)
    case V => Fusion.number1vGen(count, zero)
    case Y => Fusion.number1yGen(count, zero)
    case Z => Fusion.number1zGen(count, zero)
    case C => Fusion.number1cGen(count, zero)
    case D => Fusion.number1dGen(count, zero)
    case E => Fusion.number1eGen(count, zero)
    case F => Fusion.number1fGen(count, zero)
  }
  def numberZero(nType: String): Number1 = nType match {
    case S => Fusion.number1s
    case T => Fusion.number1t
    case U => Fusion.number1u
    case V => Fusion.number1v
    case W => Fusion.number1w
    case X => Fusion.number1x
    case Y => Fusion.number1y
    case Z => Fusion.number1z
    case A => Fusion.number1a
    case B => Fusion.number1b
    case C => Fusion.number1c
    case D => Fusion.number1d
    case E => Fusion.number1e
    case F => Fusion.number1f
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
          case ZeroType =>
            numberGen(countPlan.outerName, 1, numberZero(countPlan.innerName))
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
          case ZeroType =>
            numberGen(countPlan.outerName, value, numberZero(countPlan.innerName))
        }
    }
  }

  override def genString(result: List[CountResult]): String =
    result.map(s => s"${s.i1},${s.i2},${s.result.map(_.toString).getOrElse(UnlimitedType)}").mkString("|")

  override def fromString(str: String): List[CountResult] = {
    def splitToList(str: String)(c: Char) = str.split(c).to(List).map(_.trim).filterNot(_.isEmpty)
    def toSingle(str1: String): CountResult = {
      val List(i1, i2, result) = splitToList(str1)(',')
      val r1 = result match {
        case UnlimitedType => Option.empty
        case s             => s.toIntOption
      }
      CountResult(i1 = i1.toInt, i2 = i2.toInt, result = r1)
    }
    splitToList(str)('|').map(toSingle)
  }

  override def countNumber(countPlan: CountPlanRow, firstValue: Int, secoundValue: Int): CountResult = {
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
    CountResult(i1 = firstValue, i2 = secoundValue, result = Counter.countOpt(() => exec))
  }

}
