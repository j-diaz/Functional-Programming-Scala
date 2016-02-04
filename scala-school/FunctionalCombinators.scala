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
