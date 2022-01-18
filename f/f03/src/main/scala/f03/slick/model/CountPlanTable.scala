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
   *  @param firstOuterName Database column first_outer_name SqlType(TEXT), Default() */
  case class CountPlanRow(id: Option[Int], firstOuterName: String = "")
  /** GetResult implicit for fetching CountPlanRow objects using plain SQL queries */
  implicit def GetResultCountPlanRow(implicit e0: GR[Option[Int]], e1: GR[String]): GR[CountPlanRow] = GR{
    prs => import prs._
    CountPlanRow.tupled((<<?[Int], <<[String]))
  }
  /** Table description of table count_plan. Objects of this class serve as prototypes for rows in queries. */
  class CountPlan(_tableTag: Tag) extends profile.api.Table[CountPlanRow](_tableTag, "count_plan") {
    def * = (id, firstOuterName).<>(CountPlanRow.tupled, CountPlanRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((id, Rep.Some(firstOuterName))).shaped.<>({r=>import r._; _2.map(_=> CountPlanRow.tupled((_1, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Option[Int]] = column[Option[Int]]("id", O.AutoInc, O.PrimaryKey)
    /** Database column first_outer_name SqlType(TEXT), Default() */
    val firstOuterName: Rep[String] = column[String]("first_outer_name", O.Default(""))
  }
  /** Collection-like TableQuery object for table CountPlan */
  lazy val CountPlan = new TableQuery(tag => new CountPlan(tag))
}
