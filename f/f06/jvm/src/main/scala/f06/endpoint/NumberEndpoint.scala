package f06.endpoint

import sttp.tapir._

class NumberEndpoint extends NumberEndpointPre {

  import EndpointHelper._

  private val root = endpoint

  private val htmlDocTag = "Html 展示页"
  private val JsonTag    = "Json 请求"

  val pageHelper          = root.in("help").out(htmlBodyUtf8).description("常用页面引导").tag(htmlDocTag)
  val index               = root.in("page" / "index").out(htmlBodyUtf8).description("首页").tag(htmlDocTag)
  val countPlanReviewPage = root.in("page" / "countPlanReviewPage").out(htmlBodyUtf8).description("计算计划统计").tag(htmlDocTag)

  val deleteAllCountPlan = deleteAllCountPlanPre.description("删除所有计算计划").tag(JsonTag).appendSuccess.appendErrorMessage

  val resetAllCountPlan = resetAllCountPlanPre.description("重置所有计算计划").tag(JsonTag).appendSuccess.appendErrorMessage

  val countCountPlan = countCountPlanPre.description("统计计算计划数量").tag(JsonTag).appendSuccess.appendErrorMessage

}