package f03.slick.model
// AUTO-GENERATED Slick data model for table ResultSetSort
trait ResultSetSortTable {

  self:TablesRoot  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table ResultSetSort
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param isLimited Database column is_limited SqlType(BIT)
   *  @param countSet Database column count_set SqlType(TEXT)
   *  @param firstStart Database column first_start SqlType(INT)
   *  @param secondStart Database column second_start SqlType(INT)
   *  @param parent Database column parent SqlType(INT) */
  case class ResultSetSortRow(id: Int, isLimited: Boolean, countSet: String, firstStart: Int, secondStart: Int, parent: Int)
  /** GetResult implicit for fetching ResultSetSortRow objects using plain SQL queries */
  implicit def GetResultResultSetSortRow(implicit e0: GR[Int], e1: GR[Boolean], e2: GR[String]): GR[ResultSetSortRow] = GR{
    prs => import prs._
    ResultSetSortRow.tupled((<<[Int], <<[Boolean], <<[String], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table result_set_sort. Objects of this class serve as prototypes for rows in queries. */
  class ResultSetSort(_tableTag: Tag) extends profile.api.Table[ResultSetSortRow](_tableTag, Some("numberdatabase"), "result_set_sort") {
    def * = (id, isLimited, countSet, firstStart, secondStart, parent).<>(ResultSetSortRow.tupled, ResultSetSortRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(isLimited), Rep.Some(countSet), Rep.Some(firstStart), Rep.Some(secondStart), Rep.Some(parent))).shaped.<>({r=>import r._; _1.map(_=> ResultSetSortRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column is_limited SqlType(BIT) */
    val isLimited: Rep[Boolean] = column[Boolean]("is_limited")
    /** Database column count_set SqlType(TEXT) */
    val countSet: Rep[String] = column[String]("count_set")
    /** Database column first_start SqlType(INT) */
    val firstStart: Rep[Int] = column[Int]("first_start")
    /** Database column second_start SqlType(INT) */
    val secondStart: Rep[Int] = column[Int]("second_start")
    /** Database column parent SqlType(INT) */
    val parent: Rep[Int] = column[Int]("parent")
  }
  /** Collection-like TableQuery object for table ResultSetSort */
  lazy val ResultSetSort = new TableQuery(tag => new ResultSetSort(tag))
}
