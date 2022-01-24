package f05

import slick.codegen.SourceCodeGenerator
import slick.jdbc.MySQLProfile
import MySQLProfile.api._

import java.nio.file.Paths
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{blocking, Await, Future}

object MysqlNumberCodegen extends App {
  val db = Database.forConfig("mysqlNumberDB")
  // fetch data model
  val modelAction = MySQLProfile.createModel(Some(MySQLProfile.defaultTables)) // you can filter specific tables here
  val modelFuture = db.run(modelAction)
  val codegenFuture = modelFuture.map(model =>
    new SourceCodeGenerator(model) {
      override def Table = new Table(_) {
        override def hugeClassEnabled = true
      }
    }
  )

  val sourceRootDir = Paths.get(args(0))
  val genDir        = sourceRootDir.resolve(Paths.get("src", "main", "slick"))
  Await.result(
    codegenFuture.flatMap(codegen =>
      Future {
        blocking(
          codegen.writeToMultipleFiles(
            profile = "slick.jdbc.MySQLProfile",
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
