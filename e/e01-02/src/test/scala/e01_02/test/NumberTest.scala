package e01_02.test

import zio._
import zio.Console._
import zio.test._
import zio.test.Assertion._
import zio.test.testEnvironment._
import e01_02._

import e01_02._
import NumberOps._

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
    }
  )
}
