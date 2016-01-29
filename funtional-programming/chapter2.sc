import scala.annotation.tailrec
import sun.security.util.Length

object chapter2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  println("hi")                                   //> hi
	class Coffee{
	 val price: Double = { 10.1}
	}
   
  case class CreditCard()

// charge class
  case class Charge(cc: CreditCard, amount: Double) {
  	
  	def combine(other: Charge): Charge =
  		if (cc == other.cc)
  			Charge(cc, amount + other.amount)
  		else
  			throw new Exception("Can't combine charges to different cards")
  }

	// cafe
	class Cafe{
	
		def buyCofee(cc: CreditCard): (Coffee, Charge) = {
			val cup = new Coffee()
			(cup, Charge(cc, cup.price))
		}
	}

	
	def fib(n: Int):Int = {
		@annotation.tailrec
		def loop(a: Int, prev: Int, curr: Int): Int =
			if(a == 0) prev
			else loop(a - 1, curr, prev + curr)
		
		loop(n, 0, 1)
	}                                         //> fib: (n: Int)Int
			
	fib(3)                                    //> res0: Int = 2

	def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
		
		def loop(a: Array[A], index: Int): Boolean = {
			if(index <= 0 ) true
			else if(!ordered(a(index), a(index-1) )) false
			else loop(as, index - 1)
		}
		loop(as, as.length - 1)
	}                                         //> isSorted: [A](as: Array[A], ordered: (A, A) => Boolean)Boolean
  isSorted(Array(1,2,3,4), (x: Int, y: Int) => x > y)
                                                  //> res1: Boolean = true

	def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
			(a: A) => (b: B) => f(a,b)
	}                                         //> curry: [A, B, C](f: (A, B) => C)A => (B => C)
	curry((a: Int,b: String) => Boolean)      //> res2: Int => (String => Boolean.type) = <function1>
	
	def uncurry[A,B,C] (f: A => B => C): (A, B) => C = {
	 (a, b) => f(a)(b)
	}                                         //> uncurry: [A, B, C](f: A => (B => C))(A, B) => C
	
	def compose[A,B,C](f: B => C, g: A => B): A => C = {
		a => f(g(a))
	}                                         //> compose: [A, B, C](f: B => C, g: A => B)A => C



				
}