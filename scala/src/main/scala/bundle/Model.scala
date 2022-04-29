package bundle

object Model {

  case class Product(name: String, price: Int)

  case class Bundle(name: String, products: List[Product], price: Int)

  case class Cart(products: List[Product])

}
