import scala.annotation.tailrec
object chapter3 {
  println("Welcome to the Scala worksheet")
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

    def drop[A](n: Int, ls: List[A]): List[A] =
      ls match {
        case Nil => ls
        case Cons(h,t) => {
          if(n == 0) ls
          else drop(n - 1, t)
        }
      }

     def append[A](a1: List[A], a2: List[A]): List[A] =
      a1 match {
        case Nil => a2
        case Cons(h,t) => Cons(h, append(t, a2))
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

    def foldRight[A,B](as: List[A], b: B)(f: (A, B) => B ): B =
      as match {
        case Nil => b
        case Cons(x ,xs) => f(x, foldRight(xs, b)(f))
      }

    def lengthWithOutFold[A](as: List[A]): Int =
      as match{
        case Nil => 0
        case Cons(x, xs) => 1 + lengthWithOutFold(xs)
      }

    def length[A](as: List[A]): Int =
      foldRight(as, 0)((_,acum) => 1 + acum)

    @tailrec
    def foldLeft[A,B](as: List[A], z: B)(f: (B,A) => B): B =
      as match {
        case Nil => z
        case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
      }

    def sumFoldLeft(l: List[Int]): Int =
      foldLeft(l, 0)((x: Int, y :Int) => x + y)

    def productFoldLeft(l: List[Int]): Int =
      foldLeft(l, 1)((x, y) => x * y)

    def lengthFoldLeft(l: List[Int]): Int =
      foldLeft(l, 0)((y, _) => y + 1)

//    def concat[A](ls: List[List[A]]): List[A] =
//      ls match {
//        case Nil => Nil:List[A]
//        case Cons(x, xs) => Cons(concat(x), concat(xs))
//      }

    def reverse[A](ls: List[A]): List[A] =
      foldLeft(ls, Nil: List[A])((acc, h) => Cons(h, acc))

    def addOne[A](ls: List[Int]): List[Int] =
      foldRight(ls, Nil:List[Int])((h,t) => Cons(h + 1, t))

    def convert(ls: List[Double]): List[String] =
      foldRight(ls, Nil:List[String])((h,t) => Cons(h.toString, t))

    def map[A,B](as: List[A])(f: A => B): List[B] =
      foldRight(as, Nil:List[B])((h,t) => Cons(f(h), t))

    def filter[A](as: List[A])(f: A => Boolean): List[A] =
      foldRight(as, Nil:List[A])((h,t) => if(f(h)) Cons(h,t)
                                          else filter(t)(f))

    //def filterMap[A,B](as: List[A])(f: A => List[B]): List[A] =

//
//    def appendFoldLeft[A](a1: List[A], a2: List[A]): List[A] =
//      foldLeft(a1, Nil)((tail, h) => Cons())
	}
  val y = List(1,2,3,4)


	
	val x = List(1,2,3,4,5) match {
		case Cons(x, Cons(2, Cons(4,_))) => x
		case Nil => 42
		case Cons(x, Cons(y, Cons(3, Cons(4, _) ))) => x + y
		case _ => 101
	}
	val test = List(1,2,3,4)
		//List.tail(test)
  List.drop(test, 1)
  List.dropWhile(test, (x: Int) => x % 2 == 0)
	List.init(test)
  println("why work now")
  List.tail(test)
  List.drop(2, test)
  println("Appending")
  val test2 = List(5,6,7,8,9)
  List.append(test, test2)
  val test3 = List(1,2,3)
  List.foldRight(test3, 0)((x, y) => x + y)
  List.length(test3)
  List.foldLeft(test3, 0)((x, y) => x + y)
  List.productFoldLeft(test3)
  List.sumFoldLeft(test3)
  List.lengthFoldLeft(test3)
  List.reverse(test3)
  List.addOne(test3)
  List.filter(test3)( (x: Int) => x <= 2)
  sealed trait Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  object Tree{
    def size[A](node: Tree[A]): Int =
      node match {
        case Leaf(v: A) => 1
        case Branch(l: Tree[A],r: Tree[A]) => 1 + size(l) + size(r)
      }

    def maximum(node: Tree[Int]): Int =
      node match {
        case Leaf(v: Int) => v
        case Branch(l: Tree[Int], r:Tree[Int]) => {
          val left = maximum(l)
          val right = maximum(r)
          if(left < right) right
          else             left
        }
      }

    def depth[A](node: Tree[A]): Int =
      node match {
        case Leaf(v: A) => 0
        case Branch(l: Tree[A], r: Tree[A]) => {
          if(size(node) > 1) depth(l).max(depth(r))
          else 1
        }
      }
  }

  val lee = Leaf[Int](1)

  val luu = Leaf[Int](2)
  val tee = Branch[Int](lee,luu)
  Tree.size(tee)
  Tree.maximum(tee)
  Tree.depth(tee)
}
