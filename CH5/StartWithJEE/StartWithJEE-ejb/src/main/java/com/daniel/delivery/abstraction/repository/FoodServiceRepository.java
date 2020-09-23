package com.daniel.delivery.abstraction.repository;

import com.daniel.delivery.abstraction.entity.FoodService;
import java.util.List;

public interface FoodServiceRepository extends Repository<FoodService>{
    List<FoodService> getByFoodType(String foodType);
}
