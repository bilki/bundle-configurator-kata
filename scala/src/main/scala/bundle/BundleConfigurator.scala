package bundle

import bundle.Model.{Bundle, Cart, Product}

object BundleConfigurator {
  val p1 = Product("P1", 10)
  val p2 = Product("P2", 20)
  val p3 = Product("P3", 30)
  val p4 = Product("P4", 40)
  val p5 = Product("P5", 50)

  val products = List(p1, p2, p3, p4, p5)

  val bundles = List(
    Bundle(
      "B1", List(p1, p2), 25
    ),
    Bundle(
      "B2", List(p1, p4), 40
    ),
    Bundle(
      "B3", List(p3, p4), 60
    ),
    Bundle(
      "B4", List(p1, p2, p3, p4), 80
    ),
    Bundle(
      "B5", List(p1, p5), 50
    ),
  )

  def findBundles(products: List[Product]): List[Bundle] = {
    bundles.filter(bundle =>
      bundle.products.forall(product => products.contains(product))
    )
  }

  def findResult(products: List[Product], bundles: List[Bundle]): String = {
    val productsWithoutBundle = products.filterNot(product => bundles.exists(_.products.contains(product)))

    (bundles.map(_.name) ++ productsWithoutBundle.map(_.name)).mkString(",")
  }

  def select(cart: Cart): String = cart.products match {
    case Nil => ???
    case product :: Nil => product.name
    case products => findResult(products, findBundles(products))
  }

}
