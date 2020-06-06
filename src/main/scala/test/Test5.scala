package test

object Test5 {
  def main(args: Array[String]): Unit = {
    val f = new People("f")

  }

  def sum(a: Int, b: Int) = {
    println("sum")
    a + b
  }

  def printstar(n: Int) = {
    for (i <- 1 to n; j = n - i; x = 2 * (i - 1) + 1) {
      for (i <- 1 to j)
        print(" ")
      for (i <- 1 to x)
        print("*")
      println
    }
  }

  /*1、一个数字如果为正数，则它的signum为1;如果是负数,则signum为-1;如果为0,则signum为0.编写一个函数来计算这个值*/
  def calcSignum(n: Int) = {
    if (n < 0) -1 else if (n > 0) 1 else 0
  }

  class People() {
    var name = ""
    var age = 1
    println(s"name=$name,age=${age}")

    def this(name: String) {
      this
      this.name = name
    }
  }


}

class MethodExec {
  def printRec() = {
    for (i <- 1 to 10) {
      for (j <- 1 to 8)
        print("*")
      println()
    }
  }
}

class Dog {
  var name: String = _
  var age: Int = _
  var weight: Double = _

  def say() = {
    "name=" + name + ",age=" + age + ",weight=" + weight
  }

}