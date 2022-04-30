package bundle

object Model {

  case class Cart(products: List[Item.Product])

  sealed abstract class Item {
    def name: String
    def price: Int
  }

  object Item {

    case class Product(name: String, price: Int) extends Item

    case class Bundle(name: String, products: List[Product], price: Int)
        extends Item

  }

}
