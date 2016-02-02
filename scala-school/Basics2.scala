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
