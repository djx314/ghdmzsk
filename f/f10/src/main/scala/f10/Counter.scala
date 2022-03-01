package f09

object MethodCounter {

  def count(method: () => Method): Int = try {
    val num = method()
    num match {
      case Method(tail, _, _, _) if num == tail() => 0
      case Method(tail, _, _, _)                  => count(tail) + 1
    }
  } catch {
    case _: StackOverflowError => 0
  }

  def countOpt(number: () => Method): Option[Int] = try Option(count(number)).filter(_ < 1024)
  catch {
    case _: Exception => Option.empty
  }

}
