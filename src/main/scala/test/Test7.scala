package test

object Test7 {
  def main(args: Array[String]): Unit = {
    /*  val n1 = 1
      val n2 =4
      val oper = "5"
      var res = 0
      oper match {
        case "+"=> res = n1 + n2
        case "-"=> res = n1 - n2
        case "*"=> res = n1 * n2
        case "/"=> res = n1 / n2
        case _ => println("oper error")
      }
      println(res)*/
    /*def plus(i:Int) = i+9
      val ints: List[Int] = List(1,2,4).map(plus(_))
      println(ints)*/

    //List(1,2,3).map((x)=>x+1).foreach(println(_))
    def sum(x: Int)(y: Int) = x + y

    println(sum(1)(2))
  }


}
