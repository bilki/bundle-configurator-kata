package bundle

import bundle.Model.{Cart, Item}
import munit.ScalaCheckSuite
import org.scalacheck.Prop._
import bundle.Model.Item.Bundle
import bundle.Model.Item.Product
import Arbitraries._

class BundleConfiguratorSpec extends ScalaCheckSuite {

  import BundleConfigurator._

  Map(
    List(p1)         -> List(p1),
    List(p1, p2)     -> List(b1),
    List(p2, p1)     -> List(b1),
    List(p1, p2, p3) -> List(b1, p3),
    List(p1, p3, p2) -> List(b1, p3), // Same as previous with unordered input
    List(p1, p3, p4) -> List(p1, b3),
    List(p1, p2, p3, p4) -> List(b4),
    List(p2, p4)         -> List(p2, p4),
    List(p2, p3, p4)     -> List(p2, b3),
    List(p3, p4)         -> List(b3)
  ).foreach { case (input, expected) =>
    test(s"Bundle configurator should return ${expected
        .map(_.name)
        .mkString(",")} for input ${input.map(_.name).mkString(",")}") {
      val cart = Cart(input)

      val result = select(cart)

      assertEquals(result.toSet: Set[Item], expected.toSet: Set[Item])
    }
  }

  test("For any given set of products, all products must be contained within result") {
    forAll { cart: Cart =>
      val result = select(cart)

      val allProducts: List[Product] = result.flatMap {
        case p: Product => List(p)
        case b: Bundle => b.products
      }

      assertEquals(cart.products.size, allProducts.size)
      assertEquals(cart.products.toSet, allProducts.toSet)
    }
  }
}
