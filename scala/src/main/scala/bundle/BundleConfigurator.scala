package bundle

import bundle.Model.Item.{Bundle, Product}
import bundle.Model.{Cart, Item}

import scala.annotation.tailrec

object BundleConfigurator {
  val p1 = Product("P1", 10)
  val p2 = Product("P2", 20)
  val p3 = Product("P3", 30)
  val p4 = Product("P4", 40)
  val p5 = Product("P5", 50)

  val products = List(p1, p2, p3, p4, p5)

  val b1: Bundle = Bundle(
    "B1",
    List(p1, p2),
    25
  )
  val b2: Bundle = Bundle(
    "B2",
    List(p1, p4),
    40
  )
  val b3: Bundle = Bundle(
    "B3",
    List(p3, p4),
    60
  )
  val b4: Bundle = Bundle(
    "B4",
    List(p1, p2, p3, p4),
    80
  )
  val b5: Bundle = Bundle(
    "B5",
    List(p1, p5),
    50
  )

  val bundles = List(b1, b2, b3, b4, b5)

  def findBundles(cartProducts: List[Product]): List[Bundle] = {
    bundles.filter(bundle =>
      bundle.products.forall(bundleProduct =>
        cartProducts.contains(bundleProduct)
      )
    )
  }

  def findBestBundle(
      cartProducts: List[Product],
      bundles: List[Bundle]
  ): Option[Bundle] = {
    val totalLeft = cartProducts.map(_.price).sum
    val bestBundle = bundles
      .filter(
        _.products.forall(bundleProduct => cartProducts.contains(bundleProduct))
      )
      .minByOption(bundle => totalLeft - bundle.price)

    bestBundle
  }

  def findResultItems(
      products: List[Product],
      bundles: List[Bundle]
  ): List[Item] = {
    @tailrec
    def findResultItem0(
        rest: List[Product],
        bundlesLeft: List[Bundle],
        acc: List[Item]
    ): List[Item] = {
      if (rest.isEmpty) acc
      else {
        val nextBundle = findBestBundle(rest, bundlesLeft)

        nextBundle match {
          case Some(bundle) =>
            val nextProducts =
              rest.filterNot(product => bundle.products.contains(product))
            val nextBundles = bundlesLeft.filterNot(_ == bundle)

            findResultItem0(nextProducts, nextBundles, acc :+ bundle)
          case None => acc ++ rest
        }
      }
    }

    findResultItem0(products, bundles, List.empty)
  }

  def select(cart: Cart): List[Item] =
    findResultItems(cart.products, findBundles(cart.products))

}
