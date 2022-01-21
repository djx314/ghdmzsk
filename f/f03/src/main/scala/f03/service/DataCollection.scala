package f03.service

import f03.slick.model.Tables._

trait DataCollection {
  def allCountPlan: Vector[CountPlanRow]
}

class DataCollectionImpl extends DataCollection {
  val baseName      = Vector("u", "v", "s", "t")
  val baseType      = Vector("u", "v", "s", "t")
  val unlimitedType = "unlimited"
  val pointType     = "point"
  val valueType     = "value"

  case class SingleNumber(outerName: String, outerType: String, innerName: String, innerType: String)

  val unlimitedRows: Vector[SingleNumber] =
    for (name <- baseName)
      yield SingleNumber(outerName = name, outerType = unlimitedType, innerName = name, innerType = unlimitedType)

  val otherRows: Vector[SingleNumber] = for {
    outerType <- Vector(pointType, valueType)
    innerType <- Vector(unlimitedType, pointType, valueType)
    outerName <- baseName
    innerName <- baseName.filter(s => s != outerName)
  } yield SingleNumber(outerName = outerName, outerType = outerType, innerName = innerName, innerType = innerType)

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
      secondOuterName = secondNumber.outerName,
      secondOuterType = secondNumber.outerType,
      secondInnerName = secondNumber.innerName,
      secondInnerType = secondNumber.innerType
    )
  }
}
