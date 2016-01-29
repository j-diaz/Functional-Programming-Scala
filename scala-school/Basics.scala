class Calculator(){

	val brand: String = "HP"
	def add(m: Int, n: Int): Int = m + n
  
}

class Calculator(brand: String) {
 
 val color: String = if (brand == "TI") {
	"blue"
  } else if (brand == "HP") {
	"black"
  } else {
	"white"
  }
   // An instance method
   def add(m: Int, n: Int): Int = m + n
}
