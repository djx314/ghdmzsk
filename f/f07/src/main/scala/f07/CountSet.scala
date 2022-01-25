package f07

case class CountSet(index: Int, firstStart: Int, secondStart: Int, set: String)
case class CountPlan(
  index: Int,
  firstOuterName: String,
  firstOuterType: String,
  firstInnerName: String,
  firstInnerType: String,
  firstStart: Int,
  secondOuterName: String,
  secondOuterType: String,
  secondInnerName: String,
  secondInnerType: String,
  secondStart: Int,
  set: CountSet
)
