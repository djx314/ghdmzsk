package f07

case class CountSet(index: Int, set: String) {
  def toMap: Map[(Int, Int), Option[Int]] = {
    val list = set.split('|').to(List)
    val data = for (each <- list) yield {
      val List(i1, i2, result) = each.split(',').to(List)
      ((i1.toInt, i2.toInt), result.toIntOption)
    }
    data.to(Map)
  }
}
case class CountPlan(
  index: Int,
  planInfo: String,
  set: CountSet
)
