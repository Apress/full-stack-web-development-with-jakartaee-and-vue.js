
package com.daniel.delivery.abstraction.service.foodservice;

import com.daniel.delivery.abstraction.entity.FoodService;
import java.util.List;

public interface FoodServiceService {
    FoodService save(FoodService foodService);
    FoodService update(FoodService foodService);
    FoodService deActivate(String email);
    List<FoodService> getByFoodType(String foodType);
}
