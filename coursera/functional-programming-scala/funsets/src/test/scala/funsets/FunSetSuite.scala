package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("singletonSet(2) contains 2"){
    new TestSets {
      assert(!contains(s2, 4), "Singleton")
      assert(contains(s2, 2), "Singleton")

    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }


  test("intersect contains all elements that are both in a and b set") {
    new TestSets {
      val a = singletonSet(1)
      val s = union(s1, s2)

      val res = intersect(s, a)
      assert(contains(res, 1), "Intersection 1")
      assert(!contains(s, 3), "Intersection 3")
    }
  }

  test("diff contains all elements that are set a but not in set b") {
    new TestSets {
      val z = union(s1, s3)
      val s = union(s1, s2)

      val res = diff(s,z)
      assert(contains(res, 2), "Intersection 1")
      assert(!contains(s, 3), "Intersection 3")
    }
  }

  test("filter contains only elements that comply with the given function (all even)") {
    new TestSets {
      val z = union(s1, s3)
      val s = union(s1, s2)
      val tres = union(s,z)

      val res = filter(tres, (x) => x % 2 == 0)
      assert(contains(res, 2), "filter all even")
      assert(!contains(res, 3), "filter all even")

      val v = union(s1, s2)
      val b = union(s3, singletonSet(4))
      val n = union(v,b)
      val m = union(n, singletonSet(5))

      val rez = filter(m, (x) => x % 13 == 0)
      assert(!contains(rez, 13), "filter all divisible by 5")

      val rez2 = filter(m, (x) => x % 5 == 0)
      println("After filtering divisble by 5: "+ FunSets.toString(rez2))
      assert(!contains(rez2, 2), "filter all divisible by 5")
    }
  }

  test("forAll verifies elements in a set conform with the given function") {
    new TestSets {
      val x = singletonSet(4)
      val y = singletonSet(6)
      val z = union(x, y)
      val k = union(z, singletonSet(2))

      val res = forall(k, (x) => x % 2 == 0)
      println("for all - k: "+FunSets.toString(k) +" :"+res )
      assert(res, "forAll comply with being even on a set with all even elements")
      assert(res == true)

      val set2 = union(s1, singletonSet(5))
      val res2 = forall(set2, (x) => x % 2 == 0)
      println(FunSets.toString(set2) + ": "+res2)
      assert(res2 == false, "forAll comply with being even on a set with no even elements")

      val set3 = union(s2, singletonSet(3))
      val res3 = forall(set3, (x) => x % 2 == 0)
      println(FunSets.toString(set3) + ": "+res3)
      assert(res3 == false)
    }
  }

  test("Checks if at least one element exists that complies with the given function (all even)") {
    new TestSets {
      val z = union(s1, s3)
      val s = union(s1, s2)
      val tres = union(s,z)
      println(FunSets.toString(tres))
      val res = exists(tres, (x) => x % 2 == 0)
      assert(res, "At least one even")

      val zz = filter(tres, (x) => x % 5 == 0)
      println("zz: "+FunSets.toString(zz))
      val res2 = exists(tres, (x) => x % 5 == 0)
      println("Exists some element divisible by 5: "+res2)
      assert(!res2, "At least one is divisible by 5")
    }
  }

  test("Map transform a set with given function (squared)") {
    new TestSets {
      val z = union(s1, s3)
      val s = union(s1, s2)
      val tres = union(s,z)

      val res = map(tres, (x) => x * x)
      assert(contains(res,4), "Contains 4")
      assert(contains(res,9), "Contains 9")
    }
  }


}
