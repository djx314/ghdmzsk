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
   *  @param firstOuterName Database column first_outer_name SqlType(VARCHAR), Length(255,true)
   *  @param firstOuterType Database column first_outer_type SqlType(VARCHAR), Length(255,true)
   *  @param firstInnerName Database column first_inner_name SqlType(VARCHAR), Length(255,true)
   *  @param firstInnerType Database column first_inner_type SqlType(VARCHAR), Length(255,true)
   *  @param firstStart Database column first_start SqlType(INT)
   *  @param secondOuterName Database column second_outer_name SqlType(VARCHAR), Length(255,true)
   *  @param secondOuterType Database column second_outer_type SqlType(VARCHAR), Length(255,true)
   *  @param secondInnerName Database column second_inner_name SqlType(VARCHAR), Length(255,true)
   *  @param secondInnerType Database column second_inner_type SqlType(VARCHAR), Length(255,true)
   *  @param secondStart Database column second_start SqlType(INT)
   *  @param counterResultId Database column counter_result_id SqlType(INT), Default(None) */
  case class CountPlanRow(id: Int, firstOuterName: String, firstOuterType: String, firstInnerName: String, firstInnerType: String, firstStart: Int, secondOuterName: String, secondOuterType: String, secondInnerName: String, secondInnerType: String, secondStart: Int, counterResultId: Option[Int] = None)
  /** GetResult implicit for fetching CountPlanRow objects using plain SQL queries */
  implicit def GetResultCountPlanRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[CountPlanRow] = GR{
    prs => import prs._
    CountPlanRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[Int], <<?[Int]))
  }
  /** Table description of table count_plan. Objects of this class serve as prototypes for rows in queries. */
  class CountPlan(_tableTag: Tag) extends profile.api.Table[CountPlanRow](_tableTag, Some("numberdatabase"), "count_plan") {
    def * = (id, firstOuterName, firstOuterType, firstInnerName, firstInnerType, firstStart, secondOuterName, secondOuterType, secondInnerName, secondInnerType, secondStart, counterResultId).<>(CountPlanRow.tupled, CountPlanRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(firstOuterName), Rep.Some(firstOuterType), Rep.Some(firstInnerName), Rep.Some(firstInnerType), Rep.Some(firstStart), Rep.Some(secondOuterName), Rep.Some(secondOuterType), Rep.Some(secondInnerName), Rep.Some(secondInnerType), Rep.Some(secondStart), counterResultId)).shaped.<>({r=>import r._; _1.map(_=> CountPlanRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column first_outer_name SqlType(VARCHAR), Length(255,true) */
    val firstOuterName: Rep[String] = column[String]("first_outer_name", O.Length(255,varying=true))
    /** Database column first_outer_type SqlType(VARCHAR), Length(255,true) */
    val firstOuterType: Rep[String] = column[String]("first_outer_type", O.Length(255,varying=true))
    /** Database column first_inner_name SqlType(VARCHAR), Length(255,true) */
    val firstInnerName: Rep[String] = column[String]("first_inner_name", O.Length(255,varying=true))
    /** Database column first_inner_type SqlType(VARCHAR), Length(255,true) */
    val firstInnerType: Rep[String] = column[String]("first_inner_type", O.Length(255,varying=true))
    /** Database column first_start SqlType(INT) */
    val firstStart: Rep[Int] = column[Int]("first_start")
    /** Database column second_outer_name SqlType(VARCHAR), Length(255,true) */
    val secondOuterName: Rep[String] = column[String]("second_outer_name", O.Length(255,varying=true))
    /** Database column second_outer_type SqlType(VARCHAR), Length(255,true) */
    val secondOuterType: Rep[String] = column[String]("second_outer_type", O.Length(255,varying=true))
    /** Database column second_inner_name SqlType(VARCHAR), Length(255,true) */
    val secondInnerName: Rep[String] = column[String]("second_inner_name", O.Length(255,varying=true))
    /** Database column second_inner_type SqlType(VARCHAR), Length(255,true) */
    val secondInnerType: Rep[String] = column[String]("second_inner_type", O.Length(255,varying=true))
    /** Database column second_start SqlType(INT) */
    val secondStart: Rep[Int] = column[Int]("second_start")
    /** Database column counter_result_id SqlType(INT), Default(None) */
    val counterResultId: Rep[Option[Int]] = column[Option[Int]]("counter_result_id", O.Default(None))
  }
  /** Collection-like TableQuery object for table CountPlan */
  lazy val CountPlan = new TableQuery(tag => new CountPlan(tag))
}
