object chapter3 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //
  ////////////////// Inmutable Datastructures ////////////////////////
	sealed trait List[+A]
	case object Nil extends List[Nothing]
	case class Cons[+A](head: A, tail: List[A]) extends List[A]
	
	object List{
		
		def sum(ints: List[Int]): Int = ints match {
			case Nil => 0
			case Cons(x,xs) => x + sum(xs)
		}
		
		def product(ds: List[Double]): Double = ds match {
			case Nil => 1
			case Cons(0.0, _) => 0.0
			case Cons(x,xs) => x * product(xs)
		}
		
		def tail[A](ls: List[A]): List[A] = ls match{
			case Nil => sys.error("tail of empty list")
			case Cons(_, t) => t
		}
		
		def setHead[A](h: A, l: List[A]): List[A] =
		  l match{
				case Nil => sys.error("cannot add null element")
				case Cons(x, xs) => Cons(h, xs)
			}
		
		def drop[A](ls: List[A], n: Int): List[A] =
		{
			if(n <= 0) ls
			else ls match{
					case Nil => Nil
					case Cons(_, t) => drop(t, n - 1)
				}
		}
		
		/*def dropAll[A](ls: List[A], f: A => Boolean): List[A] = {
			ls match {
				case Nil => Nil
				case Cons(x, xs) =>
					 if( f(x) ){
							dropAll(xs, f)
					 } else Cons(x, dropAll(xs,f) )
			}
		}*/
		
		/*def init[A](ls: List[A]): List[A] =
			ls match {
				case Nil => sys.error("empty list")
				case Cons(_,Nil) => ls
				case Cons(h,t) => Cons(h, init(t) )
			}*/
		
		def init[A](l: List[A]): List[A] =
		  l match {
		    case Nil => sys.error("init of empty list")
		    case Cons(_,Nil) => Nil
		    case Cons(h,t) => Cons(h,init(t))
		 }
		
					
		def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
		  l match {
		    case Cons(h,t) if f(h) => dropWhile(t, f)
		    case _ => l
  	}
		
		def apply[A](as: A*):List[A] =
			if(as.isEmpty) Nil
			else Cons(as.head, apply(as.tail: _*))
		
	}
	
	val x = List(1,2,3,4,5) match {
		case Cons(x, Cons(2, Cons(4,_))) => x
		case Nil => 42
		case Cons(x, Cons(y, Cons(3, Cons(4, _) ))) => x + y
		case _ => 101
	}                                         //> x  : Int = 3
	val test = List(1,2,3,4)                  //> test  : chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Nil))))
		//List.tail(test)
	 List.drop(test, 1)                       //> res0: chapter3.List[Int] = Cons(2,Cons(3,Cons(4,Nil)))
		List.dropWhile(test, (x: Int) => x % 2 == 0)
                                                  //> res1: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Cons(4,Nil))))
	List.init(test)                           //> res2: chapter3.List[Int] = Cons(1,Cons(2,Cons(3,Nil)))
}