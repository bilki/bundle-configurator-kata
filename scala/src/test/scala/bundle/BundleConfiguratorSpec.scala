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

  test("Bundle configurator should return B1 for P1,P2") {
    val cart = Cart(
      List(
        Product("P1", 10),
        Product("P2", 20)
      )
    )

    val result = BundleConfigurator.select(cart)

    val expected = "B1"

    assertEquals(result, expected)
  }

  test("Bundle configurator should return B1 for P2,P1") {
    val cart = Cart(
      List(
        Product("P2", 20),
        Product("P1", 10)
      )
    )

    val result = BundleConfigurator.select(cart)

    val expected = "B1"

    assertEquals(result, expected)
  }

  test("Bundle configurator should return B1,P3 for P1,P2,P3") {
    val cart = Cart(
      List(
        Product("P1", 10),
        Product("P2", 20),
        Product("P3", 30)
      )
    )

    val result = BundleConfigurator.select(cart)

    val expected = "B1,P3"

    assertEquals(result, expected)
  }

  test("Bundle configurator should return B1,P3 for P1,P3,P2") {
    val cart = Cart(
      List(
        Product("P1", 10),
        Product("P3", 30),
        Product("P2", 20)
      )
    )

    val result = BundleConfigurator.select(cart)

    val expected = "B1,P3"

    assertEquals(result, expected)
  }

  test("Bundle configurator should return P1,B3 for P1,P3,P4") {
    val cart = Cart(
      List(
        Product("P1", 10),
        Product("P3", 30),
        Product("P4", 40)
      )
    )

    val result = BundleConfigurator.select(cart)

    val expected = "P1,B3"

    assertEquals(result, expected)
  }

}
