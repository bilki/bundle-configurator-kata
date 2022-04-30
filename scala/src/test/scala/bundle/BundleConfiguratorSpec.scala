package bundle

import bundle.Model.Cart
import munit.FunSuite

class BundleConfiguratorSpec extends FunSuite {

  import BundleConfigurator._

  Map(
    List(p1)         -> List(p1),
    List(p1, p2)     -> List(b1),
    List(p2, p1)     -> List(b1),
    List(p1, p2, p3) -> List(b1, p3),
    List(p1, p3, p2) -> List(b1, p3), // Same as previous with unordered input
    List(p1, p3, p4) -> List(p1, b3)
  ).foreach { case (input, expected) =>
    test(s"Bundle configurator should return ${expected
        .map(_.name).mkString(",")} for input ${input.map(_.name).mkString(",")}") {
      val cart = Cart(input)

      val result = select(cart)

      assertEquals(result, expected)
    }
  }

}
