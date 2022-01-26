package f07

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.io.Source
import scala.util.Using

object Gen2 {

  val str1 =
    """
  Tags.Tag001.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 + i2 * 2 + 1)
  Tags.Tag002.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 - i2 else 0)
  Tags.Tag003.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i2 * 2 + 1)
  Tags.Tag005.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2 * 2 + 1) else Option.empty)
  Tags.Tag006.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2 + 1) else Option.empty)
  Tags.Tag008.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * 2 + i2)
  Tags.Tag009.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 + i2)
  Tags.Tag010.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(i1 * 2))
  Tags.Tag011.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 1 else i1 * 2)
  Tags.Tag012.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 + i2 else i1 * 2)
  Tags.Tag013.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(i1))
  Tags.Tag014.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 >= 0) i1 * 2 - i2 else i1)
  Tags.Tag015.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) 0 else i2 - i1 + 1)
  Tags.Tag017.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 * 2 + 1 else i1 + i2)
  Tags.Tag018.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 + 1 else i1)
  Tags.Tag020.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(0))
  Tags.Tag021.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(i2) else Option.empty)
  Tags.Tag022.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) Option(0) else Option.empty)
  Tags.Tag023.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else i2)
  Tags.Tag024.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 else i1)
  Tags.Tag025.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) Option.empty else Option(0))
  Tags.Tag027.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 1 else 0)
  Tags.Tag028.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 - i2 > 0) i2 else i2 * 2 - i1 + 1)
  Tags.Tag029.firstart(0).secondStart(0).value((i1: Int, i2: Int) => Option.empty)
  Tags.Tag030.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) Option(0) else Option.empty)
  Tags.Tag031.firstart(0).secondStart(0).value((i1: Int, i2: Int) => 0)
  Tags.Tag032.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1)
  Tags.Tag033.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 + i2 + 1)
  Tags.Tag034.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 + 1)
  Tags.Tag035.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 + i2)
  Tags.Tag036.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + 1))
  Tags.Tag037.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + 2))
  Tags.Tag038.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else i2 + 1)
  Tags.Tag039.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) i2 else i2 + 1)
  Tags.Tag040.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2))
  Tags.Tag041.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) 0 else i1)
  Tags.Tag042.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) i2 + 1 else i2)
  Tags.Tag043.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) Option(i1 + 1) else Option.empty)
  Tags.Tag044.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) Option(i1) else Option.empty)
  Tags.Tag045.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 / (i2 + 1))
  Tags.Tag046.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 % (i2 + 1) == 0) i1 / (i2 + 1) else i1 / (i2 + 1) + 1)
  Tags.Tag047.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 % (i1 + 1) == i1) i2 / (i1 + 1) + 1 else i2 / (i1 + 1))
  Tags.Tag048.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i2 / (i1 + 1) + 1)
  Tags.Tag049.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2 - 1) else Option(i1 / i2))
  Tags.Tag050.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 / i2) else Option(i1 / i2 + 1))
  Tags.Tag051.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 / i1))
  Tags.Tag052.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 / i1 + 1))
  Tags.Tag053.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i2 + 1)
  Tags.Tag054.firstart(0).secondStart(0).value((i1: Int, i2: Int) => 1)
  Tags.Tag055.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i2)
  Tags.Tag056.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(1))
  Tags.Tag057.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(2))
  Tags.Tag058.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) 0 else 1)
  Tags.Tag059.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) Option.empty else Option(0))
  Tags.Tag060.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) 1 else 0)
  Tags.Tag061.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i2 == 0) Option(1) else Option.empty)
  Tags.Tag062.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) Option(1) else Option.empty)
  Tags.Tag063.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 != 0) Option(1) else Option.empty)
  Tags.Tag064.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else Option.empty)
  Tags.Tag065.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option(1) else Option.empty)
  Tags.Tag066.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 * i2 + 2 * i1)
  Tags.Tag067.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * i2 + 2 * i1 - i2)
  Tags.Tag068.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 * i2 + 2 * i2 + i1 + 1)
  Tags.Tag069.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 * i2 + 2 * i2 + 1)
  Tags.Tag070.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 * i2 + i1)
  Tags.Tag071.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else i1 * i2 + i1 - i2)
  Tags.Tag072.firstart(0).secondStart(0).value((i1: Int, i2: Int) => i1 * i2 + i1 + i2)
  Tags.Tag073.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else 1)
  Tags.Tag074.firstart(0).secondStart(0).value((i1: Int, i2: Int) => 2 * i1)
  Tags.Tag075.firstart(0).secondStart(0).value((i1: Int, i2: Int) => 2 * i1 + 1)
  Tags.Tag076.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2 - 1) else Option(2 * i1 + i1 / i2))
  Tags.Tag077.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(2 * i1 + i1 / i2) else Option(2 * i1 + i1 / i2 + 1))
  Tags.Tag078.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(2 * i2 + i2 / i1 + 1))
  Tags.Tag079.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(2 * i2 + i2 / i1 + 2))
  Tags.Tag080.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 != 0 && i2 == 0) 1 else 2 * i1)
  Tags.Tag081.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) 0 else 2 * i2 + 1)
  Tags.Tag082.firstart(0).secondStart(0).value((i1: Int, i2: Int) => 2 * i1 - i1 / (i2 + 1))
  Tags.Tag083.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 % (i2 + 1) == 0) 2 * i1 - i1 / (i2 + 1) else 2 * i1 - i1 / (i2 + 1) - 1)
  Tags.Tag084.firstart(0).secondStart(0).value((i1: Int, i2: Int) => 2 * i1 - (i1 + 1) / (i2 + 1) + 1)
  Tags.Tag085.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2 - 1) else Option(i1 + i1 / i2))
  Tags.Tag086.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option(0) else if (i2 == 0) Option.empty else if (i1 % i2 == 0) Option(i1 + i1 / i2) else Option(i1 + i1 / i2 + 1))
  Tags.Tag087.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + i2 / i1))
  Tags.Tag088.firstart(0).secondStart(0).value((i1: Int, i2: Int) => if (i1 == 0) Option.empty else Option(i2 + i2 / i1 + 1))
      """

  def genSetsRunner(): Unit = {
    val lines1 = Using.resource(Source.fromString(str1))(_.getLines().to(List))
    val lines2 = lines1.map(_.trim).filterNot(_.isEmpty)
    val lines3 = for ((each, index) <- lines2.zipWithIndex) yield {
      val i     = index + 1
      val num   = if (i < 10) s"00$i" else if (i < 100) s"0$i" else i.toString
      val right = each.split('.').drop(2).mkString(".")
      s"Tags.Tag$num.$right"
    }

    val path = Paths.get(".", "f", "f07", "src", "main", "codegen", "f07")

    Files.createDirectories(path)
    val filePath = path.resolve("Test2.txt")
    Using.resource(new PrintWriter(filePath.toFile)) { writer =>
      lines3.foreach(writer.println)
    }
  }

}
