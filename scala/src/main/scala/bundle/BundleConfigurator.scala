package bundle

import bundle.Model.{Bundle, Cart, Product}

object BundleConfigurator {
  val p1 = Product("P1", 10)
  val p2 = Product("P2", 20)

  val products = List(
    p1,
    p2,
    Product("P3", 30),
    Product("P4", 40),
    Product("P5", 50)
  )

  val bundles = List(
    Bundle(
      "B1", List(p1, p2), 25
    )
  )

  def select(cart: Cart): String = cart.products match {
    case Nil => ???
    case product :: Nil => product.name
    case products =>
      bundles.find(bundle => bundle.products.forall(product => products.contains(product))).fold("")(_.name)
  }

}
