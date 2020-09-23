package com.daniel.delivery.abstraction.repository;

import com.daniel.delivery.abstraction.entity.FoodService;
import java.util.List;
import java.util.Optional;

public interface FoodServiceRepository extends Repository<FoodService>{
    List<FoodService> getAll(Integer page, Integer pageSize);
    List<FoodService> getByFoodType(String foodType, Integer page, Integer pageSize);
    Optional<FoodService> getById(String email);
    Optional<FoodService> getByEmailAndPassword(String email, String password);
}
