package e01_02.test

import zio._
import zio.Console._
import zio.test._
import zio.test.Assertion._
import zio.test.testEnvironment._
import e01_02._

import e01_02._
import NumberOps._
import scala.util.Try

object HelloWorldSpec extends DefaultRunnableSpec {
  def spec = suite("Number-List Spec")(
    test("::") {
      check(Gen.listOf(Gen.int), Gen.int, Gen.string) { (list, item1, item2) =>
        val number1 = OpsConvertList.fromList(list)
        val result1 = item1 :: number1
        val result2 = item2 :: number1
        val equals1 = assert(OpsConvertList.toList(result1))(equalTo(item1 :: list))
        val equals2 = assert(OpsConvertList.toList(result2))(equalTo(item2 :: list))
        equals1 && equals2
      }
    },
    test(":::") {
      check(Gen.listOf(Gen.int), Gen.listOf(Gen.int), Gen.listOf(Gen.string)) { (list1, list2, list3) =>
        val number1 = OpsConvertList.fromList(list1)
        val number2 = OpsConvertList.fromList(list2)
        val number3 = OpsConvertList.fromList(list3)
        val result1 = number1 ::: number2
        val result2 = number1 ::: number3
        val equals1 = assert(OpsConvertList.toList(result1))(equalTo(list1 ::: list2))
        val equals2 = assert(OpsConvertList.toList(result2))(equalTo(list1 ::: list3))
        equals1 && equals2
      }
    },
    test("appendAll") {
      check(Gen.listOf(Gen.int), Gen.listOf(Gen.int), Gen.listOf(Gen.string)) { (list1, list2, list3) =>
        val number1 = OpsConvertList.fromList(list1)
        val number2 = OpsConvertList.fromList(list2)
        val number3 = OpsConvertList.fromList(list3)
        val result1 = number1.appendAll(number2)
        val result2 = number1.appendAll(number3)
        val equals1 = assert(OpsConvertList.toList(result1))(equalTo(list1.appendedAll(list2)))
        val equals2 = assert(OpsConvertList.toList(result2))(equalTo(list1.appendedAll(list3)))
        equals1 && equals2
      }
    },
    test("collect") {
      check(Gen.listOf(Gen.option(Gen.int)), Gen.listOf(Gen.string)) { (list1, list2) =>
        val number1 = OpsConvertList.fromList(list1)
        val number2 = OpsConvertList.fromList(list2)
        val result1 = number1.collect { case Some(s) => s }
        val result2 = number1.collect { case s if s.size > 2 => s }
        val equals1 = assert(OpsConvertList.toList(result1))(equalTo(list1.collect { case Some(s) => s }))
        val equals2 = assert(OpsConvertList.toList(result2))(equalTo(list1.collect { case s if s.size > 2 => s }))
        equals1 && equals2
      }
    },
    test("contains") {
      check(Gen.listOf(Gen.int), Gen.int, Gen.listOf(Gen.string), Gen.string) { (list1, data1, list2, data2) =>
        val number1 = OpsConvertList.fromList(list1)
        val number2 = OpsConvertList.fromList(list2)
        val result1 = number1.contains(data1)
        val result2 = number1.contains(data2)
        val result3 = number2.contains(data1)
        val result4 = number2.contains(data2)
        val result5 = number1.headOption.map(s => number1.contains(s))
        val result6 = number2.headOption.map(s => number2.contains(s))
        val equals1 = assert(result1)(equalTo(list1.contains(data1)))
        val equals2 = assert(result2)(equalTo(list1.contains(data2)))
        val equals3 = assert(result3)(equalTo(list2.contains(data1)))
        val equals4 = assert(result4)(equalTo(list2.contains(data2)))
        val equals5 = assert(result5)(equalTo(list1.headOption.map(s => list1.contains(s))))
        val equals6 = assert(result6)(equalTo(list2.headOption.map(s => list2.contains(s))))
        equals1 && equals2 && equals3 && equals4 && equals5 && equals6
      }
    },
    test("corresponds") {
      check(Gen.listOf(Gen.int), Gen.listOf(Gen.int), Gen.listOf(Gen.string), Gen.listOf(Gen.string)) { (list1, list2, list3, list4) =>
        val number1 = OpsConvertList.fromList(list1)
        val number2 = OpsConvertList.fromList(list2)
        val number3 = OpsConvertList.fromList(list3)
        val number4 = OpsConvertList.fromList(list4)
        val result1 = number1.corresponds(number2)((a, b) => a * b >= 0)
        val result2 = number3.corresponds(number4)((a, b) => a.size + b.size < 20)
        val result3 = number1.corresponds(number1)((a, b) => a == b)
        val result4 = number3.corresponds(number3)(_ == _)
        val equals1 = assert(result1)(equalTo(list1.corresponds(list2)((a, b) => a * b >= 0)))
        val equals2 = assert(result2)(equalTo(list3.corresponds(list4)((a, b) => a.size + b.size < 20)))
        val equals3 = assert(result3)(equalTo(true))
        val equals4 = assert(result4)(equalTo(true))
        equals1 && equals2 && equals3 && equals4
      }
    },
    test("headOption") {
      check(Gen.listOf(Gen.int), Gen.listOf(Gen.string)) { (list1, list2) =>
        val number1 = OpsConvertList.fromList(list1)
        val number2 = OpsConvertList.fromList(list2)
        val result1 = number1.headOption
        val result2 = number2.headOption
        val equals1 = assert(result1)(equalTo(list1.headOption))
        val equals2 = assert(result2)(equalTo(list2.headOption))
        equals1 && equals2
      }
    },
    test("head") {
      check(Gen.listOf(Gen.int), Gen.listOf(Gen.string)) { (list1, list2) =>
        val number1                                            = OpsConvertList.fromList(list1)
        val number2                                            = OpsConvertList.fromList(list2)
        def convert[T](t: => T): Either[(Class[_], String), T] = Try(t).toEither.left.map(s => (s.getClass(), s.getMessage()))
        val result1                                            = convert(number1.head)
        val result2                                            = convert(number2.head)
        val equals1                                            = assert(result1)(equalTo(convert(list1.head)))
        val equals2                                            = assert(result2)(equalTo(convert(list2.head)))
        equals1 && equals2
      }
    },
    test("equals") {
      check(Gen.listOf(Gen.int), Gen.listOf(Gen.int), Gen.listOf(Gen.string)) { (list1_1, list1_2, list2) =>
        val number1_1 = OpsConvertList.fromList(list1_1)
        val number1_2 = OpsConvertList.fromList(list1_2)
        val number2   = OpsConvertList.fromList(list2)
        val result1   = number1_1.ops_equals(number2)
        val result2   = number2.ops_equals(number1_1)
        val result3   = number1_1.ops_equals(number1_1)
        val result4   = number2.ops_equals(number2)
        val result5   = number1_1.ops_equals(number1_2)
        val equals1   = assert(result1)(equalTo(list1_1 == list2))
        val equals2   = assert(result2)(equalTo(list2 == list1_1))
        val equals3   = assert(result3)(equalTo(list1_1 == list1_1))
        val equals4   = assert(result4)(equalTo(list2 == list2))
        val equals5   = assert(result5)(equalTo(list1_1 == list1_2))
        equals1 && equals2 && equals3 && equals4 && equals5
      }
    }
  )
}
