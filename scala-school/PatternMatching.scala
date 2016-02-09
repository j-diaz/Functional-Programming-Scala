def f(s: String) = "f("+ s +")"

def g(s: String) = "g("+ s +")"

// compose mkaes a new function that composes other functions f(g(x))
val fComposeG = f _ compose g _

fComposeG("yay")

// andThen
val fAndThenG = f _ andThen g _

// Currying vs. Partial Application
// case statements

// What are case statements?
// It's a subclass of function called a PartialFunction

// Understanding PartialFunction
// A PartialFunction is only defined for certain values of the defined type.
// A PartialFunction (Int) => String might not accept every Int.
// isDefinedAt - is a method on PartialFunction that can be used to
// determine if the PartialFunction will accept a given argument.

val one: PartialFunction[Int, String] = { case 1 => "one" }

one.isDefinedAt(1)

one.isDefinedAt(2)

one(1)

val two: PartialFunction[Int, String] = { case 2 => "two" }

val three: PartialFunction[Int. String] = { case 3 => "three" }

val wildcard: PartialFunction[Int, String] = { case _ => "something else" }

val partial = one orElse two orElse three orElse wildcard

partial(5)

partial(3)

partial(2)

partial(1)

partial(0)


