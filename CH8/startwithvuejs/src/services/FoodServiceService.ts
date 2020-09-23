import Vue from 'vue'
import { FoodService } from '../entities/FoodService'
import { User } from '../entities/User'
import { Image } from '../entities/Image'

export class FoodServiceService {
  static getById (email: string) {
    return Vue.axios.get<FoodService>(`/foodservices/${email}`)
  }

  static create (foodService: FoodService) {
    let formData = new FormData()

    formData.append('file', foodService.image)

    return Vue.axios.post<Image>(`/files`, formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        foodService.imageUrl = response.data.imageUrl

        return Vue.axios.post<FoodService>(`/foodservices`, foodService)
      })
  }

  static update (foodService: FoodService) {
    let formData = new FormData()

    formData.append('file', foodService.image)

    return Vue.axios.post<Image>(`/files`, formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        foodService.imageUrl = response.data.imageUrl

        return Vue.axios.put<FoodService>(`/foodservices`, foodService)
      })
  }

  static login (user: User) {
    return Vue.axios.post<FoodService>(`/foodservices/login`, user)
  }

  static getByFoodType (foodType:string, page:number, pageSize:number) {
    return Vue.axios.get<Array<FoodService>>(`/foodservices?foodType=${foodType}&page=${page}&pageSize=${pageSize}`)
  }
}
