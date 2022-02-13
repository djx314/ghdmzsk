package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Using

object Gen1 {

  val str2 =
    """
Tags.Tag479.firstart(0).secondStart(0).value(Tags.Tag010, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag480.firstart(0).secondStart(1).value(Tags.Tag450, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag481.firstart(0).secondStart(1).value(Tags.Tag560, (i1: Int, i2: Int) => (i2, i1))
Tags.Tag482.firstart(1).secondStart(0).value(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag483.firstart(0).secondStart(1).value(Tags.Tag231, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag484.firstart(0).secondStart(1).value(Tags.Tag001, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag485.firstart(0).secondStart(0).value(Tags.Tag001, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag486.firstart(1).secondStart(0).value(Tags.Tag318, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag487.firstart(0).secondStart(0).value(Tags.Tag202, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag488.firstart(0).secondStart(1).value(Tags.Tag296, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag489.firstart(0).secondStart(1).value(Tags.Tag182, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag490.firstart(0).secondStart(1).value(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag491.firstart(0).secondStart(0).value(Tags.Tag169, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag492.firstart(0).secondStart(0).value(Tags.Tag223, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag493.firstart(0).secondStart(1).value(Tags.Tag010, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag494.firstart(1).secondStart(0).value(Tags.Tag363, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag495.firstart(0).secondStart(0).value(Tags.Tag007, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag496.firstart(0).secondStart(0).value(Tags.Tag032, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag497.firstart(0).secondStart(0).value(Tags.Tag213, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag498.firstart(0).secondStart(0).value(Tags.Tag078, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag499.firstart(1).secondStart(0).value(Tags.Tag014, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag500.firstart(0).secondStart(0).value(Tags.Tag222, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag501.firstart(0).secondStart(0).value(Tags.Tag033, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag502.firstart(0).secondStart(1).value(Tags.Tag339, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag503.firstart(0).secondStart(1).value(Tags.Tag007, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag504.firstart(0).secondStart(0).value(Tags.Tag231, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag505.firstart(0).secondStart(1).value(Tags.Tag201, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag506.firstart(0).secondStart(1).value(Tags.Tag032, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag507.firstart(0).secondStart(0).value(Tags.Tag262, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag508.firstart(0).secondStart(0).value(Tags.Tag363, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag509.firstart(0).secondStart(1).value(Tags.Tag222, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag510.firstart(0).secondStart(0).value(Tags.Tag093, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag511.firstart(0).secondStart(0).value(Tags.Tag203, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag512.firstart(1).secondStart(0).value(Tags.Tag038, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag513.firstart(0).secondStart(0).value(Tags.Tag296, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag514.firstart(0).secondStart(0).value(Tags.Tag496, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag515.firstart(1).secondStart(0).value(Tags.Tag262, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag516.firstart(1).secondStart(0).value(Tags.Tag213, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag517.firstart(0).secondStart(0).value(Tags.Tag230, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag518.firstart(0).secondStart(0).value(Tags.Tag232, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag519.firstart(0).secondStart(0).value(Tags.Tag043, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag520.firstart(0).secondStart(1).value(Tags.Tag184, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag521.firstart(0).secondStart(0).value(Tags.Tag182, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag522.firstart(0).secondStart(0).value(Tags.Tag178, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag523.firstart(1).secondStart(1).value(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag524.firstart(0).secondStart(0).value(Tags.Tag028, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag525.firstart(0).secondStart(0).value(Tags.Tag560, (i1: Int, i2: Int) => (i2, i1))
Tags.Tag526.firstart(0).secondStart(0).value(Tags.Tag362, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag527.firstart(0).secondStart(0).value(Tags.Tag201, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag528.firstart(1).secondStart(0).value(Tags.Tag362, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag529.firstart(0).secondStart(0).value(Tags.Tag014, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag530.firstart(1).secondStart(0).value(Tags.Tag169, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag531.firstart(0).secondStart(0).value(Tags.Tag237, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag532.firstart(0).secondStart(1).value(Tags.Tag034, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag533.firstart(0).secondStart(0).value(Tags.Tag318, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag534.firstart(0).secondStart(0).value(Tags.Tag238, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag535.firstart(0).secondStart(0).value(Tags.Tag184, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag536.firstart(0).secondStart(0).value(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag537.firstart(0).secondStart(0).value(Tags.Tag239, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag538.firstart(0).secondStart(0).value(Tags.Tag450, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag539.firstart(0).secondStart(0).value(Tags.Tag034, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag540.firstart(0).secondStart(1).value(Tags.Tag043, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag541.firstart(0).secondStart(1).value(Tags.Tag037, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag542.firstart(0).secondStart(1).value(Tags.Tag223, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag543.firstart(0).secondStart(0).value(Tags.Tag395, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag544.firstart(0).secondStart(1).value(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag545.firstart(1).secondStart(0).value(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag546.firstart(0).secondStart(0).value(Tags.Tag038, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag547.firstart(1).secondStart(0).value(Tags.Tag078, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag548.firstart(0).secondStart(1).value(Tags.Tag239, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag549.firstart(1).secondStart(1).value(Tags.Tag015, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag550.firstart(0).secondStart(1).value(Tags.Tag033, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag551.firstart(0).secondStart(0).value(Tags.Tag339, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag552.firstart(0).secondStart(0).value(Tags.Tag128, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag553.firstart(1).secondStart(0).value(Tags.Tag395, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag554.firstart(0).secondStart(1).value(Tags.Tag203, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag555.firstart(0).secondStart(0).value(Tags.Tag100, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag556.firstart(1).secondStart(0).value(Tags.Tag496, (i1: Int, i2: Int) => (i1 + 1, i2))
Tags.Tag557.firstart(0).secondStart(0).value(Tags.Tag037, (i1: Int, i2: Int) => (i1, i2 + 1))
Tags.Tag558.firstart(1).secondStart(0).value(Tags.Tag295, (i1: Int, i2: Int) => (i1 + 1, i2))
      """

  def genSetsRunner(): Unit = {
    val start  = 561
    val lines1 = Using.resource(Source.fromString(str2))(_.getLines().to(List)).map(_.trim).filterNot(_.isEmpty)
    val lines4 = for ((each, index) <- lines1.zipWithIndex) yield {
      val i     = index + start
      val right = each.split('.').drop(2).mkString(".")
      println(s"Tags.Tag$i.$right")
    }

    /*val path = Paths.get(".", "f", "f07", "src", "main", "codegen", "f07")

    Files.createDirectories(path)
    val filePath = path.resolve("Test.txt")
    Using.resource(new PrintWriter(filePath.toFile)) { writer =>
      lines4.foreach(writer.println)
    }*/
  }

}
