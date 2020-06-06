package test

object Test4 {
  def main(args: Array[String]): Unit = {
    println(fbn(5))
    println(sum(1, 3, 4, 6))
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
