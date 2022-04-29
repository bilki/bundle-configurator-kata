package bundle

object Model {

  case class Product(name: String, price: Int)

  case class Bundle(products: List[Product], price: Int)

  case class Cart(products: List[Product])

}
