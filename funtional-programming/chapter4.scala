
sealed trait Option2[+A] {

  def map[B] (f: A => B): Option2[B] = this match {
    case None2 => None2
    case Some2(a) => Some2(f(a)) 	
  } 

  def flatMap[B] (f: A => Option2[B]): Option2[B] = this match {
    case None2 => None2
    case Some2(a) => f(a)
  } 
 
  def getOrElse[B >: A] (default: => B): B = this match {
    case None2 => default
    case Some2(a) => a
  }
 
  def orElse[B >: A] (obs: => Option2[B]): Option2[B] = this match {
    case None2 => obs 
    case _ => this
  }
 
  def filter(f: A => Boolean): Option2[A] = this match {
    case Some2(a) if f(a) => this 
    case _ => None2
  }

}

case class Some2[+A] (get: A) extends Option2[A]

case object None2 extends Option2[Nothing]

object Option2 {
  def mean(xs: Seq[Double]): Option2[Double] = {
    if(xs.isEmpty) None2
    else Some(xs.sum / xs.length)
  } 

  def variance(xs: Seq[Double]): Option2[Double] = {
  
  }

}
