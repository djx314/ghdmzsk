package f06.endpoint

import sttp.tapir._

class CounterEndpoint extends CounterEndpointPre {

  import EndpointHelper._

  val counterPage = root.in("page" / "execute" / "counter").out(htmlBodyUtf8).description("执行计算任务").tag(htmlDocTag)

  val counterExecutionPlan = counterExecutionPlanPre.description("执行计算任务").appendSuccessMessage.appendErrorMessage.tag(JsonTag)
}
