
package com.daniel.delivery.abstraction.service.foodproduct;

import com.daniel.delivery.abstraction.entity.FoodProduct;
import java.util.List;
import java.util.Optional;

public interface FoodProductService {
    Optional<FoodProduct> getById(Integer id);
    FoodProduct save(FoodProduct foodProduct);
    FoodProduct update(FoodProduct foodProduct);
    FoodProduct deActivate(Integer id);
    List<FoodProduct> getByFoodService(String email, Integer page, Integer pageSize);    
}
