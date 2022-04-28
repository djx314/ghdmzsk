package i01_01.xml

object XmlRunner {

  val number = HtmlTag(
    "html",
    EndInfoTag,
    HtmlTagDeal(
      HtmlTagDeal(EndHtmlTag(""), HtmlTag("meta", EndInfoTag, EndHtmlTag(""))),
      HtmlTag(
        "body",
        EndInfoTag,
        HtmlTagDeal(HtmlTagDeal(EndHtmlTag(""), HtmlTag("div", EndInfoTag, EndHtmlTag("aa"))), HtmlTag("div", EndInfoTag, EndHtmlTag("bb")))
      )
    )
  )

  def main(arr: Array[String]): Unit = {
    println(number.method1(Models.model2))
  }

}
