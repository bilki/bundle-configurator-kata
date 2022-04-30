package bundle

import bundle.Model.Item.{Bundle, Product}
import bundle.Model.{Cart, Item}

object BundleConfigurator {
  val p1 = Product("P1", 10)
  val p2 = Product("P2", 20)
  val p3 = Product("P3", 30)
  val p4 = Product("P4", 40)
  val p5 = Product("P5", 50)

  val products = List(p1, p2, p3, p4, p5)

  val b1: Bundle = Bundle(
    "B1", List(p1, p2), 25
  )
  val b2: Bundle = Bundle(
    "B2", List(p1, p4), 40
  )

  val b3: Bundle = Bundle(
    "B3", List(p3, p4), 60
  )
  val b4: Bundle = Bundle(
    "B4", List(p1, p2, p3, p4), 80
  )
  val b5: Bundle = Bundle(
    "B5", List(p1, p5), 50
  )

  val bundles = List(b1, b2, b3, b4, b5)

  def findBundles(products: List[Product]): List[Bundle] = {
    bundles.filter(bundle =>
      bundle.products.forall(product => products.contains(product))
    )
  }

  def findResult(products: List[Product], bundles: List[Bundle]): List[Item] = {
    val productsWithoutBundle = products.filterNot(product => bundles.exists(_.products.contains(product)))

    (bundles ++ productsWithoutBundle)
  }

  def select(cart: Cart): List[Item] = cart.products match {
    case Nil => ???
    case product :: Nil => List(product)
    case products => findResult(products, findBundles(products))
  }

}
