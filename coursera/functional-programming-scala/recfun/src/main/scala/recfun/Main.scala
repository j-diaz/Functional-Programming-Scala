package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 && r == 0) 1
    else if (c < 0 || r < 0) 0
    else pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    if (chars.length > 1) {

      def paren(charz: List[Char], acc: Int, last: Char): Boolean =
        if (charz == Nil && acc == 0 && last == ')') true
        else if (charz == Nil && acc == 0 && last != ')') false
        else if (charz == Nil && acc != 0) false
        else if (charz.head == '(' && acc == 0) paren(charz.tail, acc + 1, '(')
        else if (charz.head == ')' && acc == 1) paren(charz.tail, acc - 1, ')')
        else if (charz.head != '(' || charz.head != ')') paren(charz.tail, acc, last)
        else false
      paren(chars, 0, 'x')

    } else {
      false
    }
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    def numberOfWays(amount: Int, l: List[Int], ways: Int): Int =
      if (l.isEmpty || amount < 0) 0
      else if (amount == 0) 1
      else {
         numberOfWays(amount, l.tail, ways) + numberOfWays(amount - l.head, l, ways)
      }

    numberOfWays(money, coins.tail, 0) + numberOfWays(money - coins.head, coins, 0)
  }

} //end of module
