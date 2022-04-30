package bundle

import bundle.Model.Cart
import org.scalacheck.{Arbitrary, Gen}

object Arbitraries {
  import BundleConfigurator._

  val productsGen = Gen.someOf(products).map(_.toList)
  val cartGen     = productsGen.map(Cart)

  implicit val cartArb = Arbitrary(cartGen)
}
