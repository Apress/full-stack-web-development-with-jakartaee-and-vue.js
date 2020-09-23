import Vue from 'vue'
import { FoodProduct } from '../entities/FoodProduct'
import { Image } from '../entities/Image'

export class FoodProductService {
  static getById (id: number) {
    return Vue.axios.get<FoodProduct>(`/foodproducts/${id}`)
  }

  static create (foodProduct: FoodProduct) {
    let formData = new FormData()

    formData.append('file', foodProduct.image)

    return Vue.axios.post<Image>(`/files`, formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        foodProduct.imageUrl = response.data.imageUrl

        return Vue.axios.post<FoodProduct>(`/foodproducts`, foodProduct)
      })
  }

  static update (foodProduct: FoodProduct) {
    let formData = new FormData()

    formData.append('file', foodProduct.image)

    return Vue.axios.post<Image>(`/files`, formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        foodProduct.imageUrl = response.data.imageUrl

        return Vue.axios.put<FoodProduct>(`/foodproducts`, foodProduct)
      })
  }

  static deActivate (foodProduct: FoodProduct) {
    return Vue.axios.delete<FoodProduct>(`/foodproducts/${foodProduct.id}`)
  }

  static getByFoodService (foodService: string, page:number, pageSize:number) {
    return Vue.axios.get<Array<FoodProduct>>(`/foodproducts?foodService=${foodService}&page=${page}&pageSize=${pageSize}`)
  }
}
