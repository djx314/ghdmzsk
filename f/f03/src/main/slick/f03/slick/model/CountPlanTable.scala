package f03.slick.model
// AUTO-GENERATED Slick data model for table CountPlan
trait CountPlanTable {

  self:TablesRoot  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table CountPlan
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param planInfo Database column plan_info SqlType(VARCHAR), Length(255,true)
   *  @param counterResultId Database column counter_result_id SqlType(INT), Default(None) */
  case class CountPlanRow(id: Int, planInfo: String, counterResultId: Option[Int] = None)
  /** GetResult implicit for fetching CountPlanRow objects using plain SQL queries */
  implicit def GetResultCountPlanRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[CountPlanRow] = GR{
    prs => import prs._
    CountPlanRow.tupled((<<[Int], <<[String], <<?[Int]))
  }
  /** Table description of table count_plan. Objects of this class serve as prototypes for rows in queries. */
  class CountPlan(_tableTag: Tag) extends profile.api.Table[CountPlanRow](_tableTag, Some("numberdatabase"), "count_plan") {
    def * = (id, planInfo, counterResultId).<>(CountPlanRow.tupled, CountPlanRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(planInfo), counterResultId)).shaped.<>({r=>import r._; _1.map(_=> CountPlanRow.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column plan_info SqlType(VARCHAR), Length(255,true) */
    val planInfo: Rep[String] = column[String]("plan_info", O.Length(255,varying=true))
    /** Database column counter_result_id SqlType(INT), Default(None) */
    val counterResultId: Rep[Option[Int]] = column[Option[Int]]("counter_result_id", O.Default(None))
  }
  /** Collection-like TableQuery object for table CountPlan */
  lazy val CountPlan = new TableQuery(tag => new CountPlan(tag))
}
