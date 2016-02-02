/**
 * Expressions
 */
1 + 1

/**
 * Values
 */
val two = 1 + 1

/**
 * Variables
 */
var name = "steve"
name = "marius"

/**
 * Functions 
 */
def addOne(m: Int): Int = m + 1

val three = addOne(2)

/**
 * Anonymous Functions
 */
(x: Int) => x + 1

val addOne = (x: Int) => x + 1

{ i: Int => 
 println("hello world")
 i * 2
}

/**
 * Partial application
 */
def adder(m: Int, n: Int) = m + n

/**
 * Curriend functions
 */
def multiply(n: Int)(m: Int): Int = m * n

/**
 * Variable length arguments
 */
def capitalizeAll(args: String*) = {
  args.map { arg => 
    arg.capitalize
  }
}

/*
* Class
*/
class Calculator(){

  val brand: String = "HP"
  def add(m: Int, n: Int): Int = m + n

}

class Calculator(brand: String) {
 
 val color: String = if (brand == "TI") {
	"blue"
  } else if (brand == "HP") {
	"black"
  } else {
	"white"
  }
   // An instance method
   def add(m: Int, n: Int): Int = m + n
}

/*
* Expressions
*/

/**
 * Inheritance
 */
class ScientificCalculator(brand: String) extends Calculator(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

/**
* Overloading methods
*/
class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
  def log(m: Int): Double = log(m, math.exp(1))
}

/**
 * When do you want a Trait instead of an Abstract Class? If you want to define an interface-like type, you might find it difficult to choose between a trait or an abstract class. Either one lets you define a type with some behavior, asking extenders to define some other behavior. Some rules of thumb:

Favor using traits. It’s handy that a class can extend several traits; a class can extend only one class.
If you need a constructor parameter, use an abstract class. Abstract class constructors can take parameters; trait constructors can’t. For example, you can’t say trait t(i: Int) {}; the i parameter is illegal.
* Traits
 */
trait Car {
  val brand: String
}

trait Shiny {
  val shineRefraction: Int
}

class BMW extends Car {
  val brand = "BMW"
}
class BMW extends Car with Shiny {
  val brand = "BMW"
  val shineRefraction = 12
}

/**
 * Types: Generics
 */
trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def delete(key: K)
}
