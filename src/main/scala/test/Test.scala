package test

/**
  * test code
  */
object Test {
  /**
    * 程序入口
    *
    * @param args 参数
    */
  def main(args: Array[String]): Unit = {

    implicit def double2Int(d: Double): Int = {
      d.toInt
    }

    val d: Int = 3.5
  }

}

trait A {
  println("A")
}

trait B extends A {
  println("B")
}

class E {
  println("E")
}

class F extends E {
  println("F")
}