package f06.endpoint

import f06.models.ResultSet
import sttp.model.StatusCode
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

object NumberEndpoint {

  implicit class appendOutPout[A, I, E, O, -R](endpo: Endpoint[A, I, E, ResultSet[O], R]) {
    def append: Endpoint[A, I, E, (O, StatusCode), R] =
      endpo.out(statusCode).mapOut(d => (d._1.data, d._2))(s => (ResultSet(s._1, s._2.code, Option.empty), s._2))
    def appendSuccess: Endpoint[A, I, E, O, R] =
      endpo.out(statusCode(StatusCode.Ok)).mapOut(d => d.data)(s => ResultSet(s, StatusCode.Ok.code, Option.empty))

    def appendMessage: Endpoint[A, I, E, (O, StatusCode, String), R] =
      endpo
        .out(statusCode)
        .mapOut(d => (d._1.data, d._2, d._1.message.getOrElse("")))(s => (ResultSet(s._1, s._2.code, Option(s._3)), s._2))
    def appendSuccessMessage: Endpoint[A, I, E, (O, String), R] =
      endpo
        .out(statusCode(StatusCode.Ok))
        .mapOut(d => (d.data, d.message.getOrElse("")))(s => ResultSet(s._1, StatusCode.Ok.code, Option(s._2)))
  }

  implicit class appendErrorOutPout[A, I, E, O, -R](endpo: Endpoint[A, I, ResultSet[E], O, R]) {
    def appendError: Endpoint[A, I, (E, StatusCode), O, R] =
      endpo.errorOut(statusCode).mapErrorOut(d => (d._1.data, d._2))(s => (ResultSet(s._1, s._2.code, Option.empty), s._2))

    def appendErrorMessage: Endpoint[A, I, (E, StatusCode, String), O, R] =
      endpo
        .errorOut(statusCode)
        .mapErrorOut(d => (d._1.data, d._2, d._1.message.getOrElse("")))(s => (ResultSet(s._1, s._2.code, Option(s._3)), s._2))
  }

  private val root = endpoint

  private val htmlDocTag = "Html 展示页"
  private val JsonTag    = "Json 请求"

  val pageHelper          = root.in("help").out(htmlBodyUtf8).description("常用页面引导").tag(htmlDocTag)
  val index               = root.in("index").out(htmlBodyUtf8).description("首页").tag(htmlDocTag)
  val countPlanReviewPage = root.in("countPlanReviewPage").out(htmlBodyUtf8).description("计算计划统计").tag(htmlDocTag)

  val deleteAllCountPlan = root
    .in("countPlan")
    .delete
    .out(jsonBody[ResultSet[Int]])
    .appendSuccess
    .errorOut(jsonBody[ResultSet[Unit]])
    .appendErrorMessage
    .description("删除所有计算计划")
    .tag(JsonTag)
  val resetAllCountPlan = root
    .in("resetAllCountPlan")
    .post
    .out(jsonBody[ResultSet[Option[Int]]])
    .appendSuccess
    .errorOut(jsonBody[ResultSet[Unit]])
    .appendErrorMessage
    .description("重置所有计算计划")
    .tag(JsonTag)
  val countCountPlan = root
    .in("countCountPlan")
    .post
    .out(jsonBody[ResultSet[Int]])
    .appendSuccess
    .errorOut(jsonBody[ResultSet[Unit]])
    .appendErrorMessage
    .description("统计计算计划数量")
    .tag(JsonTag)

}
