package i01_01

trait AppendContent
case class AppendString(content: String) extends AppendContent

class Appendable(val model: List[AppendContent]) extends AnyVal {

  def append(content: AppendContent)(list: List[AppendContent]): Appendable = new Appendable(
    model.flatMap(s => if (s eq content) s :: list else List(s))
  )
  def prePlus(content: AppendContent)(list: List[AppendContent]): Appendable = new Appendable(
    model.flatMap(s => if (s eq content) list.appended(s) else List(s))
  )

}
