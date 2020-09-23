
import { Cart } from './Cart'
export class Delivery {
    cart: Cart = Cart.emptyCart()
    address: string = ''
    email: string = ''
    phone: string = ''
    state: string = ''
    total: number = 0

    static emptyDelivery () {
      let delivery:Delivery = new Delivery()

      return delivery
    }

    static newDelivery (cart: Cart, address: string, email: string, phone: string, state: string, total: number) {
      let delivery:Delivery = new Delivery()

      delivery.cart = cart
      delivery.address = address
      delivery.email = email
      delivery.phone = phone
      delivery.state = state
      delivery.total = total

      return delivery
    }

    copyDelivery (deliveryToCopy: Delivery) {
      this.cart = deliveryToCopy.cart
      this.address = deliveryToCopy.address
      this.email = deliveryToCopy.email
      this.phone = deliveryToCopy.phone
      this.state = deliveryToCopy.state
      this.total = deliveryToCopy.total
    }
}
