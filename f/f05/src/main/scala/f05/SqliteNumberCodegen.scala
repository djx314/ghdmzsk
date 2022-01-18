package f05

import slick.codegen.SourceCodeGenerator
import slick.jdbc.SQLiteProfile
import SQLiteProfile.api._

import java.nio.file.Paths
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{blocking, Await, Future}

object SqliteNumberCodegen extends App {
  val db = Database.forURL("jdbc:sqlite:./f/f03/db/numberdatabase.db", driver = "org.sqlite.JDBC")
  // fetch data model
  val modelAction = SQLiteProfile.createModel(Some(SQLiteProfile.defaultTables)) // you can filter specific tables here
  val modelFuture = db.run(modelAction)
  val codegenFuture = modelFuture.map(model =>
    new SourceCodeGenerator(model) {
      override def Table = new Table(_) {
        override def hugeClassEnabled = true
      }
    }
  )

  val sourceRootDir = Paths.get(args(0))
  val genDir        = sourceRootDir.resolve(Paths.get("src", "main", "scala"))
  Await.result(
    codegenFuture.flatMap(codegen =>
      Future {
        blocking(
          codegen.writeToMultipleFiles(
            profile = "slick.jdbc.SQLiteProfile",
            folder = genDir.toAbsolutePath.toString,
            pkg = "f03.slick.model",
            container = "Tables"
          )
        )
      }
    ),
    scala.concurrent.duration.Duration.Inf
  )
}
