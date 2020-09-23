
import { FoodProduct } from './FoodProduct'
export class Item {
    foodProduct: FoodProduct = FoodProduct.emptyFoodProduct()
    amount: number = 0

    static emptyItem () {
      let item:Item = new Item()

      return item
    }

    static newItem (foodProduct: FoodProduct, amount: number) {
      let item:Item = new Item()

      item.foodProduct = foodProduct
      item.amount = amount

      return item
    }

    copyItem (itemToCopy: Item) {
      this.foodProduct = itemToCopy.foodProduct
      this.amount = itemToCopy.amount
    }
}
