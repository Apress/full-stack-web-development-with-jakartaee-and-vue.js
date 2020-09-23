import { FoodService } from './FoodService'
import { Cart } from './Cart';
import { Token } from './Token';

export class State {
    cart: Cart = Cart.emptyCart()
    currentDeliveryEmail: string = '';
    token: Token = Token.emptyToken()
}