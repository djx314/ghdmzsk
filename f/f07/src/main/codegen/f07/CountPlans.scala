package f07
import f07.codegen.impl._
object CountPlans {
  val sum: List[CountPlan] = (CountPlanSums0.sum ::: CountPlanSums1.sum ::: CountPlanSums2.sum ::: CountPlanSums3.sum)
}
