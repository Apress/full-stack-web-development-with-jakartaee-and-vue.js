
import { Item } from './Item'
export class Cart {
    items: Item[] = []
    foodService: string = ''

    static emptyCart () {
      let cart:Cart = new Cart()

      return cart
    }

    static newCart (items: Item[], foodService: string) {
      let cart:Cart = new Cart()

      cart.items = items
      cart.foodService = foodService

      return cart
    }

    copyCart (cartToCopy: Cart) {
      this.items = cartToCopy.items
      this.foodService = cartToCopy.foodService
    }
}
