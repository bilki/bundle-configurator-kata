package bundle

import bundle.Model.{Bundle, Cart, Product}

object BundleConfigurator {

  val products = List(
    Product("P1", 10),
    Product("P2", 20),
    Product("P3", 30),
    Product("P4", 40),
    Product("P5", 50)
  )

  val bundles = List.empty[Bundle]

  def select(cart: Cart): String = cart.products match {
    case Nil => ???
    case product :: Nil => product.name
    case products => ???
  }

}
