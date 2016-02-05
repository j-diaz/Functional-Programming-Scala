//
// Functional Combinators
//

// map
val numbers = List(1, 2, 3, 4)

numbers.map((i: Int) => i * 2)

def timesTwo(i: Int): Int = i * 2

numbers.map(timesTwo)

// foreach
numbers.foreach((i: Int) => i * 2)

val doubled = numbers.foreach((i: Int) => i * 2)

// filter
numbers.filter((i: Int) => i % 2 == 0)

def isEven(i: Int) = i % 2 == 0

numbers.filter(isEven _)

// zip
List(1, 2, 3).zip(List("a", "b", "c"))

// partion
val numbers2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
numbers2.partition(_ % 2 == 0)

// find
numbers2.find((i: Int) => i > 5)

//
// drop & dropWhile
//

// drop drops the first i elements
numbers2.drop(5)

// dropWhile
numbers2.dropWhile(_ % 2 != 0)

//
// foldLeft
//
numbers2.foldLeft(0)((m: Int, n: Int) => m + n)

numbers2.foldLeft(0) {(m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }

// foldright exists

// flatten
List( List(1, 2), List(3, 4)).flatten

// flatMap - should be called mapFlat
// runsthe .map().flatten
val nestedNumbers = List(List(1, 2), List(3, 4))

nestedNumbers.flatMap(x => x.map(_ * 2))
// res0: List[Int] = List(2, 4, 6, 8)

// Think of it like this
nestedNumbers.map((x => x.map(_ * 2)).flatten
// res1: List[Int] = List(2, 4, 6, 8)

//
// Generalized functional combinators
// Everything built in .map .flatMap .flatten etc
// Can be written using "fold"

def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
  numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) => 
    fn(x) :: xs
}

ourMap(numbers, timesTwo(_))
//res0: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

// Map
// All functional combinators shown work on Maps, too.
// Maps can be thought of as a list of pairs so the functions 
// your write work on a pair of the keys and values in the Map

val extends = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
//extends: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101), (joe,201))

// filtering out entry whose phone extension is lower than 200
extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)
//res0: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101))

// We can use pattern matching to extect the key and value nicely
scala> extensions.filter({case (name, extension) => extension < 200})
//res0: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101))
