package bundle

import bundle.Model.{Cart, Product}
import munit.FunSuite

class BundleConfiguratorSpec extends FunSuite {

  test("Bundle configurator should return a product for a product") {

    val cart = Cart(List(Product("P1", 10)))

    val result = BundleConfigurator.select(cart)

    val expected = "P1"

    assertEquals(result, expected)
  }

}
