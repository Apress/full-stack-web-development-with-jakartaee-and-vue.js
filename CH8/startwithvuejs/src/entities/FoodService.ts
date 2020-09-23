import { User } from './User'

export class FoodService {
    email: string = ''
    name: string = ''
    address: string = ''
    foodType: string = ''
    deliveryFee: number = 0.0
    image: any | null = null
    imageUrl: string = ''
    user: User = User.emptyUser()
    active: boolean = false

    static emptyFoodService () {
      let foodService:FoodService = new FoodService()

      return foodService
    }

    static newFoodService (email: string, name: string, address: string, foodType: string, deliveryFee: number, image: any, imageUrl: string, user: User, active: boolean) {
      let foodService:FoodService = new FoodService()

      foodService.email = email
      foodService.name = name
      foodService.address = address
      foodService.foodType = foodType
      foodService.deliveryFee = deliveryFee
      foodService.image = image
      foodService.imageUrl = imageUrl
      foodService.user = user
      foodService.active = active

      return foodService
    }

    copyFoodService (foodServiceToCopy: FoodService) {
      this.email = foodServiceToCopy.email
      this.name = foodServiceToCopy.name
      this.address = foodServiceToCopy.address
      this.foodType = foodServiceToCopy.foodType
      this.deliveryFee = foodServiceToCopy.deliveryFee
      this.image = foodServiceToCopy.image
      this.imageUrl = foodServiceToCopy.imageUrl
      this.user = foodServiceToCopy.user
      this.active = foodServiceToCopy.active
    }
}
