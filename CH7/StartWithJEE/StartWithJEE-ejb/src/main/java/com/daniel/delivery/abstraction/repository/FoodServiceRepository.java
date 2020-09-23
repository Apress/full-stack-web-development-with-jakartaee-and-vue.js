package com.daniel.delivery.abstraction.repository;

import com.daniel.delivery.abstraction.entity.FoodService;
import java.util.List;
import java.util.Optional;

public interface FoodServiceRepository extends Repository<FoodService>{
    List<FoodService> getByFoodType(String foodType);
    Optional<FoodService> getById(String email);
}
