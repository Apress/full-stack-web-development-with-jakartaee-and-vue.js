
package com.daniel.delivery.abstraction.service.foodservice;

import com.daniel.delivery.abstraction.entity.FoodService;
import com.daniel.delivery.abstraction.entity.User;
import java.util.List;
import java.util.Optional;

public interface FoodServiceService {
    Optional<FoodService> login(User user);
    Optional<FoodService> getById(String email);
    FoodService save(FoodService foodService);
    FoodService update(FoodService foodService);
    FoodService deActivate(String email);
    List<FoodService> getByFoodType(String foodType, Integer page, Integer pageSize);
}
