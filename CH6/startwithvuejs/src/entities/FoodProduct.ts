
export class FoodProduct {
    id: number = -1
    name: string = ''
    description: string = ''
    price: number = 0.0
    image: any = new Image()
    foodService: string = ''

    static emptyFoodProduct () {
      let foodProduct:FoodProduct = new FoodProduct()

      return foodProduct
    }

    static newFoodProduct (id: number, name: string, description: string, price: number, image: any, foodService: string) {
      let foodProduct:FoodProduct = new FoodProduct()

      foodProduct.id = id
      foodProduct.name = name
      foodProduct.description = description
      foodProduct.price = price
      foodProduct.image = image
      foodProduct.foodService = foodService

      return foodProduct
    }

    copyFoodProduct (foodProductToCopy: FoodProduct) {
      this.id = foodProductToCopy.id
      this.name = foodProductToCopy.name
      this.description = foodProductToCopy.description
      this.price = foodProductToCopy.price
      this.image = foodProductToCopy.image
      this.foodService = foodProductToCopy.foodService
    }
}
