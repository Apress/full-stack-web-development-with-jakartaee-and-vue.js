import { FoodProduct } from './FoodProduct'
import { FoodService } from './FoodService'
import { Cart } from './Cart';
import { Delivery } from './Delivery';

export class State {
    foodProducts: FoodProduct[] = [];
    foodServices: FoodService[] = [];
    cart: Cart = Cart.emptyCart()
    deliveries: Delivery [] = []
    currentFoodServiceLoggedIn: string = '';
    currentDeliveryEmail: string = '';
}