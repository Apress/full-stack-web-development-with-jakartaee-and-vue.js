
export class FoodProduct {
    id: number | null = null
    name: string = ''
    description: string = ''
    price: number = 0.0
    imageUrl: string = ''
    image: any | null = null
    foodService: string = ''
    active: boolean = false

    static emptyFoodProduct () {
      let foodProduct:FoodProduct = new FoodProduct()

      return foodProduct
    }

    static newFoodProduct (id: number, name: string, description: string, price: number, image: any, imageUrl: string, foodService: string, active: boolean) {
      let foodProduct:FoodProduct = new FoodProduct()

      foodProduct.id = id
      foodProduct.name = name
      foodProduct.description = description
      foodProduct.price = price
      foodProduct.image = image
      foodProduct.imageUrl = imageUrl
      foodProduct.foodService = foodService
      foodProduct.active = active

      return foodProduct
    }

    copyFoodProduct (foodProductToCopy: FoodProduct) {
      this.id = foodProductToCopy.id
      this.name = foodProductToCopy.name
      this.description = foodProductToCopy.description
      this.price = foodProductToCopy.price
      this.image = foodProductToCopy.image
      this.imageUrl = foodProductToCopy.imageUrl
      this.foodService = foodProductToCopy.foodService
      this.active = foodProductToCopy.active
    }
}
