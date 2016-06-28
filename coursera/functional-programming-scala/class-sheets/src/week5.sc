
object module {

  println("Week5")

  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }

  // key is to pattern match on the result's first element
  // dependent
  // xs ::: ys - the response depends on the first elements
  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case List(x) => x :: ys
    case z :: zs => z :: concat(zs, ys)
  }

  val l1 = List(1, 2, 3)
  val l2 = List(4, 5, 6)
  val l3 = concat(l1, l2)

  def reverse[T](xs: List[T]): List[T] = xs match {
    case List() => List()
    case y :: ys => reverse(ys) ++ List(y)
  }

  val r1 = reverse(l3)

  def removeAt[T](xs: List[T], n: Int): List[T] =
    xs match {
      case List() => throw new Error("Empty list")
      case y :: ys => if (n == 0) ys else removeAt(ys ++ List(y), n - 1).reverse
    }

  removeAt(r1, 4)

val nums = List(2, -4, 5, 7, 1)


  nums filter (x => x > 0)
  nums filterNot (x => x > 0)
  nums partition (x => x > 0)

  nums takeWhile (x => x > 0)
  nums dropWhile (x => x > 0)
  nums span (x => x > 0)



  val data = List("a","a","a","b", "c", "c", "a")


  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xsl => {
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
    }
  }

  pack(data)

  def econde[T](xs: List[T]): List[(T,Int)] = pack(xs) map (ys => (ys.head, ys.length))

  def isPrime(n: Int): Boolean = (2 until n) forall (x => n % x != 0)

  isPrime(13)

  isPrime(22)
}

