object TestListMap {
  val x = "abc few".r
  
  def main(args : Array[String]) {
    System.out.println(x)
    val l = List(1,2,3,4)
    val lx = l map (x => if (x>1) x)
    System.out.println(lx);
  }
}