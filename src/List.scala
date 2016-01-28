

import scala.annotation.tailrec

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]
object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def tail[A](ls: List[A]): List[A] = ls match {
    case Nil => sys.error("tail of empty list")
    case Cons(_, t) => t
  }

  def setHead[A](h: A, l: List[A]): List[A] =
    l match {
      case Nil => sys.error("cannot add null element")
      case Cons(x, xs) => Cons(h, xs)
    }

  def drop[A](ls: List[A], n: Int): List[A] = {
    if (n <= 0) ls
    else ls match {
      case Nil => Nil
      case Cons(_, t) => drop(t, n - 1)
    }
  }

  def drop[A](n: Int, ls: List[A]): List[A] =
    ls match {
      case Nil => ls
      case Cons(h, t) => {
        if (n == 0) ls
        else drop(n - 1, t)
      }
    }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  def init[A](l: List[A]): List[A] =
    l match {
      case Nil => sys.error("init of empty list")
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
    }


  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
    l match {
      case Cons(h, t) if f(h) => dropWhile(t, f)
      case _ => l
    }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def foldRight[A, B](as: List[A], b: B)(f: (A, B) => B): B =
    as match {
      case Nil => b
      case Cons(x, xs) => f(x, foldRight(xs, b)(f))
    }

  def lengthWithOutFold[A](as: List[A]): Int =
    as match {
      case Nil => 0
      case Cons(x, xs) => 1 + lengthWithOutFold(xs)
    }

  def length[A](as: List[A]): Int =
    foldRight(as, 0)((_, acum) => 1 + acum)

  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }

  def sumFoldLeft(l: List[Int]): Int =
    foldLeft(l, 0)((x: Int, y: Int) => x + y)

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
    foldRight(ls, Nil: List[Int])((h, t) => Cons(h + 1, t))

  def convert(ls: List[Double]): List[String] =
    foldRight(ls, Nil: List[String])((h, t) => Cons(h.toString, t))

  def map[A, B](as: List[A])(f: A => B): List[B] =
    foldRight(as, Nil: List[B])((h, t) => Cons(f(h), t))

  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    foldRight(as, Nil: List[A])((h, t) => if (f(h)) Cons(h, t)
    else filter(t)(f))
}