/**
 * Created by zeek on 09-22-15.
 */

import scala.{Option => _, Either => _, _}

case class Some2[+A](get: A) extends Option[A]
case object None2 extends Option[Nothing]
sealed trait Option[+A] {

  def map[B](f: A => B): Option[B] = this match {
    case None2 => None2
    case Some2(a) => Some2(f(a))
  }

  def getOrElse[B >: A](default: => B): B =
    this match {
      case None => default
      case Some2(a) => a
    }

  def orElse[B >: A](ob: => Option[B]): Option[B] =
    this match {
      case None2 => ob
      case Some2(a) => Some2(a)
    }
}

object test{
  val x = Some2(2)



}