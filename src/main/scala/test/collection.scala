package test

import java.util

object collection {
  def main(args: Array[String]): Unit = {
    val array: Array[Array[Int]] = Array.ofDim[Int](3, 5)
    for (item1 <- array) {
      println()
      for (item2 <- item1)
        print(item2)
    }
    val ints = new util.ArrayList[String]()

  }

}
