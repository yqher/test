package test

import java.sql.Timestamp

object Test4 {
  def main(args: Array[String]): Unit = {
    /*val str: String = intToTimeString(68,"2020-09-01")
    println(str)*/
    val timestamp: Timestamp = Timestamp.valueOf("2020-09-02 09:34:45")
    val timestamp1: Timestamp = Timestamp.valueOf("2020-09-02 09:33:45")
    println(timestamp.compareTo(timestamp1))
  }


  def intToTimeString(timeInt: Int, StatDay: String): String = {
    val hourInt: Int = (timeInt - 1) / 60
    val minInt: Int = (timeInt - 1) % 60
    var hour: String = null
    var min: String = null
    if (hourInt < 10) {
      hour = "0" + hourInt.toString
    } else {
      hour = hourInt.toString
    }
    if (minInt < 10) {
      min = "0" + minInt.toString
    } else {
      min = minInt.toString
    }
    StatDay + " " + hour + ":" + min + ":00"
  }

  def fbn(n: Int): Int = {
    if (n == 1 || n == 2)
      1
    else
      fbn(n - 1) + fbn(n - 2)
  }

  def sum(arg: Int*): Int = {
    var total: Int = 0
    for (i <- arg)
      total = total + i

    total
  }
}
