package bundle

import bundle.Model.Cart
import bundle.Model.Item.Product
import munit.FunSuite

class BundleConfiguratorSpec extends FunSuite {
  
  import BundleConfigurator._

  test("Bundle configurator should return a product for a product") {
    val cart = Cart(List(Product("P1", 10)))

    val result = select(cart)

    val expected = List(p1)

    assertEquals(result, expected)
  }

  test("Bundle configurator should return B1 for P1,P2") {
    val cart = Cart(
      List(
        Product("P1", 10),
        Product("P2", 20)
      )
    )

    val result = select(cart)

    val expected = List(b1)

    assertEquals(result, expected)
  }

  test("Bundle configurator should return B1 for P2,P1") {
    val cart = Cart(
      List(
        Product("P2", 20),
        Product("P1", 10)
      )
    )

    val result = select(cart)

    val expected = List(b1)

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

    val result = select(cart)

    val expected = List(b1, p3)

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

    val result = select(cart)

    val expected = List(b1, p3)

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

    val result = select(cart)

    val expected = List(p1, b3)

    assertEquals(result, expected)
  }

}
