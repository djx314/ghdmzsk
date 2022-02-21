package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._
import f06.models.PlanCountReview
import zio.stream.ZStream

import scala.concurrent.ExecutionContext

trait CountPlanService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def deleteAll(): CTask[Int]
  def resetAll(): CTask[Option[Int]]
  def insertAllDistinct(): CTask[Long]
  def count(): CTask[PlanCountReview]
}

class CountPlanServiceImpl(db: SlickDB, dCol: DataCollection) extends CountPlanService {
  override def deleteAll(): CTask[Int] = {
    def action(implicit ec: ExecutionContext) = for {
      result1 <- CountPlan.delete
      result2 <- CountSet.delete
      result3 <- ResultSetSort.delete
    } yield result1 + result2 + result3

    for {
      result <- db.run(implicit ec => action.transactionally)
      _      <- log.info(s"清空了${result}条数据")
    } yield result
  }

  override def resetAll(): CTask[Option[Int]] = for {
    action <- ZIO.effectTotal(CountPlan ++= dCol.allCountPlan)
    result <- db.run(action.transactionally)
    _      <- log.info(result.map(s => s"重置了${s}条数据").getOrElse("没有重置数据"))
  } yield result

  override def insertAllDistinct(): CTask[Long] = {
    val stream = ZStream.fromIterable(dCol.allCountPlan)
    val notExists = for (countPlan <- stream) yield {
      val filterAction = CountPlan
        .filter(c =>
          c.firstStart === countPlan.firstStart && c.secondStart === countPlan.secondStart && c.firstOuterName === countPlan.firstOuterName && c.firstOuterType === countPlan.firstOuterType && c.firstInnerName === countPlan.firstInnerName && c.firstInnerType === countPlan.firstInnerType && c.secondOuterName === countPlan.secondOuterName && c.secondOuterType === countPlan.secondOuterType && c.secondInnerName === countPlan.secondInnerName && c.secondInnerType === countPlan.secondInnerType
        )
        .map(_.id)
        .take(1)
      val countPlanAction = filterAction.result.headOption
      val exists          = db.run(countPlanAction).map(_.isDefined)
      for (e <- exists) yield if (e) Option.empty else Option(countPlan)
    }

    val insert = for (countPlan <- notExists.mapMPar(20)(s => s).collect { case Some(s) => s }) yield {
      val insertAction = CountPlan += countPlan
      db.run(insertAction.transactionally)
    }
    insert.mapM(t => t).runCount
  }

  override def count(): CTask[PlanCountReview] = {
    val countPlanAllCountDBIO  = CountPlan.map(_.id).size.result
    val finishedCountCountDBIO = CountPlan.filter(_.counterResultId.isDefined).map(_.id).size.result
    val waitForCountCountDBIO  = CountPlan.filter(_.counterResultId.isEmpty).map(_.id).size.result
    val countSetCountDBIO      = CountPlan.map(_.counterResultId).filter(_.isDefined).distinct.length.result
    val reSortedCountSetDBIO   = ResultSetSort.filter(_.parent < 0).map(_.id).size.result
    def dbio(implicit ec: ExecutionContext) = for {
      countPlanAllCount  <- countPlanAllCountDBIO
      finishedCountCount <- finishedCountCountDBIO
      waitForCountCount  <- waitForCountCountDBIO
      countSetCount      <- countSetCountDBIO
      reSortedCountSet   <- reSortedCountSetDBIO
    } yield PlanCountReview(
      countPlanAllCount = countPlanAllCount,
      finishedCountCount = finishedCountCount,
      waitForCountCount = waitForCountCount,
      countSetCount = countSetCount,
      reSortedCountSet = reSortedCountSet
    )
    db.run(implicit ec => dbio)
  }
}
