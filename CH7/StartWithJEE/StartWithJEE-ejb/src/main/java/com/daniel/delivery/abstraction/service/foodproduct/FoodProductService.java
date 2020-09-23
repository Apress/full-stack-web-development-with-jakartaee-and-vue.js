
package com.daniel.delivery.abstraction.service.foodproduct;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import java.util.List;

public interface FoodProductService {
    FoodProduct save(FoodProduct foodProduct);
    FoodProduct update(FoodProduct foodProduct);
    FoodProduct deActivate(Integer id);
    List<FoodProduct> getByFoodService(String email);
    
}
