package designmode

import scala.io.StdIn

object Order {
  def main(args: Array[String]): Unit = {
    println("请输入需要的pizza:")
    val pizza: String = StdIn.readLine()
    val orderPizza: Pizza = SimplePizzaFactory.createPizza(pizza)
    orderPizza.prepare()
  }

}
