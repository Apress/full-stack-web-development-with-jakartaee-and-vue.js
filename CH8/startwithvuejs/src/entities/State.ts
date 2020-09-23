import { FoodService } from './FoodService'
import { Cart } from './Cart';

export class State {
    cart: Cart = Cart.emptyCart()
    currentFoodServiceLoggedIn: FoodService = FoodService.emptyFoodService();
    currentDeliveryEmail: string = '';
}