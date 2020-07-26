package designmode

object SimplePizzaFactory {
  def createPizza(s: String): Pizza = {
    var pizza: Pizza = null
    if (s.equals("greek"))
      pizza = new GreekPizza
    else if (s.equals("pepper"))
      pizza = new PepperPizza
    pizza

  }
}
