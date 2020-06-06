package com.atguigu

import java.util.{HashMap => JavaHashMap}

object testpack {
  def main(args: Array[String]): Unit = {
    pr()
    val map = new JavaHashMap(5)
    println(map.size())
  }
}
