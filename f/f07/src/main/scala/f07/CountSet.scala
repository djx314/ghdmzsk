package f07

case class CountSet(index: Int, set: String)
case class CountPlan(
  index: Int,
  planInfo: String,
  set: CountSet
)
