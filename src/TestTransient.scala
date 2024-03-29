object Test {

  def main(args: Array[String]){

    //----------------
    // ClassA - with @transient
    //----------------

    val objA1 = ClassA("world");

    println(objA1);
    // This works as expected as follows:
    //   "Good morning."
    //   "Hello, world"

    saveObject("testA.dat", objA1);

    val objA2 = loadObject("testA.dat").asInstanceOf[ClassA];

    println(objA2);
    // I expect this will work as follows:
    //   "Good morning."
    //   "Hello, world"
    // but actually it works as follows:
    //   "null"



    //----------------
    // ClassB - without @transient
    // this works as expected
    //----------------

    val objB1 = ClassB("world");

    println(objB1);
    // This works as expected as follows:
    //   "Good morning."
    //   "Hello, world"

    saveObject("testB.dat", objB1);

    val objB2 = loadObject("testB.dat").asInstanceOf[ClassB];

    println(objB2);
    // This works as expected as follows:
    //   "Hello, world"

  }

  case class ClassA(name: String){

    @transient private lazy val msg = {
      println("Good morning.");
      "Hello, " + name;
    }

    override def toString = msg;

  }

  case class ClassB(name: String){

    private lazy val msg = {
      println("Good morning.");
      "Hello, " + name;
    }

    override def toString = msg;

  }

  import java.io.FileInputStream;
  import java.io.FileOutputStream;
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;

  def saveObject(fname: String, obj: AnyRef){
    val fop = new FileOutputStream(fname);
    val oop = new ObjectOutputStream(fop);
    try {
      oop.writeObject(obj);
    } finally {
      oop.close();
    }
  }

  def loadObject(fname: String): AnyRef = {
    val fip = new FileInputStream(fname);
    val oip = new ObjectInputStream(fip);
    try {
      oip.readObject();
    } finally {
      oip.close();
    }
  }

}