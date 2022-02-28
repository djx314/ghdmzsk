package f09

object Counter {

  def count(number: () => Number): Int = {
    val num =
      try Option(number())
      catch {
        case _: StackOverflowError => Option.empty
      }

    num match {
      case Some(Number1(tail, _, _, _, _)) => count(tail) + 1
      case Some(Number2(tail, _, _, _, _)) => count(tail) + 1
      case Some(Number3(_, _, _, _))       => 0
      case None                            => 0
    }
  }

  def countOpt(number: () => Number): Option[Int] = try Option(count(number)).filter(_ < 1024)
  catch {
    case _: Exception => Option.empty
  }

}

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
