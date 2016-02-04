// Lists
val numbers = List(1, 2, 3, 4)

// Sets
Set(1, 1, 2)

// Tuple
val hostPort = ("localhost", 80)

hostPort._1

hostPort._2

hostPort match {
  case ("localhost", port) => println("matched tuple 1")
  case (host, port) => println("matched tuple 2")
}

// Tuple has some special sauce for simply making Tuples of 2 values: ->
1 -> 2

// Maps
Map(1 -> 2)
Map("foot" -> "bar")

// Map(1 -> "one", 2 -> "two") expands Map((1, "one"), (2, "two"))
Map(1 -> Map("foo" -> "bar"))
def timesTwo(x: Int) = x * x
Map("timesTwo" -> { timesTwo(_) })

// Option
// Option is a container that may or may not hold something
trait Option[A] {
 def isDefined: Boolean
 def get: A
 def getOrElse(a: A): A
}

val numbersMap = Map("one" -> 1, "two" -> 2)
numbersMap.get("two")
numbersMap.get("three")

// The isDefined() is discouraged to be used like so
val result = if( numbersMap.get("two").isDefined ){
    numbersMap.get("two").get * 2
  } else {
    0
  }
}

// Instead use getOrElse()   
val result2 = numbersMap.get("two").getOrElse(0) * 2

val result3 = numbersMap.get("two") match {
  case Some(n) => n * 2
  case None => 0  
}
