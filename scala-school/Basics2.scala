/**
* Apply
*/
class Foo {}

object FooMaker {
   def apply() = new Foo
}

val newFoo = FooMaker()

// OR

class Bar {
  def apply() = 0
}

val bar = new Bar

bar()

/**
* Objects
*/
object Timer {
  var count = 0 
  
  def currentCount(): Long = {
   count += 1
   count
  }
}

Timer.currentCount()

/**
* Classes and Objects can have the same name. The object is called a ‘Companion Object’. We commonly use Companion Objects for Factories.
* Here is a trivial example that only serves to remove the need to use ‘new’ to create an instance.
*/
class Bar(foo: String)
object Bar {
  def apply(foo: String) = new Bar(foo)
}

/**
* Functions are Objects
*/

object addOne extends Function1[Int, Int] {
	def apply(m: Int): Int = m + 1
}

addOne(1)

// The syntactic sugar of apply helps unify the duality of object and functional programming. You can pass classes around and use them as 
// functions and functions are just instances of classes under the covers.

// Classes can also exendd Function and those instances can be called with ()
class AddOne extends Function[Int, Int]  {
	def apply(m: Int): Int = m + 1
} 

val plusOne = new AddOne()
// call it with argument
plusOne(1)

/**
* Short hand for extends Function[Int, Int] is extends (Int => Int)
*/
class AddOne extends (Int => Int){
	def apply(m: Int): Int = m + 1
}

// Packages

// Values and functions cannot be outside of a class or object. 
// Objects are usefull for organizing static functions
