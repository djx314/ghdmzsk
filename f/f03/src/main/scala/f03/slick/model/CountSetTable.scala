package f03.slick.model
// AUTO-GENERATED Slick data model for table CountSet
trait CountSetTable {

  self:TablesRoot  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table CountSet
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param countSet Database column count_set SqlType(TEXT)
   *  @param secondStart Database column second_start SqlType(INT)
   *  @param firstStart Database column first_start SqlType(INT)
   *  @param isLimited Database column is_limited SqlType(BIT)
   *  @param mapping Database column mapping SqlType(TEXT), Default(None) */
  case class CountSetRow(id: Int, countSet: String, secondStart: Int, firstStart: Int, isLimited: Boolean, mapping: Option[String] = None)
  /** GetResult implicit for fetching CountSetRow objects using plain SQL queries */
  implicit def GetResultCountSetRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean], e3: GR[Option[String]]): GR[CountSetRow] = GR{
    prs => import prs._
    CountSetRow.tupled((<<[Int], <<[String], <<[Int], <<[Int], <<[Boolean], <<?[String]))
  }
  /** Table description of table count_set. Objects of this class serve as prototypes for rows in queries. */
  class CountSet(_tableTag: Tag) extends profile.api.Table[CountSetRow](_tableTag, Some("numberdatabase"), "count_set") {
    def * = (id, countSet, secondStart, firstStart, isLimited, mapping).<>(CountSetRow.tupled, CountSetRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(countSet), Rep.Some(secondStart), Rep.Some(firstStart), Rep.Some(isLimited), mapping)).shaped.<>({r=>import r._; _1.map(_=> CountSetRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column count_set SqlType(TEXT) */
    val countSet: Rep[String] = column[String]("count_set")
    /** Database column second_start SqlType(INT) */
    val secondStart: Rep[Int] = column[Int]("second_start")
    /** Database column first_start SqlType(INT) */
    val firstStart: Rep[Int] = column[Int]("first_start")
    /** Database column is_limited SqlType(BIT) */
    val isLimited: Rep[Boolean] = column[Boolean]("is_limited")
    /** Database column mapping SqlType(TEXT), Default(None) */
    val mapping: Rep[Option[String]] = column[Option[String]]("mapping", O.Default(None))
  }
  /** Collection-like TableQuery object for table CountSet */
  lazy val CountSet = new TableQuery(tag => new CountSet(tag))
}
