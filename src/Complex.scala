import ComplexImplicits._

object ComplexImplicits {
  implicit def Double2Complex(value : Double) = new Complex(value, 0.0)
  implicit def Tuple2Complex(value : Tuple2[Double,Double]) = new Complex(value._1, value._2);
}

class Complex(val real : Double, val imag : Double) {

  def +(that: Complex) = new Complex(this.real + that.real, this.imag + that.imag)

  def -(that: Complex) = new Complex(this.real - that.real, this.imag - that.imag)
  
  def unary_~ = Math.sqrt(real*real+imag*imag)

  override def toString = real + " + " + imag + "i"

}

object Complex {
  def main(args : Array[String]) : Unit = {
       var a = new Complex(4.0,3.0)
       var b = new Complex(2.0,3.0)
       println(~a)  // 4.0 + 3.0i
       println(a + b)  // 6.0 + 8.0i
       println(a - b)  // 2.0 + 2.0i
       println(2 + b)  // 2.0 + 2.0i
       var c = a + (1.0,2.0)
       println(~c)
       var d = (1, 2)
  }
}