import Vue from 'vue'
import { Delivery } from '../entities/Delivery'

export class DeliveryService {
  static getDeliveriesByEmailAndState (email:string, state:string) {
    return Vue.axios.get<Array<Delivery>>(`/deliveries/${email}?state=${state}`)
  }

  static request (delivery: Delivery) {
    return Vue.axios.post<Delivery>(`/deliveries`, delivery)
  }
}
