package f03.slick.model
// AUTO-GENERATED Slick data model for table CountPlan
trait CountPlanTable {

  self:TablesRoot  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table CountPlan
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param firstOuterName Database column first_outer_name SqlType(TEXT)
   *  @param firstOuterType Database column first_outer_type SqlType(TEXT)
   *  @param firstInnerName Database column first_inner_name SqlType(TEXT)
   *  @param firstInnerType Database column first_inner_type SqlType(TEXT)
   *  @param secondOuterName Database column second_outer_name SqlType(TEXT)
   *  @param secondOuterType Database column second_outer_type SqlType(TEXT)
   *  @param secondInnerName Database column second_inner_name SqlType(TEXT)
   *  @param secondInnerType Database column second_inner_type SqlType(TEXT)
   *  @param counterResultId Database column counter_result_id SqlType(INTEGER), Default(None) */
  case class CountPlanRow(id: Int, firstOuterName: String, firstOuterType: String, firstInnerName: String, firstInnerType: String, secondOuterName: String, secondOuterType: String, secondInnerName: String, secondInnerType: String, counterResultId: Option[Int] = None)
  /** GetResult implicit for fetching CountPlanRow objects using plain SQL queries */
  implicit def GetResultCountPlanRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]]): GR[CountPlanRow] = GR{
    prs => import prs._
    CountPlanRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<?[Int]))
  }
  /** Table description of table count_plan. Objects of this class serve as prototypes for rows in queries. */
  class CountPlan(_tableTag: Tag) extends profile.api.Table[CountPlanRow](_tableTag, "count_plan") {
    def * = (id, firstOuterName, firstOuterType, firstInnerName, firstInnerType, secondOuterName, secondOuterType, secondInnerName, secondInnerType, counterResultId).<>(CountPlanRow.tupled, CountPlanRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(firstOuterName), Rep.Some(firstOuterType), Rep.Some(firstInnerName), Rep.Some(firstInnerType), Rep.Some(secondOuterName), Rep.Some(secondOuterType), Rep.Some(secondInnerName), Rep.Some(secondInnerType), counterResultId)).shaped.<>({r=>import r._; _1.map(_=> CountPlanRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column first_outer_name SqlType(TEXT) */
    val firstOuterName: Rep[String] = column[String]("first_outer_name")
    /** Database column first_outer_type SqlType(TEXT) */
    val firstOuterType: Rep[String] = column[String]("first_outer_type")
    /** Database column first_inner_name SqlType(TEXT) */
    val firstInnerName: Rep[String] = column[String]("first_inner_name")
    /** Database column first_inner_type SqlType(TEXT) */
    val firstInnerType: Rep[String] = column[String]("first_inner_type")
    /** Database column second_outer_name SqlType(TEXT) */
    val secondOuterName: Rep[String] = column[String]("second_outer_name")
    /** Database column second_outer_type SqlType(TEXT) */
    val secondOuterType: Rep[String] = column[String]("second_outer_type")
    /** Database column second_inner_name SqlType(TEXT) */
    val secondInnerName: Rep[String] = column[String]("second_inner_name")
    /** Database column second_inner_type SqlType(TEXT) */
    val secondInnerType: Rep[String] = column[String]("second_inner_type")
    /** Database column counter_result_id SqlType(INTEGER), Default(None) */
    val counterResultId: Rep[Option[Int]] = column[Option[Int]]("counter_result_id", O.Default(None))
  }
  /** Collection-like TableQuery object for table CountPlan */
  lazy val CountPlan = new TableQuery(tag => new CountPlan(tag))
}
